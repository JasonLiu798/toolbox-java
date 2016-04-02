package com.jason.threadpool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadStrategy {
    /*
    public static final String BUFPOOL_TYPE_MYFIX = "MYFiX";
    HashMap execs = new HashMap();
    public ThreadStrategy(Map ThreadPoolTypes) {
        for (Iterator it = ThreadPoolTypes.entrySet().iterator(); it.hasNext(); ) {
            //DataHandleServer,MYFiX,5,10,200000,30000
            //ListenerThreadsPool_组件名称,MYFiX,6,10,200000,30000
            Map.Entry entry = (Map.Entry) it.next();
            String param = (String) entry.getValue();
            String[] params = param.split(",");
            String threadPoolName = params[0];//name
            ExecutorService execFixed = null;
            DefaultThreadPool dp = null;

            if (params[1].equals(BUFPOOL_TYPE_MYFIX)) {
                //corePoolSize,maxPoolSize,maxTaskCount,keepAliveTime
                //AH809Server,MYFiX,12,12,200000,30000
                //AH809Server|messageConnect,MYFiX,10->4,10->8,200000->,30000
                dp = new DefaultThreadPool(
                        Integer.parseInt(params[2]),//corePoolSize	5
                        Integer.parseInt(params[3]),//maximumPoolSize	10
                        Integer.parseInt(params[4]),//maxTasksCount	200000
                        Long.parseLong(params[5]),//keepAliveTime 30*1000
                        TimeUnit.MILLISECONDS);//unit
                dp.setName(threadPoolName);
                execFixed = dp;
            } else {
                execFixed = Executors.newFixedThreadPool(Integer.parseInt(params[2]));
            }
            execs.put(threadPoolName, execFixed);
        }
    }

    public ExecutorService getThreadPoolImp(String ThreadPoolType) {
        return (ExecutorService) execs.get(ThreadPoolType);
    }

    /**
     * create thread pool
     * @param poolName
     * @param TaskCount
     *
    public void createThreadPoolImp(String poolName, int TaskCount) {
        DefaultThreadPool dp = new DefaultThreadPool(TaskCount, TaskCount, 20000, 2000l, TimeUnit.MILLISECONDS);
        dp.setName(poolName);
        execs.put(poolName, dp);
    }

    /**
     * get thread pool
     * @param ThreadPoolNum
     * @return
     *
    public ExecutorService getThreadPoolImp(int ThreadPoolNum) {
        //return (ExecutorService)execs.get(ThreadPoolType);
        int i = 0;
        ExecutorService es = null;
        for (Iterator it = execs.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            if (ThreadPoolNum == i) {
                es = (ExecutorService) entry.getValue();
                break;
            }

            i++;
        }
        return es;
    }

    public void ShutDown() {
        for (Iterator it = execs.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            ExecutorService exec = (ExecutorService) entry.getValue();
            exec.shutdownNow();
        }
    }

    public Runnable testCreaterun() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ok");
            }
        };
    }

    public static void main(String[] args) {
        HashMap para = new HashMap();
        para.put(1, "threadPoolName1,MYFiX,5,10,200000,30000");
        ThreadStrategy st = new ThreadStrategy(para);

        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        try {
            Thread.currentThread();
            Thread.sleep(4000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());
        st.getThreadPoolImp("threadPoolName1").submit(st.testCreaterun());

        byte[] b = new byte[]{(byte) 0xba, (byte) 0xba};

        String ll = "汉";

        try {
            String s = new String(b, "GBK");
            System.out.println(s);
//			String strKey=new String(b,"UTF-8");
//			byte[] b1=strKey.getBytes();

//			Charset  cs=Charset.forName("UTF-8");
//			ByteBuffer bbbb=ByteBuffer.wrap(b);
//			bbbb=cs.encode(s);
//			System.out.println(bbbb);

            //String ss=new String(b1,"UTF-8");
//
            //System.out.println(bb);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        st.ShutDown();


    }
    */


}
