package com.atjl.dbtiming.util;


import com.atjl.dbtiming.domain.constant.TimingConstant;
import com.atjl.util.reflect.ReflectMethodUtil;

import java.lang.reflect.Method;

public class TimingMethodUtil {
    private TimingMethodUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * get cond method
     */
    public static Method getCondMethod(Object service) {
        return ReflectMethodUtil.getDeclaredMethod(service, TimingConstant.METHOD_COND);
    }

    public static Method getExeMethod(Object service) {
        return ReflectMethodUtil.getDeclaredMethod(service, TimingConstant.METHOD_EXECUTE);
    }


}
