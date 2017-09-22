package com.atjl.kafka.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperOperatorTest {


    
    @Test
    public void testClose() throws Exception { 
        
    }
    
    @Test
    public void testProcess() throws Exception { 
        
    }
    
    @Test
    public void testSetTopicGroupOffset() throws Exception { 
        
    }
    
    @Test
    public void testGetChildrenList() throws Exception {
        /*
        LowLevelConsumer lc = new LowLevelConsumer("kafka.sconsumer.test.properties");
        lc.init();
        String zk = null;//lc.getZkUrl("A1C","A1");
        System.out.println("zkurl "+zk);
        ZookeeperOperator zo = new ZookeeperOperator(zk);
        List<String> list = zo.getChildrenList("/consumers/A1C/offsets/A1");
        System.out.println(list);
        zo.close();
        */
        // 10.202.34.28:2182,10.202.34.29:2182,10.202.34.30:2182/kafka/other

    }
    
      
    @Test
    public void testSetData() throws Exception { 
                /* 
                try { 
                   Method method = ZookeeperOperator.getClass().getMethod("setData", String.class, String.class); 
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
} 
