package com.atjl.util.reflect;


import com.atjl.util.common.ReflectUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReflectClassUtil {
    private ReflectClassUtil(){
        throw new UnsupportedOperationException();
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
     * ########################## class list association ##########################
     **/

    /**
     * get class list,include self,all parent
     *
     * @param bean bean object
     * @return class list
     */
    public static List<Class<?>> getSelfAndParentClassList(Object bean) {
        return getClassList(bean.getClass(), ReflectUtil.GetClzOpt.ALL);
    }

    public static List<Class<?>> getSelfAndParentClassList(Class<?> clz) {
        return getClassList(clz, ReflectUtil.GetClzOpt.ALL);
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
    public static List<Class<?>> getClassList(Class<?> clz, ReflectUtil.GetClzOpt opt) {
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
                res.add(parClz);
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

    public static List<Class<?>> getClassList(Object bean, ReflectUtil.GetClzOpt opt) {
        if (bean == null) {
            return new ArrayList<>();
        }
        return getClassList(bean.getClass(), opt);
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
}
