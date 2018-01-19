package com.atjl.util.character;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;


public class RegexUtilTest {

    @Test
    public void replaceAll() {
        String res = RegexUtil.replaceAll("<p>hahah</p>", "<[^>]*>", "");
        assertEquals("hahah",res);

        res = RegexUtil.replaceAll(null, "<[^>]*>", "");
        assertEquals(null,res);

        res = RegexUtil.replaceAll("aa", "", "");
        assertEquals("aa",res);

        res = RegexUtil.replaceAll("aa", null, "");
        assertEquals("aa",res);

        res = RegexUtil.replaceAll("<p>hahah</p>", "<[^>]*>", null);
        assertEquals("hahah",res);

//        System.out.println("res:" + res);


    }

    @Test
    public void testLeftNumStrCNUL() throws Exception {
//        String dig = RegexUtil.getDigits("char(123)");
        String dig = RegexUtil.getDigits("timestamp");
        System.out.println("res:" + dig);


    }

    @Test
    public void testIsAlpha() {
        assertEquals(true, RegexUtil.isAlpha("a"));
        assertEquals(true, RegexUtil.isAlpha("B"));
        assertEquals(true, RegexUtil.isAlpha("dsfAsdf"));
        assertEquals(true, RegexUtil.isAlpha("dsfsd"));

        assertEquals(false, RegexUtil.isAlpha("dsf  Asdf"));
        assertEquals(false, RegexUtil.isAlpha("  sdfs  djkl  "));
        assertEquals(false, RegexUtil.isAlpha(""));
        assertEquals(false, RegexUtil.isAlpha(null));
    }

    @Test
    public void testIsAlphaNum() {
        assertEquals(true, RegexUtil.isAlphaNum("a"));
        assertEquals(true, RegexUtil.isAlphaNum("1"));
        assertEquals(true, RegexUtil.isAlphaNum("1a"));
        assertEquals(true, RegexUtil.isAlphaNum("a3"));
        assertEquals(true, RegexUtil.isAlphaNum("B"));
        assertEquals(true, RegexUtil.isAlphaNum("dsf324Asdf"));
        assertEquals(true, RegexUtil.isAlphaNum("dsfsd"));
        assertEquals(true, RegexUtil.isAlphaNum("dsfsdfsd"));
        assertEquals(true, RegexUtil.isAlphaNum("adsf343SD"));

        assertEquals(false, RegexUtil.isAlphaNum("dsf  As232df"));
        assertEquals(false, RegexUtil.isAlphaNum("  sdfs  djdf34 kl  "));
        assertEquals(false, RegexUtil.isAlphaNum(""));
        assertEquals(false, RegexUtil.isAlphaNum(null));
    }

