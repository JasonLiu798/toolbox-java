package com.atjl.dbtiming.service;

import com.atjl.dbtiming.helper.TimingLockHelper;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingLockHelperTest {


    @Resource
    TimingLockHelper timingLockHelper;
    
    @Test
    public void testHasLockForMutexMutexTm() throws Exception { 
        
    }
    
    @Test
    public void testHasLockMutexTm() throws Exception { 
        
    }
    
    @Test
    public void testLock() throws Exception { 
        
    }
    
    @Test
    public void testUnlock() throws Exception { 
        
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
