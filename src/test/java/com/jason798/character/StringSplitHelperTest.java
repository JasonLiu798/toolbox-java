package com.jason798.character;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;
import java.util.HashSet;


public class StringSplitHelperTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
    /**
     * Method: tokenizeToStringArray(String str, String delimiters)
     */
    @Test
    public void testTokenizeToStringArrayForStrDelimiters() throws Exception {
        String[] arr = StringSplitUtil.tokenizeToStringArray("a,b,c",",");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
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
     * Method: tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
     */
    @Test
    public void testTokenizeToStringArrayForStrDelimitersTrimTokensIgnoreEmptyTokens() throws Exception {

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
     * Method: substringBeforeLast(String str, String separator)
     */
    @Test
    public void testSubstringBeforeLast() throws Exception {

    }

    /**
     * Method: substringAfterLast(String str, String separator)
     */
    @Test
    public void testSubstringAfterLast() throws Exception {

    }


}
