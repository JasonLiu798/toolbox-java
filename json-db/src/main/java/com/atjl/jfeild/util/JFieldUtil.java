package com.atjl.jfeild.util;

import com.atjl.jfeild.domain.JFieldMeta;
import com.atjl.jfeild.domain.JTabMeta;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.util.reflect.ReflectFieldUtil;
import com.atjl.util.reflect.ReflectGetUtil;
import com.atjl.util.reflect.ReflectSetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 三种类型
 * TT-table索引字段
 * TB-basic json 中字段
 * TO-其他对应对象字段
 */
public class JFieldUtil {
    private static final Logger logger = LoggerFactory.getLogger(JFieldUtil.class);

    /**
     * 只更新 基础字段
     *
     * @param bizObj 更新了 primitive 值的对象
     */
    public static <T> T updatePrimitive(Object bizObj, T dbObj, JTabMeta meta) {
        return updateSpecified(bizObj, dbObj, meta, true, false, null);
    }


    /**
     * 更新指定字段
     * biz -> db
     * TT- copyfield
     * TB-dbObj中取出 json,反序列化 jsonObj，biz->jsonObj拷贝，反序列化，放回到dbObj
     * TO-bizObj取出对应对象，序列化json，放入dbObj对应字段
     *
     * @param bizObj     需要更新的业务对象，无需填充所有字段，主键字段必填
     * @param dbObj      db中查出原始对象
     * @param meta       表-对象 元信息
     * @param tb         是否拷贝 basic字段
     * @param to         是否拷贝 object字段
     * @param fieldNames 要更新的字段列表
     * @return 生成的db对象，可直接调用 updateSelective
     */
    public static <T> T updateSpecified(Object bizObj, T dbObj, JTabMeta meta, boolean tb, boolean to, List<String> fieldNames) {

        //basic json 中字段
        if (tb) {
            Object basicJsonObj = ReflectGetUtil.getterForce(dbObj, meta.getBasic().getColumnName());
            if (basicJsonObj != null) {
                String basicJson = String.valueOf(basicJsonObj);
                Object basicObj = JSONFastJsonUtil.jsonToObject(basicJson, meta.getBasic().getFieldType());
                List<String> blackList = null;
                if (to) {
                    blackList = fieldNames;
                }
                ReflectFieldUtil.copyField(bizObj, basicObj, ReflectUtil.GetClzOpt.ALL, false, CollectionUtil.list2array(blackList), null);
                ReflectSetUtil.setterForce(basicObj, meta.getBasic().getFieldName(), null);

                String basicJsonUpdated = JSONFastJsonUtil.objectToJson(basicObj);
                ReflectSetUtil.setterForce(dbObj, meta.getBasic().getColumnName(), basicJsonUpdated);
                ReflectSetUtil.setterForce(bizObj, meta.getBasic().getFieldName(), null);

            }
        }

        //table索引字段  无需指定，copy biz->dbObj
        ReflectFieldUtil.copyField(bizObj, dbObj, ReflectUtil.GetClzOpt.ALL, false, null, null);

        //其他 字段
        if (to && !CollectionUtil.isEmpty(fieldNames) && !CollectionUtil.isEmpty(meta.getFieldList())) {
            for (String fieldName : fieldNames) {
                JFieldMeta fieldMeta = meta.getField(fieldName);
                if (fieldMeta == null) {
                    continue;
                }
                Object obj = ReflectGetUtil.getterForce(bizObj, fieldMeta.getFieldName());
                if (obj != null) {
                    String objJson = JSONFastJsonUtil.objectToJson(obj);
                    ReflectSetUtil.setterForce(dbObj, fieldMeta.getColumnName(), objJson);
                    ReflectSetUtil.setterForce(bizObj, fieldMeta.getFieldName(), null);
                }
            }
        }

//        String basisJson = JSONFastJsonUtil.objectToJson(bizObj);
//        ReflectSetUtil.setterForce(dbObj, meta.getBasic().getColumnName(), basisJson);

        ReflectSetUtil.setterForce(dbObj, "updTm", null);
        ReflectSetUtil.setterForce(dbObj, "crtTm", null);
        ReflectSetUtil.setterForce(dbObj, "deleted", null);
        logger.debug("upd obj specified field,{}", JSONFastJsonUtil.objectToJson(dbObj));
        return dbObj;
    }


