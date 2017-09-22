package com.atjl.kafka.core.thread;

import com.atjl.kafka.consumer.TConsumer;
import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.kafka.domain.OffsetRange;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.event.BatchEventMC;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import kafka.javaapi.consumer.ConsumerConnector;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * CommitThread
 *
 * @since 1.0
 */
public class TimingCommitThreadTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    KafkaConsumeContext context = new KafkaConsumeContext();
    ConsumerConnector connector = Mockito.mock(ConsumerConnector.class);

    /**
     * Method: run()
     */
    @Test
    public void testRun() throws Exception {
        TConsumer km = new TConsumer();
        ConsumerConnector cc = km.getConsumer();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.scheduleAtFixedRate(new TimingCommitThread(0,2000,"test1",cc,context),2000,2000,TimeUnit.SECONDS);

        while (true);
    }


    /**
     * Method: commitPartition(Map<OffsetRange, Boolean> offsetRangeBooleanMap)
     */
    @Test
    public void testCommitSeq() throws Exception {
        TimingCommitThread ct = new TimingCommitThread(0,1000,"test1",connector,context);
        //2->3, 5->9
        generateAndAdd2Context(1,new long[]{2, 3, 4,5, 6, 7,8, 9});
        //BatchEvent rawDatas = generateBatchCommitData(new long[]{2, 3, 5, 6, 7, 9});
        //System.out.println(rawDatas);

        ConcurrentHashMap<OffsetRange, Boolean> map = context.getOffsetProcessCache().get(1);
        ct.commitPartition(1,map);
        context.showContextStatus("AFC");
    }

    /**
     *  commit 非连续 offset
     * @throws Exception
     */
    @Test
    public void testCommitNotSeq() throws Exception {
        //KafkaManagerHL km = new KafkaManagerHL();
        //ConsumerConnector cc = km.getConsumer();
        TimingCommitThread ct = new TimingCommitThread(0,1000,"test1",null,context);//"test1",cc);
        generateAndAdd2Context(1,new long[]{2, 3, 4, 5, 7, 9});

//        Map<OffsetRange, Boolean> offsetRangeBooleanMap = new HashMap<>();
//        offsetRangeBooleanMap.put(new OffsetRange(0,3),true);
//        offsetRangeBooleanMap.put(new OffsetRange(4,5),true);
//        非连续点
//        offsetRangeBooleanMap.put(new OffsetRange(8,9),true);
//        offsetRangeBooleanMap.put(new OffsetRange(10,14),true);
        ConcurrentHashMap<OffsetRange, Boolean> map = context.getOffsetProcessCache().get(1);
        ct.commitPartition(1,map);
        context.showContextStatus("AFC");
    }


    /**
     * 未处理offset 被检查次数达到最大
     */
    @Test
    public void testNotProcessOffsetRangeReachMaxCount(){
        TimingCommitThread ct = new TimingCommitThread(0,1000,"test1",null,context);
        //2->3, 5->9
        BatchEventMC batchRawData = generateAndAddFreshData2Context(1,new long[]{2, 3, 5, 6, 7,9});
        BatchEventMC batchRawData1 = generateAndAddFreshData2Context(1,new long[]{11,16,5,20,30,6});
        System.out.println(batchRawData.getOffsetMap()+String.valueOf(batchRawData1.getOffsetMap()));
        context.addProcessedData(batchRawData1);
        context.showContextStatus();
        //processSomeDataUpdateContext(new long[]{5,6,7,9});
        ConcurrentHashMap<OffsetRange, Boolean> map = context.getOffsetProcessCache().get(1);
        for(int i=0;i<5;i++) {
            ct.commitPartition(1, map);
            context.showContextStatus("AFC");
        }
    }

    /**
     * context map 达到最大，强制提交 最小offset range
     */
    @Test
    public void testContextMapReachMaxSize(){
        //KafkaInnerConstant.OFFSET_PROCESS_STATUS_MAP_MAXSIZE = 3;
        TimingCommitThread ct = new TimingCommitThread(0,1000,"test1",null,context);
        //2->3, 5->9
        BatchEventMC batchRawData = generateAndAddFreshData2Context(1,new long[]{2, 3, 5, 6, 7, 9, 10,11,15,16});
        processSomeData(batchRawData,1);
        context.showContextStatus("BF");
        //ConcurrentHashMap<OffsetRange, Boolean> map = KafkaConsumeContext.getOffsetProcessCache().get(1);
        ct.run();
        context.showContextStatus("AFC");
        /**
         for(int i=0;i<4;i++) {
         ct.commitPartition(1, map);
         KafkaConsumeContext.showContextStatus("AFC");
         }*/
    }


    /**
     * 伪造 未处理数据，并放入context
     * @param arr
     */
    private BatchEventMC generateAndAddFreshData2Context(int partition, long[] arr) {
        BatchEventMC rawDatas = generateBatchCommitData(partition,arr);
        context.addUnProcessData(rawDatas);
        context.showContextStatus("BF");
        return rawDatas;
    }

    private void generateAndAdd2Context(int partition,long[] arr){
        BatchEventMC rawDatas = generateBatchCommitData(partition,arr);
        context.addUnProcessData(rawDatas);
        context.showContextStatus("BF");

        context.addProcessedData(rawDatas);
        context.showContextStatus("AFP");
    }

    private BatchEventMC generateBatchCommitData(int partition, long[] offsets){
        List<Event> list = generateList(partition,offsets);
        BatchEventMC res =  new BatchEventMC();
        for(int i=0;i<list.size();i++){
            res.addEvent2Partition(partition,list.get(i));
        }
        return res;
    }

    private void processSomeData(BatchEventMC batchRawData, int partition ){
        //batchRawData.getOffsetMap().get(partition);//TODO:return ignored
        context.addProcessedData(batchRawData);
        context.showContextStatus();
    }

    private List<Event> generateList(int partition,long[] offsets){
        List<Event> list = new ArrayList<>();
        for(long offset:offsets){
            //	public Event(String topic, int partition, long offset, String key, String value)
            list.add(new Event("test1",partition,offset,String.valueOf(offset),String.valueOf(offset) ));
        }
        System.out.println("generateConfigModel raw data list "+list);
        return list;
    }


    /**
     * test all three thread
     */
    @SuppressWarnings("rawtypes")
	@Test
    public void testAll(){
        TConsumer km = new TConsumer();
        context = km.getContext();
        TopicHandler handler = da -> System.out.println("GET DATA "+da);
        km.subscribeTopic("test1", handler);
        km.constructStream();

        ConsumerConnector connector = km.getConsumer();

        int id = 1;
        IQueue dataQ = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);

        /**
         * fetch thread
         */
        Thread rt = new Thread(new FetchDataMCThread(id,km.getConfig()  , context,km.getStreams().get(0),dataQ));
        rt.start();

        id++;
        /**
         * process thread
         */
        Thread pt = new Thread(new ProcessMCThread(id,context,dataQ,handler));
        pt.start();

        /**
         * commit thread
         */
        TimingCommitThread ct = new TimingCommitThread(0,1000,"test1",connector,context);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.scheduleAtFixedRate(ct,2,5,TimeUnit.SECONDS);

        Thread st = new Thread(new TimingStatisticsThread(1,1000));
        st.start();
        while (true);


    }

}
