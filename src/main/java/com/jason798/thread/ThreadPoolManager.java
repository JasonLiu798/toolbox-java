package com.jason798.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolManager {
	private static final Logger log = LoggerFactory.getLogger(ThreadPoolManager.class);
    public static final String BUFPOOL_TYPE_MYFIX = "Fix";
	public static final String PARAM_SEP = ",";

    ConcurrentHashMap<String,Executor> execs = new ConcurrentHashMap();
    public ThreadPoolManager(Map ThreadPoolTypes) {
        for (Iterator it = ThreadPoolTypes.entrySet().iterator(); it.hasNext(); ) {
            //Server,Fix,5,10,200000,30000
            Map.Entry entry = (Map.Entry) it.next();
            String param = (String) entry.getValue();
            String[] params = param.split(PARAM_SEP);
            String threadPoolName = params[0];//name
            ExecutorService execFixed = null;
            ThreadPoolExecutor dp = null;

            if (params[1].equals(BUFPOOL_TYPE_MYFIX)) {
                //corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount
                //Server,Fix,12,12,30000,200000
                //Server|messageConnect,MYFiX,10->4,10->8,200000->,30000
                dp = new ThreadPoolExecutor(
                        Integer.parseInt(params[2]),//corePoolSize	5
                        Integer.parseInt(params[3]),//maximumPoolSize	10
                        Long.parseLong(params[4]),//maxTasksCount	200000
						TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>(Integer.parseInt(params[5]))
				);
                execFixed = dp;
            } else {
                execFixed = Executors.newFixedThreadPool(Integer.parseInt(params[2]));
            }
            execs.put(threadPoolName, execFixed);
			log.debug("thread {} has been created and add to the map",threadPoolName);
        }
    }

    public ExecutorService getThreadPoolImp(String ThreadPoolType) {
        return (ExecutorService) execs.get(ThreadPoolType);
    }

    /**
     * @param poolName
     * @param TaskCount
     */
    public void createThreadPoolImp(String poolName, int TaskCount) {
        ThreadPoolExecutor dp = new ThreadPoolExecutor(TaskCount, TaskCount, 20000l,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        execs.put(poolName, dp);
    }

    /**
     * get com.jason798.thread pool
     * @param ThreadPoolNum
     * @return
     */
    public ExecutorService getThreadPoolImp(int ThreadPoolNum) {
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

    public void shutdown() {
        for (Iterator it = execs.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            ExecutorService exec = (ExecutorService) entry.getValue();
            exec.shutdownNow();
        }
    }

	/**
	 * for test
	 * @return
	 */
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
		//para.put(1, "threadPoolName1,MYFiX,5,10,200000,30000");
		para.put(1, "1,Fix,12,12,30000,200000");
        ThreadPoolManager st = new ThreadPoolManager(para);

        st.getThreadPoolImp("1").submit(st.testCreaterun());
        st.getThreadPoolImp("1").submit(st.testCreaterun());
        try {
            Thread.currentThread();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        st.getThreadPoolImp("1").submit(st.testCreaterun());
        st.getThreadPoolImp("1").submit(st.testCreaterun());
        st.getThreadPoolImp("1").submit(st.testCreaterun());
        st.getThreadPoolImp("1").submit(st.testCreaterun());
        st.getThreadPoolImp("1").submit(st.testCreaterun());
        st.getThreadPoolImp("1").submit(st.testCreaterun());

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
		st.shutdown();
    }


}
