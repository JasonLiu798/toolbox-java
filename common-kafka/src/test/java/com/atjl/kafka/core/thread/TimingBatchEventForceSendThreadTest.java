package com.atjl.kafka.core.thread;

import com.atjl.kafka.core.KafkaThreadContext;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.config.ConsumerConfig;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import kafka.consumer.KafkaStream;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.Mockito;

/**
 * TimingBatchEventForceSendThread
 * @since 1.0
 */
public class TimingBatchEventForceSendThreadTest { 

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

    /**
     * 
     * Method: run() 
     * 
     */ 
    @Test
    @SuppressWarnings("rawtypes")
    public void testRun() throws Exception {
		KafkaStream stream = Mockito.mock(KafkaStream.class);
        @SuppressWarnings("unchecked")
		FetchDataMCThread ft = new FetchDataMCThread(0,new ConsumerConfig(),new KafkaConsumeContext(),stream, QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY));
        KafkaThreadContext.addThread(KafkaThreadContext.FETCH_THREAD,ft);
        TimingBatchEventForceSendThread tt = new TimingBatchEventForceSendThread(1,5000);
        tt.run();

    } 

    
    } 
