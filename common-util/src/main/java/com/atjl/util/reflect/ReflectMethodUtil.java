package com.atjl.util.reflect;


import java.lang.reflect.Method;

public class ReflectMethodUtil {
    private ReflectMethodUtil(){
        throw new UnsupportedOperationException();
    }



    /**
     * ####################### methods #############################
     */
    /**
     * get object DeclaredMethod ,include all parent
     *
     * @param object         object
     * @param methodName     method name
     * @param parameterTypes parameter types
     * @return method
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                continue;
                //logger.warn("getDeclaredMethod method not found,{}", e.getMsg());
            }
        }
        return null;
    }


    /**
     * invoke method,include private protected and parent's method
     *
     * @param object         invoke object
     * @param methodName     method name
     * @param parameterTypes parameter types
     * @param parameters     parameters
     * @return result
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        try {
            if (null != method) {
                method.setAccessible(true);
                // 调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
