package com.atjl.config.api;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ConfigDbUtilTest {


    @Test
    public void testGet() throws Exception {
        String key = "DD-RoleTypeValue1";
        String v = ConfigDbUtil.get(ConfigDbConstant.CONF_DB_PLAIN_SERVICE, key);
        System.out.println("res:" + v);
//
    }

    @Test
    public void testGets() throws Exception {
//        Map res = ConfigDbUtil.gets("BisArguSetting.prjType");
//        System.out.println("res:" + res);
    }

    @Test
    public void testGetBatch() throws Exception {
//        Map res = ConfigDbUtil.getBatch(ConfigDbConstant.CONF_DB_PLAIN_SERVICE, CollectionUtil.newList("DD-RoleTypeValue1", "DD-RoleTypeValue2"));
//        System.out.println("res:" + res);
    }


    @Test
    public void testGetConfDbService() throws Exception {
    }

    @Test
    public void testPreChk() throws Exception {
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
