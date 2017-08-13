package com.atjl.util.json;

import com.atjl.util.dto.TestDto;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class JSONFastJsonUtilTest {

    
    @Test
    public void testJsonToObjectJsonStr() throws Exception { 
        String json = "{\"loopId\":100,\"testInner\":{\"name\":\"data1\",\"isProvide\":true}}";
        TestDto testDto = JSONFastJsonUtil.jsonToObject(json, TestDto.class);
        System.out.println(testDto);
    }
    
    @Test
    public void testObjectToJson() throws Exception { 
        
    }
    
    @Test
    public void testJsonToObjectForJsonStrClazz() throws Exception { 
        
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
