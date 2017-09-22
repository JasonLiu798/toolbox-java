package com.atjl.kafka.core.thread;

import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.consumer.Consumer;
import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * FetchDataThread
 *
 * @since 1.0
 */
public class FetchDataThreadTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * 消费数据线程测试用例
     * @throws Exception
     */
    @Test
    public void testFetchData() throws Exception {
        //(int id, ConsumerConnector connector, KafkaStream<String, String> stream, IQueue dataQueue)
//        KafkaManagerHL consumer
        Consumer km = new Consumer();
        km.subscribeTopic("test1", new TopicHandler() {
            @Override
            public void execute(List<Event> da) {
                System.out.println("get data "+da);
            }
        });
        km.constructStream();

        IQueue dataQ = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        int id = 1;
        Thread rt = new Thread(new FetchDataThread(id,km.getConfig(),km.getStreams().get(0),dataQ));
        rt.start();
        while (true) {
            String data = String.valueOf(dataQ.receiveMessage());
            System.out.println("recv data "+data);
            Thread.sleep(1000);
        }
    }


}
