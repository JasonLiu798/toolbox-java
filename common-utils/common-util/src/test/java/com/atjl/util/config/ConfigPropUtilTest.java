package com.atjl.util.config;

import com.atjl.util.collection.CollectionUtilEx;
import com.atjl.util.file.FileUtilEx;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;


public class ConfigPropUtilTest {


    @Test
    public void testCheckPropertiesExist() throws Exception {

        List<String> f = FileUtilEx.ls("D:\\project\\java\\admin.zip");
        System.out.println("res:" + f);

    }

    @Test
    public void testInitPropFromFile() throws Exception {
        String file = "test";
        String key = "test";
        String res = ConfigPropUtil.get(key, file);
        System.out.println("res:" + res);
    }

    @Test
    public void testGetKey() throws Exception {
        String file = "test";
        List<String> keys = CollectionUtilEx.newList("test1", "test2");

        ConfigPropUtil.init(file, ConfigPropConstant.TP_CLASSPATH);

        Map<String, String> res = ConfigPropUtil.getBatch(file, keys);

        System.out.println("res:" + res);
    }


    @Test
    public void testGetBatchForFileNameKeys() throws Exception {

    }

    @Test
    public void testGetBatchKeys() throws Exception {

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