    @Test
    public void testIsAlphaNumUl() {
        assertEquals(true, RegexUtil.isAlphaNumDash("a"));
        assertEquals(true, RegexUtil.isAlphaNumDash("1"));
        assertEquals(true, RegexUtil.isAlphaNumDash("_"));
        assertEquals(true, RegexUtil.isAlphaNumDash("1a"));
        assertEquals(true, RegexUtil.isAlphaNumDash("a3"));
        assertEquals(true, RegexUtil.isAlphaNumDash("B"));
        assertEquals(true, RegexUtil.isAlphaNumDash("_B"));
        assertEquals(true, RegexUtil.isAlphaNumDash("23_B"));
        assertEquals(true, RegexUtil.isAlphaNumDash("a_"));
        assertEquals(true, RegexUtil.isAlphaNumDash("dsf324Asdf"));
        assertEquals(true, RegexUtil.isAlphaNumDash("dsfsd"));
        assertEquals(true, RegexUtil.isAlphaNumDash("dsfsdfsd"));
        assertEquals(true, RegexUtil.isAlphaNumDash("adsf343SD"));
        assertEquals(true, RegexUtil.isAlphaNumDash("ad_f343SD"));

        assertEquals(false, RegexUtil.isAlphaNumDash("dsf  As232df"));
        assertEquals(false, RegexUtil.isAlphaNumDash("  sdfs  djdf34 kl  "));
        assertEquals(false, RegexUtil.isAlphaNumDash(""));
        assertEquals(false, RegexUtil.isAlphaNumDash("  sdfs_djdf34 kl  "));
        String s = null;
        assertEquals(false, RegexUtil.isAlphaNumDash(s));
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
    public void testIsIP() throws Exception {
        assertEquals(false, RegexUtil.isIPV4("2234"));
        assertEquals(false, RegexUtil.isIPV4(""));
        assertEquals(false, RegexUtil.isIPV4("123.123"));
        assertEquals(false, RegexUtil.isIPV4("123.123.234.adf"));
        assertEquals(false, RegexUtil.isIPV4("1.34.034.234"));
        assertEquals(false, RegexUtil.isIPV4("1.34.0034.234"));
        assertEquals(false, RegexUtil.isIPV4("01.34.34.234"));
        assertEquals(false, RegexUtil.isIPV4("001.34.34.234"));

        assertEquals(true, RegexUtil.isIPV4("1.34.234.234"));
        assertEquals(true, RegexUtil.isIPV4("255.34.34.0"));
        assertEquals(true, RegexUtil.isIPV4("255.34.34.101"));
    }

    @Test
    public void testIsIPv6() throws Exception {
        assertEquals(false, RegexUtil.isIPV6("2234"));
        assertEquals(false, RegexUtil.isIPV6(""));
        assertEquals(false, RegexUtil.isIPV6(":"));
        assertEquals(false, RegexUtil.isIPV6("123.123"));
        assertEquals(false, RegexUtil.isIPV6("123.123.234.adf"));
        assertEquals(false, RegexUtil.isIPV6("1.34.034.234"));
        assertEquals(false, RegexUtil.isIPV6("1.34.0034.234"));
        assertEquals(false, RegexUtil.isIPV6("01.34.34.234"));
        assertEquals(false, RegexUtil.isIPV6("001.34.34.234"));

        assertEquals(true, RegexUtil.isIPV6("::"));
        assertEquals(true, RegexUtil.isIPV6("2001:0DB8:0000:0023:0008:0800:200C:417A"));
        assertEquals(true, RegexUtil.isIPV6("2001:DB8:0:23:8:800:200C:417A"));
        assertEquals(true, RegexUtil.isIPV6("23:34"));
    }

    @Test
    public void getMatcher() throws Exception {

        String regex = "FONT[1-9]+";
//        String target = "aaa-FONT3-sdfjkdklj";
        String target = "aaa-FONT3-sdfj-FONT5-kdklj";
        Matcher m = RegexUtil.getMatcher(regex, target);

        boolean find = m.find();
        assertEquals(true, find);
        System.out.println("res:" + find);

        String g = m.group();
        assertEquals("FONT3", g);


        regex = "FONT[1-9]+";
        target = "aaa-kdklj";
        m = RegexUtil.getMatcher(regex, target);
        find = m.find();
        assertEquals(false, find);


        regex = "FONT[1-9]+";
        target = "aaa-kdklj-FONT2";
        m = RegexUtil.getMatcher(regex, target);
        find = m.find();
        assertEquals(true, find);
        g = m.group();
        assertEquals("FONT2", g);


        regex = "FONT[1-9]+";
        target = "FONT2-aaa-kdklj";
        m = RegexUtil.getMatcher(regex, target);
        find = m.find();
        assertEquals(true, find);
        g = m.group();
        assertEquals("FONT2", g);
//        assertEquals("FON");

    }


    @Test
    public void getMatcher2() throws Exception {

        String regex = "-(SEQADDPARENT)([1-9]+)-*";
//        String target = "aaa-FONT3-sdfjkdklj";
//        String target = "aaa-SEQADDPARENT3-sdfj";
        String target = "aaa-SEQADDPARENT3";
        Matcher m = RegexUtil.getMatcher(regex, target);

        boolean find = m.find();
        assertEquals(true, find);
        System.out.println("res:" + find);


        String g = m.group();
        String g1 = m.group(1);
        String g2 = m.group(2);
        System.out.println("group " + g + ",g1 " + g1 + ",g2 " + g2);

//        assertEquals("FONT3", g);


//        regex = "FONT[1-9]+";
//        target = "aaa-kdklj";
//        m = RegexUtil.getMatcher(regex, target);
//        find = m.find();
//        assertEquals(false, find);
//
//
//        regex = "FONT[1-9]+";
//        target = "aaa-kdklj-FONT2";
//        m = RegexUtil.getMatcher(regex, target);
//        find = m.find();
//        assertEquals(true, find);
//        g = m.group();
//        assertEquals("FONT2", g);
//
//
//        regex = "FONT[1-9]+";
//        target = "FONT2-aaa-kdklj";
//        m = RegexUtil.getMatcher(regex, target);
//        find = m.find();
//        assertEquals(true, find);
//        g = m.group();
//        assertEquals("FONT2", g);
////        assertEquals("FON");

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
