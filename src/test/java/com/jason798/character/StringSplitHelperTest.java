package com.jason798.character; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* StringSplitHelper Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 27, 2016</pre> 
* @version 1.0 
*/ 
public class StringSplitHelperTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: tokenizeToStringArray(String str, String delimiters) 
* 
*/ 
@Test
public void testTokenizeToStringArrayForStrDelimiters() throws Exception { 
//TODO: Test goes here...
	String[] arr = StringSplitHelper.tokenizeToStringArray("a,b,c",",");
	for(int i=0;i<arr.length;i++){
		System.out.println(arr[i]);
	}
} 

/** 
* 
* Method: tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) 
* 
*/ 
@Test
public void testTokenizeToStringArrayForStrDelimitersTrimTokensIgnoreEmptyTokens() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toStringArray(Collection collection) 
* 
*/ 
@Test
public void testToStringArrayCollection() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toStringArray(Enumeration enumeration) 
* 
*/ 
@Test
public void testToStringArrayEnumeration() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: split(String toSplit, String delimiter) 
* 
*/ 
@Test
public void testSplit() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: splitEachArrayElementAndCreateMap(String array[], String delimiter, String removeCharacters) 
* 
*/ 
@Test
public void testSplitEachArrayElementAndCreateMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: substringBeforeLast(String str, String separator) 
* 
*/ 
@Test
public void testSubstringBeforeLast() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: substringAfterLast(String str, String separator) 
* 
*/ 
@Test
public void testSubstringAfterLast() throws Exception { 
//TODO: Test goes here... 
} 


} 
