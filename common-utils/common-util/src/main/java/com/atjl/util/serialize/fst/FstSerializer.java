package com.atjl.util.serialize.fst;

import com.atjl.util.serialize.Serializer;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * simple packaging of FST
 */
public class FstSerializer implements Serializer {
    private static final Logger logger = LoggerFactory.getLogger(FstSerializer.class);
    private FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    /**
     * @param object
     * @return
     */
    @Override
    public byte[] serialize(Object object) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        FSTObjectOutput out = conf.getObjectOutput(stream);
        try {
            out.writeObject(object);//reuse get
            // DON'T out.close() when using factory method;
            out.flush();
        } catch (IOException e) {
            logger.error("serialize error", e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                logger.error("close stream error");
                e.printStackTrace();
            }
        }
        return stream.toByteArray();
    }


    public byte[] serialize1(Object object){
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
        T result = null;
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        try {
            FSTObjectInput in = conf.getObjectInput(stream);//reuse get
            result = (T) in.readObject();
            // DON'T: in.close(); here prevents reuse and will result in an
            // exception
        } catch (Exception e) {
            logger.error("deserialize error", e);
        }finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                logger.error("close stream error");
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     *
     * @param bytes
     * @return
     */
    public Object deserialize(byte[] bytes) {
        Object result = null;
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        try {
            FSTObjectInput in = conf.getObjectInput(stream);
            result =  in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(stream!=null){
                    stream.close();
                }
            }catch (IOException e){
                logger.error("close stream error");
                e.printStackTrace();
            }
        }
        return result;
    }
}
