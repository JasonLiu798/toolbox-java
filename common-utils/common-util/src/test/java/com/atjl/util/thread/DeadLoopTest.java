package com.atjl.util.thread;

import com.atjl.util.common.SystemUtil;
import com.atjl.util.thread.task.BaseTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DeadLoopTest {
	/**
	 * for test
	 */
	public BaseTask testCreaterun() {
		return new BaseTask() {
			@Override
			public void bizRun() {
				/*
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				*/
				int i=0;
//				while(true){
				System.out.println(i++);
				SystemUtil.sleep(1000);
				CountDownLatch c = new CountDownLatch(3);
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				}
			}
		};
	}
	
	@Test
	public void test()
	{
//		List<String> params = new ArrayList<>();
		
//		params.add("1,UD,12,12,30000,200000");
//		params.add("2,UD,12,12,30000,200000");
		
		ThreadPoolManager.addPool("1,UD,12,12,30000,200000");
//		ThreadPoolManager.addPool("2,UD,12,12,30000,200000");

//        ThreadPoolManager.init(params);
//        ThreadPoolManager.getPool("1");
		
//		Thread t = new Thread(this.testCreaterun());
//		BaseTask
		BaseTask b = this.testCreaterun();
		ThreadPoolManager.submit("1", b);
		
		SystemUtil.sleep(2000);
		ThreadPoolManager.shutdownNow("1");
		
		SystemUtil.sleep(2000);
		
		
		
		SystemUtil.sleepForever();
	}
}
