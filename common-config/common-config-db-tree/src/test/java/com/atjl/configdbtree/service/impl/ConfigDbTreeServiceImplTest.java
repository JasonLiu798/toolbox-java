package com.atjl.configdbtree.service.impl;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ConfigDbTreeServiceImplTest {
    @Resource
    private ConfigDbTreeServiceImpl configDbTreeServiceImpl;

    @Test
    public void testSet() throws Exception {
        String key = "BaseSetting.MailSendSetting.toControl.toControl";
        String v = configDbTreeServiceImpl.get(key);
        System.out.println("get res:" + v);
    }

    @Test
    public void testGet() throws Exception {
        String v = configDbTreeServiceImpl.get("BaseSetting.MailSendSetting.toControl.toControl");
        System.out.println("res:" + v);
    }

    @Test
    public void testGets() throws Exception {
        Map res = configDbTreeServiceImpl.gets("BisArguSetting.prjType");
        System.out.println("res:" + res);
    }

    @Test
    public void testGetBatch() throws Exception {
        Map res = configDbTreeServiceImpl.getBatch(CollectionUtil.newList("BisArguSetting.prjStatus.running", "BisArguSetting.prjStage.end"));
        System.out.println("res:" + res);
    }


    @Test
    public void testSetCache() {
        String[] key = {
                "BaseSetting.MailSendSetting",
                "BaseSetting.MailSendSetting.toControl",
                "BaseSetting.MailSendSetting.toControl.toControl"
        };
        for (int i = 0; i < 10; i++) {
            String v = configDbTreeServiceImpl.get(key[i % 3]);
            System.out.println("get res" + i + ":" + key[i % 3] + "-" + v);
            Map<String, String> m = configDbTreeServiceImpl.gets(key[i % 3]);
            System.out.println("get resm" + i + ":" + key[i % 3] + "-" + JSONFastJsonUtil.objectToJson(m));
        }

        boolean res = configDbTreeServiceImpl.set(key[0], "CC");
        System.out.println("set res:" + res);
        res = configDbTreeServiceImpl.set(key[1], "DD");
        System.out.println("set res:" + res);

        for (int i = 0; i < 10; i++) {
            String v = configDbTreeServiceImpl.get(key[i % 3]);
            System.out.println("get res" + i + ":" + key[i % 3] + "-" + v);
            Map<String, String> m = configDbTreeServiceImpl.gets(key[i % 3]);
            System.out.println("get resm" + i + ":" + key[i % 3] + "-" + JSONFastJsonUtil.objectToJson(m));
        }
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
