package com.atjl.cas.api;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserContext {

    private UserContext() {
        throw new IllegalAccessError("Utility class");
    }

    public static final String NAME_KEY = "__USER_NAME__";

    public static void setCurrentUserName(HttpServletRequest req,String userName) {

        req.getSession().setAttribute(NAME_KEY, userName);
    }

    public static void setCurrentUserName(String userName) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        req.getSession().setAttribute(NAME_KEY, userName);
    }

    public static String getCurrentUserName(HttpServletRequest req) {
//        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes()).getRequest();
        return (String) req.getSession().getAttribute(NAME_KEY);
    }

    public static String getCurrentUserName() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return (String) req.getSession().getAttribute(NAME_KEY);
    }

    /*
    public static String getCurrentUserName() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return (String) req.getSession().getAttribute(NAME_KEY);
    }*/


//    public static void setSessionContext(String userName) {
//        setCurrentUserName(userName);
//    }

}