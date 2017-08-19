package com.atjl.util.character;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class RegexUtilTest {


    @Test
    public void testLeftNumStrCNUL() throws Exception {
//        String dig = RegexUtil.getDigits("char(123)");
        String dig = RegexUtil.getDigits("timestamp");
        System.out.println("res:" + dig);


    }

    @Test
    public void testIsRationNum() {

        assertEquals(true, RegexUtil.isRationalNum("0"));
        assertEquals(true, RegexUtil.isRationalNum("-0"));
        assertEquals(true, RegexUtil.isRationalNum("1"));
        assertEquals(true, RegexUtil.isRationalNum("1.0"));
        assertEquals(true, RegexUtil.isRationalNum("-21.0"));
        assertEquals(true, RegexUtil.isRationalNum("1231.0"));
        assertEquals(false, RegexUtil.isRationalNum(null));
        assertEquals(false, RegexUtil.isRationalNum("3e4"));
        assertEquals(true, RegexUtil.isRationalNum("123123333333333333332323"));
        assertEquals(true, RegexUtil.isRationalNum("123123333.333333333332323"));
        assertEquals(true, RegexUtil.isRationalNum("0.333333333332323"));
        assertEquals(false, RegexUtil.isRationalNum("000.333333333332323"));
        assertEquals(true, RegexUtil.isRationalNum("-0.333333333332323"));
        assertEquals(false, RegexUtil.isRationalNum("-000.333333333332323"));
    }

    @Test
    public void testGetMatcher() throws Exception {

    }

    @Test
    public void testGetPattern() throws Exception {

    }

    @Test
    public void testIsMobile() throws Exception {

    }

    @Test
    public void testIsEmail() throws Exception {

    }

    @Test
    public void testGetDigits() throws Exception {

    }

    @Test
    public void testIsDigit() throws Exception {

    }

    @Test
    public void testIsPositive() throws Exception {

    }

    @Test
    public void testIsNatural() throws Exception {

    }

    @Test
    public void testIsInteger() throws Exception {

    }

    @Test
    public void testIsBoolean() throws Exception {

    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
