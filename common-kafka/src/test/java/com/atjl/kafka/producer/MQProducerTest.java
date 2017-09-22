package com.atjl.kafka.producer;

import com.atjl.util.common.SystemUtil;
import com.atjl.kafka.api.event.Event;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


/**
 * MQProducer

 */
public class MQProducerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testProduce() throws Exception {
        long events = 100000;
        MQProducer producer = new MQProducer();
        for (long nEvents = 0; nEvents < events; nEvents++) {
//            long runtime = new Date().getTime();
            String ip = String.valueOf(nEvents);
//            String msg = runtime + ",www.example.com," + ip;
//            KeyedMessage<String, String> data = new KeyedMessage<>("page_visits", ip, msg);
            Event e = new Event();
            e.setValue(ip);
            e.setTopic("test1");
            Thread.sleep(2000);
            producer.send(e);
        }

        while (true) {
            SystemUtil.sleep(1000);
        }
    }


}
