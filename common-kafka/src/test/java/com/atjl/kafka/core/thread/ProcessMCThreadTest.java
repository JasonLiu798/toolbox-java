package com.atjl.kafka.core.thread;

import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.event.BatchEventMC;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * ProcessThread
 * @since 1.0
 */
public class ProcessMCThreadTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    KafkaConsumeContext context = new KafkaConsumeContext();
    /**
     * Method: run()
     */
    @Test
    public void testRunSingleThread() throws Exception {
		IQueue dataQueue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        TopicHandler mh = new TopicHandler() {
            @Override
            public void execute(List<Event> data) {
                System.out.println("process data "+data);
            }
        };
        Thread t = new Thread(new ProcessMCThread(0,context,dataQueue,mh));
        t.start();
        Thread st = new Thread(new Sender(dataQueue));
        st.start();
        while(true){
            Thread.sleep(2000);
            context.showContextStatus();
        }
    }

    @Test
    public void testRunMultiThread() throws Exception {
        IQueue dataQueue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        //IQueue commitQueue = QueueManager.getQueue(KafkaInnerConstant.COMMIT_QUEUE_KEY);
        TopicHandler mh = new TopicHandler() {
            @Override
            public void execute(List<Event> data) {
                System.out.println("process data "+data);
            }
        };
        for(int i=0;i<3;i++) {
            //Thread t = new Thread(new ProcessThread(i,dataQueue, commitQueue, mh));
            Thread t = new Thread(new ProcessMCThread(i,context,dataQueue, mh));
            t.start();
        }
        Thread st = new Thread(new Sender(dataQueue));
        st.start();
        while(true){
            //CommitData rd = (CommitData) commitQueue.receiveMessage();
            //System.out.println("get commitPartition data "+rd);
        }
    }


    public class Sender implements Runnable{
        private IQueue queue;
        public Sender(IQueue queue){
            this.queue = queue;
        }
        
		@Override
        public void run() {
            int i= 0;
            while(true) {
                BatchEventMC brd = new BatchEventMC();
                for(int j =i;j<i+10;j++){
                    Event e = new Event();
                    String value = String.valueOf(j);
                    e.setValue(value);
                    e.setKey(value);
                    e.setOffset(j);
                    brd.addEvent2Partition(1,e);
                }
                brd.setOffsetMap(context.addUnProcessData(brd));
                context.showContextStatus();
                i+=10;
                try {
                    queue.sendMessage(brd);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
