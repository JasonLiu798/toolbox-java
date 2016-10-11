package com.jason798.file;

import com.jason798.queue.QueueManager;
import com.jason798.queue.impl.BlockingQueueEncap;

import java.util.List;
import java.util.Queue;

/**
 * Created by JasonLiu798 on 16/6/2.
 */
public class MultiThreadFileWriterUtil {

	private MultiThreadFileWriter multiThreadFileWriter;
	public static final String DFT_PATH= "/opt/logs/test.log";
	public static final String DFT_QUEUNAME= "fileQ";
	private static final int DFT_QUEUE_SIZE = 100000;

	/**
	 * construct
	 */
	public MultiThreadFileWriterUtil() {
		init(DFT_PATH,DFT_QUEUNAME);
	}
	public MultiThreadFileWriterUtil(String writeFile) {
		init(writeFile,DFT_QUEUNAME);
	}

	public MultiThreadFileWriterUtil(boolean autostart, String writeFile, String queueName){
		init(writeFile,queueName);
		if(autostart){
			start();
		}
	}

	public MultiThreadFileWriterUtil(boolean autoStart) {
		init(DFT_PATH,DFT_QUEUNAME);
		if(autoStart){
			start();
		}
	}

	/**
	 * init method
	 * @param writeFile write file
	 * @param queueName queue name
	 */
	private void init(String writeFile, String queueName){
		boolean exist = QueueManager.queueExist(queueName);
		if(!exist){
			QueueManager.addQueue(queueName, new BlockingQueueEncap(DFT_QUEUE_SIZE));
		}
		this.multiThreadFileWriter = new MultiThreadFileWriter(writeFile,queueName);
	}

	public void start(){
		if( !multiThreadFileWriter.isStart()){
			Thread thread = new Thread(multiThreadFileWriter);
			thread.start();
		}
	}

	public void write(String contnet){
		multiThreadFileWriter.write(contnet);
	}

	public void write(List<String> contnet){
		multiThreadFileWriter.write(contnet);
	}

	public void write(String path,String contnet){
		multiThreadFileWriter.write(path,contnet);
	}

	public void stopThread(){
		multiThreadFileWriter.close();
	}
}


