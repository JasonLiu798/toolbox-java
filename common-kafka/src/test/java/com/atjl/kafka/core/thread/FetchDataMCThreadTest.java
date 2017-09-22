package com.atjl.kafka.core.thread;

import com.atjl.kafka.consumer.TConsumer;
import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * FetchDataThread
 *
 * @since 1.0
 */
@SuppressWarnings({"rawtypes"})
public class FetchDataMCThreadTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    KafkaConsumeContext context = new KafkaConsumeContext();

    /**
     * 消费数据线程测试用例
     * @throws Exception
     */
    @Test
    public void testFetchData() throws Exception {
        //(int id, ConsumerConnector connector, KafkaStream<String, String> stream, IQueue dataQueue)
//        KafkaManagerHL consumer
        TConsumer km = new TConsumer();
        km.subscribeTopic("test1", new TopicHandler() {
            @Override
            public void execute(List<Event> da) {
                System.out.println("get data "+da);
            }
        });
        km.constructStream();

        IQueue dataQ = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        int id = 1;
        Thread rt = new Thread(new FetchDataMCThread(id,km.getConfig(),context,km.getStreams().get(0),dataQ));
        rt.start();
        int i= 0;

        while (true) {
            String data = String.valueOf(dataQ.receiveMessage());
            System.out.println("recv data "+data);
            Thread.sleep(1000);
            if(i%2==0){
                context.showContextStatus();
            }
            i++;
        }
    }


}
