package com.jason798.constant; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/** 
* SystemConstant Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 1, 2016</pre> 
* @version 1.0 
*/ 
public class SystemConstantTest {

	/**
	 *
	 */
	@Test
	public void test() throws IllegalAccessException {
		Class clz = SystemConstant.class;
		Field[] fileds = clz.getDeclaredFields();
		for(Field field:fileds){
//			Type type = field.getGenericType();
			System.out.println(field.getName()+"		"+field.get(field.getName()));

		}
	}


} 
