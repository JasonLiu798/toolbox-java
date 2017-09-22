package com.atjl.kafka.domain;

import java.io.Serializable;

public class PartitionMeta implements Serializable{
	private static final long serialVersionUID = 5480575327143696692L;
	private int pid;
    private String topic;
    private String ownerid;
    private long consumedOffset;
    private long producedOffset;
    private long remain;

    public PartitionMeta() {

    }


    @Override
    public String toString() {
        return "PartitionMeta{" +
                "pid=" + pid +
                ", topic='" + topic + '\'' +
                ", ownerid='" + ownerid + '\'' +
                ", consumedOffset=" + consumedOffset +
                ", producedOffset=" + producedOffset +
                ", remain=" + remain +
                '}';
    }

    public long getRemain() {
        return remain;
    }

    public void setRemain(long remain) {
        this.remain = remain;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public long getConsumedOffset() {
        return consumedOffset;
    }

    public void setConsumedOffset(long consumedOffset) {
        this.consumedOffset = consumedOffset;
    }

    public long getProducedOffset() {
        return producedOffset;
    }

    public void setProducedOffset(long producedOffset) {
        this.producedOffset = producedOffset;
    }
}
