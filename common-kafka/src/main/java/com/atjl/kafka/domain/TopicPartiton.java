package com.atjl.kafka.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class TopicPartiton implements Serializable {
    private static final long serialVersionUID = -1436759478674873654L;

    public static final String TOPIC_GROUPID_SEP = "-";

    String topic;
    String groupid;
    Map<Integer, PartitionMeta> map;


    public TopicPartiton() {
    }

    public TopicPartiton(String topic, String groupid) {
        this.topic = topic;
        this.groupid = groupid;
    }

    /**
     * map key
     *
     * @return
     */
    public String getTopicGroupid() {
        return this.topic + TOPIC_GROUPID_SEP + this.groupid;
    }

    public static String fromatKey(String topic, String groupid) {
        return topic + TOPIC_GROUPID_SEP + groupid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addPartition(PartitionMeta pm) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(pm.getPid(), pm);
    }


    public Map<Integer, PartitionMeta> getMap() {
        return map;
    }

    public void setMap(Map<Integer, PartitionMeta> map) {
        this.map = map;
    }

    public PartitionMeta getPartition(Integer pid) {
        if (map == null) {
            return null;
        }
        return map.get(pid);
    }

    @Override
    public String toString() {
        return "TopicPartiton{" +
                "topic='" + topic + '\'' +
                ", map=" + map +
                '}';
    }
}
