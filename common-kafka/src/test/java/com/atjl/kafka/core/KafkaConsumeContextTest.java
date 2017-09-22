package com.atjl.kafka.core;

import com.atjl.kafka.api.event.BatchEventMC;
import com.atjl.kafka.api.event.Event;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


/**
 * KafkaConsumeContext
 *
 * @since 1.0
 */
public class KafkaConsumeContextTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
    KafkaConsumeContext context = new KafkaConsumeContext();

    /**
     * Method: addUnProcessData(BatchEvent batchRawData)
     */
    @Test
    public void testAddUnProcessData() throws Exception {
        BatchEventMC batchRawData = new BatchEventMC();

        long[] offsetArr = {3,6,2,4,5,6,5};
        for(int i=0;i<offsetArr.length;i++) {
            Event rd = new Event();
            //rd.setPartition(i%3);
            rd.setOffset(offsetArr[i]);
            rd.setValue(String.valueOf(i));
            batchRawData.addEvent2Partition(i%3,rd);
        }
        System.out.println(batchRawData);
        context.addUnProcessData(batchRawData);
        context.showContextStatus();
    }


    /**
     * Method: batchRawData2Map(BatchEvent batchRawData)
     */
    @Test
    public void testBatchRawDataSeq() throws Exception {
        BatchEventMC batchRawData = new BatchEventMC();

        long[] offsetArr = {3,1,2,4,4,6,5};
        for(int i=0; i < offsetArr.length;i++) {
            //	public Event(String topic, int partition, long offset, String key, String value)
            Event rd = new Event("test1",1, offsetArr[i],String.valueOf(i),String.valueOf(i));
            batchRawData.addEvent2Partition(i%3,rd);
        }
        System.out.println(batchRawData);

        context.addUnProcessData(batchRawData);
        context.showContextStatus();
    }

}
