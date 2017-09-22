package com.atjl.kafka.api.event;

import java.io.Serializable;
import java.util.*;

import com.atjl.kafka.domain.constant.KafkaInnerConstant;
import com.atjl.util.collection.CollectionUtil;


public class BatchEvent implements Serializable{
	private static final long serialVersionUID = -5425240964351849458L;

    protected int totalCount;

    /**
     * key:partition
     * value:RawData
     */
    protected Map<Integer,List<Event>> eventMap = new HashMap<>();


    public BatchEvent(){
    }

    public Map<Integer, List<Event>> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<Integer, List<Event>> eventMap) {
        this.eventMap = eventMap;
    }

    public void addEvent2Partition(int partition, Event e){
        //int partitionId = rawData.getPartition();
        //List<RawData> list = dataListMap.get(partitionId);
        List<Event> list= eventMap.get(partition);
        if(list == null) {
            list = new ArrayList<>(KafkaInnerConstant.BATCH_PROCESS_COUNT);
            eventMap.put(partition,list);
        }
        list.add(e);
        totalCount++;
    }

    /**
     * 获取总数
     * @return all data count
     */
    public int getTotalCount(){
        return totalCount;
    }

    /**
     * 获取包含的partition
     * @return partition set
     */
    public Set<Integer> getPartitionSet(){
        if(CollectionUtil.isEmpty(eventMap)){
            return  null;
        }
        return eventMap.keySet();
    }

    /**
     * 获取指定partition的 list
     * @param partition partition
     * @return list of partition 's RawData list
     */
    public List<Event> getEvents(int partition){
        if(CollectionUtil.isEmpty(eventMap)){
            return null;
        }
        return eventMap.get(partition);
    }

    /**
     * for data processor
     * @return list of raw data
     */
    public List<Event> getAllEvents() {
        if(CollectionUtil.isEmpty(eventMap)){
            return null;
        }
        boolean first = true;
        List<Event> res = null;
        for(Map.Entry<Integer,List<Event>> entry:eventMap.entrySet()){
            List<Event> list = entry.getValue();
            if(first){
                res = list;
                first = false;
            } else {
                res.addAll(list);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "BatchEvent{" +
                "totalCount=" + totalCount +
                ", eventMap=" + eventMap +
                '}';
    }
}
