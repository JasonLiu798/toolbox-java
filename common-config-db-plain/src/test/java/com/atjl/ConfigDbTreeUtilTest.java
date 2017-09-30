package com.atjl;

//import com.atjl.configdb.api.ConfigDbConstant;
//import com.atjl.configdb.api.ConfigDbUtil;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ConfigDbTreeUtilTest {


    @Test
    public void testGet() throws Exception {
        //
//        String key = "BaseSetting.DD-RoleTypeValue1";
//        String v = ConfigDbUtil.get(ConfigDbConstant.CONF_DB_TREE_SERVICE, key);
//        System.out.println("res:" + v);

    }

    @Test
    public void testGets() throws Exception {
//        Map res = ConfigDbUtil.gets("BisArguSetting.test");
//        System.out.println("res:" + res);
    }

    @Test
    public void testGetBatch() throws Exception {
//        Map res = ConfigDbUtil.getBatch(ConfigDbConstant.CONF_DB_TREE_SERVICE, CollectionUtil.newList("BaseSetting.DD-RoleTypeValue1", "BaseSetting.DD-RoleTypeValue2"));
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
