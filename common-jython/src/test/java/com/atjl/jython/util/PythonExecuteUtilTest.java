package com.atjl.jython.util;

import org.junit.*;
import org.junit.rules.ExpectedException;
import com.atjl.util.common.SystemUtil;
import org.junit.runner.RunWith;
import org.python.core.PyObject;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class PythonExecuteUtilTest {


    @Test
    public void testExecuteScript() throws Exception {
        long t = System.currentTimeMillis();
        try {

            //        interpreter.execfile("D:\\project\\python\\python-libs\\util\\string\\stringutil.py");
            String file = "D:\\project\\python\\python-libs\\util\\string\\stringutil.py";

            for (int i = 0; i < 1000; i++) {
                PyObject res = PythonExecuteUtil.executeFile(file, "startWith", (1000-i)+"abcde", i + "");
                System.out.println("res: succ " + res);
            }
            //18193/1000/1000=0.018s
//            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testExecuteFile() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
