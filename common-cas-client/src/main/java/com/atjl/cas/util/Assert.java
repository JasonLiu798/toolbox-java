package com.atjl.cas.util;


import com.atjl.cas.api.CASBaseException;

public class Assert {
	
	private Assert() {
		throw new IllegalAccessError("Utility class");
	}

	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new CASBaseException(message);
		}
	}


}
