package com.atjl.util;

import com.atjl.util.config.ConfigIntParser;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.List;

public class PlainTest {
	
	
	
	
	@Test
	public void test(){
		int COUNT_BITS = Integer.SIZE - 3;
		
		int CAPACITY   = (1 << COUNT_BITS) - 1;
		System.out.println("CAPACITY "+CAPACITY);
		List<Boolean> l =  ConfigIntParser.int2bits(CAPACITY,32);
		System.out.println("CAPACITY "+l);
		
		int running = -1 << COUNT_BITS;
		System.out.println("count "+COUNT_BITS);
		System.out.println("RUNNING "+running);
		int res = ctlOf(running, 0);
		
		l =  ConfigIntParser.int2bits(running,32);
		System.out.println("RUNNING "+l);
		
		int aaa = res & CAPACITY;
		System.out.println("aaa "+aaa);
		l =  ConfigIntParser.int2bits(aaa,32);
		System.out.println("aaa "+l);
		
		int SHUTDOWN = 1 << COUNT_BITS;
		System.out.println("SHUTDOWN "+SHUTDOWN);
		l =  ConfigIntParser.int2bits(SHUTDOWN,32);
		System.out.println("SHUTDOWN "+l);
		
		int TIDYING    =  2 << COUNT_BITS;
		System.out.println("TIDYING "+TIDYING);
		l =  ConfigIntParser.int2bits(TIDYING,32);
		System.out.println("TIDYING "+l);
		
		
		int TERMINATED =  3 << COUNT_BITS;
		System.out.println("TERMINATED "+TERMINATED);
		l =  ConfigIntParser.int2bits(TERMINATED,32);
		System.out.println("TERMINATED "+l);
		
		
	}
	
	
	private static int ctlOf(int rs, int wc) {
		return rs | wc;
	}
	
	public static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe)field.get(null);
			
		} catch (Exception e) {
		}
		return null;
	}
	
	@Test
	public void unsafe(){
		Unsafe unsafe = getUnsafe();//sun.misc.Unsafe.getUnsafe();
		
		Thread currThread = Thread.currentThread();
		/*
		new Thread(()->{
			try {
				Thread.sleep(3000);
				currThread.interrupt();
				//unsafe.unpark(currThread);
			} catch (Exception e) {}
		}).start();
		*/
//		unsafe.park(false, 0);
		unsafe.park(false, 3000 * 1000 *1000l);
		
		System.out.println("SUCCESS!!!");
	}
	@Test
	public void unsafeUnpark(){
		Unsafe unsafe = getUnsafe();
		
		Thread currThread = Thread.currentThread();
		
		unsafe.unpark(currThread);
		unsafe.unpark(currThread);
		unsafe.unpark(currThread);
		
		unsafe.park(false, 0);
		
		System.out.println("SUCCESS!!!");
		
	}

    public static void main(String[] args) {
        testTimed();
        System.out.println("test ");
    }


    public static void testTimed(){
        System.out.println("timed called ");
    }
}
