package com.atjl.util.config;

import com.atjl.util.collection.CollectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConfigIntParserTest {


    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBinaryString2Int() throws Exception {

    }

    @Test
    public void testBooleanList2Int() throws Exception {
        List<Boolean> l = CollectionUtil.newList(true, false, false);
        int opt = ConfigIntParser.booleanList2Int(l);
        System.out.println("opt:" + opt);
        List<Boolean> pl = ConfigIntParser.int2bits(opt, 3);
        System.out.println("res:" + pl);


    }

    @Test
    public void testInt2BooleaOne() {
        List<Boolean> l = ConfigIntParser.int2bits(1, 1);
        System.out.println(l);

    }

    @Test
    public void testInt2boolean() throws Exception {
        int config = 15;
        List<Boolean> l = ConfigIntParser.int2bits(config, 4);
        System.out.println(l);

        config = 2;
        l = ConfigIntParser.int2bits(config, 4);
        assertEquals(false, l.get(3));
        assertEquals(false, l.get(2));
        assertEquals(true, l.get(1));
        assertEquals(false, l.get(0));

        config = 8;
        l = ConfigIntParser.int2bits(config, 4);
        assertEquals(true, l.get(3));
        assertEquals(false, l.get(2));
        assertEquals(false, l.get(1));
        assertEquals(false, l.get(0));

        config = 7;
        l = ConfigIntParser.int2bits(config, 4);
        assertEquals(false, l.get(3));
        assertEquals(true, l.get(2));
        assertEquals(true, l.get(1));
        assertEquals(true, l.get(0));

        config = 14;
        l = ConfigIntParser.int2bits(config, 4);
        assertEquals(true, l.get(3));
        assertEquals(true, l.get(2));
        assertEquals(true, l.get(1));
        assertEquals(false, l.get(0));

        //out of range
        config = 16;
        l = ConfigIntParser.int2bits(config, 4);
        assertEquals(false, l.get(3));
        assertEquals(false, l.get(2));
        assertEquals(false, l.get(1));
        assertEquals(false, l.get(0));

        config = -1;
        expectedException.expect(IllegalArgumentException.class);
        l = ConfigIntParser.int2bits(config, 4);
        assertEquals(0, l.size());

    }


    @Test
    public void int2map() {
        /*
        Map<String, Boolean> map = new HashMap<>();
        map.put("A", true);
        map.put("C", false);
        map.put("B", false);
        map.put("E", true);
        map.put("D", false);
        map.put("F", false);
        */
        int opt = 17;//ConfigIntParser.boolMap2Int(map);
        /**
         * res:{A=true, B=false, C=false, D=false, E=true, F=false}
         */
        System.out.println("opt:" + opt);//17
        List<String> l = new ArrayList<>();
        l.add("C");
        l.add("B");
        l.add("D");
        l.add("A");
        l.add("F");
        l.add("E");
        l.add("G");
        Map<String, Boolean> config = ConfigIntParser.int2boolMap(opt, l);
        System.out.println("res:" + config);

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
