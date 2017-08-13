package com.atjl.util.config;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParameterConfigParserTest {


    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testBinaryString2Int() throws Exception { 
        
    }
    
    @Test
    public void testBooleanList2Int() throws Exception { 
        
    }

    @Test
    public void testInt2BooleaOne(){
        List<Boolean> l = ParameterConfigParser.int2bits(1,1);
        System.out.println(l);

    }
    
    @Test
    public void testInt2boolean() throws Exception { 
        int config = 15;
        List<Boolean> l = ParameterConfigParser.int2bits(config,4);
        System.out.println(l);

        config = 2;
        l = ParameterConfigParser.int2bits(config,4);
        assertEquals(false,l.get(3));
        assertEquals(false,l.get(2));
        assertEquals(true,l.get(1));
        assertEquals(false,l.get(0));

        config = 8;
        l = ParameterConfigParser.int2bits(config,4);
        assertEquals(true,l.get(3));
        assertEquals(false,l.get(2));
        assertEquals(false,l.get(1));
        assertEquals(false,l.get(0));

        config = 7;
        l = ParameterConfigParser.int2bits(config,4);
        assertEquals(false,l.get(3));
        assertEquals(true,l.get(2));
        assertEquals(true,l.get(1));
        assertEquals(true,l.get(0));

        config = 14;
        l = ParameterConfigParser.int2bits(config,4);
        assertEquals(true,l.get(3));
        assertEquals(true,l.get(2));
        assertEquals(true,l.get(1));
        assertEquals(false,l.get(0));

        //out of range
        config = 16;
        l = ParameterConfigParser.int2bits(config,4);
        assertEquals(false,l.get(3));
        assertEquals(false,l.get(2));
        assertEquals(false,l.get(1));
        assertEquals(false,l.get(0));

        config = -1;
        expectedException.expect(IllegalArgumentException.class);
        l = ParameterConfigParser.int2bits(config,4);
        assertEquals(0,l.size());

    }
    
    @Test
    public void testInt2int() throws Exception { 
        
    }
    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
} 
