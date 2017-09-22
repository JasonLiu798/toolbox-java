package com.atjl.kafka.api.config;

import com.atjl.util.common.CheckUtil;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;

import java.io.Serializable;
import java.util.Properties;

/**
 * kafka config for producer
 *
 * @since 1.0
 */
public class ProducerConfig implements Serializable {
    private static final long serialVersionUID = 6633220195327111885L;
    //private static Logger LOG = LoggerFactory.getLogger(ConsumerConfig.class);

    private String bootstrap;
    private Integer ack = 1;
    private Integer timeout = 100 * 1000;
    private Integer retry = 3;
    private Integer backoff = 100;
    private String partitionClass = "com.atjl.kafka.core.ModPartition";


    private int poolSize = 1;
    private String url;
    private String clusterName;
    private String topicTokens;

    /**
     * construct function
     */
    public ProducerConfig() {
    }

    public ProducerConfig createProcuderConfig() {
        //return new ProduceConfig(this.poolSize, this.url, this.clusterName, this.topicTokens);
        return null;
    }

    /**
     * 获取 构建 consumerConnector的参数
     *
     * @return
     */
    public Properties createProducerConfig() {
        Properties props = new Properties();
        CheckUtil.checkStrExistNull(this.bootstrap);

        props.put(KafkaConfigConstant.PCONF_BOOTSTRAP_KEY, this.bootstrap);
        props.put(KafkaConfigConstant.PCONF_ACK_KEY, this.ack);
        props.put(KafkaConfigConstant.PCONF_TIMEOUT_KEY, this.timeout);
        props.put(KafkaConfigConstant.PCONF_BACKOFF_KEY, this.backoff);
        props.put(KafkaConfigConstant.PCONF_RETRY_KEY, this.retry);
        props.put(KafkaConfigConstant.PCONF_PARTITION_CLASS, this.partitionClass);
        return props;
    }

    @Override
    public String toString() {
        return "ProducerConfig{" +
                "bootstrap='" + bootstrap + '\'' +
                ", ack=" + ack +
                ", timeout=" + timeout +
                ", retry=" + retry +
                ", backoff=" + backoff +
                ", partitionClass='" + partitionClass + '\'' +
                '}';
    }

    public String getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap;
    }

    public Integer getAck() {
        return ack;
    }

    public void setAck(Integer ack) {
        this.ack = ack;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getBackoff() {
        return backoff;
    }

    public void setBackoff(Integer backoff) {
        this.backoff = backoff;
    }

    public String getPartitionClass() {
        return partitionClass;
    }

    public void setPartitionClass(String partitionClass) {
        this.partitionClass = partitionClass;
    }
}
