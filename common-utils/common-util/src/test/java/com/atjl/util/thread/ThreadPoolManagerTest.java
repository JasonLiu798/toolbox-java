package com.atjl.util.thread;

import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import com.atjl.util.thread.domain.ThreadPoolStatus;
import com.atjl.util.thread.task.BaseTask;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;


public class ThreadPoolManagerTest {
    /**
     * for test
     */
    public BaseTask testCreaterun() {
        return new BaseTask() {
            @Override
            public void bizRun() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ok");
            }
        };
    }

    @Test
    public void testInit() throws Exception {
        List<String> params = new ArrayList<>();

        params.add("1,UD,12,12,30000,200000");
        params.add("2,UD,12,12,30000,200000");

        ThreadPoolManager.init(params);
        //ThreadPoolManager.getPool("1");

        ThreadPoolManager.submit("1", this.testCreaterun());
        ThreadPoolManager.submit("1", this.testCreaterun());
        ThreadPoolManager.submit("1", this.testCreaterun());

        ThreadPoolManager.submit("2", this.testCreaterun());
        ThreadPoolManager.submit("2", this.testCreaterun());
        ThreadPoolManager.submit("2", this.testCreaterun());

        List<ThreadPoolStatus> status = ThreadPoolManager.getStatus();
        System.out.println("status:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(status)));

        SystemUtil.sleep(6000);
        status = ThreadPoolManager.getStatus();
        System.out.println("10s after status:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(status)));


        /**
         * 重建测试
         */

        ResponseDataDto bresp = ThreadPoolManager.rebuild("1,UD,5,5,30000,200000");
        System.out.println("bresp:" + JSONFastJsonUtil.objectToJson(bresp));


        ThreadPoolManager.submit("1", this.testCreaterun());
        ThreadPoolManager.submit("1", this.testCreaterun());
        ThreadPoolManager.submit("1", this.testCreaterun());
        status = ThreadPoolManager.getStatus();
        System.out.println("after rebuild status:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(status)));
        SystemUtil.sleep(6000);
        status = ThreadPoolManager.getStatus();
        System.out.println("after rebuild 10s after status:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(status)));


        SystemUtil.sleepForever();


//        ThreadPoolManager.shutdown();
//        byte[] b = new byte[]{(byte) 0xba, (byte) 0xba};
//
//        String ll = "汉";

//        try {
//            String s = new String(b, "GBK");
//            System.out.println(s);
////			String strKey=new String(b,"UTF-8");
////			byte[] b1=strKey.getBytes();
//
////			Charset  cs=Charset.forName("UTF-8");
////			ByteBuffer bbbb=ByteBuffer.wrap(b);
////			bbbb=cs.encode(s);
////			System.out.println(bbbb);
//
//            //String ss=new String(b1,"UTF-8");
////
//            //System.out.println(bb);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void testDestroy() throws Exception {

    }

    @Test
    public void testGetThreadPoolImpThreadPoolType() throws Exception {

    }

    @Test
    public void testCreateThreadPoolImp() throws Exception {

    }

    @Test
    public void testGetThreadPoolImpThreadPoolNum() throws Exception {

    }

    @Test
    public void testShutdown() throws Exception {

    }

    @Test
    public void testMain() throws Exception {

    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
