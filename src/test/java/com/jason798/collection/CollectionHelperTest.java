package com.jason798.collection; 

import com.jason798.number.ProgressionHelper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollectionHelperTest {


    
    @Test
    public void testIsEmptyArr() throws Exception { 
        
    }
    
    @Test
    public void testIsNotEmptyArr() throws Exception { 
        
    }
    
    @Test
    public void testIsEmptyCollection() throws Exception { 
        
    }
    
    @Test
    public void testIsNotEmptyCollection() throws Exception { 
        
    }
    
    @Test
    public void testIsEmptyMap() throws Exception { 
        
    }
    
    @Test
    public void testGetPreNode() throws Exception { 
        
    }
    
    @Test
    public void testGetNextNode() throws Exception { 
        
    }
    
    @Test
    public void testFilterList2Size() throws Exception { 
        
    }
    
    @Test
    public void testFilterMap() throws Exception { 
        
    }
    
    @Test
    public void testFilterDelList() throws Exception { 
        
    }
    
    @Test
    public void testSet2List() throws Exception { 
        
    }
    
    @Test
    public void testArray2List() throws Exception { 
        
    }
    
    @Test
    public void testArray2Set() throws Exception { 
        
    }
    
    @Test
    public void testDeleteDuplicatItemLoop() throws Exception { 
        
    }
    
    @Test
    public void testDeleteDuplicatItemHash() throws Exception { 
        
    }
    
    @Test
    public void testPrintListC() throws Exception { 
        
    }
    
    @Test
    public void testPrintListForCName() throws Exception { 
        
    }
    
    @Test
    public void testPrintListForCLevel() throws Exception { 
        
    }
    
    @Test
    public void testPrintListForCNameLevel() throws Exception { 
        
    }
    
    @Test
    public void testSeparateList() throws Exception { 
        
    }
    
    @Test
    public void testGetInterval() throws Exception { 
        
    }
    
    @Test
    public void testCopyList() throws Exception { 
        
    }
    
    @Test
    public void testCopyForListStartEnd() throws Exception { 
        
    }
    
    @Test
    public void testResizeArray() throws Exception { 
        
    }
    
    @Test
    public void testRemoveListFirstN() throws Exception {
        List<Integer> list =  ProgressionHelper.range(10);
        System.out.println(list);
        list = CollectionHelper.removeListFirstN(list,3);
        assertEquals(7,list.size());
        for(int i=0;i<list.size();i++) {
            int num = list.get(i);
            assertEquals(i+3 , num);
        }
        System.out.println(list);
    }
    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
} 
