package com.atjl.cas.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationUtil {

	private AuthenticationUtil() {
		throw new IllegalAccessError("Utility class");
	}

	public static boolean cookieChk(HttpServletRequest request, HttpServletResponse response) {
		return CasHelper.checkToken(request, response) || beforeRedirect(request);
	}

	public static boolean beforeRedirect(HttpServletRequest request) {
		boolean isInWhiteListUri = AuthenticationUtil.isInWhiteListUrl(
				request.getRequestURI(), AuthenticationUtil.getWhiteListUrls());

		return isInWhiteListUri;
	}

	/**
	 * 获取sys.conf url白名单
	 * 
	 * @return
	 */
	public static Set<String> getWhiteListUrls() {
		Set<String> safeUrls = new HashSet<>();
		String url = CasHelper.getPropertyFromInitParams("safeUrls", null);
		if (url != null) {
			String[] urls = url.split("[|]");
			for (String s : urls) {
				s = s.trim();
				if (!"".equals(s)) {
					safeUrls.add(s);
				}
			}
		}
		return safeUrls;
	}

	/**
	 * 检查url是否安全路径
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isInWhiteListUrl(String url, Set<String> safeUrls) {
		if (url == null) {
			return false;
		}
		for (String safeUrl : safeUrls) {
			Pattern pattern = Pattern.compile(safeUrl);
			Matcher matcher = pattern.matcher(url);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}
}