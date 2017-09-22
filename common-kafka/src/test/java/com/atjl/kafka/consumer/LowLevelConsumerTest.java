package com.atjl.kafka.consumer;

import com.atjl.kafka.consumerlowlevel.LowLevelConsumer;
import com.atjl.kafka.core.KafkaLowLevelUtil;
import com.atjl.kafka.domain.ResultDto;
import com.atjl.kafka.domain.TopicPartiton;
import com.atjl.util.collection.CollectionUtil;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import com.atjl.kafka.api.event.Event;

import java.util.LinkedList;
import java.util.List;


/**
 * LowLevelConsumer
 * @since 1.0
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(KafkaLowLevelUtil.class)
public class LowLevelConsumerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void getZk(){
        //10.202.34.28:2182,10.202.34.29:2182,10.202.34.30:2182/kafka/other
//        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");
//        c.init();
//        String groupid = "A1C";
//        String topic = "A1";
        String zk = null;//c.getZkUrl(groupid,topic);
        System.out.println("zk "+zk);
    }

    @Test
    public void testOffset() throws Exception {
//        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");
//        c.init();
//        String groupid = "A1C";
//        String topic = "A1";
//        long po = c.getProducedOffset(topic, "A1C", 0);
//        long co = c.getCommitedOffset(topic, groupid, 0);
//        System.out.println("po "+po +",co "+co);
    }



    /**
     * Method: getOrInitFetchSizeBytes(String topic)
     */
    @Test
    public void testGetOrInitFetchSizeBytes() throws Exception {

    }

    /**
     * Method: fetchOneProducedEvent(String groupid, String topic, int partition, long offset)
     */
    @Test
    public void testFetchOneEvent() throws Exception {
        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");
        c.init();
        String groupid = "A1C";
        String topic = "A1";
        int partition = 0;
        //long last = c.getCommitedOffset(groupid,topic,partition);
        long cur = c.getProducedOffset(topic, "A1C", 0);
        long read = cur-100;
        ResultDto<Event> resultDto = c.fetchOneProducedEvent(groupid,topic,partition,read);
        System.out.println(String.format("offset %s res %s",read,resultDto));
    }

    /**
     * Method: fetchEventRange(String groupid, String topic, int parition, long startOffset, long endoffset)
     */
    @Test
    public void testFetchEventRange() throws Exception {
        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");
        c.init();
        String groupid = "A1C";
        String topic = "A1";
        int partition = 0;
        long read = 0;
        ResultDto<List<Event>> resultDto = c.fetchEventRange(groupid,topic,partition,read,read+1000);
        String res = CollectionUtil.toStringConsole(resultDto.getData());
        System.out.println(String.format("offset %s res %s",read,res));

    }



    /**
     * Method: close()
     */
    @Test
    public void testClose() throws Exception {
//        PowerMock.mockStatic(KafkaLowLevelUtil.class);
        String topic = "A1";
        int partition = 0;
        List<Event> l = new LinkedList<>();
        l.add(new Event());
//        expect( KafkaLowLevelUtil.fetchEvent(null,topic,partition,0,0)).andReturn(l);

//        PowerMock.replay(KafkaLowLevelUtil.class);

        List<Event> l2  = KafkaLowLevelUtil.fetchEvent(null,topic,partition,0,0);
        System.out.println(l2);
        System.out.println(l);
//        List<Event> l = new LinkedList<>();
//        l.add(new Event());
//        l.add(new Event());
//        EasyMock.expect( ).andReturn(l);

        //PowerMock.replay(KafkaLowLevelUtil.class);
//        verifyAll();
    }


    /**
     * Method: checkOffset(SimpleConsumer sc, String topic, int partition, long offset)
     */
    @Test
    public void testCheckOffset() throws Exception { 
            /* 
            try { 
               Method method = LowLevelConsumer.getClass().getMethod("checkOffset", SimpleConsumer.class, String.class, int.class, long.class);
               method.setAccessible(true); 
               method.invoke(<Object>, <Parameters>); 
            } catch(NoSuchMethodException e) { 
            } catch(IllegalAccessException e) { 
            } catch(InvocationTargetException e) { 
            } 
            */
    }

    /**
     * Method: getMaxOffset(List<Event> list)
     */
    @Test
    public void testGetMaxOffset() throws Exception {
        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");

        long produced = c.getProducedOffset("A1", "A1C", 0);
        long consumed = c.getConsumedOffset("A1C","A1",0);
        System.out.println(produced+","+consumed);
    }

    @Test
    public void testGetPartitions() throws Exception {
        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");
        List<Integer> list = c.getPartitions("A1","g1");
        System.out.println(list);
    }

    /**
     * Method: calcMsgSize(long startOffset, long endOffset, int fetchSize)
     */
    @Test
    public void testGetPartitionsProcessStatus() throws Exception {
        LowLevelConsumer c = new LowLevelConsumer("kafka.sconsumer.test.properties");
		TopicPartiton tp = c.getAllPartitionConsumeState("A1C","A1");
        System.out.println(tp);
    }

}
