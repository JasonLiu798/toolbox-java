package com.atjl.util.serialize.fst;

import com.atjl.util.serialize.Serializer;
import org.nustaq.serialization.FSTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * simple packaging of FST
 */
public class FstSerializer1 implements Serializer {
    private static final Logger logger = LoggerFactory.getLogger(FstSerializer1.class);
    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    /**
     * @param object
     * @return
     */
    @Override
    public byte[] serialize(Object object) {
        return conf.asByteArray(object);
    }

    /**
     * @param bytes
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        T res = (T) conf.asObject(bytes);
        return res;
    }


    /**
     * @param bytes
     * @return
     */
    public Object deserialize(byte[] bytes) {
        return conf.asObject(bytes);
    }
}
