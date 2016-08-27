package com.jason798.queue.impl;

import com.jason798.queue.IQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by async on 2016/8/28.
 */
public class BlockingQueueEncap<T> implements IQueue<T> {
	/**
	 * block queue
	 */
	private BlockingQueue<T> linkq;

	/**
	 * default max size 65535
	 */
	private static int maxSize = 65535;

	/**
	 * construct method
	 */
	public BlockingQueueEncap(){
		linkq = new ArrayBlockingQueue<>(maxSize);
	}

	/**
	 * construct method
	 * @param imaxSize maxSize
	 */
	public BlockingQueueEncap(int imaxSize){
		maxSize = imaxSize;
		linkq = new ArrayBlockingQueue<>(maxSize);
	}

	/**
	 * send one message
	 *
	 * @param message
	 * @throws InterruptedException
	 * @return void
	 * @author JasonLiu
	 * @createtime Jun 29, 2015 1:05:16 PM
	 */
	public void sendMessage(T message) throws InterruptedException {
		if (message != null) {
			linkq.put(message);
		}
	}

	/**
	 * receive message
	 *
	 * @return
	 * @throws InterruptedException
	 * @return Object
	 * @author JasonLiu
	 * @createtime Jun 29, 2015 1:08:56 PM
	 */
	@Override
	public T receiveMessage() throws InterruptedException {
		T message = null;
		message = linkq.take();
		return message;
	}

	/**
	 * get now size
	 *
	 * @return
	 * @return int
	 * @author JasonLiu
	 * @createtime Jun 29, 2015 1:08:48 PM
	 */
	public int getCount(){
		return linkq.size();
	}

	/**
	 * chk producer buffer
	 *
	 * @return
	 * @return boolean
	 * @author JasonLiu
	 * @createtime Jun 29, 2015 12:52:49 PM
	 */
	public boolean ifMaxCount(){
		return (maxSize - getCount())<10;
	}

}

