package com.atjl;

import com.atjl.configdb.api.ConfigDbService;
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
public class ConfigDbServiceImplTest {

    @Resource
    private ConfigDbService configDbService;


    @Test
    public void testSet() {
//        EhCacheCacheManager cm = ApplicationContextHepler.getBean("cacheManager",EhCacheCacheManager.class);
        String key = "BaseSetting.MailSendSetting.toControl.toControl";
        String v = configDbService.get(key);
        System.out.println("get res:" + v);

//        boolean res = configDbService.set(key, "vv");
//        System.out.println("set res:" + res);
//        String v = configDbService.get(key);
//        System.out.println("get res:" + v);
    }

    @Test
    public void testGet() throws Exception {
        String v = configDbService.get("BaseSetting.MailSendSetting.toControl.toControl");
        System.out.println("res:" + v);
    }

    @Test
    public void testGets() throws Exception {
        Map res = configDbService.gets("BisArguSetting.prjType");
        System.out.println("res:" + res);
    }

    @Test
    public void testGetBatch() throws Exception {
        Map res = configDbService.getBatch(CollectionUtil.newList("BisArguSetting.prjStatus.running", "BisArguSetting.prjStage.end"));
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
            String v = configDbService.get(key[i % 3]);
            System.out.println("get res" + i + ":" + key[i % 3] + "-" + v);
            Map<String, String> m = configDbService.gets(key[i % 3]);
            System.out.println("get resm" + i + ":" + key[i % 3] + "-" + JSONFastJsonUtil.objectToJson(m));
        }

        boolean res = configDbService.set(key[0], "CC");
        System.out.println("set res:" + res);
        res = configDbService.set(key[1], "DD");
        System.out.println("set res:" + res);

        for (int i = 0; i < 10; i++) {
            String v = configDbService.get(key[i % 3]);
            System.out.println("get res" + i + ":" + key[i % 3] + "-" + v);
            Map<String, String> m = configDbService.gets(key[i % 3]);
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
