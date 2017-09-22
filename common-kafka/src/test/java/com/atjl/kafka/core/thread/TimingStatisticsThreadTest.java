package com.atjl.kafka.core.thread;

import com.atjl.kafka.api.config.ConsumerConfig;
import com.atjl.kafka.consumer.TConsumer;
import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.kafka.core.KafkaThreadContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.event.Event;

import kafka.consumer.KafkaStream;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * StatisticsThread
 *
 * @since 1.0
 */
public class TimingStatisticsThreadTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: run()
     */
    @SuppressWarnings("rawtypes")
	@Test
    public void testRun() throws Exception {
        TConsumer km = new TConsumer();
        km.subscribeTopic("test1", new TopicHandler() {
            @Override
            public void execute(List<Event> dalist) {
                System.out.println("get data "+dalist);
            }
        });
        km.constructStream();
        IQueue dataQ = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        @SuppressWarnings("unused")
		int id = 1;

        TimingStatisticsThread statT = new  TimingStatisticsThread(0,1000);
        ScheduledThreadPoolExecutor es = new ScheduledThreadPoolExecutor(10);
        es.scheduleAtFixedRate( statT,0,1, TimeUnit.SECONDS);

        //Thread rt = new Thread(new FetchDataThread(id,km.getStreams().get(0),dataQ));
//        Thread rt = new Thread(new MockSender(QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY),new KafkaConsumeContext()));
//        rt.start();
        //int i= 0;

        while (true) {
            String data = String.valueOf(dataQ.receiveMessage());
            System.out.println("recv data "+data);
            Thread.sleep(3000);
            //KafkaConsumeContext.showContextStatus();
            //i++;
        }
    }


    /**
     * method:statFetchData
     */
    @Test
    public void testFetchStat(){
        TConsumer km = new TConsumer();
        km.subscribeTopic("test1", dalist -> System.out.println("get data "+dalist));
        km.constructStream();
//        List<FetchDataThread> list = new LinkedList<>();
        @SuppressWarnings("rawtypes")
		KafkaStream stream = Mockito.mock(KafkaStream.class);
        for(int i=0;i<3;i++){
            @SuppressWarnings("unchecked")
			FetchDataThread t = new FetchDataThread(0,new ConsumerConfig(),stream, QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY));
//            list.add(t);
            KafkaThreadContext.addThread(KafkaThreadContext.FETCH_THREAD, t);
        }
        TimingStatisticsThread t = new TimingStatisticsThread(0,1000);
        t.statFetchData();
    }


    @Test
    public void testun(){
        TimingStatisticsThread st = new TimingStatisticsThread(0,1000);
        //KafkaStaticContext.incrFetchDataTotalCount();
        st.run();
    }

    @Test
    public void testUpdate() {
//		TimingStatisticsThread st = new TimingStatisticsThread(0, 1000);
//        for (int i = 0; i < 10; i++) {
            //KafkaStaticContext.incrFetchDataTotalCount();
//            String stat = st.statFetchData();
//            System.out.println(stat);
//        }
    }

}
