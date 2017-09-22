package com.atjl.kafka.service.impl;

import com.atjl.kafka.domain.constant.KafkaInnerConstant;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;


/**
 * QueueManager
 */
public class BusManagerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: init()
     */
    @SuppressWarnings("unused")
	@Test
    public void testInit() throws Exception {
//        QueueManager bm =
        IQueue dataQueue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        for(int i=0;i<10;i++){
            String data = String.valueOf(i);
            dataQueue.sendMessage(data);
            System.out.println("send "+data);
        }
        for(int i=0;i<15;i++){
            String recv = (String) dataQueue.receiveMessage();
            System.out.println("recv "+recv);
        }
        IQueue commitQueue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);

    }

    /**
     * Method: initQueueCaches(String[] queueConfigs)
     */
    @Test
    public void testInitConfig() throws Exception {
        String config = "dataQ:1000";
        String[] queueArr = config.split(",");
        System.out.println(Arrays.toString(queueArr));

    }

    /**
     * Method: destroy()
     */
    @Test
    public void testDestroy() throws Exception {

    }

    /**
     * Method: getQueue(String connectName)
     */
    @Test
    public void testGetQueue() throws Exception {

    }


}
