package com.atjl.util.thread;


import com.atjl.util.common.SystemUtil;
import com.atjl.util.thread.task.BaseTask;

import java.util.ArrayList;
import java.util.List;

public class StopTest {


    public static BaseTask crt() {
        return new BaseTask() {
            @Override
            public void bizRun() {

                long i = 0;
                while (true) {
                    System.out.println(i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                String s = map.get("1");
//                System.out.println("res:res");
            }
        };
    }

    public static void main(String[] args) {
        List<String> params = new ArrayList<>();

        params.add("1,UD,12,12,30000,200000");
        //params.add("2,UD,12,12,30000,200000");

        ThreadPoolManager.init(params);
        //ThreadPoolManager.getPool("1");
//        for (int i = 0; i < 20; i++) {
        BaseTask b = crt();
        Thread t = new Thread(b);
        t.start();
//        Future f = ThreadPoolManager.submit("1", b);
        SystemUtil.sleep(4000);
        t.interrupt();
        boolean alv = t.isAlive();
        SystemUtil.sleep(2000);
        System.out.println("is alive int " + alv);
        t.stop();
//        f.cancel(true);
        SystemUtil.sleep(2000);
        System.out.println("is alive stop " + alv);


        SystemUtil.sleep(1000000);
    }
}
