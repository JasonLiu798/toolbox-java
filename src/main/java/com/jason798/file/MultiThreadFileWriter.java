package com.jason798.file;

import com.jason798.collection.CollectionUtil;
import com.jason798.queue.IQueue;
import com.jason798.queue.QueueManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for multi thread write same file
 *
 * PS:
 * must new Thread and start
 *
 * Created by JasonLiu798 on 15/6/2.
 */
public class MultiThreadFileWriter implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(MultiThreadFileWriter.class);

	private boolean start = false;

	private IQueue writebuffer;

	private String destinationFile;

	private int interval = 3000;
	private int writeSize = 500;



	public MultiThreadFileWriter(String destinationFile, String queueName) {
		init(destinationFile, queueName);
	}

	private void init(String file, String queueName) {
		this.destinationFile = file;
		IQueue queue = QueueManager.getQueue(queueName);
		if (queue == null) {
			throw new NullPointerException();
		}
		this.writebuffer = queue;
	}


	public boolean isStart() {
		return start;
	}

	public void write(String content) {
		write(destinationFile, content);
	}

	public void write(List<String> content) {
		write(destinationFile, content);
	}

	/**
	 * write file to buffer
	 *
	 * @param path
	 * @param content
	 * @return
	 */
	public boolean write(String path, List<String> content) {
		FileDto fd = new FileDto(path, content);
		return writeInner(fd);
	}

	/**
	 * write to buffer
	 *
	 * @param path
	 * @param content
	 * @return
	 */
	public boolean write(String path, String content) {
		FileDto fd = new FileDto(path, content);
		return writeInner(fd);
	}

	/**
	 * write to buffer inner
	 *
	 * @param fd
	 * @return
	 */
	private boolean writeInner(FileDto fd) {
		try {
			writebuffer.sendMessage(fd);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void close() {
		start = false;
	}

	private Map<String,List<String>> memPage = new HashMap<>();

	/**
	 * file write
	 */
	@Override
	public void run() {
		start = true;
		LOG.debug("multi thread file writer started!");
		while (start) {
			try {
				long t = System.currentTimeMillis();
				Object msg = writebuffer.receiveMessage(interval);
				FileDto fd = null;
				if(msg!=null){
					fd = (FileDto)msg;
				}
				if( memPage.size()>writeSize ){
					doAdd(fd);
					doWrite();
				}else{
					doAdd(fd);
					long tt = System.currentTimeMillis();
					if( tt-t > interval && memPage.size()>0 ){
						if(LOG.isDebugEnabled()){
							LOG.debug("times up,write to file");
						}
						doWrite();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOG.debug("multi thread file writer stopped!");
	}

	private void doAdd(FileDto fd){
		if(fd!=null) {
			List<String> oldContent = memPage.get(fd.getPath());
			if (CollectionUtil.isEmpty(oldContent)) {
				memPage.put(fd.getPath(), fd.getContents());
			} else {
				oldContent.addAll(fd.getContents());
				memPage.put(fd.getPath(), oldContent);
			}
		}
	}

	private void doWrite(){
		if(LOG.isDebugEnabled()){
			LOG.debug("before write map:"+memPage);
		}
		for(Map.Entry<String,List<String>> entry:memPage.entrySet()) {
			FileUtil.append(entry.getKey(),entry.getValue());
		}
		memPage.clear();
		if(LOG.isDebugEnabled()){
			LOG.debug("after write "+memPage);
		}
	}

	public void stop(){
		start = false;
	}

}
