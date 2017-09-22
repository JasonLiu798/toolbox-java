package com.atjl.kafka.consumer;

import com.atjl.util.common.SystemUtil;
import com.atjl.kafka.api.event.Event;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;


/**
 * TConsumer
 */
public class TConsumerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: resume()
     */
    @Test
    public void testStart() throws Exception {
        TConsumer mqConsumerMC = new TConsumer();
        TopicHandler handler = new TopicHandler() {
            @Override
            public void execute(List<Event> da) {
                System.out.println("process data " + da);
            }
        };
        mqConsumerMC.subscribeTopic("test1", handler);
        mqConsumerMC.start();

        while (true) {
            SystemUtil.sleep(1000);
        }
    }

    /**
     * Method: getConsumer()
     */
    @Test
    public void testGetConsumer() throws Exception {

    }

    /**
     * Method: setConsumer(ConsumerConnector consumer)
     */
    @Test
    public void testSetConsumer() throws Exception {

    }

    /**
     * Method: getStatus()
     */
    @Test
    public void testGetStatus() throws Exception {

    }

    /**
     * Method: setStatus(Status status)
     */
    @Test
    public void testSetStatus() throws Exception {

    }

    /**
     * Method: getConfig()
     */
    @Test
    public void testGetMqConfig() throws Exception {

    }

    /**
     * Method: getContext()
     */
    @Test
    public void testGetContext() throws Exception {

    }

    /**
     * Method: getStreams()
     */
    @Test
    public void testGetStreams() throws Exception {

    }


    /**
     * Method: startFetchThread()
     */
    @Test
    public void testStartFetchThread() throws Exception { 
            /* 
            try { 
               Method method = TConsumer.getClass().getMethod("startFetchThread");
               method.setAccessible(true); 
               method.invoke(<Object>, <Parameters>); 
            } catch(NoSuchMethodException e) { 
            } catch(IllegalAccessException e) { 
            } catch(InvocationTargetException e) { 
            } 
            */
    }

    /**
     * Method: startProcessThread()
     */
    @Test
    public void testStartProcessThread() throws Exception { 
            /* 
            try { 
               Method method = TConsumer.getClass().getMethod("startProcessThread");
               method.setAccessible(true); 
               method.invoke(<Object>, <Parameters>); 
            } catch(NoSuchMethodException e) { 
            } catch(IllegalAccessException e) { 
            } catch(InvocationTargetException e) { 
            } 
            */
    }

    /**
     * Method: startCommitThread()
     */
    @Test
    public void testStartCommitThread() throws Exception { 
            /* 
            try { 
               Method method = TConsumer.getClass().getMethod("startCommitThread");
               method.setAccessible(true); 
               method.invoke(<Object>, <Parameters>); 
            } catch(NoSuchMethodException e) { 
            } catch(IllegalAccessException e) { 
            } catch(InvocationTargetException e) { 
            } 
            */
    }

}
