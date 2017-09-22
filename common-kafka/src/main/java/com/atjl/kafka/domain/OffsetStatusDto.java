package com.atjl.kafka.domain;


import java.io.Serializable;

public class OffsetStatusDto implements Serializable{

	private static final long serialVersionUID = -1985399304293629765L;
	private String topic;
    private String groupid;
    private int partition;
    private long producedoffset;
    private long consumedOffset;
    private long remain;

    public OffsetStatusDto() {
    }


    @Override
    public String toString() {
        return "OffsetStatusDto{" +
                "topic='" + topic + '\'' +
                ", groupid='" + groupid + '\'' +
                ", partition=" + partition +
                ", producedoffset=" + producedoffset +
                ", consumedOffset=" + consumedOffset +
                ", remain=" + remain +
                '}';
    }

    public long getRemain() {
        return remain;
    }

    public void setRemain(long remain) {
        this.remain = remain;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public long getProducedoffset() {
        return producedoffset;
    }

    public void setProducedoffset(long producedoffset) {
        this.producedoffset = producedoffset;
    }

    public long getConsumedOffset() {
        return consumedOffset;
    }

    public void setConsumedOffset(long consumedOffset) {
        this.consumedOffset = consumedOffset;
    }
}
