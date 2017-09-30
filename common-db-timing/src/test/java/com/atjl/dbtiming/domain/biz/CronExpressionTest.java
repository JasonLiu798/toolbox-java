package com.atjl.dbtiming.domain.biz;

import com.atjl.util.cron.CronExpression;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Date;


public class CronExpressionTest {


    
    @Test
    public void testIsSatisfiedBy() throws Exception {
//        String crtPjr = "0 0 8 30 11 ?";
        String clrTask = "0 0 0 */1 * ?";
        CronExpression e = new CronExpression(clrTask);
        Date next = e.getNextValidTimeAfter(new Date());
        System.out.println("res:"+next);



    }
    
    @Test
    public void testGetNextValidTimeAfter() throws Exception { 
        
    }
    
    @Test
    public void testGetNextInvalidTimeAfter() throws Exception { 
        
    }
    
    @Test
    public void testGetTimeZone() throws Exception { 
        
    }
    
    @Test
    public void testSetTimeZone() throws Exception { 
        
    }
    
    @Test
    public void testToString() throws Exception { 
        
    }
    
    @Test
    public void testIsValidExpression() throws Exception { 
        
    }
    
    @Test
    public void testValidateExpression() throws Exception { 
        
    }
    
    @Test
    public void testBuildExpression() throws Exception { 
        
    }
    
    @Test
    public void testStoreExpressionVals() throws Exception { 
        
    }
    
    @Test
    public void testCheckNext() throws Exception { 
        
    }
    
    @Test
    public void testGetCronExpression() throws Exception { 
        
    }
    
    @Test
    public void testGetExpressionSummary() throws Exception { 
        
    }
    
    @Test
    public void testGetExpressionSetSummarySet() throws Exception { 
        
    }
    
    @Test
    public void testGetExpressionSetSummaryList() throws Exception { 
        
    }
    
    @Test
    public void testSkipWhiteSpace() throws Exception { 
        
    }
    
    @Test
    public void testFindNextWhiteSpace() throws Exception { 
        
    }
    
    @Test
    public void testAddToSet() throws Exception { 
        
    }
    
    @Test
    public void testGetSet() throws Exception { 
        
    }
    
    @Test
    public void testGetValue() throws Exception { 
        
    }
    
    @Test
    public void testGetNumericValue() throws Exception { 
        
    }
    
    @Test
    public void testGetMonthNumber() throws Exception { 
        
    }
    
    @Test
    public void testGetDayOfWeekNumber() throws Exception { 
        
    }
    
    @Test
    public void testGetTimeAfter() throws Exception { 
        
    }
    
    @Test
    public void testSetCalendarHour() throws Exception { 
        
    }
    
    @Test
    public void testGetTimeBefore() throws Exception { 
        
    }
    
    @Test
    public void testGetFinalFireTime() throws Exception { 
        
    }
    
    @Test
    public void testIsLeapYear() throws Exception { 
        
    }
    
    @Test
    public void testGetLastDayOfMonth() throws Exception { 
        
    }
    
    @Test
    public void testClone() throws Exception { 
        
    }
    
      
    @Test
    public void testCheckIncrementRange() throws Exception { 
                /* 
                try { 
                   Method method = CronExpression.getClass().getMethod("checkIncrementRange", int.class, int.class, int.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            }
      
    @Test
    public void testReadObject() throws Exception { 
                /* 
                try { 
                   Method method = CronExpression.getClass().getMethod("readObject", java.io.ObjectInputStream.class); 
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
