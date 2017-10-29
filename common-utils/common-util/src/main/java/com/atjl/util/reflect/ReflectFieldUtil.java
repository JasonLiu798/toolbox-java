package com.atjl.util.reflect;

import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionFilterUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil.GetClzOpt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 反射 field相关 辅助类
 *
 * @author JasonLiu
 */
public class ReflectFieldUtil {
    private ReflectFieldUtil() {
        throw new UnsupportedOperationException();
    }

    private static Logger logger = LoggerFactory.getLogger(ReflectFieldUtil.class);


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
    public static void copyField(Object source, Object target, GetClzOpt opt, boolean allowNull, String[] blackList, String[] whiteList) {
        List<Field> fields = getFieldList(source, opt, blackList, whiteList);
        if (CollectionUtil.isEmpty(fields)) {
            if (logger.isWarnEnabled()) {
                logger.warn("copy fields,source filed empty {}", source);
            }
            return;
        }
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            String fieldName = field.getName();
            Object sourceFieldValue = ReflectGetUtil.getterForce(source, fieldName);
            if (!allowNull && sourceFieldValue == null || ReflectCommonUtil.isEmpty(sourceFieldValue)) {
                continue;
            }
            ReflectSetUtil.setterForce(target, fieldName, sourceFieldValue);
        }
    }

    public static void copyField(Object source, Object target) {
        copyField(source, target, GetClzOpt.ALL, true, null, null);
    }

    /**
     * 递归拷贝
     *
     * @Param allowNull 是否拷贝空值
     *
    public static void copyField(Object source, Object target, GetClzOpt opt, boolean allowNull) {
    List<Field> fields = getFieldList(source, opt, null, null, true);
    if (CollectionUtil.isEmpty(fields)) {
    if (logger.isWarnEnabled()) {
    logger.warn("copy fields,source filed empty {}", source);
    }
    return;
    }
    for (Field field : fields) {
    if ("serialVersionUID".equals(field.getName())) {
    continue;
    }
    String fieldName = field.getName();
    Object sourceFieldValue = ReflectGetUtil.getterForce(source, fieldName);
    if (!allowNull && sourceFieldValue == null || ReflectCommonUtil.isEmpty(sourceFieldValue)) {
    continue;
    }
    if (sourceFieldValue == null) {
    ReflectSetUtil.setterForce(target, fieldName, null);
    return;
    } else {
    ClassType classType = CovertUtil.getClassType(sourceFieldValue.getClass());
    switch (classType) {
    case PRIMITIVE:
    ReflectSetUtil.setterForce(target, fieldName, sourceFieldValue);
    break;
    case OBJECT:
    //targetValue
    Object tgtVal = ReflectGetUtil.getterForce(target, fieldName);
    copyField(sourceFieldValue, tgtVal, opt, allowNull);
    break;
    case LIST:
    break;
    }
    }
    }
    }*/

    /**
     * 获取所有field
     *
     * @param obj
     * @param parentOpt
     * @param blackArr
     * @param whiteArr
     * @return
     */
    public static List<Field> getFieldList(Object obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
        if (obj == null) {
            logger.warn("get fields,obj null");
            return new ArrayList<>();
        }
        return getFieldList(obj.getClass(), parentOpt, blackArr, whiteArr);
    }

    public static List<Field> getFieldListAll(Object obj) {
        return getFieldList(obj, GetClzOpt.ALL, null, null);
    }


