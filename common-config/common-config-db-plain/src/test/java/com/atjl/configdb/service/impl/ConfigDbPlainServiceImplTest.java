package com.atjl.configdb.service.impl; 

import org.junit.*;
import org.junit.rules.ExpectedException;
import com.atjl.util.common.SystemUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ConfigDbPlainServiceImplTest {

    @Resource
    ConfigDbPlainServiceImpl configDbPlainService;

    
    @Test
    public void testSet() throws Exception { 
        
    }
    
    @Test
    public void testGet() throws Exception {
//        configDbPlainService.get

    }
    
    @Test
    public void testGetNoCache() throws Exception { 
        
    }
    
    @Test
    public void testGetBatch() throws Exception { 
        
    }
    
    @Test
    public void testGetBatchNoCache() throws Exception { 
        
    }
    
    @Test
    public void testGets() throws Exception { 
        
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
        
        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
