package com.atjl.kafka.core.thread;

import com.atjl.kafka.api.domain.MQCode;
import com.atjl.kafka.domain.OffsetRange;
import com.atjl.kafka.domain.OffsetRangeComparator;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CheckUtil;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.core.KafkaStaticContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CommitThread
 * @since 1.0
 */
@SuppressWarnings("unused")
public class TimingCommitThread extends TimingBaseThread implements Runnable {


    private static Logger LOG = LoggerFactory.getLogger(TimingCommitThread.class);

    private static Logger LOGBAK = LoggerFactory.getLogger("backup");
    //private IQueue commitQueue;
    private ConsumerConnector consumer;
    //private PartitionOffset partitionOffset;
    private String topic;
    private KafkaConsumeContext context;

    private volatile boolean commiting = false;

    public TimingCommitThread(int id, int interval, String topic, ConsumerConnector consumer, KafkaConsumeContext context) {
        super(id, interval);
        CheckUtil.checkExistNull(consumer);
        this.consumer = consumer;
        this.topic = topic;
        this.context = context;
    }

    @Override
    public void run() {
        if (LOG.isDebugEnabled()) {
            LOG.info("commit thread resume");
        }
        if (this.commiting) {
            this.commiting = false;
            return;
        }
        this.commiting = true;

        /**
         * key:partitionId
         * value:
         *      key:offset range
         *      value:process status
         */
        ConcurrentHashMap<Integer, ConcurrentHashMap<OffsetRange, Boolean>> offsetProcessMap = context.getOffsetProcessCache();

        if (LOG.isDebugEnabled()) {//LOG
            LOG.debug(context.getContextStatus("BF COMMIT"));
        }

        for (Integer partition : offsetProcessMap.keySet()) {
            Map<OffsetRange, Boolean> offsetStatusMap = offsetProcessMap.get(partition);

            MQCode ret = commitPartition(partition, offsetStatusMap);
            if (ret == MQCode.RE_PROCESS) {
                /**
                 * context map 满时，继续commit
                 */
                commitPartition(partition, offsetStatusMap);
            }
        }

        if (LOG.isDebugEnabled()) {//log
            LOG.debug(context.getContextStatus("AF COMMIT"));
        }

        this.commiting = false;
    }

