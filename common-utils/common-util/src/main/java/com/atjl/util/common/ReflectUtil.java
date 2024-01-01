package com.atjl.util.common;

import com.atjl.util.reflect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 反射相关util
 * PS:大部分异常 只记录，不抛出
 *
 * @author JasonLiu
 */
public class ReflectUtil {
    private ReflectUtil() {
        throw new UnsupportedOperationException();
    }

    private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    /**
     * get class type
     * 0   self and parents classes
     * 1   only self
     * 2   only parent class
     * 3   all parent classes
     */
    public enum GetClzOpt {
        ALL,//所有类
        SELF,//自身
        PARENT,//上一层父类
        ALLPARENT//所有父类
    }

    /**
     * ########################### new #################################
     */
    /**
     * get instance by string
     *
     * @param classStr class str
     * @return instance
     * @throws ClassNotFoundException class not found
     * @throws IllegalAccessException private constract
     * @throws InstantiationException interface,abstract class,or no-parameter construct function not exist
     */
    @Deprecated
    public static Object getInstance(String classStr) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return ReflectClassUtil.newInstance(classStr);
    }

    @Deprecated
    public static Object getInstance(Class clz) {
        return ReflectClassUtil.newInstance(clz);
    }

    @Deprecated
    public static Object getInstance(Class clz, Class[] paramClz, Object[] params) {
        return ReflectClassUtil.newInstance(clz, paramClz, params);
    }


    /**
     * ######################### fields #############################
     */
    /**
     * 建议使用 ReflectField类
     *
     * @param obj
     * @param parentOpt
     * @param blackArr
     * @param whiteArr
     * @return
     */
    @Deprecated
    public static List<Field> getFields(Object obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
        return ReflectFieldUtil.getFieldList(obj.getClass(), parentOpt, blackArr, whiteArr);
    }

    @Deprecated
    public static List<Field> getFields(Class obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
        return ReflectFieldUtil.getFieldList(obj.getClass(), parentOpt, blackArr, whiteArr);
    }

    @Deprecated
    public static List<Field> getFieldAll(Object obj) {
        return getFields(obj, GetClzOpt.ALL, null, null);
    }


    /**
     * 字段拷贝
     *
     * @param source
     * @param target
     * @param opt
     * @param allowNull
     * @param blackList
     * @param whiteList
     */
    @Deprecated
    public static void copyField(Object source, Object target, GetClzOpt opt, boolean allowNull, String[] blackList, String[] whiteList) {
        ReflectFieldUtil.copyField(source, target, opt, allowNull, blackList, whiteList);
    }

    @Deprecated
    public static void copyField(Object source, Object target) {
        ReflectFieldUtil.copyField(source, target, GetClzOpt.ALL, true, null, null);
    }


    /**
     * ####################### methods #############################
     */
    /**
     * invoke method,include private protected and parent's method
     *
     * @param object         invoke object
     * @param methodName     method name
     * @param parameterTypes parameter types
     * @param parameters     parameters
     * @return result
     */
    @Deprecated
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        return ReflectMethodUtil.invokeMethod(object, methodName, parameterTypes, parameters);
    }


    /**
     * ########################## getter method association ##########################
     **/

    /**
     * check get method exist
     *
     * @param methods     method list
     * @param fieldGetMet get method name string
     * @return is equal
     */
    private static boolean checkGetMethod(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * #################### coverters #######################
     */
    /**
     * field 转 field数组
     *
     * @param filesList
     * @return
     */
    @Deprecated
    public static List<String> filed2string(List<Field> filesList) {
        return ReflectFieldUtil.filed2string(filesList);
    }

}
