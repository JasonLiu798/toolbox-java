package com.atjl.util.config; 

import com.atjl.util.common.ReflectUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigModelUtilTest {

    @Test
    public void testGenerateConfigModelForFilePathTypeClz() throws Exception {
        TestConfig tc = ConfigModelUtil.generateConfigModel("test", ConfigPropConstant.TP_CLASSPATH, TestConfig.class);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(tc));
    }


    @Test
    public void testPrefix() throws Exception {
        TestConfig tc = ConfigModelUtil.generateConfigModel("test", ConfigPropConstant.TP_CLASSPATH, TestConfig.class, "test.prefix", null);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(tc));
    }

    @Test
    public void testGenerateConfigModelForFilePathTypeClzPrefixKeyModelExecludeFields() throws Exception {

        List<Field> fieldList = ReflectUtil.getFields(TestConfig.class, ReflectUtil.GetClzOpt.ALL, null, null);
        List<String> fields = ReflectUtil.filed2string(fieldList);

        System.out.println("res:" + fields);
    }

    /**
     *
     *     private String p1;
     private Long pl;
     private Integer pi;
     private Short ps;
     private Double pd;
     private Float pf;
     private int pri;
     private Byte pb=12;
     *
     */

    @Test
    public void map() throws Exception {
        Map<String,String> config = new HashMap<>();
        config.put("p1","234324");
        config.put("pi","2344");
        config.put("pd","234.324");
        config.put("ps","");

        TestConfig tc = ConfigModelUtil.generateConfigModel(config, TestConfig.class,true);

        System.out.println("res:" + JSONFastJsonUtil.objectToJson(tc));
    }
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
    
    @BeforeClass
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
