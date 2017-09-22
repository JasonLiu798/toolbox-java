package com.atjl.kafka.consumerlowlevel;

import com.atjl.kafka.domain.TopicMeta;
import com.atjl.util.character.StringSplitUtil;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * kafka config base
 * 自动提交 consumer，只需要的基础参数
 * topic
 * group1,group2
 * c1 c2 ,

 * @since 1.0
 */
public class LowLevelConsumerConfig implements Serializable {

    private static final long serialVersionUID = 2457666178152817826L;


    public Map<String,TopicMeta> buildTopicMap(){
        if(this.topicMap!=null){
            return this.topicMap;
        }
        String[] topicMetas = StringSplitUtil.split(this.topicClusterTTokenGroupidCTokenHandler,this.sepL2);
        Map<String,TopicMeta> map  = new HashMap<>();
        for(String topicMeta:topicMetas) {
            TopicMeta tci = null;
            if (this.constructHandler) {
                tci = TopicMeta.build(topicMeta, this.sepL1, TopicMeta.TP_LL_CONSUMER_H);
            } else {
                tci = TopicMeta.build(topicMeta, this.sepL1, TopicMeta.TP_LL_CONSUMER_NH);
            }
            map.put(tci.getTopicGroupid(),tci);
        }
        this.topicMap = map;
        return map;
    }

    /**
     * ############################ Auth associate ############################
     */
    private String url;
    /**
     * producer associate,for get broker list
     * format:
     * topic1:token1,topic2:token2
     */
    private String topicClusterTTokenGroupidCTokenHandler;

    private Map<String,TopicMeta> topicMap;
    public static final String [] EXECLUDE_FIELDS = new String[]{"topicMap"};

    /**
     * is init handler Object,
     */
    private boolean constructHandler = false;

    /**
     * 二级分隔符
     */
    private String sepL2 = ";";
    /**
     * 一级分隔符
     */
    private String sepL1 = ":";


    /**
     * consumer associate,for get zk address
     * format:
     * groupId1:token1,groupId2:token2
     *
     *      topic:groupid
     *
    private String groupIdTokens;*/

    /**
     * ############################ fetch size associate ############################
     */
    /**
     * Values can be: CUSTOM/EARLIEST/LATEST/RESTART.
     */
    private String startOffsetFrom = "earliest";

    /**
     * fetch event count max
     * fetch range offset data,fetch data count at one time
     */
    private int fetchEventCountMax = 1000;

    /**
     * fetch range every loop can fetch max count
     *
     */
    private int innerFetchEventCountMax = 1000;

    /**
     * fetch size bytes ,depend on message size
     */
    private int fetchSizeBytes = 100 * 1000;

    /**
     * msg max size
     */
    private int fetchMaxByte = 900*1000;

    /**
     * use resume offset time if offset out of range
     */
    private boolean useStartOffsetTimeIfOffsetOutOfRange = true;



    /**
     * ######################## zookeeper associate ###############################
     */
    /**
     * Zookeeper session timeout in MS
     */
    private int zkSessionTimeoutMs = 10000;

    /**
     * Zookeeper connection timeout in MS
     */
    private int zkConnectionTimeoutMs = 15000;

    /**
     * Zookeeper number of retries when creating a curator client
     */
    private int zkCuratorRetryTimes = 3;

    /**
     * Zookeeper: time in ms between re-tries when creating a Curator
     */
    private int zkCuratorRetryDelayMs = 2000;


    /**
     * ###################### socket assocaite #################################
     */
    /**
     * SimpleConsumer socket timeout in MS
     */
    public final int socketTimeoutMs = 10000;

    /**
     * FetchRequest's minBytes value
     */
    public final int kafkaFetchSizeMinBytes = 1;

    /**
     * fetch size bytes increase retry time
     */
    private int fetchSizeIncreaseRetryTime = 10;

    /**
     * process data thread count
     */
    protected int processThreadCount = KafkaConfigConstant.DFT_PROCESS_DATA_THREAD_COUNT;



    /**
     * construct function
     */
    public LowLevelConsumerConfig() {
    }

    public boolean isConstructHandler() {
        return constructHandler;
    }

    public void setConstructHandler(boolean constructHandler) {
        this.constructHandler = constructHandler;
    }

    public String getSepL1() {
        return sepL1;
    }

    public void setSepL1(String sepL1) {
        this.sepL1 = sepL1;
    }

    public String getSepL2() {
        return sepL2;
    }

