package com.atjl.kafka.api.event;

import java.io.Serializable;


public final class Event implements Serializable {

	private static final long serialVersionUID = -5889484951591932338L;

	private String topic;
	private String key;
	private String value;

	/**
	 * for consumer to manual process data consistent
	 */
	private int partition;
	/**
	 * for consumer to manual process data consistent
	 */
	private long offset;



	public Event(){
	}

	/**
	 * for producer
	 * @param topic
	 * @param key
	 * @param value
	 */
	public Event(String topic,String key,String value){
		this.topic = topic;
		this.key = key;
		this.value = value;
	}

	/**
	 * for consumer
	 * @param topic
	 * @param partition
	 * @param offset
	 * @param key
	 * @param value
	 */
	public Event(String topic, int partition, long offset, String key, String value) {
		this.topic = topic;
		this.partition = partition;
		this.offset = offset;
		this.key = key;
		this.value = value;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Event{" +
				"topic='" + topic + '\'' +
				", partition=" + partition +
				", offset=" + offset +
				", key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