    /**
     * commit one partition's offset
     *
     * @param partitionId
     * @param offsetRangeBooleanMap key:offrange,value:isProcessed
     */
    public MQCode commitPartition(int partitionId, Map<OffsetRange, Boolean> offsetRangeBooleanMap) {
        //synchronized (offsetRangeBooleanMap){
        //检查 是否存在 offsetRange
        Set<OffsetRange> offsetRangeSet = offsetRangeBooleanMap.keySet();
        if (CollectionUtil.isEmpty(offsetRangeSet)) {
            return MQCode.NO_ERROR;
        }

        /**
         * 排序 OffsetRange
         */
        TreeSet<OffsetRange> offsetRangeSetSorted = new TreeSet<>(new OffsetRangeComparator());
        offsetRangeSetSorted.addAll(offsetRangeSet);
        Object[] arr = offsetRangeSetSorted.toArray();
        if (LOG.isDebugEnabled()) {
            LOG.debug("after sort {}", Arrays.toString(arr));
        }

        //遍历有序offsetRange数组，找到头尾连续且值为true的一段offsetRange，放入processedList，最后一个不连续的offsetRange count+1
        List<OffsetRange> processedList = findContinuousOffsetRangeList(offsetRangeBooleanMap, arr, 0);

        /**
         * 遍历完毕，无可以commit节点，检查第一个节点，查看是否已经超过检查次数，超过则备份后强制commit
         */
        if (processedList.size() == 0 && arr.length > 0) {

            OffsetRange firstElement = (OffsetRange) arr[0];
            if (firstElement.isReachMaxCount()) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("offset range reach max check count,force remove", firstElement);
                }
                forceCommitUnProcessed(topic, partitionId, firstElement);
                return MQCode.NO_ERROR;
            }
            //TODO: 如后续节点 连续且被处理，则继续commit or 立即再次调用此函数 （附件条件，map已经达到最大值）
        }

        /**
         * context map 的 offset status map 大小达到临界值，强制提交，offset range最小
         * TODO：流控，减缓fetch data 速度；检查 process thread是否有 stuck 线程，强制重启线程
         */
        if (LOG.isDebugEnabled()) {
            LOG.debug("processlist size {},offsetRangesize: {}", processedList.size(), offsetRangeSet.size());
        }
        if (processedList.size() == 0 && offsetRangeSet.size() >= KafkaInnerConstant.OFFSET_PROCESS_STATUS_MAP_MAXSIZE) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("context map reach max count ");
            }
            forceCommitUnProcessed(topic, partitionId, (OffsetRange) arr[0]);
            // TODO:如后续节点 连续且被处理，则继续commit
            return MQCode.RE_PROCESS;
            // processedList = findContinuousOffsetRangeList(offsetRangeBooleanMap, arr,1);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("processed item :{}", String.valueOf(processedList));
        }
        if (processedList.size() > 0) {
            commitProcessedOffset(partitionId, processedList);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("after remove ");
            }
        }
        return MQCode.NO_ERROR;
    }


    /**
     * commitPartition processed offsetRanges
     *
     * @param processedList
     */
    private void commitProcessedOffset(int partitionId, List<OffsetRange> processedList) {
        long commitOffset = processedList.get(processedList.size() - 1).getEndOffset();
        commit(consumer,topic,partitionId,commitOffset);
        if (LOG.isInfoEnabled()) {
            LOG.info("committed offset {}", commitOffset);
        }
        context.removeOffsetRange(partitionId, processedList);
    }


    private void commit(ConsumerConnector consumer,String topic,int partition,long offset){
        // 0.9之后 public abstract void commitOffsets(Map<TopicAndPartition, OffsetAndMetadata> paramMap, boolean paramBoolean);
        // 自定义 consumer.commitOffsets(topic, partitionId, commitOffset);
    }

    /**
     * 查找一段连续 offset range
     *
     * @param offsetRangeBooleanMap for get value
     * @param arr                   key
     * @return
     */
    private List<OffsetRange> findContinuousOffsetRangeList(Map<OffsetRange, Boolean> offsetRangeBooleanMap, Object[] arr, int startIndex) {
        List<OffsetRange> processedList = new LinkedList<>();

        OffsetRange curOffset = (OffsetRange) arr[0];
        OffsetRange preOffset = null;

        OffsetRange notProcessedOffset = null;
        for (int i = 0; i < arr.length; i++) {
            if (LOG.isDebugEnabled()) {
                //LOG.debug("pre " + preOffset + ",cur " + curOffset);
            }
            curOffset = (OffsetRange) arr[i];

            //检查是否处理
            boolean processed = offsetRangeBooleanMap.get(curOffset);
            if (processed) {
                if (preOffset == null) {
                    //init pre
                    preOffset = curOffset;
                    processedList.add(curOffset); // add first
                } else {
                    //检查是否连续
                    if (preOffset.getEndOffset() + 1 == curOffset.getStartOffset()) {
                        processedList.add(curOffset);
                        preOffset = curOffset;
                        continue;
                    }
                    //commitProcessedOffset = preOffset.getEndOffset();
                }
            } else {
                notProcessedOffset = curOffset;
                notProcessedOffset.incrCount();
                /*
                commitProcessedOffset = preOffset.getEndOffset();
                if(notProcessedOffset.isReachMaxCount()){
                    forceCommitUnProcessed(partitionId,topic,notProcessedOffset);
                }*/
                break;
            }
        }
        return processedList;
    }

    /**
     * 强制 commitPartition 未处理的commitRange
     *
     * @param topic
     * @param partition
     * @param offsetRange
     */
    private void forceCommitUnProcessed(String topic, int partition, OffsetRange offsetRange) {
        if (LOG.isInfoEnabled()) {
            LOG.info("force committed offsetrange {}", offsetRange);
        }
        KafkaStaticContext.incrForceCommitTotalCount();

        // save processing data ,force commit data to,interval reconsume data
        LOGBAK.info("{},{},{},{}", topic, partition, offsetRange.getStartOffset(), offsetRange.getEndOffset());
        /**
         * remove from context
         * !!!!重要!!!!：必须先备份数据至 本地 or redis or mysql
         */
        context.removeOffsetRange(partition, offsetRange);

        //！！！！！！！！alert 调试用！！！！！！！！！！！！！
        if (LOG.isDebugEnabled()) {
            LOG.debug("force commit offset t:{},p:{},o:{}", topic, partition, offsetRange.getEndOffset());
        } else {
            // commit(topic,partitionId,commitOffset);
            commit(consumer,topic,partition, offsetRange.getEndOffset());
        }

    }


    private void saveUnprocessedData() {

    }

    private void removeCommited() {

    }

}
