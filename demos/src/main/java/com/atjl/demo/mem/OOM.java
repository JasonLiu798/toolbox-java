package com.atjl.demo.mem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by async on 2018/1/27.
 */
public class OOM {
	
	
	/**
	 * OutOfMemoryError
	 * 1.6,1.7 -XX:PermSize=10M -XX:MaxPermSize=10M
	 * 1.8 -Xmx18m
	 */
	public void stringTest(){
		List<String> list = new ArrayList<>();
		int i=0;
		int j=0;
		while(true){
			list.add(String.valueOf(i++).intern());
			if(i%1000*1000*1000==0){
				System.out.println(j++);
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
