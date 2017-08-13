package com.atjl.config.api;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ConfigUtilTest {

    @Test
    public void testGetForKeyDftVal() throws Exception {
        String v = ConfigUtil.get("BaseSetting.MailSendSetting.toControl.toControl", "123123");
        System.out.println("res:" + v);
    }

    @Test
    public void testGetForTypeKeyDftVal() throws Exception {
        Map res = ConfigUtil.gets("BisArguSetting.prjType");
        System.out.println("res:" + res);
    }

    @Test
    public void testGetBatch() throws Exception {
        Map<String, String> param = CollectionUtil.newMap("BisArguSetting.prjStatus.running", "running");
        param.put("BisArguSetting.prjStage.end", "end");
        Map res = ConfigUtil.getBatch(param);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(res));

    }

    @Test
    public void testGets() throws Exception {

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
