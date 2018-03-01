package com.atjl.util.reflect;

import com.atjl.util.dto.TestDtoChild;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class CpDozerTest {

    @Test
    public void testCopyDz() {
        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);


        TestDtoChild dest = new TestDtoChild();
        System.out.println("res bf:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res bf:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));

        ReflectFieldUtil.copyFieldUseDz(src, dest);
        System.out.println("res af:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res af:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));
    }


    @Test
    public void testCopyDzExclude() {
        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);

        TestDtoChild dest = new TestDtoChild();
        System.out.println("res bf src:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res bf tgt:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));

//        ReflectFieldUtil.copyFieldUseDz(src, dest, CollectionUtil.newList("childField"));
        System.out.println("res af src:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res af tgt:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));

        TestDtoChild dest2 = new TestDtoChild();
        ReflectFieldUtil.copyFieldUseDz(src, dest2);
        System.out.println("res af2 src:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res af2 tgt:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest2)));

    }

    /**
     *
     */
    @Test
    public void performance() {
        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);

        int cnt = 10 * 1000;
//        int cnt = 100 * 1000;
        TestDtoChild init = new TestDtoChild();
        ReflectFieldUtil.copyFieldUseDz(src, init);
        ReflectFieldUtil.copyField(src, init);

        long t = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++) {
            TestDtoChild dest = new TestDtoChild();
            ReflectFieldUtil.copyField (src, dest);
        }
//        RunTimeUtil.clear();
        long t1 = System.currentTimeMillis();
        long c1 = t1 - t;

        for (int i = 0; i < cnt; i++) {
            TestDtoChild dest = new TestDtoChild();
            ReflectFieldUtil.copyFieldUseDz (src, dest);
        }

        long t2 = System.currentTimeMillis();
        long c2 = t2 - t1;
//        res += RunTimeUtil.getFmtTimeForStdout();

        // 5W
        //R 950,D 837
        //R 785,D 1386
        //R 1475,D 1019
        //R 1030,D 1136
        // 1W
        // R 173,D 554
        // R 325,D 1183
        System.out.println("R "+c1+",D "+c2);
        // 5W
        // D 1674,R 671
        // D 1173,R 596
//        System.out.println("D " + c1 + ",R " + c2);

        /**
         1W
         dozer then reflect
         D total cost: 866 ms,default
         R total cost: 736 ms,default
         D total cost: 980 ms,default
         R total cost: 700 ms,default

         reflect then dozer
         R total cost: 1098 ms,default
         D  total cost: 338 ms,default
         R total cost: 916 ms,default
         D total cost: 410 ms,default
         R total cost: 1151 ms,default
         D total cost: 712 ms,default

         5W
         D total cost: 1525 ms,default
         R total cost: 2159 ms,default

         D total cost: 993 ms,default
         R total cost: 1957 ms,default

         R total cost: 2963 ms,default
         D total cost: 636 ms,default
         R total cost: 2306 ms,default
         D total cost: 741 ms,default
         */

        /*
        TestDtoChild dest = new TestDtoChild();
        System.out.println("res bf:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res bf:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));

        ReflectFieldUtil.copyFieldUseDz(src, dest);
        System.out.println("res af:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src)));
        System.out.println("res af:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));
        */
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
