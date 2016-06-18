package com.jason798.serialize;

import com.jason798.serialize.fst.FstSerializer;


/**
 * package of FstSerializer
 */
public class SerializerHelper {
    public static byte[] serialize(Object object){
        Serializer serializer = new FstSerializer();
        return serializer.serialize(object);
    }

    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Serializer serializer = new FstSerializer();
        return serializer.deserialize(bytes, clazz);
    }

    public static Object deserialize(byte[] bytes) {
        Serializer serializer = new FstSerializer();
        return serializer.deserialize(bytes);
    }
}
