package com.atjl.kafka.domain;


public class ZkConstant {
    public static final String CONSUMER_NAMESPACE = "consumers";

    public static final String CONSUMER_NAMESPACE_PATH = "/consumers";
    /**
     * {groupid}/owners/{topic}/{partitionid}
     */
    public static final String CONSUMER_PARTITION_OWNER_PATH = "/consumers/%s/owners/%s/%s";

    /**
     * {groupid}/offsets/{topic}/{partitionid}
     */
    public static final String CONSUMER_TOPIC_OFFSET_PARENT_PATH = "/consumers/%s/offsets/%s";
    public static final String CONSUMER_TOPIC_OFFSET_CHILD_PATH = "/%s/offsets/%s/%s";

}
