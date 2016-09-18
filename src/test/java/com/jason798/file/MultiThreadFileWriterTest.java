package com.jason798.file;

import com.jason798.queue.IQueue;
import com.jason798.queue.QueueManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class MultiThreadFileWriterTest {


    @Test
    public void testIsStart() throws Exception {

    }

    @Test
    public void testWriteContent() throws Exception {
        WriteThread wr = new WriteThread();
        Thread wt = new Thread(wr);
        wt.start();

        MultiThreadFileWriter writer = new MultiThreadFileWriter("D:\\test.log", MultiThreadFileWriterHelper.DFT_QUEUNAME);
        Thread t = new Thread(writer);
        t.start();

        t.join();

    }

    public class WriteThread implements Runnable {
        public WriteThread() {
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 100) {
                IQueue queue = QueueManager.getQueue(MultiThreadFileWriterHelper.DFT_QUEUNAME);
                FileDto fd = new FileDto("D:\\test.log", "" + i);
                try {
                    queue.sendMessage(fd);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("write " + i);
                i++;
            }
        }
    }


    @Test
    public void testWriteForPathContent() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

    }

    @Test
    public void testStop() throws Exception {

    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = MultiThreadFileWriter.getClass().getMethod("init", String.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testWriteInner() throws Exception { 
    }

    @Test
    public void testDoAdd() throws Exception { 
    }

    @Test
    public void testDoWrite() throws Exception {

    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
} 