    public static <T> List<T> updateBatch(List dbObjs, JTabMeta jbiz, Class<T> bizClz) {
        if (CollectionUtil.isEmpty(dbObjs)) {
            return new ArrayList<>();
        }
        List<T> res = new ArrayList<>();
        for (Object dbObj : dbObjs) {
            res.add(update(dbObj, jbiz, bizClz));
        }
        return res;
    }

    /**
     * biz -> db
     */
    public static <T> T update(Object bizObj, JTabMeta meta, Class<T> dbClass) {
        Object dbObj = ReflectClassUtil.newInstance(dbClass);
        ReflectFieldUtil.copyField(bizObj, dbObj);

        if (!CollectionUtil.isEmpty(meta.getFieldList())) {
            filter(bizObj, dbObj, meta);
        }

        String json = JSONFastJsonUtil.objectToJson(bizObj);
        ReflectSetUtil.setterForce(dbObj, meta.getBasic().getColumnName(), json);

        ReflectSetUtil.setterForce(dbObj, "updTm", null);
        ReflectSetUtil.setterForce(dbObj, "crtTm", null);
        ReflectSetUtil.setterForce(dbObj, "deleted", null);
        logger.debug("upd obj,{}", JSONFastJsonUtil.objectToJson(dbObj));
        return (T) dbObj;
    }

    private static void filter(Object bizObj, Object dbObj, JTabMeta meta) {
        for (JFieldMeta f : meta.getFieldList()) {
            Object fieldVal = ReflectGetUtil.getterForce(bizObj, f.getFieldName());
            if (fieldVal != null) {
                String json = JSONFastJsonUtil.objectToJson(fieldVal);
                ReflectSetUtil.setterForce(dbObj, f.getColumnName(), json);
                ReflectSetUtil.setterForce(bizObj, f.getFieldName(), null);
            }
        }
    }

//    public static <T> T trans(Object db){
//	}

    /**
     * db -> biz
     */
    public static <T> List<T> selectBatch(List dbObjs, JTabMeta jbiz, Class<T> bizClz) {
        if (CollectionUtil.isEmpty(dbObjs)) {
            return new ArrayList<>();
        }
        List<T> res = new ArrayList<>();
        for (Object dbObj : dbObjs) {
            res.add(select(dbObj, jbiz, bizClz));
        }
        return res;
    }


    /**
     * db -> biz
     */
    public static <T> T select(Object dbObj, JTabMeta jbiz, Class<T> bizClz) {
        Object mainObjStrRaw = ReflectGetUtil.getterForce(dbObj, jbiz.getBasic().getColumnName());
        Object mainObj = json2obj(mainObjStrRaw, bizClz);

        if (mainObj == null) {
            mainObj = ReflectClassUtil.newInstance(bizClz);
            //throw new JsonFieldException("转换错误，基础信息json为空");
        }

        //再拷贝 ，注：顺序不可改，用db字段覆盖 json 串内的同名属性
        ReflectFieldUtil.copyField(dbObj, mainObj);
        //置空 basic字段
        ReflectSetUtil.setter(mainObj, jbiz.getBasic().getColumnName(), String.class, null);

        if (CollectionUtil.isEmpty(jbiz.getFieldList())) {
            return (T) mainObj;
        }

        for (JFieldMeta f : jbiz.getFieldList()) {
            Object bizFieldObj = ReflectGetUtil.getterForce(dbObj, f.getColumnName());
            if (bizFieldObj != null) {
                String bizFieldStr = bizFieldObj.toString();
                Object p = JSONFastJsonUtil.jsonToObject(bizFieldStr, f.getFieldType());
                ReflectSetUtil.setterForce(mainObj, f.getFieldName(), p);
                //置空 原始字段
                ReflectSetUtil.setter(mainObj, f.getColumnName(), String.class, null);
            }
        }
        return (T) mainObj;
    }


    public static Object json2obj(Object o, Class clz) {
        if (o != null) {
            String bizFieldStr = o.toString();
            return JSONFastJsonUtil.jsonToObject(bizFieldStr, clz);
        }
        return null;
    }

    /**
     TmProjectWithBLOBs prjRaw = tmProjectMapper.selectByPrimaryKey(pk);
     String basis = prjRaw.getBasisInfo();

     ProjectV2 p = JSONFastJsonUtil.jsonToObject(basis, ProjectV2.class);
     String crewRaw = prjRaw.getPrjCrew();
     ProjectCrews c = JSONFastJsonUtil.jsonToObject(crewRaw, ProjectCrews.class);
     p.setProjectCrews(c);

     ReflectFieldUtil.copyField(prjRaw, p);
     */


}
