package com.jason798.file;

import com.jason798.queue.IQueue;
import com.jason798.queue.QueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

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

	/**
	 * file write
	 */
	@Override
	public void run() {
		start = true;
		LOG.debug("multi thread file writer started!");
		while (start) {
			try {
				FileDto fd = (FileDto) writebuffer.receiveMessage();
				FileHelper.writeLines2File(fd.getPath(), fd.getContents());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOG.debug("multi thread file writer stopped!");
	}

	public void stop(){
		start = false;
	}

}
