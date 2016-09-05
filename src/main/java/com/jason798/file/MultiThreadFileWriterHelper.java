package com.jason798.file;

import com.jason798.queue.QueueManager;
import com.jason798.queue.impl.BlockingQueueEncap;

import java.util.List;
import java.util.Queue;

/**
 * Created by JasonLiu798 on 16/6/2.
 */
public class MultiThreadFileWriterHelper {

	private MultiThreadFileWriter multiThreadFileWriter;
	public static final String DFT_PATH= "/opt/logs/test.log";
	public static final String DFT_QUEUNAME= "fileQ";

	/**
	 * construct
	 */
	public MultiThreadFileWriterHelper() {
		init(DFT_PATH,DFT_QUEUNAME);
	}
	public MultiThreadFileWriterHelper(String defaultFile) {
		init(defaultFile,DFT_QUEUNAME);
	}
	public MultiThreadFileWriterHelper(String defaultFile,String queueName){
		init(defaultFile,queueName);
	}

	private void init(String defaultFile,String queueName){
		boolean exist = QueueManager.queueExist(queueName);
		if(!exist){
			QueueManager.addQueue(queueName, new BlockingQueueEncap());
		}
		this.multiThreadFileWriter = new MultiThreadFileWriter(defaultFile,queueName);
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

