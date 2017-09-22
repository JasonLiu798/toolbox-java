package com.atjl.kafka.core;

import com.atjl.kafka.api.config.StatisticsConfig;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 存储 监控，统计 信息 上下文
 * 注：如并发较高，且jdk版本>jdk8u20，CPU核心数>4 可替换AtomicLong 为 LongAdder
 *
 * @author jasonliu
 * @since 1.0
 */
public class KafkaStaticContext {

    /**
     * 计数开关
     **/
    private static Map<String,Boolean> switchMap = new HashMap<>();
    
	private static String SW_FETCH_KEY = "SW_FETCH";
    private static String SW_POCESS_KEY = "SW_POCESS";
    private static String SW_QUEUE_KEY = "SW_QUEUE";

    /**
     * 是否开启统计
     */
    private static boolean statisticsFetch = KafkaConfigConstant.CONF_STATISTICS_FETCH_SWITCH_DFT_VALUE;
    private static boolean statisticsProcess = KafkaConfigConstant.CONF_STATISTICS_POCESS_SWITCH_DFT_VALUE;
    private static boolean statisticsQueue = KafkaConfigConstant.CONF_STATISTICS_QUEUE_SWITCH_DFT_VALUE;

    /**
     * 统计间隔
     */
    private static int statisticsInterval = KafkaConfigConstant.CONF_STATISTICS_INTERVAL_DFT_VALUE;

    /*
    static {
        Properties properties = ConfigUtil.loadProperties(KafkaConfigConstant.DFT_CONF_FILE);

        statistics = ConfigUtil.getBooleanFromPropertiesDefault(properties,KafkaConfigConstant.CONF_STATISTICS_SWITCH_KEY,KafkaInnerConstant.CONF_STATISTICS_SWITCH_DFT_VALUE);
        statisticsInterval = ConfigUtil.getIntFromPropertiesDefault(properties,KafkaConfigConstant.CONF_STATISTICS_INTERVAL_KEY,KafkaConfigConstant.CONF_STATISTICS_INTERVAL_DFT_VALUE);
    }

    public static boolean isStatistics() {
        return statistics;
    }

    public static long getStatisticsIntervalMillSecond() {
        return statisticsInterval;
    }

    public static long getStatisticsIntervalSecond() {
        return statisticsInterval/1000;
    }
    */


    /**
     * 接收总数据量统计
     */
    //private static AtomicLong fetchDataTotalCount = new AtomicLong();
    private static long preFetchDataTotalCount = 0;
    private static long nowFetchDataTotalCount = 0;


    /**
     * 处理线程接收 数据量统计
     */
    private static AtomicLong processGetDataTotalCount = new AtomicLong();

    /**
     * 已处理数据量统计
     */
    private static AtomicLong processedDataTotalCount = new AtomicLong();

    /**
     * 强制刷新，未提交数据量统计
     */
    private static AtomicLong forceCommitCount = new AtomicLong();

    private static StatisticsConfig statisticsConfig;
    /**
     * init conf
     */
//    static {
//        StatisticsConfig config = ConfigUtil.generateConfigModel(StatisticsConfig.class,KafkaConfigConstant.CONF_PREFIX,KafkaConfigConstant.DFT_CONF_FILE,null);
//        statisticsConfig = config;

//        Properties properties = ConfigUtil.loadProperties(KafkaConfigConstant.DFT_CONF_FILE);
//
//        statisticsFetch = ConfigUtil.getBooleanFromPropertiesDefault(properties,KafkaConfigConstant.CONF_STATISTICS_FETCH_SWITCH_KEY,KafkaInnerConstant.CONF_STATISTICS_FETCH_SWITCH_DFT_VALUE);
//        statisticsProcess = ConfigUtil.getBooleanFromPropertiesDefault(properties,KafkaConfigConstant.CONF_STATISTICS_POCESS_SWITCH_KEY,KafkaInnerConstant.CONF_STATISTICS_POCESS_SWITCH_DFT_VALUE);
//        statisticsQueue = ConfigUtil.getBooleanFromPropertiesDefault(properties,KafkaConfigConstant.CONF_STATISTICS_QUEUE_SWITCH_KEY,KafkaInnerConstant.CONF_STATISTICS_QUEUE_SWITCH_DFT_VALUE);
//
//        statisticsInterval = ConfigUtil.getIntFromPropertiesDefault(properties,KafkaConfigConstant.CONF_STATISTICS_INTERVAL_KEY,KafkaInnerConstant.CONF_STATISTICS_INTERVAL_DFT_VALUE);
//
//        switchMap.put(SW_FETCH_KEY,statisticsFetch);
//        switchMap.put(SW_POCESS_KEY,statisticsProcess);
//        switchMap.put(SW_QUEUE_KEY, statisticsQueue);

//        if(LOG.isDebugEnabled()) {
//            LOG.debug("init KafkaStaticContext switch {},interval {}", switchMap,statisticsInterval);
//        }
//    }

    public static boolean isStatisticsFetch() {
        return statisticsFetch;
    }

    public static void setStatisticsFetch(boolean statisticsFetch) {
        KafkaStaticContext.statisticsFetch = statisticsFetch;
    }

    public static boolean isStatisticsProcess() {
        return statisticsProcess;
    }

    public static void setStatisticsProcess(boolean statisticsProcess) {
        KafkaStaticContext.statisticsProcess = statisticsProcess;
    }

    public static boolean isStatisticsQueue() {
        return statisticsQueue;
    }

    public static void setStatisticsQueue(boolean statisticsQueue) {
        KafkaStaticContext.statisticsQueue = statisticsQueue;
    }

    public static int getStatisticsIntervalMillSecond() {
        return statisticsInterval;
    }

    public static long getStatisticsIntervalSecond() {
        return statisticsInterval/1000;
    }



//    public static void incrFetchDataTotalCount(){
//        fetchDataTotalCount.incrementAndGet();
//    }

    public static void incrProcessGetDataTotalCount(){
        processGetDataTotalCount.incrementAndGet();
    }

    public static void incrProcessedDataTotalCount(){
        processedDataTotalCount.incrementAndGet();
    }

    public static void incrForceCommitTotalCount() {
        processedDataTotalCount.incrementAndGet();
    }


    /**
     * fetch 数据总量
     * @return
     */
//    public static AtomicLong getFetchDataTotalCount() {
//        return fetchDataTotalCount;
//    }


    public static AtomicLong getForceCommitCount() {
        return forceCommitCount;
    }

    public static AtomicLong getProcessGetDataTotalCount() {
        return processGetDataTotalCount;
    }

    public static AtomicLong getProcessedDataTotalCount() {
        return processedDataTotalCount;
    }
}
