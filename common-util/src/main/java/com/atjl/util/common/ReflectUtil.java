package com.atjl.util.common;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
     * ################## methods ######################
     */
    private static final String SET_PREFIX = "set";
    private static final String GET_PREFIX = "get";
    private static final String GET_BOOL_PREFIX = "is";


    /**
     * get field map type
     * 0   self and parents
     * 1   self
     * 2   parent
     * 3   parents
     */
    public enum ParentOpt {
        ALL,
        SELF,
        PARENT,
        ALLPARENT
    }

    private static Map<Class<?>, Class<?>> boxTypeMap = new HashMap<Class<?>, Class<?>>();

    static {
        boxTypeMap.put(byte.class, Byte.class);
        boxTypeMap.put(short.class, Short.class);
        boxTypeMap.put(int.class, Integer.class);
        boxTypeMap.put(long.class, Long.class);
        boxTypeMap.put(char.class, Character.class);
        boxTypeMap.put(float.class, Float.class);
        boxTypeMap.put(double.class, Double.class);
        boxTypeMap.put(boolean.class, Boolean.class);
    }


    /**
     * ######################### check methods ###########################
     */
    /**
     * is empty
     *
     * @param obj object
     * @return is empty or not
     */
    private static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;
        return false;
    }

    /**
     * check class exist
     *
     * @param clz class string
     * @return class is exist
     */
    public static boolean checkClassExist(String clz) {
        try {
            Class.forName(clz);
        } catch (ClassNotFoundException e1) {
            return false;
        }
        return true;
    }

    /**
     * Returns boxer of c or c if c can't be boxed.
     */
    public static Class<?> tryConvertToBoxClass(Class<?> c) {
        if (c == null) {
            return null;
        }
        if (boxTypeMap.containsKey(c)) {
            return boxTypeMap.get(c);
        }
        return c;
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
    public static Object getInstance(String classStr) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clz = Class.forName(classStr);
        return clz.newInstance();
    }


    /**
     * ################## interface ############################
     */
    /**
     * check A is implement interface B
     *
     * @param A check class
     * @param B parent or interface
     * @return is impl
     */
    public static boolean chkAImplementB(Class<?> A, Class<?> B) {
        return B.isAssignableFrom(A);
    }

    public static boolean chkAImplementB(Object A, Class<?> B) {
        return chkAImplementB(A.getClass(), B);
    }

    public static boolean chkAImplementBList(Object A, List<Class<?>> B) {
        return chkAImplementBList(A.getClass(), B);
    }

    public static boolean chkAImplementBList(Class<?> A, List<Class<?>> B) {
        for (Class<?> intf : B) {
            if (chkAImplementB(A, intf)) {

                return true;
            }
        }
        return false;
    }


    /**
     * ######################### fields #############################
     */
	/**
	 * get fields
	 * @param obj
	 * @return
	 */
	public static List<Field> getAllField(Object obj) {
		List<Class<?>> clzList = getSelfAndParentClassList(obj);
		if(CollectionUtil.isEmpty(clzList)){
			return new ArrayList<>();
		}
		List<Field> fieldList = new ArrayList<>(10);
		for (Class<?> clz : clzList) {
			Field[] fields = clz.getDeclaredFields();
			if(!CollectionUtil.isEmpty(fields)){
				fieldList.addAll(CollectionUtil.array2List(fields));
			}
		}
		return fieldList;
	}
	
    /**
     * get field set,class self
     *
     * @param clz class to get
     * @return field set
     */
    public static Set<String> getFieldSet(Class<?> clz) {
        Field[] fields = clz.getDeclaredFields();
        Set<String> set = new HashSet<>();
        for (Field field : fields) {
            set.add(field.getName());
        }
        return set;
    }

    /**
     * get filed have setter method ,include parents
     *
     * @param clz class to get
     * @return field got setter
     */
    public static Set<String> getAllFieldsHaveSetter(Class<?> clz) {
        List<Class<?>> list = getSelfAndParentClassList(clz);
        Set<String> sets = new HashSet<>();
        for (Class<?> clzi : list) {
            sets.addAll(getFieldsHaveSetter(clzi));
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
        List<Class<?>> list = getSelfAndParentClassList(obj);
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
     * copy field
     *
     * @param source    source obj
     * @param target    target obj
     * @param ignores   ignore field
     * @param allowNull allow null field
     */
    public static void copyField(Object source, Object target, String[] ignores, boolean allowNull) {
        List<Field> fields = getAllField(source);
		if(CollectionUtil.isEmpty(fields)){
			if(logger.isWarnEnabled()){
				logger.warn("copy fields,source filed empty {}",source);
			}
		}
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            String fieldName = field.getName();
            Object sourceFieldValue = getterForce(source, fieldName);
            if (ignores != null && ignores.length != 0 && Arrays.asList(ignores).indexOf(fieldName) != -1) {
                continue;
            }
            if (!allowNull && sourceFieldValue == null || isEmpty(sourceFieldValue)) {
                continue;
            }
            setterForce(target, fieldName, sourceFieldValue);
        }
    }

    public static void copyField(Object source, Object target) {
        copyField(source, target, null, true);
    }

    /**
     * get field-value map
     *
     * @param obj            待取值的bean
     * @param allowNullValue {@link Boolean} 允许空值
     * @param parentOpt      {@link Integer}
     *                       获取父类值选项：
     *                       0   只获取本类
     *                       1 获取本类和所有父类
     *                       2 只获取上一级父类
     *                       3 获取所有父类
     * @param specifies      specified list
     *                       null,no effect
     *                       not null, only get list's feild
     * @param ignores        ignore field list
     *                       null, no effect
     *                       not null,list feilds not add to res
     * @return field name - value object
     */
    public static Map<String, Object> getFieldValueMap(Object obj,
                                                       boolean allowNullValue, ParentOpt parentOpt,
                                                       String[] ignores, String[] specifies) {
        List<Class<?>> clazzList = getClassList(obj, parentOpt);
        if (CollectionUtil.isEmpty(clazzList)) {
            return null;
        }

        Map<String, Object> res = new HashMap<>();

        //init specified list
        boolean specified = false;
        List<String> specifiedList = null;
        if (specifies != null && specifies.length != 0) {
            specified = true;
            specifiedList = Arrays.asList(specifies);
        }

        //init ignore list
        boolean ignored = false;
        List<String> ignoreList = null;
        if (ignores != null && ignores.length != 0) {
            ignored = true;
            ignoreList = Arrays.asList(ignores);
        }

        for (Class<?> cls : clazzList) {
            Method[] methods = cls.getDeclaredMethods();//get all method
            Field[] fields = cls.getDeclaredFields();//get all field
            for (Field field : fields) {
                try {

                    String fieldGetName = generateGetName(field.getName(), false);
                    //process boolean field
                    if (fieldGetName == null && isBoolean(field)) {
                        fieldGetName = generateGetName(field.getName(), true);
                    }
                    //in specified list field,not ignore
                    //process ignore
                    if (!specified &&
                            ignored &&
                            ignoreList.indexOf(field.getName()) != -1) {
                        continue;
                    }

                    //process not specified field
                    if (specified &&
                            specifiedList.indexOf(field.getName()) != -1) {
                        continue;
                    }

                    //check get method exist
                    if (!checkGetMethod(methods, fieldGetName)) {
                        continue;
                    }
                    Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                    Object fieldVal = fieldGetMet.invoke(obj, new Object[]{});

                    //process allow null
                    if (allowNullValue) {
                        res.put(field.getName(), fieldVal);
                        continue;
                    }

                    //not allow null,not add
                    if (fieldVal == null || isEmpty(fieldVal)) {
                        continue;
                    }
                    //field.getType();
                    res.put(field.getName(), fieldVal);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    continue;
                }
            }
        }
        return res;
    }


//    public static Filed[] get

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

    public static Map<String, Object> getFieldValueMapIncludeNull(Object bean) throws Exception {
        return getFieldValueMap(bean, true, ParentOpt.SELF, null, null);
    }

    public static Map<String, Object> getFieldValueMapAllIncludeNull(Object bean) throws Exception {
        return getFieldValueMap(bean, true, ParentOpt.ALL, null, null);
    }

    public static Map<String, Object> getFieldValueMap(Object bean, boolean allowNullValue) throws Exception {
        return getFieldValueMap(bean, allowNullValue, ParentOpt.SELF, null, null);
    }

    public static Map<String, Object> getFieldValueMapAll(Object bean, boolean allowNullValue) throws Exception {
        return getFieldValueMap(bean, allowNullValue, ParentOpt.ALL, null, null);
    }

    public static Map<String, Object> getFieldValueMapIncludeNullSpec(Object bean, String[] sepcifies) throws Exception {
        return getFieldValueMap(bean, true, ParentOpt.ALL, null, sepcifies);
    }

    /**
     * 取Bean的属性和值对应关系的MAP
     */
    public static Map<String, String> getFieldMap(Object bean, boolean allowNullValue, ParentOpt opt, String[] ignores, String[] specifies) throws Exception {

        return convert(getFieldValueMap(bean, allowNullValue, opt, ignores, specifies));
    }

    public static Map<String, String> getFieldMap(Object bean) throws Exception {
        return convert(getFieldValueMap(bean, false));
    }

    public static Map<String, String> getFieldMapAll(Object bean) throws Exception {
        return convert(getFieldValueMapAll(bean, false));
    }

    public static Map<String, String> getFieldMap(Object bean, boolean allowNullValue) throws Exception {
        return convert(getFieldValueMap(bean, allowNullValue));
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
            if (name.startsWith(SET_PREFIX)) {
                name = name.substring(SET_PREFIX.length());
                if (name.length() > 0) {
                    name = StringUtil.toLowerCaseFirstOne(name);
                    sets.add(name);
                }

            }
        }
        return sets;
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


    /**
     * ###################### class #########################
     */
    /**
     * 获取简化类名 手动取最后一节
     *
     * @param obj
     * @return
     */
    public static String getClassSimpleName(Object obj) {
        String wholeName = obj.getClass().getName();
        int dotIdx = wholeName.lastIndexOf(".");
        String res = "";
        if (dotIdx + 1 > 0 && dotIdx + 1 != wholeName.length() - 1) {
            res = wholeName.substring(dotIdx + 1);
        } else {
            res = wholeName;
        }
        return res;
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
        Field field = getDeclaredField(clz, fieldName);
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

    /**
     * get getter method,if type is boolean ,try to get is method
     *
     * @param clz   class
     * @param field field string
     * @return method
     */
    public static Method getGetterMethod(Class clz, String field) {
        String methodName = generateGetName(field, false);

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
                getterMethod = clz.getMethod(generateGetName(field, true));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return getterMethod;
    }

    public static Object getterInner(Object obj, Method method) throws InvocationTargetException, IllegalAccessException {
        if (method != null) {
            Object res = method.invoke(obj, (Object[]) null);
            return res;
        }
        return null;
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
        String methodName = SET_PREFIX + StringUtil.toUpperCaseFirstOne(field);
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
        Field field = getDeclaredField(object, fieldName);
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


    /**
     * ########################## class list association ##########################
     **/

    /**
     * get class list,include self,all parent
     *
     * @param bean bean object
     * @return class list
     */
    public static List<Class<?>> getSelfAndParentClassList(Object bean) {
        return getClassList(bean.getClass(), ParentOpt.ALL);
    }

    public static List<Class<?>> getSelfAndParentClassList(Class<?> clz) {
        return getClassList(clz, ParentOpt.ALL);
    }

    /**
     * get class list by option
     *
     * @param clz target class
     * @param opt option
     *            ALL self,parent,....gp
     *            PARENT parent
     *            ALLPARENT parent,....gp
     *            SELF return this
     * @return class list
     */
    public static List<Class<?>> getClassList(Class<?> clz, ParentOpt opt) {
        List<Class<?>> res = new LinkedList<>();
        switch (opt) {
            case ALL:
                for (Class<?> clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
                    res.add(clazz);
                }
                break;
            case PARENT://only get parent
                Class<?> parClz = clz.getSuperclass();
                if (parClz == Object.class || parClz == null) {//no parent or Object.class
                    return null;
                }
                res.add(clz);
                break;
            case ALLPARENT:
                for (Class<?> clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
                    if (clazz == clz) {
                        continue;
                    }
                    res.add(clazz);
                }
                break;
            default://默认只获取本类
                res.add(clz);
                break;
        }
        return res;
    }

    public static List<Class<?>> getClassList(Object bean, ParentOpt opt) {
        return getClassList(bean.getClass(), opt);
    }


    /**
     * covert to String
     *
     * @param paramMap feild - value Object map
     * @return field-value String
     */
    public static Map<String, String> convert(Map<String, Object> paramMap) {
        Map<String, String> params = new HashMap<>();
        if (paramMap == null) {
            return null;
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String mapKey = entry.getKey();
            Object value = entry.getValue();
            params.put(mapKey, value == null ? "" : value.toString());
        }
        return params;
    }


    /**
     * generate Get method
     *
     * @param field
     * @return String
     */
    private static String generateGetName(String field, boolean isBoolean) {
        if (StringCheckUtil.isEmpty(field)) {
            return null;
        }
        String upperFiled = StringUtil.toUpperCaseFirstOne(field);
        if (isBoolean) {
            return GET_BOOL_PREFIX + upperFiled;
        }
        return GET_PREFIX + upperFiled;
    }


}