    public void setSepL2(String sepL2) {
        this.sepL2 = sepL2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFetchMaxByte() {
        return fetchMaxByte;
    }

    public void setFetchMaxByte(int fetchMaxByte) {
        this.fetchMaxByte = fetchMaxByte;
    }

    public String getStartOffsetFrom() {
        return startOffsetFrom;
    }

    public void setStartOffsetFrom(String startOffsetFrom) {
        this.startOffsetFrom = startOffsetFrom;
    }

    public int getFetchEventCountMax() {
        return fetchEventCountMax;
    }

    public int getInnerFetchEventCountMax() {
        return innerFetchEventCountMax;
    }

    public void setInnerFetchEventCountMax(int innerFetchEventCountMax) {
        this.innerFetchEventCountMax = innerFetchEventCountMax;
    }

    public void setFetchEventCountMax(int fetchEventCountMax) {
        this.fetchEventCountMax = fetchEventCountMax;
    }

    public int getFetchSizeBytes() {
        return fetchSizeBytes;
    }

    public void setFetchSizeBytes(int fetchSizeBytes) {
        this.fetchSizeBytes = fetchSizeBytes;
    }

    public boolean isUseStartOffsetTimeIfOffsetOutOfRange() {
        return useStartOffsetTimeIfOffsetOutOfRange;
    }

    public void setUseStartOffsetTimeIfOffsetOutOfRange(boolean useStartOffsetTimeIfOffsetOutOfRange) {
        this.useStartOffsetTimeIfOffsetOutOfRange = useStartOffsetTimeIfOffsetOutOfRange;
    }

    public int getZkSessionTimeoutMs() {
        return zkSessionTimeoutMs;
    }

    public void setZkSessionTimeoutMs(int zkSessionTimeoutMs) {
        this.zkSessionTimeoutMs = zkSessionTimeoutMs;
    }

    public int getZkConnectionTimeoutMs() {
        return zkConnectionTimeoutMs;
    }

    public void setZkConnectionTimeoutMs(int zkConnectionTimeoutMs) {
        this.zkConnectionTimeoutMs = zkConnectionTimeoutMs;
    }

    public int getZkCuratorRetryTimes() {
        return zkCuratorRetryTimes;
    }

    public void setZkCuratorRetryTimes(int zkCuratorRetryTimes) {
        this.zkCuratorRetryTimes = zkCuratorRetryTimes;
    }

    public int getZkCuratorRetryDelayMs() {
        return zkCuratorRetryDelayMs;
    }

    public void setZkCuratorRetryDelayMs(int zkCuratorRetryDelayMs) {
        this.zkCuratorRetryDelayMs = zkCuratorRetryDelayMs;
    }

    public int getSocketTimeoutMs() {
        return socketTimeoutMs;
    }

    public int getKafkaFetchSizeMinBytes() {
        return kafkaFetchSizeMinBytes;
    }

    public int getFetchSizeIncreaseRetryTime() {
        return fetchSizeIncreaseRetryTime;
    }

    public void setFetchSizeIncreaseRetryTime(int fetchSizeIncreaseRetryTime) {
        this.fetchSizeIncreaseRetryTime = fetchSizeIncreaseRetryTime;
    }

    public int getProcessThreadCount() {
        return processThreadCount;
    }

    public void setProcessThreadCount(int processThreadCount) {
        this.processThreadCount = processThreadCount;
    }

    public String getTopicClusterTTokenGroupidCTokenHandler() {
        return topicClusterTTokenGroupidCTokenHandler;
    }

    public void setTopicClusterTTokenGroupidCTokenHandler(String topicClusterTTokenGroupidCTokenHandler) {
        this.topicClusterTTokenGroupidCTokenHandler = topicClusterTTokenGroupidCTokenHandler;
    }

    public Map<String, TopicMeta> getTopicMap() {
        if(this.topicMap==null){
            return this.buildTopicMap();
        }
        return this.topicMap;
    }

    public void setTopicMap(Map<String, TopicMeta> topicMap) {
        this.topicMap = topicMap;
    }

    @Override
    public String toString() {
        return "LowLevelConsumerConfig{" +
                "url='" + url + '\'' +
                ", topicClusterTTokenGroupidCTokenHandler='" + topicClusterTTokenGroupidCTokenHandler + '\'' +
                ", topicMap=" + topicMap +
                ", startOffsetFrom='" + startOffsetFrom + '\'' +
                ", fetchEventCountMax=" + fetchEventCountMax +
                ", innerFetchEventCountMax=" + innerFetchEventCountMax +
                ", fetchSizeBytes=" + fetchSizeBytes +
                ", fetchMaxByte=" + fetchMaxByte +
                ", useStartOffsetTimeIfOffsetOutOfRange=" + useStartOffsetTimeIfOffsetOutOfRange +
                ", zkSessionTimeoutMs=" + zkSessionTimeoutMs +
                ", zkConnectionTimeoutMs=" + zkConnectionTimeoutMs +
                ", zkCuratorRetryTimes=" + zkCuratorRetryTimes +
                ", zkCuratorRetryDelayMs=" + zkCuratorRetryDelayMs +
                ", socketTimeoutMs=" + socketTimeoutMs +
                ", kafkaFetchSizeMinBytes=" + kafkaFetchSizeMinBytes +
                ", fetchSizeIncreaseRetryTime=" + fetchSizeIncreaseRetryTime +
                ", processThreadPoolSize=" + processThreadCount +
                '}';
    }
}
