package com.atjl.util.serialize;

public interface Serializer {
	public byte[] serialize(Object object) ;
	public <T> T deserialize(byte[] bytes, Class<T> clazz) ;
    public Object deserialize(byte[] bytes);
}