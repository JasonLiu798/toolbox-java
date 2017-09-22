package com.atjl.kafka.core;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.0
 */
public class ModPartition implements Partitioner {
    private static Logger log = LoggerFactory.getLogger(ModPartition.class);

    public ModPartition(VerifiableProperties props) {
    }

    public int partition(Object key, int a_numPartitions) {
        int partition = 0;
        String stringKey = (String) key;
        int offset = stringKey.lastIndexOf('.');
        if (offset > 0) {
            partition = Integer.parseInt( stringKey.substring(offset+1)) % a_numPartitions;
        }
        if(log.isDebugEnabled()){
            log.debug("key {},partition {}",key,partition);
        }
        return partition;
    }
}
