package com.atjl.kafka.domain;

import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.util.character.StringSplitUtil;
import com.atjl.util.common.CheckUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.config.ParameterConfigParser;
import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.kafka.api.KafkaConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TopicMeta implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TopicMeta.class);

    private static final long serialVersionUID = -4684546936120437835L;

    private static Logger LOG = LoggerFactory.getLogger(TopicMeta.class);

    private String topic;//0
    private String cluster;//1

    private String ttoken;//2 ,for sender
    private String groupid;//3,for consumer,lowlevel consumer
    private String ctoken;//4,for consumer,lowlevel consumer
    private TopicHandler handler;//5,for consumer,[lowlevel consumer]

    private String consumerId;//set after kafka.consumer start

    public static final String TOPIC_GROUPID_SEP = "-";

    public static final int FIELD_COUNT = 6;
    /**
     * TYPE:
     * ID      |    2     |   3   |   4     |    5    |
     * BIT       |    8(3)  |   4(2)|   2(1) |    1(0)  |
     * NAME      |  ttoken | groupid| ctoken | handler  |
     * sender       |    y    |   n   |   n    |   n       |  =8
     * consumer     |    n    |   y   |   y    |   y       |  =7
     * llconsumerI  |    y    |   y   |   y    |   y       |  =15
     * llconsumerNI |    y    |   y   |   y    |   n       |  =14
     */
    public static final int TP_SENDER = 8;
    public static final int TP_CONSUMER = 7;
    public static final int TP_LL_CONSUMER_H = 15;
    public static final int TP_LL_CONSUMER_NH = 14;

    public TopicMeta() {

    }

    public static TopicMeta build(String topicClusterTTokenGroupidCTokenHandler, String sep, int type) {
        String[] arr = StringSplitUtil.splitCheckSize(topicClusterTTokenGroupidCTokenHandler, sep, FIELD_COUNT);
        if (LOG.isDebugEnabled()) {
            LOG.debug("raw arr {}", Arrays.toString(arr));
        }
        TopicMeta tm = new TopicMeta();
        List<Boolean> settConfig = ParameterConfigParser.int2bits(type, 4);
        tm.setTopic(arr[0]);
        tm.setCluster(arr[1]);

        if (settConfig.get(3) && notNull(arr[2])) {//ttoken
            tm.setTtoken(arr[2]);
        }
        if (settConfig.get(2) && notNull(arr[3])) {//groupid
            tm.setGroupid(arr[3]);
        }
        if (settConfig.get(1) && notNull(arr[4])) {//ctoken
            tm.setCtoken(arr[4]);
        }
        if (settConfig.get(0) && notNull(arr[5])) {//handler
            String handlerStr = arr[5];

            TopicHandler handler = null;
            int handlerType = getHandlerStringType(handlerStr);
            if (handlerType == KafkaConstant.INSTANCE_NEW) {
                try {
                    handler = (TopicHandler) ReflectUtil.getInstance(handlerStr);
                } catch (Exception e) {
                    String msg = "new handler error,e {},handler {}";
                    logger.error(msg, e, handlerStr);
                }
            } else {
                //从spring获取
                logger.debug("get handler from spring {}", handlerStr);
                try {
                    handler = (TopicHandler) ApplicationContextHepler.getBeanByName(handlerStr);
                } catch (Exception e) {
                    String msg = "get handler from spring {} error {}";
                    logger.error(msg, e, handlerStr);
                }
            }
            CheckUtil.checkExistNull(handler);
            if (!ReflectClassUtil.chkAImplementB(handler, TopicHandler.class)) {
                throw new IllegalArgumentException("handler class not assign from SfTopicHandler.class");
            }
            tm.setHandler(handler);
        }
        return tm;
    }

    private static boolean notNull(String s) {
        if (s.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * map key
     *
     * @return
     */
    public String getTopicGroupid() {
        return this.topic + TOPIC_GROUPID_SEP + this.groupid;
    }

    public static String fromatKey(String topic, String groupid) {
        return topic + TOPIC_GROUPID_SEP + groupid;
    }


    /**
     * check handler type
     *
     * @param handlerStr handler string,if is a exit class name,
     * @return handler type
     */
    private static int getHandlerStringType(String handlerStr) {
        if (ReflectClassUtil.checkClassExist(handlerStr)) {
            return KafkaConstant.INSTANCE_NEW;
        } else {
            return KafkaConstant.INSTANCE_SPRING;
        }
    }


    @Override
    public String toString() {
        return "TopicMeta{" +
                "topic='" + topic + '\'' +
                ", cluster='" + cluster + '\'' +
                ", ttoken='" + ttoken + '\'' +
                ", groupid='" + groupid + '\'' +
                ", ctoken='" + ctoken + '\'' +
                ", handler=" + handler +
                ", consumerId='" + consumerId + '\'' +
                '}';
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getTopicToken() {
        return this.topic + ":" + this.ttoken;
    }

    public String getGroupIdToken() {
        return this.groupid + ":" + this.ctoken;
    }

    public String getTtoken() {
        return ttoken;
    }

    public void setTtoken(String ttoken) {
        this.ttoken = ttoken;
    }

    public TopicHandler getHandler() {
        return handler;
    }

    public void setHandler(TopicHandler handler) {
        this.handler = handler;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getCtoken() {
        return ctoken;
    }

    public void setCtoken(String token) {
        this.ctoken = token;
    }
}
