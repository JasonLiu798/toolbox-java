package com.atjl.log.util;


import org.aspectj.lang.ProceedingJoinPoint;

public class LogFixUtil {
    private LogFixUtil(){
        super();
    }

    public static Object process(ProceedingJoinPoint jp, Object[] args) {
        try {
            return jp.proceed(args);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
