package com.atjl.validate.multithread;


import com.atjl.util.common.SystemUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadTest {

    static Map<Integer, Boolean> resMap = new ConcurrentHashMap<>();
    static Map<Integer, String> msgMap = new ConcurrentHashMap<>();

    static class BizThread implements Runnable {
        int id;
        CountDownLatch l;
        boolean isnull;

        public BizThread(int id, CountDownLatch l, boolean isnull) {
            this.l = l;
            this.id = id;
            this.isnull = isnull;
        }

        @Override
        public void run() {
            l.countDown();
            try {
                l.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ValidateForm f = ValidateFormFactory.build(MultiThreadFormEg.class);
            Map<String, String> v = new HashMap<>();
            if (this.isnull) {
                v.put("f1", null);
                f.setValue(v);
            } else {
                v.put("f1", "aaa");
                f.setValue(v);
            }
//            f.getField();
            System.out.println(id + " start,val " + v);
            boolean res = f.validate();
            String msg = f.getOneLineError();
            System.out.println(id + " res:" + res + ",msg:" + msg);
            resMap.put(id, res);
            msgMap.put(id, msg);
        }
    }

    @Test
    public void test() {
        int size = 5;
        ExecutorService e = Executors.newFixedThreadPool(size);

        CountDownLatch l = new CountDownLatch(size);

        boolean[] param = new boolean[]{
            true, false, true, true, false
        };

        ValidateFormFactory.build(MultiThreadFormEg.class);

        for (int i = 0; i < size; i++) {
            BizThread t = new BizThread(i, l, param[i]);
            e.submit(t);
        }

        SystemUtil.sleep(5000);
        for (int i = 0; i < size; i++) {
            System.out.println("id:" + i);
            System.out.println("res:" + resMap.get(i));
            System.out.println("msg:" + msgMap.get(i));
        }
    }

    @Test
    public void tt() {
        ValidateForm f = ValidateFormFactory.build(MultiThreadFormEg.class);
        Map<String, String> v = new HashMap<>();
        v.put("f1", null);
        f.setValue(v);
//        }else{
//            v.put("f1","aaa");
//            f.setValue(v);
        boolean res = f.validate();

        String msg = f.getOneLineError();
        System.out.println("res:" + res + ",msg:" + msg);

        Map<String, String> v1 = new HashMap<>();
        v1.put("f1", "22");
        f.setValue(v1);
        res = f.validate();
        msg = f.getOneLineError();
        System.out.println("res:" + res + ",msg:" + msg);

    }
}
