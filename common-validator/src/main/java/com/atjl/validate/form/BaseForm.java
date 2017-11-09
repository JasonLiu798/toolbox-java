package com.atjl.validate.form;

import com.atjl.common.api.annotation.ThreadSafe;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.util.reflect.ReflectFieldUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.exception.ValidateNotRecMsgException;
import com.atjl.validate.api.field.FormField;
import com.atjl.validate.api.field.ListField;
import com.atjl.validate.api.field.StringField;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.util.ValidateCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单基类
 *
 * @author jasonliu
 */
@ThreadSafe
public class BaseForm implements ValidateForm, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(BaseForm.class);
    private static final long serialVersionUID = 234361604226488140L;


    private Class formClz;//自定义form
    /**
     * ValidateField内的value，各线程不同，需要隔离
     */
    private Map<String, ValidateField> fieldMap;
    private Map<String, String> labelMap;
//    private Map<String, String> errorMap;//错误信息

    public BaseForm(Class formClz) {
        this.formClz = formClz;
        init();
    }

    public static BaseForm newForm(Class childForm) {
        BaseForm form = new BaseForm(childForm);
        form.init();
        return form;
    }


    /**
     * 初始化
     * <p>
     * 异常情况：
     * 子类父类存在相同名称字段
     */
    public void init() {
        if (this.formClz == null) {
            throw new ValidateInitException("自定义表单类为空");
        }
        Object formObj = ReflectClassUtil.newInstance(this.formClz);
        if (formObj == null) {
            throw new ValidateInitException("自定义表单对象无法创建");
        }

        List<Field> fields = ReflectFieldUtil.getFieldList(formObj, ReflectUtil.GetClzOpt.ALL, null, null);
        fields = ReflectFieldUtil.filterField(fields, CollectionUtil.newArr(StringField.class, FormField.class, ListField.class));
        if (CollectionUtil.isEmpty(fields)) {
            logger.warn("init form,field null");
            return;
        }
        if (ValidateCheckUtil.existSameField(ReflectFieldUtil.filed2string(fields))) {
            /**
             * 检查父类和子类中是否有相同名称字段
             */
            throw new ValidateInitException("存在相同名称字段");
        }

        //fieldMap = new ThreadLocal<>();//初始化
        fieldMap = new HashMap<>();
        //new HashMap<>();
        this.labelMap = new HashMap<>();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                ValidateField vf = (ValidateField) f.get(formObj);
                List<Validator> validators = vf.getValidators();
                if (CollectionUtil.isEmpty(validators)) {
                    throw new ValidateInitException("校验器为空");
                }

                fieldMap.put(f.getName(), vf);
                labelMap.put(f.getName(), vf.getLabel());
            } catch (IllegalAccessException e) {
                throw new ValidateInitException("表单字段无法访问");
            }
        }
        if (CollectionUtil.isEmpty(fieldMap)) {
            logger.warn("init form warning,empty field {}");
        }
//        errorMap = new HashMap<>();
    }

    /**
     * 重设输入值
     */
    public void setValue(Object param) {
        Map paramStrMap;
        if (param instanceof Map) {
            paramStrMap = (Map) param;
        } else {
            paramStrMap = ReflectFieldUtil.getFieldValue(param);
        }
        setValue(paramStrMap);
    }

    /**
     * 重设 输入值
     */
    @Override
    public void setValue(Map valueMap) {
        clearValue();//同时会清除 值和错误信息
        if (CollectionUtil.isEmpty(valueMap)) {
            return;
        }

        if (CollectionUtil.isEmpty(fieldMap)) {
            return;
        }

        for (Map.Entry<String, ValidateField> entry : fieldMap.entrySet()) {
            entry.getValue().setRawValue(valueMap.get(entry.getKey()));
        }
//        this.errorMap.clear();
    }


    public void clearValue() {
        if (CollectionUtil.isEmpty(fieldMap)) {
            return;
        }
        for (Map.Entry<String, ValidateField> entry : fieldMap.entrySet()) {
            ValidateField vf = entry.getValue();
            vf.clearValue();
        }
    }


    /**
     * @return 通过返回 ture,不通过返回 false
     */
    @Override
    public boolean validate() {
        long t = System.currentTimeMillis();
        boolean res = validateInner();
        long cost = System.currentTimeMillis() - t;
        logger.debug("validate res {},cost {}", res, cost);
        return res;
    }


    private boolean validateInner() {
        if (CollectionUtil.isEmpty(this.fieldMap)) {
            return true;
        }

        boolean existError = false;
        for (Map.Entry<String, ValidateField> entry : this.fieldMap.entrySet()) {
            ValidateField field = entry.getValue();
            List<Validator> validators = field.getValidators();
            if (!CollectionUtil.isEmpty(validators)) {
                try {
                    for (Validator v : validators) {
                        v.validate(this, field);
                    }
                    if (field instanceof FormField) {
                        ValidateForm refForm = ((FormField) field).getRefForm();
                        refForm.setValue(field.getRawValue());
                        /**
                         * 递归校验
                         */
                        if (!refForm.validate()) {
                            throw new ValidateException(refForm.getOneLineError());
                        }
                    } else if (field instanceof ListField) {
                        ValidateForm refForm = ((ListField) field).getRefForm();
                        Object raw = field.getRawValue();
//                        v.validate(this, field);
                        if (raw == null) {
                            logger.warn("list field value null");
                            continue;
                        }
                        List valList = (List) raw;
                        if (CollectionUtil.isEmpty(valList)) {
                            logger.warn("list field value empty");
                            continue;
                        }

                        List<String> errMsg = new ArrayList<>();
                        for (Object v : valList) {
                            refForm.setValue(v);
                            if (!refForm.validate()) {
                                errMsg.add(refForm.getOneLineError());
                            }
                        }
                        if (!errMsg.isEmpty()) {
                            throw new ValidateException(errMsg.toString());
                        }
                    }
                } catch (ValidateNotRecMsgException e) {
                    //Optional null do nothing
                } catch (ValidateException e) {
                    existError = true;
                    field.setErrorMsg(field.getLabel() + ":" + e.getMessage());
//                    errorMap.put(entry.getKey(), field.getLabel() + ":" + e.getMessage());
                }
            }
        }
        return !existError;
    }

    public ValidateField getField(String fieldKey) {
        return fieldMap.get(fieldKey);
    }

    public Map<String, ValidateField> getFields() {
        return fieldMap;
    }

    public String getFieldRawVal(String fieldKey) {
        ValidateField vf = fieldMap.get(fieldKey);
        if (vf == null) {
            return null;
        }
        return vf.getStrValue();
    }

    @Override
    public Map<String, String> getErrors() {
        Map<String, String> res = new HashMap<>();
        for (Map.Entry<String, ValidateField> entry : fieldMap.entrySet()) {
            if (!StringCheckUtil.isEmpty(entry.getValue().getErrorMsg())) {
                res.put(entry.getKey(), entry.getValue().getErrorMsg());
            }
        }
//        ValidateField vf = fieldMap.get(fieldKey);
        return res;//this.errorMap;
    }

    public String getOneLineError() {
        Map<String, String> errMap = getErrors();
        if (CollectionUtil.isEmpty(errMap)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int last = errMap.entrySet().size() - 1;
        int i = 0;
        for (Map.Entry<String, String> entry : errMap.entrySet()) {
            sb.append(entry.getValue());
            if (i != last) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }
}
