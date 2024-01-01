package com.atjl.util.queue;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtilEx;
import com.atjl.util.queue.impl.BlockingQueueEncap;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * memory queue manager
 */
public class QueueManager {
    private static final Logger logger = LoggerFactory.getLogger(QueueManager.class);
    // queueName -> Queue
    private static Map<String, IQueue> messageQueues = new ConcurrentHashMap<>();

    static {
        /*
        if (ConfigUtil.checkPropertiesExist(DFT_QUEUE_CONFIG_FILE)) {
            Properties properties = ConfigUtil.loadProperties(DFT_QUEUE_CONFIG_FILE);

            String queueStr = properties.getProperty(CONF_QUEUE_KEY);
            if (!StringCheckUtil.isEmpty(queueStr)) {
                String queueSep = properties.getProperty(QUEUE_SEP_KEY);
                //init queue sep
                if (StringCheckUtil.isEmpty(queueSep)) {
                    queueSep = QUEUE_SEP_DFT;
                    if (LOG.isInfoEnabled())
                        LOG.info("key {} empty use {}", QUEUE_SEP_KEY, queueSep);
                }
                //init queue size sep
                String queueSizeSep = properties.getProperty(QUEUE_SIZE_SEP_KEY);
                if (StringCheckUtil.isEmpty(queueSizeSep)) {
                    queueSizeSep = QUEUE_SIZE_SEP_DFT;
                    if (LOG.isInfoEnabled())
                        LOG.info("key {} empty use {}", QUEUE_SIZE_SEP_KEY, queueSizeSep);
                }

                String[] queueArr = queueStr.split(queueSep);
                if (CollectionUtil.isEmpty(queueArr)) {
                    if (LOG.isWarnEnabled())
                        LOG.warn("read properties get null queue config");
                } else {
                    for (String queueConfig : queueArr) {
                        String[] queue = queueConfig.split(queueSizeSep);
                        String queueName = queue[0];
                        int queueSize = Integer.parseInt(queue[1]);
                        messageQueues.put(queueName, new ConcurrentLinkedBlockingQueue(queueSize));
                        if (LOG.isInfoEnabled())
                            LOG.info("bus manager add Queue:{},size:{}", queueName, String.valueOf(queueSize));
                    }
                }
                if (LOG.isInfoEnabled())
                    LOG.info("init bus manager");
            }
        }*/
    }


    /**
     * add queue to manager
     *
     * @param queueName queue name
     * @param queue     queue entity
     */
    public static void addQueue(String queueName, IQueue queue) {
        messageQueues.put(queueName, queue);
    }

    /**
     * 获取队列
     *
     * @param queueName
     * @return MessageConnect
     */
    public static IQueue getQueue(String queueName) {
        if (StringCheckUtil.isEmpty(queueName)) {
            throw new NullPointerException("queueName null");
        }
        if (MapUtils.isEmpty(messageQueues) || messageQueues.get(queueName) == null) {
            //LOG.warn("queue map null");
            //throw new NullPointerException();
            IQueue queue = new BlockingQueueEncap(QueueConstant.CONF_DFT_QUEUE_SIZE);
            messageQueues.put(queueName, queue);
            return queue;
        }
        return messageQueues.get(queueName);
    }

    /**
     * 获取所有队列名
     *
     * @return
     */
    public static Set<String> getQueuesName() {
        return messageQueues.keySet();
    }

    public static boolean queueExist(String queueName) {
        if (MapUtils.isEmpty(messageQueues)) {
            return false;
        }
        return messageQueues.containsKey(queueName);
    }

}

