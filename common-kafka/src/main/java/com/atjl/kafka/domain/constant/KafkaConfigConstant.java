package com.atjl.kafka.domain.constant;

/**
 * KafkaInnerConstant
 * @since 1.0
 */
public class KafkaConfigConstant {

    /**
     * 配置文件默认文件名
     */
    public static final String DFT_CONF_FILE = "kafka.properties";
    //测试用
    public static final String DFT_PCONF_FILE = "kafka.producer.properties";

    public static final String CCONF_PREFIX_KEY = "kafka.consumer.prefix";
    public static final String PCONF_PREFIX_KEY = "kafka.producer.prefix";

    public static final String CONF_PREFIX = "kafka";

    public static final String CONF_SC_PREFIX = "kafka.sconsumer";
    /**
     * ######################### Procuder 配置文件key 和 默认值 #########################
     */
    public static final String PCONF_PREFIX = "kafka.producer";
    /**
     * kafka servers
     */
    public static final String PCONF_BOOTSTRAP_KEY = "bootstrap.servers";
    /**
     * config ack
     */
    public static final String PCONF_ACK_KEY = "request.required.acks";
    /**
     * timeout
     */
    public static final String PCONF_TIMEOUT_KEY = "request.timeout.ms";
    /**
     * retry
     */
    public static final String PCONF_RETRY_KEY = "message.send.max.retries";
    /**
     * backoff
     */
    public static final String PCONF_BACKOFF_KEY = "retry.backoff.ms";
    /**
     * partition class
     */
    public static final String PCONF_PARTITION_CLASS = "partitioner.class";

    /**
     * ######################### Consumer 配置文件key 和 默认值 #########################
     */
    public static final String CCONF_PREFIX = "kafka.consumer";

    /**
     * ####################### 公用配置 ##########################
     */
    /**
     * inner queue config key
     */
    public static final String CONF_QUEUE_KEY = "kafka.inner.queues";
    /**
     *
     * type:性能相关
     */
    public static final String CONF_FETCH_MIN_BYTES_KEY = "kafka.fetch.min.bytes";
    public static final int CONF_FETCH_MIN_BYTES_DFT_VAL = 1;

    /**
     * auto commit interval ,unit ms, default 1000ms
     * 自动提交间隔 / 手动提交线程启动间隔
     * type:功能相关，性能相关
     */
    public static final String CONF_AUTO_COMMIT_INTERVAL_KEY = "kafka.commit.interval";
    public static final int CONF_AUTO_COMMIT_INTERVAL_DFT_VAL = 1000;

    /**
     * kafka data receiver thread count key
     * 拉取数据线程数，总体数量不能超过kafka partition数量，超过的线程会闲置获取不到数据，小于partition数量，则单个线程会接收多个分区数据
     * type:性能相关
     */
    public static final String CONF_FETCH_DATA_THREAD_COUNT_KEY = "kafka.fetch.thread.count";
    public static final int CONF_FETCH_DATA_THREAD_COUNT_DFT_VAL = 1;

    /**
     * kafka data process thread count key
     * 处理线程数，总数根据CPU核心数，处理任务时间，机器负载等，共同确定
     */
    public static final String CONF_PROCESS_DATA_THREAD_COUNT_KEY = "kafka.process.thread.count";
    public static final int CONF_PROCESS_DATA_THREAD_COUNT_DFT_VAL = 4;
    /**
     * kafka data commitPartition thread count key
     * 提交线程数
     * TODO: 开多线程，需要核查KafkaConsumerContext的 线程安全性
     */
    public static final String  CONF_COMMIT_DATA_THREAD_COUNT_KEY = "kafka.commit.thread.count";
    public static final int     CONF_COMMIT_DATA_THREAD_COUNT_DFT_VAL = 1;

