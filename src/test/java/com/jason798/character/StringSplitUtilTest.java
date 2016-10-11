package com.jason798.character;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;


public class StringSplitUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: splitTwo(String toSplit, String delimiter)
     */
    @Test
    public void testSplitTwo() throws Exception {

    }

    /**
     * Method: splitEachArrayElementAndCreateMap(String array[], String delimiter, String removeCharacters)
     */
    @Test
    public void testSplitEachArrayElementAndCreateMap() throws Exception {

    }


    /**
     * Method: toStringArray(Collection<String> collection)
     */
    @Test
    public void testToStringArray() throws Exception {
        HashSet set = new HashSet();
        set.add("aa");
        set.add("bb");
        String[] arr = StringSplitUtil.toStringArray(set);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Method: tokenizeToStringArray(String str, String delimiters)
     */
    @Test
    public void testTokenizeToStringArrayForStrDelimiters() throws Exception {
//TODO: Test goes here...
        String[] arr = StringSplitUtil.tokenizeToStringArray("a,b,c", ",");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * Method: toStringArray(Collection collection)
     */
    @Test
    public void testToStringArrayCollection() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: toStringArray(Enumeration enumeration)
     */
    @Test
    public void testToStringArrayEnumeration() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: split(String toSplit, String delimiter)
     */
    @Test
    public void testSplit() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: substringBeforeLast(String str, String separator)
     */
    @Test
    public void testSubstringBeforeLast() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: substringAfterLast(String str, String separator)
     */
    @Test
    public void testSubstringAfterLast() throws Exception {
//TODO: Test goes here... 
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void splitCheckSize() {
        String s = "test1:test2:test3";

        String[] arr = StringSplitUtil.splitCheckSize(s, ":", 3);
        String[] expec = {"test1", "test2", "test3"};
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], expec[i]);
        }

        expectedException.expect(IllegalStateException.class);
        arr = StringSplitUtil.splitCheckSize(s, ":", 4);
    }

    @Test
    public void splitCheckSizeDelimerNotFound() {
        String s = "test1:test2:test3";
        String[] arr = StringSplitUtil.splitCheckSize(s, "a", 1);
        System.out.println(Arrays.toString(arr));
        assertEquals(1,arr.length);
        assertEquals(s,arr[0]);
    }

    @Test
    public void testRemoveTillDelimiter(){
        String s = "/aaa/bbb/ccc";
        String e = StringSplitUtil.removeAfterLastDelimiter(s,"/");
        assertEquals("/aaa/bbb",e);
        //one is null,do nothing
        s = "/aaa/bbb/ccc";
        e = StringSplitUtil.removeAfterLastDelimiter(s,null);
        assertEquals(s,e);

        s = null;
        e = StringSplitUtil.removeAfterLastDelimiter(s,"c");
        assertEquals(s,e);

        // not find delimiter
        s = "/aaa/bbb/ccc";
        e = StringSplitUtil.removeAfterLastDelimiter(s,"=");
        assertEquals(s,e);

        //delimiter length > 1
        s = "/aaa/bbb/ccc";
        e = StringSplitUtil.removeAfterLastDelimiter(s,"bb");
        assertEquals("/aaa/b",e);

        s = "/";
        e = StringSplitUtil.removeAfterLastDelimiter(s,"/");
        assertEquals("",e);


    }

}
