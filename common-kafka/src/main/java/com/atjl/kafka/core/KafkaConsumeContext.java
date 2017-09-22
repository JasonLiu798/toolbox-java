package com.atjl.kafka.core;

import com.atjl.kafka.api.event.BatchEventMC;
import com.atjl.kafka.domain.OffsetRange;
import com.atjl.kafka.domain.OffsetRangeComparator;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.kafka.api.event.BatchEvent;
import com.atjl.kafka.api.event.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KafkaConsumeContext
 * 存储未commit数据信息
 *
 * @since 1.0
 */
public class KafkaConsumeContext {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumeContext.class);


    /**
     * ！！！注意 ALERT ！！！！
     * 手动提交 核心数据结构，内存常驻，谨防内存泄露；size过大，会导致频繁FullGC
     * size 超过 KafkaInnerConstant.OFFSET_PROCESS_STATUS_MAP_MAXSIZE，触发 强制commit
     * <p>
     * key: partition id
     * value:
     * 使用map，未使用有序队列，主要考虑 保证添加删除O(1)，时间消耗较高的排序，放在定时commit线程内
     * TODO: value确定是否可以换为hashmap，并发主要在process thread 更新boolean时
     * TODO: offsetProcessCache大小控制策略：
     * 开源（增加出口速度）：增加commit处理速度；增加client进程分担数据；增加 process 线程处理速度，如有CPU 内存资源，增加process 线程数量；略过非必要处理环节；
     * 节流（减少进口速度）：减缓fetch thread读取速度；减小dataqueue大小；
     */
    private ConcurrentHashMap<Integer, ConcurrentHashMap<OffsetRange, Boolean>> offsetProcessCache = new ConcurrentHashMap<>();

    /**
     * 添加从kafka接收到的新鲜数据
     *
     * @param batchRawData batch raw data
     */
    public Map<Integer, OffsetRange> addUnProcessData(BatchEventMC batchRawData) {
        //synchronized (KafkaConsumeContext.class)
        //PS:一个分区只能被一个线程取到
        Map<Integer, OffsetRange> map = batchEvent2OffsetRange(batchRawData);
        if (logger.isDebugEnabled()) {
            logger.debug("batch raw data 2 map res:{}", map);
        }
        batchSetDataStatus(map, false);
        batchRawData.setOffsetMap(map);
        return map;
    }

    /**
     * 更新已处理的 kafka数据
     *
     * @param batchRawData
     */
    public void addProcessedData(BatchEventMC batchRawData) {
        Map<Integer, OffsetRange> map = batchRawData.getOffsetMap();//batchEvent2OffsetRange(batchRawData);
        if (logger.isDebugEnabled()) {
            logger.debug("batch processed data 2 map res:{}", map);
        }
        batchSetDataStatus(map, true);
    }


    /**
     * 批量设置 context中 map状态
     *
     * @param map
     * @param processed
     */
    private void batchSetDataStatus(Map<Integer, OffsetRange> map, boolean processed) {
        for (Map.Entry<Integer, OffsetRange> entry : map.entrySet()) {
            Integer partition = entry.getKey();
            OffsetRange orange = entry.getValue();

            ConcurrentHashMap<OffsetRange, Boolean> offsetCache = offsetProcessCache.get(partition);
            if (offsetCache == null) {
                offsetCache = new ConcurrentHashMap<>();
                offsetProcessCache.putIfAbsent(partition, offsetCache);
            }

            if (processed == true) {//设为已处理
                Boolean proceedStatus = offsetCache.get(orange);
                if (proceedStatus == null) {//查看是否存在
                    //处理速度 低于 N=(commitThread interval * max commit check) ms ，极少情况下才会出现，offset 已经被强制commit， 记录日志，可以不补录
                    //未处理的，不存在，force commit后，被移除，记录日志，不需要补录，
                    logger.info("already force committed partition:{},resume offset:{},end offset:{}", partition, orange.getStartOffset(), orange.getEndOffset());
                    return;
                } else {
                    //存在则设置为已处理
                    offsetCache.put(orange, processed);
                }
            } else {//设为未处理，无需校验存在性

                offsetCache.put(orange, processed);
            }
        }
    }


    public String getContextStatus() {
        return getContextStatus("default");
    }

    /**
     * 调试监控用
     * ！！！！线上环境禁止调用！！！！
     *
     * @param mark
     * @return
     */
    public String getContextStatus(String mark) {
        StringBuffer sb = new StringBuffer();
        sb.append("\nCONTEXT[");
        sb.append(mark);
        sb.append("]---------resume--------------\n");
        for (Integer partitionId : offsetProcessCache.keySet()) {
            sb.append("partition:").append(partitionId).append("\n");
            Map<OffsetRange, Boolean> queue = offsetProcessCache.get(partitionId);
            Set<OffsetRange> keySet = queue.keySet();
            TreeSet<OffsetRange> keySetSorted = new TreeSet<>(new OffsetRangeComparator());
            keySetSorted.addAll(keySet);
            Object[] arr = keySetSorted.toArray();
            int i = 0;
            for (Object obj : arr) {
                OffsetRange offsetRange = (OffsetRange) obj;
                sb.append("[").append(i).append("](")
                        .append(offsetRange.getStartOffset()).append("->")
                        .append(offsetRange.getEndOffset())
                        .append(",cnt:").append(offsetRange.getCount())
                        .append(",status:").append(queue.get(obj)).append("),\n");
                i++;
            }
//            sb.append("\n");
        }
        sb.append("CONTEXT[");
        sb.append(mark);
        sb.append("]---------end---------------\n");
        return sb.toString();
    }


    public void showContextStatus() {
        showContextStatus("default");
    }

    /**
     * for debug,show context map data,status check
     */
    public void showContextStatus(String mark) {
        String str = getContextStatus(mark);
        logger.debug("CONTEXT CONTENT: " + str);
    }


    /**
     * @param batchRawData
     * @return
     */
    private Map<Integer, OffsetRange> batchEvent2OffsetRange(BatchEvent batchRawData) {
        Map<Integer, List<Event>> map = batchRawData.getEventMap();
        Map<Integer, OffsetRange> res = new HashMap<>();

        for (Map.Entry<Integer, List<Event>> entry : map.entrySet()) {
            Integer partition = entry.getKey();
            OffsetRange orange = event2range(entry.getValue());
            if (orange != null) {
                res.put(partition, orange);
            }
        }
        return res;
    }

    /**
     * transfer List<RawData> to List<OffsetRange>
     *
     * @param list
     * @return
     */
    private OffsetRange event2range(List<Event> list) {
        //List<OffsetRange> res = new LinkedList<>();
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        long offset = list.get(0).getOffset();
        if (list.size() == 1) {
            return new OffsetRange(offset);
        }
        //find min,max
        long minOffset = offset;
        long maxOffset = offset;

        for (int i = 0; i < list.size(); i++) {
            Event e = list.get(i);
            long tmpOffset = e.getOffset();
            if (tmpOffset < minOffset) {
                minOffset = tmpOffset;
            }
            if (tmpOffset > maxOffset) {
                maxOffset = tmpOffset;
            }
        }
        OffsetRange result = new OffsetRange(minOffset, maxOffset);
        return result;
        /*
        for(int i=0;i<list.size();i++){
            Event e = list.get(i);
            long offset = e.getCommitedOffset();

            if(res.size()==0) {
                //初始化 第一个
                res.add( new OffsetRange(offset));
            }else{

                OffsetRange existOffsetRange = res.get(res.size() - 1);//获取最后一个
                if (offset == existOffsetRange.getEndOffset() + 1) {//判断是否连续
                    existOffsetRange.setEndOffset(offset);//连续则不生成，直接增加结束 offset
                } else {//超过1，不连续，(出现在多次rebalance后)
                    OffsetRange newOffsetRange = new OffsetRange(offset);
                    res.add(newOffsetRange);
                }
            }
        }*/
    }

    /**
     * 获取 cache
     *
     * @return
     */
    public ConcurrentHashMap<Integer, ConcurrentHashMap<OffsetRange, Boolean>> getOffsetProcessCache() {
        return offsetProcessCache;
    }


    /**
     * 删除已处理的offset range
     *
     * @param partition
     * @param offsetRange2remove
     */
    public void removeOffsetRange(int partition, OffsetRange offsetRange2remove) {
        ConcurrentHashMap<OffsetRange, Boolean> offsetMap = offsetProcessCache.get(partition);
        offsetMap.remove(offsetRange2remove);
    }

    /**
     * 删除已处理的offset range
     *
     * @param partition
     * @param offsetRange2removes
     */
    public void removeOffsetRange(int partition, List<OffsetRange> offsetRange2removes) {
        ConcurrentHashMap<OffsetRange, Boolean> offsetMap = offsetProcessCache.get(partition);
        for (OffsetRange offsetRange2remove : offsetRange2removes) {
            offsetMap.remove(offsetRange2remove);
        }
    }


    /**
     * 旧版
     *
     private static Map<Integer,List<OffsetRange>> batchEvent2OffsetRange(BatchEvent batchRawData){
     HashMap<Integer,List<OffsetRange>> map = new HashMap<>();
     //separate
     for(RawData rawData :batchRawData.getEvents()){
     int partitionId = rawData.getPartition();
     long offset = rawData.getCommitedOffset();
     List<OffsetRange> list = map.get(partitionId);
     if(list == null){
     list = new LinkedList<>();
     list.add(new OffsetRange(offset));
     map.put(partitionId,list);
     }else{
     OffsetRange existOffsetRange = list.get(list.size()-1);
     if( offset == existOffsetRange.getEndOffset()+1){
     existOffsetRange.setEndOffset(offset);
     }else{//超过1
     OffsetRange newOffsetRange = new OffsetRange(offset);
     list.add(newOffsetRange);
     }
     }
     }
     return map;
     }*/


}
