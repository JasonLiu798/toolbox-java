package com.atjl.util.reflect;

import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionFilterUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil.GetClzOpt;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
     * cache class field list
     */
    private static class FieldCache {
        private static ConcurrentHashMap<String, SoftReference<List<Field>>> cache = new ConcurrentHashMap<>();

        public static String genKey(Class clz, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
            StringBuilder key = new StringBuilder().append(clz.getName()).append(parentOpt);
            if (blackArr != null) {
                key.append("B[");
                for (String b : blackArr) {
                    key.append(b).append(",");
                }
            }
            if (whiteArr != null) {
                key.append("W[");
                for (String w : whiteArr) {
                    key.append(w).append(",");
                }
            }
            return key.toString();
        }

        public static List<Field> get(Class clz, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
            SoftReference<List<Field>> sr = cache.get(genKey(clz, parentOpt, blackArr, whiteArr));
            if (sr != null) {
                return sr.get();
            }
            return null;
        }

        public static void put(Class clz, GetClzOpt opt, String[] blackArr, String[] whiteArr, List<Field> fields) {
            SoftReference<List<Field>> sr = new SoftReference<>(fields);
            cache.put(genKey(clz, opt, blackArr, whiteArr), sr);
        }
    }

    /**
     * 字段拷贝
     *
     * @param allowNull source对象中的空值 是否设置 到 target对象
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
            field.setAccessible(true);
            Object sourceFieldValue = null;
            try {
                sourceFieldValue = field.get(source);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
     * src多出的字段：不会报错
     * 字段类型匹配： Long和Integer互转
     * src或dest为空：抛异常
     */
    public static void copyFieldUsePU(Object src, Object tgt) {
        try {
            PropertyUtils.copyProperties(tgt, src);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            logger.error("copyfield use proputil error {}", e);
        }
    }

    /**
     */
    public static void copyFieldUseBU(Object src, Object tgt) {
        try {
            BeanUtils.copyProperties(tgt, src);
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error("copyfield use beanutil error {}", e);
        }
    }

    /**
     * ######################### copy ################################
     */
    /**
     * @param src
     * @param tgt
     */
    public static void copyFieldUseDz(Object src, Object tgt) {
        try {
            DozerBeanMapper mapper = MapperInstanceCache.getInstance();
//            mapper.addMapping(genBuilder(src.getClass(), tgt.getClass()));
            mapper.map(src, tgt);
        } catch (Exception e) {
            logger.error("copyfield use dozer error {}", e);
        }
    }

    /**
     * BeanMappingBuilder builder = new BeanMappingBuilder() {
     * protected void configure() {
     * mapping(Bean.class, Bean.class,
     * TypeMappingOptions.oneWay(),
     * mapId("A"),
     * mapNull(true)
     * )
     * .exclude("excluded")
     * .fields("src", "dest",
     * copyByReference(),
     * collectionStrategy(true,
     * RelationshipType.NON_CUMULATIVE),
     * hintA(String.class),
     * hintB(Integer.class),
     * FieldsMappingOptions.oneWay(),
     * useMapId("A"),
     * customConverterId("id")
     * )
     * .fields("src", "dest",
     * customConverter("org.dozer.CustomConverter")
     * );
     * }
     * };
     * <p>
     * public static void copyFieldUseDz(Object src, Object tgt, List<String> excludes) {
     * if (src == null || tgt == null) {
     * return;
     * }
     * List<String> excludeFiltered = StringUtil.filterEmpty(excludes);
     * if (excludeFiltered == null || excludeFiltered.size() == 0) {
     * copyFieldUseDz(src, tgt);
     * return;
     * }
     * <p>
     * //        String key = MapperInstanceCache.genKey(src.getClass(), tgt.getClass(), excludeFiltered);
     * DozerBeanMapper mapper = MapperInstanceCache.getInstance();
     * //        DozerBeanMapper mapper = new DozerBeanMapper();//MapperInstanceCache.getInstance();
     * BeanMappingBuilder builder = genExcludeBuilder(src.getClass(), tgt.getClass(), excludeFiltered);
     * mapper.addMapping(builder);
     * mapper.map(src, tgt);
     * }
     */

//    private static BeanMappingBuilder genBuilder(Class src, Class tgt) {
//        return new BeanMappingBuilder() {
//            protected void configure() {
//                TypeMappingBuilder typeMappingBuilder = mapping(src, tgt);
//            }
//        };
//    }

//    private static BeanMappingBuilder genExcludeBuilder(Class src, Class tgt, List<String> excludes) {
//        return new BeanMappingBuilder() {
//            protected void configure() {
//                TypeMappingBuilder typeMappingBuilder = mapping(src, tgt);
//                excludes.forEach(typeMappingBuilder::exclude);
//            }
//        };
//    }

    private static class MapperInstanceCache {
        private static DozerBeanMapper mapper = new DozerBeanMapper();
        /*
        private static ConcurrentHashMap<String, DozerBeanMapper> mapperCache = new ConcurrentHashMap<>();

        private static String genKey(Class src, Class tgt, String[] excludes) {
            StringBuilder res = new StringBuilder(src.getName() + tgt.getName());
            for (String exclude : excludes) {
                res.append(exclude);
            }
            return res.toString();
        }*/

        public static DozerBeanMapper getInstance() {
            return mapper;
        }
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
     * @param clz       对象
     * @param parentOpt 选项
     * @param blackArr  黑名单
     * @param whiteArr  白名单（优先级高于黑名单）
     * @return
     */
    public static List<Field> getFieldList(Class clz, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
        List<Field> l = FieldCache.get(clz, parentOpt, blackArr, whiteArr);
        if (l != null) {
            return l;
        }

        List<Class<?>> clazzList = ReflectClassUtil.getClassList(clz, parentOpt);
//        if (logger.isDebugEnabled()) {
//            logger.debug("getField clz list:{}", clazzList);
//        }
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

        FieldCache.put(clz, parentOpt, blackArr, whiteArr, res);
//        if (logger.isDebugEnabled()) {
//            logger.debug("get fields res {}", res);
//        }
        return res;
    }


    /**
     * 获取 字段列表，不包含 值为空的字段
     */
    public static List<Field> getFieldList(Object obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr, boolean filterNull) {
        List<Class<?>> clazzList = ReflectClassUtil.getClassList(obj, parentOpt);
//        if (logger.isDebugEnabled()) {
//            logger.debug("getField clz list:{}", clazzList);
//        }
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

//        if (logger.isDebugEnabled()) {
//            logger.debug("get fields res {}", res);
//        }
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
