package com.jason798.queue;

import com.jason798.collection.CollectionHelper;
import com.jason798.config.PropertiesHelper;
import com.jason798.queue.impl.BlockingQueueEncap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * memory queue manager
 */
public class QueueManager {
	private static final Logger log = LoggerFactory.getLogger(QueueManager.class);
	// queueName -> Queue
	private static Map<String,IQueue> messageQueues = new ConcurrentHashMap<>();

	public static final String DFT_QUEUE_CONFIG_FILE = "";
	public static final String CONF_QUEUE_KEY = "queue";
	public static final String QUEUE_SEP = ",";
	public static final String SIZE_SEP = ":";

	static {
		Properties properties = PropertiesHelper.loadProperties(DFT_QUEUE_CONFIG_FILE);
		String queueStr = properties.getProperty(CONF_QUEUE_KEY);

		String[] queueArr = queueStr.split(QUEUE_SEP);


		if(CollectionHelper.isEmpty(queueArr)){
			if(log.isWarnEnabled())
				log.warn("read properties get null queue config");
		}else{
			for(String queueConfig:queueArr){
				String[] queue = queueConfig.split(SIZE_SEP);
				String queueName = queue[0];
				int queueSize = Integer.parseInt(queue[1]);
				messageQueues.put(queueName, new BlockingQueueEncap(queueSize));
				if(log.isInfoEnabled())
					log.info("bus manager add Queue:{},size:{}",queueName,String.valueOf(queueSize));
			}
		}
		if(log.isInfoEnabled())
			log.info("init bus manager");
	}

	/**
	 * add queue to manager
	 * @param queueName queue name
	 * @param queue queue entity
	 */
	public static void addQueue(String queueName,IQueue queue){
		messageQueues.put(queueName,queue);
	}

	/**
	 * get queue
	 * @param queueName queue name
	 * @return
	 * @return MessageConnect
	 */
	public static IQueue getQueue(String queueName){
		if(CollectionHelper.isEmpty(messageQueues)){
			log.warn("queue map null");
			throw new NullPointerException();
		}
		return messageQueues.get(queueName);
	}

	/**
	 * get all exist queue name
	 * @return
	 */
	public static Set<String> getQueuesName(){
		return messageQueues.keySet();
	}

	/**
	 * check queue exist
	 * @param queueName check queue name
	 * @return
	 */
	public static boolean queueExist(String queueName){
		if(CollectionHelper.isEmpty(messageQueues)){
			log.warn("queue map null");
			return false;
		}
		return messageQueues.containsKey(queueName);
	}

}

