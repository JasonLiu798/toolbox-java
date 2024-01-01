package com.atjl.util;

import com.atjl.util.config.ConfigIntParser;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import java.lang.reflect.Field;
import java.util.List;


public class PlainTest {


    private int a = 1;

    public static void main(String[] args) {
        System.out.println(new PlainTest().a);

    }


    @Test
    public void testAll() {
        Double d = 0d;
        Double d1 = 0d;

        System.out.println(d == d1);
    }


    @Test
    public void test() {
        int COUNT_BITS = Integer.SIZE - 3;

        int CAPACITY = (1 << COUNT_BITS) - 1;
        System.out.println("CAPACITY " + CAPACITY);
        List<Boolean> l = ConfigIntParser.int2bits(CAPACITY, 32);
        System.out.println("CAPACITY " + l);

        int running = -1 << COUNT_BITS;
        System.out.println("count " + COUNT_BITS);
        System.out.println("RUNNING " + running);
        int res = ctlOf(running, 0);

        l = ConfigIntParser.int2bits(running, 32);
        System.out.println("RUNNING " + l);

        int aaa = res & CAPACITY;
        System.out.println("aaa " + aaa);
        l = ConfigIntParser.int2bits(aaa, 32);
        System.out.println("aaa " + l);

        int SHUTDOWN = 1 << COUNT_BITS;
        System.out.println("SHUTDOWN " + SHUTDOWN);
        l = ConfigIntParser.int2bits(SHUTDOWN, 32);
        System.out.println("SHUTDOWN " + l);

        int TIDYING = 2 << COUNT_BITS;
        System.out.println("TIDYING " + TIDYING);
        l = ConfigIntParser.int2bits(TIDYING, 32);
        System.out.println("TIDYING " + l);


        int TERMINATED = 3 << COUNT_BITS;
        System.out.println("TERMINATED " + TERMINATED);
        l = ConfigIntParser.int2bits(TERMINATED, 32);
        System.out.println("TERMINATED " + l);


    }


    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    /*

    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);

        } catch (Exception e) {
        }
        return null;
    }

    @Test
    public void unsafe() {
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
		*
//		unsafe.park(false, 0);
        unsafe.park(false, 3000 * 1000 * 1000l);

        System.out.println("SUCCESS!!!");
    }

    @Test
    public void unsafeUnpark() {
        Unsafe unsafe = getUnsafe();

        Thread currThread = Thread.currentThread();

        unsafe.unpark(currThread);
        unsafe.unpark(currThread);
        unsafe.unpark(currThread);

        unsafe.park(false, 0);

        System.out.println("SUCCESS!!!");

    }


    public static void test2() {
        int MAXIMUM_CAPACITY = Integer.MAX_VALUE;
        int c = 1073234824;
        //4611686018427387904
        //4611686018427387904
        // 2147483648
        // 1142344648
        // 1073741824
        // 1073234824
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int res = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        System.out.println("res:" + res + ",max:" + MAXIMUM_CAPACITY);
    }*/

    public static void print() {
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        char c = 'A';
        for (int i = 0; i < 16; i++) {
            m.put(String.valueOf(c), String.valueOf(c) + String.valueOf(c));
            c++;
        }

        m.get(String.valueOf(c));


        int tab = 0;
        if (tab == 0) {
            tab = 1;
            System.out.println("set 1");
        } else if (tab == 1) {
            System.out.println("1");
        }
        System.out.println("end");
    }

    public static void print11() {
        int i = 0;
        int n = 16;
        int m = n << 1;
        for (; i < 100; i++) {
            int x = (n - 1) & i;
            int y = (m - 1) & i;
            System.out.println(i + ",x:" + x + ",y:" + y);
        }
        System.out.println("-----------------");

    }


    public static void print1() {
        int i = 0;
        for (; i < 1000 * 1000; i++) {
            int h = i ^ (i >>> 16);
            System.out.println(i + ",h:" + h);
        }
    }
//    public static void main(String[] args) {
//        testTimed();
//        System.out.println("test ");
//    }


    public static void testTimed() {
        System.out.println("timed called ");
    }
}
