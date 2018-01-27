package com.atjl.util;

import com.atjl.util.config.ConfigIntParser;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.List;

public class PlainTest1 {
	
	public static void main(String[] args) {
		int[] ints = {1,2,3,4,5};
		
		for (int i : ints)
			System.out.println(i);
	}
}
