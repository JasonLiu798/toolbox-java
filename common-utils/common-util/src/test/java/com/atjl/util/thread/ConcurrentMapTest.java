package com.atjl.util.thread;


import com.atjl.util.common.SystemUtil;
import com.atjl.util.thread.task.BaseTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentMapTest {


    static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    static CountDownLatch c;

    public BaseTask crt() {
        return new BaseTask() {
            @Override
            public void bizRun() {
                c.countDown();
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String s = map.get("1");
                System.out.println("res:" + s);
            }
        };
    }


    @Test
    public void test() {
        c = new CountDownLatch(20);
        map.put("1", "1");
        List<String> params = new ArrayList<>();

        params.add("1,UD,12,12,30000,200000");
        //params.add("2,UD,12,12,30000,200000");

        ThreadPoolManager.init(params);
        //ThreadPoolManager.getPool("1");
        for (int i = 0; i < 20; i++) {
            ThreadPoolManager.submit("1", this.crt());
        }

        SystemUtil.sleep(1000000);


    }

}
