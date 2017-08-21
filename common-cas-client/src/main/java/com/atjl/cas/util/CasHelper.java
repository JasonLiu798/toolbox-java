package com.atjl.cas.util;

import com.alibaba.fastjson.JSONObject;
import com.atjl.cas.api.BeanFactory;
import com.atjl.cas.api.UserContext;
import com.atjl.cas.domain.CasConfig;
import com.atjl.cas.domain.CasConstant;
import com.atjl.cas.domain.Params;
import com.atjl.cas.domain.VerifyStatus;
import com.atjl.cas.service.CasService;
import com.atjl.cas.service.impl.CasServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CasHelper {

    private CasHelper() {
        throw new IllegalAccessError("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(CasHelper.class);

    public static void responseOutWithJson(HttpServletResponse response,
                                           Object responseObject) {
        // 将实体对象转换为JSON Object转换
        String responseJSONObject = JSONObject.toJSONString(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject);
            logger.debug("返回是\n");
            logger.debug(responseJSONObject);
        } catch (IOException e) {
            logger.info("写入错误", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static String getPropertyFromInitParams(final String propertyName,
                                                   final String defaultValue) {
        CasConfig config = BeanFactory.getBean(BeanFactory.BEAN_CAS_CONFIG, true);
        String propertyValue = config.getConfig(propertyName);
        return ConfigEx.getConfigValue(propertyValue, defaultValue);
    }

    public static String getProperty(final String propertyName) {
        CasConfig config = BeanFactory.getBean(BeanFactory.BEAN_CAS_CONFIG, true);
        return config.getConfig(propertyName);
    }

    /**
     * 获取各阶段埋点回调
     */
    public static CasService getPostCallBack() {
        CasService callback = BeanFactory.getUniqueBean(CasService.class);
        if (callback == null) {
            callback = new CasServiceImpl();
        }
        return callback;
    }


    public static boolean isMobileDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        logger.info("Variable userAgent: {}", userAgent);
        return isMobileDevice(userAgent);
    }

    public static boolean isMobileDevice(String requestHeader) {
        /**
         * android : 所有android设备
         * mac os : iphone ipad windows
         * phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android", "mac os",
                "windows phone"};
        if (requestHeader == null)
            return false;
        for (int i = 0; i < deviceArray.length; i++) {
            if (requestHeader.toLowerCase().indexOf(deviceArray[i]) > -1) {
                return true;
            }
        }
        return false;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            deleteToken(request, response);
            response.sendRedirect(CasHelper.getProperty("casServerLogoutUrl"));
        } catch (IOException e) {
            logger.error("登出失败", e);
        }
    }

    public static void addToken(HttpServletRequest req, HttpServletResponse resp, String userName) {
        String token = CasJwt.create(new Params(userName));
        resp.addCookie(buildCookie(req, token));
        UserContext.setCurrentUserName(req,userName);
    }

    public static boolean tokenExist(HttpServletRequest request) {
        String token = getCookie(request, CasConstant.TOKEN_COOKIE_KEY);
        return StringUtils.isEmpty(token);
    }

    public static boolean checkToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = getCookie(request, CasConstant.TOKEN_COOKIE_KEY);
            VerifyStatus status = CasJwt.Verify(request, token);
//			if (status.getStatus()) {
//				addToken(request,response, status.getIss());
//			}
            return status.getStatus();
        } catch (Exception e) {
            logger.error("token校验失败", e);
            return false;
        }
    }

    /**
     * 删除 cookie session
     * @param request
     * @param response
     */
    public static void deleteToken(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.invalidate();
        }
        deleteCookie(response);
    }

    public static Cookie buildCookie(HttpServletRequest req, String token) {
        Cookie cookie = new Cookie(CasConstant.TOKEN_COOKIE_KEY, token);
        int maxage = Integer.valueOf(CasHelper.getProperty("CookieMaxage"));
        cookie.setMaxAge(maxage); // seconds

        cookie.setPath(CasHelper.getProperty("CookiePath"));

        //req.getRequestURL();
//        String reqUrl = req.getRequestURL().toString();
//        reqUrl = reqUrl.replaceAll("http://", "");
//        reqUrl = reqUrl.replaceAll("https://", "");
//        String domain = reqUrl.substring(0, reqUrl.indexOf("/"));
        //http://
//        String domain = reqUrl.substring(0,reqUrl.indexOf("/",8));
//        cookie.setDomain(CasHelper.getProperty("CookieDomain"));
//        cookie.setDomain(domain);
        // cookie.setSecure(true); 不能设置为true
        return cookie;
    }

    public static Cookie deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(CasConstant.TOKEN_COOKIE_KEY, "");// 这边得用"",不能用null
        cookie.setMaxAge(0); // seconds
        cookie.setPath(CasHelper.getProperty("CookiePath"));
//        cookie.setDomain(CasHelper.getProperty("CookieDomain"));
        // cookie.setSecure(true); 不能设置为true
        response.addCookie(cookie);
        return cookie;
    }

    public static String getCookie(HttpServletRequest request, String key) {
        String cookie = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (key.equals(c.getName())) {
                cookie = c.getValue();
            }
        }
        return cookie;
    }
}
