package com.atjl.logdb.service;

import com.atjl.common.api.resp.PageResp;
import com.atjl.logdb.api.domain.LogDbConstant;
import com.atjl.logdb.api.LogService;
import com.atjl.logdb.api.domain.OpLog;
import com.atjl.logdb.api.req.OpLogReq;
import com.atjl.logdb.mapper.OpLogMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志 相关 service
 */
@Component(LogDbConstant.LOG_SERVICE)
public class LogServiceImpl implements LogService {
    //    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Resource
    private OpLogMapper opLogMapper;

    /**
     * insert one log to db
     */
    @Override
    public int insert(OpLog record) {
        return opLogMapper.insertSelective(record);
    }

    /**
     * search by page
     */
    @Override
    public PageResp<OpLog> searchPage(OpLogReq param) {
        long cnt = count(param);
        if (cnt == 0) {
            return new PageResp<>();
        }
        return new PageResp<>(cnt, search(param));
    }

    private List<OpLog> search(OpLogReq rawparam) {
        return opLogMapper.select(rawparam);
    }

    private long count(OpLogReq param) {
        return opLogMapper.count(param);
    }


//    @Override
//    public void updateBlackList() {
//        String[] blacklist = dictService.getValueArrByPathKey(LogDbConstant.DICT_LOG_BLACK_LIST_KEY);
//        LogDbUtil.setBlackList(blacklist);
//    }

    /**
     * 初始化 黑名单
     * spring初始化后
     @Override public void init()  {
     updateBlackList();
     initKafka();
     }*/

    /**
     * 初始化 kafka producer
     *
     @Override public void initKafka() {
     updateCfg();
     buildProducer(poolSize, kafkaUrl, clusterName, topicTokens);
     }

     /**
      * 更新 配置
      *
     public void updateCfg() {
     send2kafka = dictService.getValueByPathKeyDft(
     DictConstant.LOG_PK + ".sendkafka", "N");
     //有默认值
     topic = dictService.getValueByPathKeyDft(DictConstant.LOG_PK + ".kafkalogtopic", "");
     poolSize = dictService.getIntValueByPathKey(DictConstant.LOG_PK + ".poolsize", 10);
     clusterName = dictService.getValueByPathKeyDft(DictConstant.LOG_PK + ".clusterName", "other");

     //无默认值
     kafkaUrl = dictService.getValueByPathKeyDft(DictConstant.LOG_PK + ".kafkaurl", "");
     topicTokens = dictService.getValueByPathKey(DictConstant.LOG_PK + ".topicTokens");

     if (poolSize <= 5 || poolSize > 1024) {
     poolSize = 10;
     }

     if (StringCheckUtil.isExistEmpty(kafkaUrl, topicTokens)) {
     if (logger.isDebugEnabled()) {
     logger.debug("kafka producer init fail, exist null string");
     }
     LogDbUtil.writeError(LogDbConstant.MODULE_LOG, "init kafka topic,parameter exist null");
     return;
     }
     }*/

    /**
     * 构建 producer
     *
     * @param poolSize
     * @param kafkaUrl
     * @param clusterName
     * @param topicTokens
     *
    private void buildProducer(Integer poolSize, String kafkaUrl, String clusterName, String topicTokens) {
    synchronized (LogServiceImpl.class) {
    try {
    ProduceConfig produceConfig = new ProduceConfig(poolSize, kafkaUrl, clusterName, topicTokens);
    kafkaProducer = new ProducerPool(produceConfig);
    } catch (Exception e) {
    LogDbUtil.writeError(LogDbConstant.MODULE_LOG, "init kafka producer fail", e);
    return;
    }
    }
    LogDbUtil.writeInfo(LogDbConstant.MODULE_LOG, "init kafka producer success");
    }*/

    /**
     * 更新配置，重建producer
     *
     @Override public void rebuildProducer() {
     updateCfg();
     buildProducer(poolSize, kafkaUrl, clusterName, topicTokens);
     }*/

    /**
     * 半小时衰减 一半错误数量
     *
     * @Override public void sendLog2Kafka(String v) {
     * try {
     * if (StringCheckUtil.equal(CommonConstant.Y, send2kafka)) {
     * if (kafkaProducer != null) {
     * if (!StringCheckUtil.isExistEmpty(topic, v)) {
     * kafkaProducer.sendString(topic, v);
     * }
     * } else {
     * if (logger.isDebugEnabled()) {
     * logger.debug("send2kafka producer null");
     * }
     * }
     * } else {
     * if (logger.isDebugEnabled()) {
     * logger.debug("send2kafka equals N");
     * }
     * }
     * } catch (Exception e) {
     * ErrorContext.KAFKA_SEND_CNT.incrementAndGet();
     * if (ErrorContext.KAFKA_SEND_CNT.intValue() < 1000) {
     * LogDbUtil.writeError(LogDbConstant.MODULE_LOG, "kafka write error producer " + kafkaProducer + ",topic " + topic + ",value " + v + ",exception " ,e);
     * }
     * }
     * }
     */

//    @Override
//    public boolean producerNull() {
//        return kafkaProducer != null;
//    }

//    public void destroy() {
//        logger.info("log service destroy");
//    }
}
