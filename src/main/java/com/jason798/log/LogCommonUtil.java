package com.jason798.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 */
public class LogCommonUtil {
	
	
	
	/**
	 * level 1
	 *
	 * @param msg
	 * @return
	 */
	public static String getStackAndMsg(String msg, int level) {
		String msgStack = "";
		msgStack = getStack(level);//直接调用，本层为1层，本类调用方还有2层，类外调用方1层 = 4
		return msgStack + " " + msg;
	}
	
	
	
	/**
	 * 获取第几层 caller
	 * level 0
	 *
	 * @param preLevel
	 * @return
	 */
	private static String getStack(int preLevel) {
		if (preLevel < 0) {
			preLevel = 0;
		}
		Throwable throwable = new Throwable();
		StackTraceElement[] stes = throwable.getStackTrace();
		if (stes.length > preLevel) {
			StringBuilder sb = new StringBuilder();
			//get upper caller stack trace
			sb.append(stes[preLevel].toString());
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 获取异常的堆栈信息
	 *
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		if (t == null) {
			return "exception null";
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		String res = "get stack trace fail,throwable " + t;
		try {
			t.printStackTrace(pw);
			res = sw.toString();
			return res;
		} catch (Exception e) {
			res = res + "," + e.getMessage();
		} finally {
			pw.close();
		}
		return res;
	}
	
	public static String format(String locate, String exception) {
		return format(locate, exception, null);
	}
	
	public static String format(String locate, String exception, String data) {
		if (data == null) {
			return String.format("%s,%s", locate, exception);
		}
		return String.format("%s,%s,data %s", locate, exception, data);
	}
	
	
}
