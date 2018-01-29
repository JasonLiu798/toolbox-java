package com.atjl.demo.grammar;

public class TestFinally {
	
	/**
	 * public int inc();
	 * Code:
	 * 0: iconst_1
	 * 1: istore_1
	 * 2: iload_1
	 * 3: istore_2
	 * 4: iconst_3
	 * 5: istore_1
	 * 6: iload_2
	 * 7: ireturn
	 * 
	 * 8: astore_2
	 * 9: iconst_2
	 * 10: istore_1
	 * 11: iload_1
	 * 12: istore_3
	 * 13: iconst_3
	 * 14: istore_1
	 * 15: iload_3
	 * 16: ireturn
	 * 
	 * 17: astore        4
	 * 19: iconst_3
	 * 20: istore_1
	 * 21: aload         4
	 * 23: athrow
	 * Exception table:
	 * from    to  target type
	 * 0     4     8   Class java/lang/Exception
	 * 0     4    17   any
	 * 8    13    17   any
	 * 17    19    17   any
	 */
	public int inc() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}
	}
	
	
	public static void main(String[] args) {
		int res = new TestFinally().inc();
		System.out.println("res:" + res);
	}
}
