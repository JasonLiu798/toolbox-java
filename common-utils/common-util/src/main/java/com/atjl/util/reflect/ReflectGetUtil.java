package com.atjl.util.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectGetUtil {
    private static Logger logger = LoggerFactory.getLogger(ReflectGetUtil.class);

    private ReflectGetUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * ################# get methods ###################
     */

    /**
     * @param clz
     * @param fieldName
     * @return
     */
    public static Object getterForceClz(Class clz, String fieldName) {
        return getterForce(clz, null, fieldName);
    }

    public static Object getterForce(Object obj, String fieldName) {
        return getterForce(obj.getClass(), obj, fieldName);
    }

    /**
     * force get field value,[include private,protecte,parent's]
     *
     * @param obj       target object
     * @param fieldName field name
     * @return field value
     */
    public static Object getterForce(Class clz, Object obj, String fieldName) {
        Field field = ReflectFieldUtil.getDeclaredField(clz, fieldName);
        if (field == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("field null");
            }
            return null;
        }
        try {
            field.setAccessible(true);
            if (obj != null) {
                return field.get(obj);
            } else {
                return field.get(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get value from getter
     *
     * @param obj   target object
     * @param field field name
     * @return
     */
    public static <T> Object getter(T obj, String field) {
        Class clz = obj.getClass();
        Method getterMethod = getGetterMethod(clz, field);
        try {
            return getterInner(obj, getterMethod);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object getterInner(Object obj, Method method) throws InvocationTargetException, IllegalAccessException {
        if (method != null) {
            Object res = method.invoke(obj, (Object[]) null);
            return res;
        }
        return null;
    }

    /**
     * get getter method,if type is boolean ,try to get is method
     *
     * @param clz   class
     * @param field field string
     * @return method
     */
    public static Method getGetterMethod(Class clz, String field) {
        String methodName = ReflectCommonUtil.generateGetName(field, false);

        boolean methodNotFound = false;
        Method getterMethod = null;
        try {
            getterMethod = clz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            methodNotFound = true;
        }

        // if boolean try to find exist isXXX
        if (methodNotFound) {
            try {
                getterMethod = clz.getMethod(ReflectCommonUtil.generateGetName(field, true));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return getterMethod;
    }


}
