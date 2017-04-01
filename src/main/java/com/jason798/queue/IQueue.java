package com.jason798.queue;

/**
 */
public interface IQueue <T> {

	/**
	 * send one message
	 *
	 * @param message
	 * @throws InterruptedException
	 * @return void
	 */
	void sendMessage(T message) throws InterruptedException;

	/**
	 * get now size
	 *
	 * @return
	 * @return int
	 */
	int getCount();

	/**
	 * receive message
	 * @return
	 * @throws InterruptedException
	 */
	T receiveMessage() throws InterruptedException;

	/**
	 * wait mstime
	 * @param mstime
	 * @return
	 * @throws InterruptedException
	 */
	T receiveMessage(long mstime) throws InterruptedException;

	/**
	 * chk producer buffer
	 *
	 * @return
	 * @return boolean
	 */
	boolean ifMaxCount();
}
