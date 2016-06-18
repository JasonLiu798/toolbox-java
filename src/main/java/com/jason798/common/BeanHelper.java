package com.jason798.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Bean helper
 */
public final class BeanHelper {

    /**
     * get
     */
    private static final String GET = "get";

    /**
     * 得到当前类父类列表
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @return {@link Map}
     */
    public static Map<String, String> getFieldMap(Object bean) {
        return convert(getFieldValueMap(bean, false));
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @param allowNullValue
     *            {@link Boolean} 允许空值
     * @return {@link Map}
     */
    public static Map<String, String> getFieldMap(Object bean, boolean allowNullValue) {
        return convert(getFieldValueMap(bean, allowNullValue));
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @param allowNullValue
     *            {@link Boolean} 允许空值
     * @param ignores
     *            {@link String}[] 忽略属性列表
     * @return {@link Map}
     */
    public static Map<String, String> getFieldMap(Object bean, boolean allowNullValue, String[] ignores) {
        return convert(getFieldValueMap(bean, allowNullValue, ignores));
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @return Map
     */
    public static Map<String, Object> getFieldValueMap(Object bean) {
        return getFieldValueMap(bean, true, null);
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @param allowNullValue
     *            {@link Boolean} 允许空值
     * @return {@link Map}
     */
    public static Map<String, Object> getFieldValueMap(Object bean, boolean allowNullValue) {
        return getFieldValueMap(bean, allowNullValue, null);
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @param allowNullValue
     *            {@link Boolean} 允许空值
     * @param ignores
     *            {@link String}[] 忽略属性列表
     * @return {@link Map}
     */
    public static Map<String, Object> getFieldValueMap(Object bean, boolean allowNullValue, String[] ignores) {
        List<Class<?>> clazzList = clazzList(bean);
        Map<String, Object> valueMap = new HashMap<String, Object>();
        for (Class<?> cls : clazzList) {
            // 取出bean里的所有方法
            Method[] methods = cls.getDeclaredMethods();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                try {
                    String fieldGetName = parGetName(field.getName());
                    if (ignores != null && ignores.length != 0 && Arrays.asList(ignores).indexOf(field.getName()) != -1) {
                        continue;
                    }
                    if (!checkGetMet(methods, fieldGetName)) {
                        continue;
                    }
                    Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});
                    Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
                    if (allowNullValue) {
                        valueMap.put(field.getName(), fieldVal);
                        continue;
                    }
                    if (fieldVal == null || isEmpty(fieldVal)) {
                        continue;
                    }
                    valueMap.put(field.getName(), fieldVal);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return valueMap;
    }

    /**
     * 得到当前类父类列表
     *
     * @param bean
     *            {@link Object} 待取值的bean
     * @return
     */
    private static List<Class<?>> clazzList(Object bean) {
        List<Class<?>> clazzList = new LinkedList<Class<?>>();
        for (Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                clazzList.add(clazz);
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz =
                // clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return clazzList;
    }

    /**
     * 转换数据类型
     *
     * @param paramMap
     *            {@link Map} 待转换的数据
     * @return Map<String, String>
     */
    public static Map<String, String> convert(Map<String, Object> paramMap) {
        Map<String, String> params = new HashMap<String, String>();
        Iterator<String> keys = paramMap.keySet().iterator();
        while (keys.hasNext()) {
            String mapKey = keys.next();
            Object value = paramMap.get(mapKey);
            params.put(mapKey, value == null ? "" : value.toString());
        }
        return params;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param object
     *            {@link Object} 子类对象
     * @param methodName
     *            {@link String} 父类中的方法名
     * @param parameterTypes
     *            {@link String}父类中的方法参数类型
     * @return {@link Method} 父类中的方法对象
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz =
                // clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected, default)
     *
     * @param object
     *            {@link Object} 子类对象
     * @param methodName
     *            {@link String} 父类中的方法名
     * @param parameterTypes
     *            {@link Class} 父类中的方法参数类型
     * @param parameters
     *            {@link Object} 父类中的方法参数
     * @return {@link Object} 父类中方法的执行结果
     */

    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        // 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        // 抑制Java对方法进行检查,主要是针对私有方法而言
        method.setAccessible(true);
        try {
            if (null != method) {
                // 调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object
     *            {@link Object} 子类对象
     * @param fieldName
     *            {@link String} 父类中的属性名
     * @return {@link String} 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz =
                // clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object
     *            {@link Object} 子类对象
     * @param fieldName
     *            {@link String} 父类中的属性名
     * @param value
     *            {@link String} 将要设置的值
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);
        try {
            // 如果获取的属性为空直接返回Null
            if (field == null) {
                return;
            }
            // 抑制Java对其的检查
            field.setAccessible(true);
            // 将 object 中 field 所代表的值 设置为 value
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将Map中的值设置到bean中，bean必须是实例化后的对象
     *
     * @param paramMap
     *            {@link Map} 参数Map
     * @param bean
     *            {@link Object} 赋值后的对象
     */
    public static void setBean(Map<String, Object> paramMap, Object bean) {
        if (paramMap == null || paramMap.isEmpty())
            throw new IllegalArgumentException("parameter paramMap must have a value");
        if (bean == null)
            throw new IllegalArgumentException("bean the value of must be instantiated");
        // 获得key迭代器
        Iterator<String> it = paramMap.keySet().iterator();
        while (it.hasNext()) {
            String fieldName = it.next();
            setFieldValue(bean, fieldName, paramMap.get(fieldName));
        }
    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object
     *            {@link Object} 子类对象
     * @param fieldName
     *            {@link String} 父类中的属性名
     * @return {@link Object} 父类中的属性值
     */

    public static Object getFieldValue(Object object, String fieldName) {
        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);
        // 如果获取的属性为空直接返回Null
        if (field == null) {
            return null;
        }
        try {
            // 抑制Java对其的检查
            field.setAccessible(true);
            // 获取 object 中 field 所代表的属性值
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     *            {@link Method} 方法数组
     * @param fieldGetMet
     *            {@link String} 属性get方法名称
     * @return boolean 检查属性Get方法是否存在
     */
    private static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    private static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return GET + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * 对象属性copy
     *
     * @param source
     *            {@link Object} 拷贝对象来源
     * @param target
     *            {@link Object} 拷贝到目标对象
     * @param ignores
     *            {@link String[]} 忽略的属性名称
     * @param allowNull
     *            {@link Boolean} 允许空值
     */
    public static void copyProperties(Object source, Object target, String[] ignores, boolean allowNull) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            String fieldName = field.getName();
            Object sourceFieldValue = getFieldValue(source, fieldName);
            if (ignores != null && ignores.length != 0 && Arrays.asList(ignores).indexOf(fieldName) != -1) {
                continue;
            }
            if (!allowNull && sourceFieldValue == null || isEmpty(sourceFieldValue)) {
                continue;
            }
            setFieldValue(target, fieldName, sourceFieldValue);
        }
    }

    /**
     * 是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if (obj == null)
            return true;
        return false;
    }

    /**
     * 对象属性copy
     *
     * @param source
     *            {@link Object} 拷贝对象来源
     * @param target
     *            {@link Object} 拷贝到目标对象
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, null, true);
    }
}
