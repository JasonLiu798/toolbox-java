package com.atjl.commoncli.util;

import com.atjl.commoncli.domain.CmdDomain;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class CommandParseUtilTest {


    @Test
    public void testLsParser() throws Exception {
        long t = System.currentTimeMillis();
        try {
//            Object res = null;
//            String[] opts = CollectionUtil.newArr("h", "v", "f");
//            String[] opts = CollectionUtil.newArr("--block-size=10","-h");

            String cmd = "ls -l D:\\setup";
            CmdDomain cmdObj = CommandParseUtil.preParse(cmd);

            List<String> res = CommandParseUtil.lsParser(cmdObj.getOpts());

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
