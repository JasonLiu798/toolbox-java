package com.atjl.util.reflect;


import com.atjl.util.character.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ReflectSetUtil {
//    private static Logger logger = LoggerFactory.getLogger(ReflectSetUtil.class);

    private ReflectSetUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * ########################## setter method association ##########################
     **/
    /**
     * set 指定类型 value
     *
     * @param obj     target object
     * @param field   field
     * @param valType field type
     * @param val     value
     */
    public static <T> void setter(T obj, String field, Class<?> valType, Object val) {
        String methodName = ReflectConstant.SET_PREFIX + StringUtil.toUpperCaseFirstOne(field);
        Method setter = null;
        Class clz = obj.getClass();
        try {
            setter = clz.getMethod(methodName, valType);
            setter.invoke(obj, val);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void setter(T obj, String field, Object val) {
        setter(obj, field, val.getClass(), val);
    }

    /**
     * force set,[include private,protecte,parent's]
     *
     * @param object    object
     * @param fieldName field name
     * @param value     value
     */
    public static void setterForce(Object object, String fieldName, Object value) {
        Field field = ReflectFieldUtil.getDeclaredField(object, fieldName);
        try {
            if (field == null) {//do nothing
                return;
            }
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ################# other ######################
     */

    /**
     * set bean use map K-field,V-value
     *
     * @param paramMap parameter map
     * @param bean     bean
     */
    public static void setBean(Map<String, Object> paramMap, Object bean) {
        if (paramMap == null || paramMap.isEmpty())
            throw new IllegalArgumentException("parameter paramMap must have a value");
        if (bean == null)
            throw new IllegalArgumentException("bean the value of must be instantiated");
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            //String fieldName = entry.getKey();
            setterForce(bean, entry.getKey(), entry.getValue());
        }
    }


}
