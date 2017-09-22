package com.atjl.kafka.core.thread;

import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.core.KafkaStaticContext;
import com.atjl.kafka.core.KafkaThreadContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * StatisticsThread
 * 监控 统计用
 * @since 1.0
 */
public class TimingStatisticsThread extends TimingBaseThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TimingStatisticsThread.class);

    private static long preFetchCount = -1;
    //public static long preProcessingCount = -1;
    private static long preProcessedCount = -1;

    public static long preForceCommitCount = -1;
    private static final String LOG_TEXT = "total:{},count/s:{}";

    @SuppressWarnings("unused")
	private List<FetchDataThread> list;
    /**
     * 间隔统一为每秒
     */
    private static long INTERVAL_SEC;

    @SuppressWarnings("unused")
	public TimingStatisticsThread(int id, int interval ) {
        super(id, interval);
        List<FetchDataThread> list = new LinkedList<>();
        List<BaseThread> baseList = KafkaThreadContext.getThreadList(KafkaThreadContext.FETCH_THREAD);
        for(BaseThread t:list){
        	
        }

        INTERVAL_SEC = KafkaStaticContext.getStatisticsIntervalMillSecond() / 1000;
    }

	@Override
    public void run() {
        LOG.info("stat thread resume,interval {} second",INTERVAL_SEC);
//        StringBuilder sb = new StringBuilder();
        if(KafkaStaticContext.isStatisticsFetch()) {

        }

        if(KafkaStaticContext.isStatisticsProcess()) {
        }

        if(KafkaStaticContext.isStatisticsQueue()){
            /*
            String fetchStat = statFetchData();
            sb.append(fetchStat).append(",");

            //statProcessingData();
            statProcessedData();*/
            statQueueSize();
        }
        checkForceCommitSize();
    }


    /**
     * 监控 force commit
     */
    private void checkForceCommitSize(){
        AtomicLong forceCommitSize = KafkaStaticContext.getForceCommitCount();
        long diff = forceCommitSize.get();
        if (preProcessedCount >= 0) {
            diff = getLongDiff(preProcessedCount, forceCommitSize);
        }
        preProcessedCount = forceCommitSize.get();
        LOG.info("forceCommit total:{},count/{}:{}", preProcessedCount, KafkaStaticContext.getStatisticsIntervalMillSecond(),diff);
        if(diff> KafkaInnerConstant.FORCE_COMMIT_ALERT_DIFF_SIZE){
            LOG.warn("force commit diff size reach {}", KafkaInnerConstant.FORCE_COMMIT_ALERT_DIFF_SIZE);
            //TODO: 发送报警邮件
        }
        if(preProcessedCount > KafkaInnerConstant.FORCE_COMMIT_ALERT_TOTAL_SIZE){
            LOG.warn("force commit total size reach {}", KafkaInnerConstant.FORCE_COMMIT_ALERT_DIFF_SIZE);
            //TODO: 发送报警邮件
        }

    }


    @SuppressWarnings("unused")
	private void statProcessedData() {
        AtomicLong processedDataCount = KafkaStaticContext.getProcessedDataTotalCount();
        long diff = processedDataCount.get();
        if (preProcessedCount >= 0) {
            diff = getLongDiff(preProcessedCount, processedDataCount);
        }
        preProcessedCount = processedDataCount.get();
        long everySecondCount = diff / INTERVAL_SEC;
        LOG.info("processed " + LOG_TEXT, preProcessedCount, everySecondCount);
    }

    /**
     * 统计 fetch数据量
     */
    public void statFetchData() {
        List<FetchDataThread> list = KafkaThreadContext.getFetchDataThreadList();

        long total = 0;
        for(FetchDataThread t:list){
            total+=t.getFetchCount();
        }

        long diff = preFetchCount;
        if (preFetchCount >= 0) {
            diff = total - preFetchCount;
        }
        TimingStatisticsThread.preFetchCount = total;
        long everySecondCount = diff / INTERVAL_SEC;
        //String res =
        LOG.info("fetch " + LOG_TEXT, preFetchCount, everySecondCount);
    }

    private long getLongDiff(long pre, AtomicLong now) {
        long curCount = now.get();
        long diff = curCount - pre;
        //LOG.info("fetch {} data every {}s", diff, KafkaStaticContext.getStatisticsIntervalMillSecond());
        return diff;
    }


    @SuppressWarnings("rawtypes")
	private void statQueueSize() {
        IQueue queue = QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY);
        int queueSize = queue.getCount();
        LOG.info("{} queue size {}", KafkaInnerConstant.DATA_QUEUE_KEY, queueSize);
    }


    /*
    private void statProcessingData() {
        AtomicLong processingDataCount = KafkaStaticContext.getProcessGetDataTotalCount();
        long diff = processingDataCount.get();
        if (preProcessingCount >= 0) {
            diff = getLongDiff(preProcessingCount, processingDataCount);
        }
        preProcessingCount = processingDataCount.get();
        long everySecondCount = diff / INTERVAL_SEC;
        LOG.info("processget " + LOG_TEXT, preProcessedCount, everySecondCount);
    }*/
}
