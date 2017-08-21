package com.atjl.cas.util;


import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

public class CasUrlGenUtil {
	private CasUrlGenUtil() {
		super();
	}
	
	public static String encode(String url) {
		return URLEncoder.encode(url);
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		//http://
		int idx = url.indexOf("/", 7);
		if (idx > 0) {
			url = url.substring(0, idx);
		}
		return url;
	}
	
	
	/**
	 * 获取请求 url
	 *
	 * @param request
	 * @return
	 */
	public static String getRawUrl(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String redirect = url.toString();
		String queryString = request.getQueryString();
		if (queryString != null && !"".equals(queryString)) {
			redirect = redirect + "?" + queryString;
		}
		return redirect;
	}
	
	/**
	 * 获取不包含 ticket的请求url
	 *
	 * @param request
	 * @return
	 */
	public static String getRawUrlNoTicket(HttpServletRequest request) {
		String rawUrl = getRawUrl(request);
		int ticketIdx = rawUrl.indexOf("ticket");
		//移除ticket 做校验
		if (ticketIdx > 0) {
			rawUrl = rawUrl.substring(0, ticketIdx - 1);
		}
		return rawUrl;
	}
}
