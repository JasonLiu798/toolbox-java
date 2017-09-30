package com.atjl.configdb.service.impl;

import com.atjl.util.collection.CollectionUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ConfigDbPlainServiceImplTest {

    @Resource
    private ConfigDbPlainServiceImpl configDbPlainServiceImpl;

    
    @Test
    public void testSet() throws Exception { 
        
    }
    
    @Test
    public void testGet() throws Exception {
        String res = configDbPlainServiceImpl.get("DD-RoleTypeValue2");
        System.out.println("res:"+res);
    }
    
    @Test
    public void testGetNoCache() throws Exception {

        Map res = configDbPlainServiceImpl.getBatch(CollectionUtil.newList("DD-RoleTypeValue2","DD-RoleTypeValue1"));

        System.out.println("res:"+res);
    }
    
    @Test
    public void testGets() throws Exception { 
        
    }
    
    @Test
    public void testGetBatch() throws Exception { 
        
    }
    
      
    @Test
    public void testList2map() throws Exception { 
                /* 
                try { 
                   Method method = ConfigDbPlainServiceImpl.getClass().getMethod("list2map", List<Map<String,.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
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