    /**
     * 是否开启统计
     * type:性能相关
     */
    //public static final String  CONF_STATISTICS_SWITCH_KEY = "kafka.statistics.all";
    //public static final boolean CONF_STATISTICS_SWITCH_DFT_VALUE = false;

    public static final String  CONF_STATISTICS_FETCH_SWITCH_KEY = "kafka.statistics.fetch";
    public static final boolean  CONF_STATISTICS_FETCH_SWITCH_DFT_VALUE = false;

    public static final String  CONF_STATISTICS_POCESS_SWITCH_KEY = "kafka.statistics.process";
    public static final boolean  CONF_STATISTICS_POCESS_SWITCH_DFT_VALUE = false;

    public static final String  CONF_STATISTICS_QUEUE_SWITCH_KEY = "kafka.statistics.queue";
    public static final boolean CONF_STATISTICS_QUEUE_SWITCH_DFT_VALUE = false;



    /**
     * 统计时间间隔
     * type:性能相关
     */
    public static final String CONF_STATISTICS_INTERVAL_KEY = "kafka.statistics.interval";
    public static final int    CONF_STATISTICS_INTERVAL_DFT_VALUE = 5000;
    /**
     * 定时线程池，大小
     * 1x stat thread
     * 1x batch fetch time counter
     * 1x commit thread
     */
    public static final String CONF_TIMER_THREAD_MIN_COUNT_KEY = "kafka.timer.thread.count";
    public static final int CONF_TIMER_THREAD_MIN_COUNT_DFT_VAL = 1+1+1;

    public static final String CONF_BATCH_SEND_COUNT_KEY = "kafka.batch.send.count";
    public static final int CONF_BATCH_SEND_COUNT_DFT_VAL = 5;


    public static final String CONF_FETCH_FOCE_SEND_INTERVAL_KEY = "kafak.fetch.force.send.interval";
    public static final int CONF_FETCH_FOCE_SEND_INTERVAL_DFT_VAL = 5000;
    /**
     * ######################### 配置默认值 #########################
     */
    /**
     * 默认接收数据线程数
     */
    public static final int DFT_RECV_DATA_THREAD_COUNT = 1;
    /**
     * 默认 业务处理 线程数，推荐值= CPU / RATE
     * RATE:业务CPU利用率
     */
	public static final int DFT_PROCESS_DATA_THREAD_COUNT = 4;
    /**
     * 默认，commit线程数
     */
    public static final int DFT_COMMIT_DATA_THREAD_COUNT = 1;


    /**
     * 内存队列 名，fetch->process
     */
    public static final String DATA_QUEUE_KEY = "dataQ";
    /**
    public static final String COMMIT_QUEUE_KEY = "commitQ";*/


    /**
     * commitPartition in queue retry count
     */
    public static final int MAX_RETRY_COUNT = 2;


    /**
     * offset range map可存放最大数量offset range，超过此数值，强制提交，以防OOM
     */
//    public static final int OFFSET_PROCESS_STATUS_MAP_MAXSIZE = 3;
    public static final int OFFSET_PROCESS_STATUS_MAP_MAXSIZE = 1024;

    public static final int OFFSET_STAY_IN_MAP_UNPROCESS_TIME = 30;//COUNT
    /**
     * 未处理的offset ，被检查 OFFSET_STAY_IN_MAP_COUNT 次数之后，仍然未提交，强制写入文件/redis/mysql，并提交offset，防止 contexMap OOM
     */
    public static final int OFFSET_STAY_IN_MAP_COUNT = 2;

    public static final int COMMIT_TIME_INTERVAL = 10;


    /**
     * force commit 瞬时超过了一定次数，告警阈值
     */
    public static final int FORCE_COMMIT_ALERT_DIFF_SIZE = 3;
    /**
     *  force commit 总量超过一定次数，告警阈值
     */
    public static final int FORCE_COMMIT_ALERT_TOTAL_SIZE = 1000;


    /**
     *
     */
    public static final int BATCH_PROCESS_COUNT = 5;


}