//    public static List<Field> getFieldList(Class obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
//        return getFieldList(obj, parentOpt, blackArr, whiteArr);
//    }

    /**
     * @param obj       对象
     * @param parentOpt 选项
     * @param blackArr  黑名单
     * @param whiteArr  白名单（优先级高于黑名单）
     * @return
     */
    public static List<Field> getFieldList(Class obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
        List<Class<?>> clazzList = ReflectClassUtil.getClassList(obj, parentOpt);
        if (logger.isDebugEnabled()) {
            logger.debug("getField clz list:{}", clazzList);
        }
        if (CollectionUtil.isEmpty(clazzList)) {
            return new ArrayList<>();
        }
        List<Field> res = new ArrayList<>();
        //init white list
        boolean filterWhite = false;
        List<String> whiteList = null;
        if (whiteArr != null && whiteArr.length != 0) {
            filterWhite = true;
            whiteList = Arrays.asList(whiteArr);
        }

        //init black list
        boolean filterBlack = false;
        List<String> blackList = null;
        if (blackArr != null && blackArr.length != 0) {
            filterBlack = true;
            blackList = Arrays.asList(blackArr);
            if (filterWhite) {
                blackList = CollectionFilterUtil.filterDelList(blackList, whiteList);
            }
        }

        for (Class<?> cls : clazzList) {
            Field[] fields = cls.getDeclaredFields();//get all field
            for (Field field : fields) {
                try {
                    //process black list
                    if (filterBlack &&
                            blackList.indexOf(field.getName()) >= 0) {
                        continue;
                    }
                    //process white list
                    if (filterWhite &&
                            whiteList.indexOf(field.getName()) < 0) {
                        continue;
                    }
                    //process white class list
//                    if (filterClzWhite && whiteClzList.indexOf(field.getType()) < 0) {
//                        continue;
//                    }

                    res.add(field);
                } catch (Exception e) {
                    logger.error("getFieldValue {}", e.getMessage());
                    continue;
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("get fields res {}", res);
        }
        return res;
    }


    /**
     * 获取 字段列表，不包含 值为空的字段
     */
    public static List<Field> getFieldList(Object obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr, boolean filterNull) {
        List<Class<?>> clazzList = ReflectClassUtil.getClassList(obj, parentOpt);
        if (logger.isDebugEnabled()) {
            logger.debug("getField clz list:{}", clazzList);
        }
        if (CollectionUtil.isEmpty(clazzList)) {
            return new ArrayList<>();
        }
        List<Field> res = new ArrayList<>();

        //init white list
        boolean filterWhite = false;
        List<String> whiteList = null;
        if (whiteArr != null && whiteArr.length != 0) {
            filterWhite = true;
            whiteList = Arrays.asList(whiteArr);
        }

        //init black list
        boolean filterBlack = false;
        List<String> blackList = null;
        if (blackArr != null && blackArr.length != 0) {
            filterBlack = true;
            blackList = Arrays.asList(blackArr);
            if (filterWhite) {
                blackList = CollectionFilterUtil.filterDelList(blackList, whiteList);
            }
        }

        for (Class<?> cls : clazzList) {
            Field[] fields = cls.getDeclaredFields();//get all field
            for (Field field : fields) {
                try {
                    //process black list
                    if (filterBlack && blackList.indexOf(field.getName()) >= 0) {
                        continue;
                    }
                    //process white list
                    if (filterWhite && whiteList.indexOf(field.getName()) < 0) {
                        continue;
                    }

                    field.setAccessible(true);
                    Object val = field.get(obj);

                    if (val == null && filterNull) {
                        continue;
                    }
                    res.add(field);
                } catch (Exception e) {
                    logger.error("getFieldList {}", e.getMessage());
                    continue;
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("get fields res {}", res);
        }
        return res;
    }


    /**
     * 获取 field map
     *
     * @param obj
     * @param parentOpt
     * @param blackArr
     * @param whiteArr
     * @return
     */
    public static Map<String, Field> getFieldMap(Class obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
        List<Field> l = getFieldList(obj, parentOpt, blackArr, whiteArr);
        if (CollectionUtil.isEmpty(l)) {
            return new HashMap<>();
        }
        Map<String, Field> res = new HashMap<>();
        for (Field f : l) {
            res.put(f.getName(), f);
        }
        return res;
    }

    /**
     * ############################# filters ##############################
     */

    /**
     * @param fields
     * @param whiteClzArr
     * @return
     */
    public static List<Field> filterField(List<Field> fields, Class[] whiteClzArr) {
        if (CollectionUtil.isEmpty(fields)) {
            return fields;
        }
        boolean filterClzWhite = false;
        List<Class> whiteClzList = null;
        if (whiteClzArr != null && whiteClzArr.length > 0) {
            filterClzWhite = true;
            whiteClzList = Arrays.asList(whiteClzArr);
        }
        if (!filterClzWhite) {
            return fields;
        }

        List<Field> res = new ArrayList<>(fields.size());
        for (Field f : fields) {
            if (filterClzWhite && whiteClzList.indexOf(f.getType()) < 0) {
                continue;
            }
            res.add(f);
        }
        return res;
    }


    /**
     * get filed have setter method ,include parents
     *
     * @param clz class to get
     * @return field got setter
     */
    public static Set<String> getAllFieldsHaveSetter(Class<?> clz) {
        List<Class<?>> list = ReflectClassUtil.getSelfAndParentClassList(clz);
        Set<String> sets = new HashSet<>();
        for (Class<?> clzi : list) {
            sets.addAll(getFieldsHaveSetter(clzi));
        }
        return sets;
    }


    /**
     * get filed have setter method ,not include  parents's field
     *
     * @param clz class to get
     * @return field got set-method Set
     */
    public static Set<String> getFieldsHaveSetter(Class<?> clz) {
        Method[] methods = clz.getMethods();
        Set<String> sets = new HashSet<>();
        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith(ReflectConstant.SET_PREFIX)) {
                name = name.substring(ReflectConstant.SET_PREFIX.length());
                if (name.length() > 0) {
                    name = StringUtil.toLowerCaseFirstOne(name);
                    sets.add(name);
                }

            }
        }
        return sets;
    }

    /**
     * get field type
     *
     * @param obj       target field
     * @param fieldName field name
     * @return field type
     */
    public static <T> Class<?> getFieldType(T obj, String fieldName) {
        Class<?> clz = obj.getClass();
        try {
            Field field = clz.getDeclaredField(fieldName);
            return field.getType();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get field 's type include parent class
     *
     * @param obj       target object
     * @param fieldName field name
     * @return class type include parent
     */
    public static <T> Class<?> getFieldTypeAll(T obj, String fieldName) {
        List<Class<?>> list = ReflectClassUtil.getSelfAndParentClassList(obj);
        for (Class<?> clz : list) {
            Field field = null;
            try {
                field = clz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                logger.warn("class field not found ");
            }
            if (field != null) {
                return field.getType();
            }
        }
        return null;
    }


    /**
     * ############################# value ############################
     *
     public static Map<String, Object> getFieldValue(Object obj, GetClzOpt parentOpt, boolean allowNullValue, String[] blackArr, String[] whiteArr) {
     if (obj == null) {
     return null;
     }
     return getFieldValue(obj.getClass(), parentOpt, allowNullValue, blackArr, whiteArr);
     }*/

    /**
     * get field-value map
     *
     * @param obj            待取值的bean
     * @param parentOpt      {@link Integer}
     *                       获取父类值选项：
     * @param allowNullValue {@link Boolean} 允许空值
     * @param blackArr       ignore field list
     *                       null, no effect
     *                       not null,list feilds not add to res
     *                       注：白名单优先级高于黑名单
     * @param whiteArr       specified list
     *                       null,no effect
     *                       not null, only get list's feild
     *                       设置后，只取此列表指定的field
     * @return field name - value object
     */
    public static Map<String, Object> getFieldValue(Object obj, GetClzOpt parentOpt, boolean allowNullValue, String[] blackArr, String[] whiteArr) {
        if (obj == null) {
            return null;
        }
        Class clz = obj.getClass();

        List<Field> fields = getFieldList(clz, parentOpt, blackArr, whiteArr);
        if (CollectionUtil.isEmpty(fields)) {
            return new HashMap<>();
        }

        Map<String, Object> res = new HashMap<>();
        for (Field field : fields) {
            try {
                //String fieldGetName = generateGetName(field.getName(), false);
                field.setAccessible(true);
                Object fieldVal = field.get(obj);

                //process allow null
                if (allowNullValue) {
                    res.put(field.getName(), fieldVal);
                    continue;
                }
                //not allow null,not add
                if (fieldVal == null || ReflectCommonUtil.isEmpty(fieldVal)) {
                    continue;
                }
                res.put(field.getName(), fieldVal);
            } catch (Exception e) {
                logger.error("getFieldValue {}", e.getMessage());
                continue;
            }
        }
        return res;
    }

    public static Map<String, Object> getFieldValue(Object obj, String[] blackList) {
        return getFieldValue(obj, GetClzOpt.ALL, true, blackList, null);
    }

    public static Map<String, Object> getFieldValue(Object obj) {
        return getFieldValue(obj, GetClzOpt.ALL, true, null, null);
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     */
    public static Map<String, String> getFieldValueString(Object bean, GetClzOpt opt, boolean allowNullValue, String[] black, String[] white) {
        return ReflectCommonUtil.convert(getFieldValue(bean, opt, allowNullValue, black, white));
    }

    public static Map<String, String> getFieldValueStringAll(Object bean) {
        return ReflectCommonUtil.convert(getFieldValue(bean, GetClzOpt.ALL, true, null, null));
    }


    /**
     * is boolean field
     *
     * @param field field name
     * @return
     */
    private static boolean isBoolean(Field field) {
        if (field.getType() == boolean.class || field.getType() == Boolean.class) {
            return true;
        }
        return false;
    }

    /**
     * get declare field
     *
     * @param clz       target object
     * @param fieldName field
     * @return field
     */
    public static Field getDeclaredField(Class clz, String fieldName) {
        Field field = null;
        for (; clz != Object.class; clz = clz.getSuperclass()) {
            try {
                field = clz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //logger.error("loop class parnet get field error {}", clz);
                continue;
            }
        }
        return null;
    }

    public static Field getDeclaredField(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        return getDeclaredField(clazz, fieldName);
    }

    public static Object getDeclaredFieldValue(Class clz, String fieldName) {
        Field f = getDeclaredField(clz, fieldName);
        try {
            return f.get(null);
        } catch (IllegalAccessException e) {
            logger.error("{}", e.getMessage());
            return null;
        }
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
    public static List<String> filed2string(List<Field> filesList) {
        if (!CollectionUtil.isEmpty(filesList)) {
            List<String> res = new ArrayList<>(filesList.size());
            for (Field f : filesList) {
                res.add(f.getName());
            }
            return res;
        }
        return new ArrayList<>();
    }

}
