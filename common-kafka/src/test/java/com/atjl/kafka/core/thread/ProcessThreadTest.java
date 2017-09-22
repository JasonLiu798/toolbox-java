package com.atjl.kafka.core.thread;

import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * ProcessThread
 *
 * @since 1.0
 */
public class ProcessThreadTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: run()
     */
    @Test
    public void testRunSingleThread() throws Exception {
        IQueue dataQueue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        //IQueue commitQueue = QueueManager.getQueue(KafkaInnerConstant.COMMIT_QUEUE_KEY);
        TopicHandler mh = new TopicHandler() {
            @Override
            public void execute(List<Event> data) {
                System.out.println("process data " + data);
            }
        };
        Thread t = new Thread(new ProcessThread(0, dataQueue, mh));
        t.start();
        Thread st = new Thread(new MockSender(dataQueue));
        st.start();
        while (true) {
            Thread.sleep(2000);
        }
    }

    @Test
    public void testRunMultiThread() throws Exception {
        IQueue dataQueue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        //IQueue commitQueue = QueueManager.getQueue(KafkaInnerConstant.COMMIT_QUEUE_KEY);
        TopicHandler mh = new TopicHandler() {
            @Override
            public void execute(List<Event> data) {
                System.out.println("process data " + data);
            }
        };
        for (int i = 0; i < 3; i++) {
            //Thread t = new Thread(new ProcessThread(i,dataQueue, commitQueue, mh));
            Thread t = new Thread(new ProcessThread(i, dataQueue, mh));
            t.start();
        }
        Thread st = new Thread(new MockSender(dataQueue));
        st.start();
        while (true) {
            //CommitData rd = (CommitData) commitQueue.receiveMessage();
            //System.out.println("get commitPartition data "+rd);
        }
    }


}
