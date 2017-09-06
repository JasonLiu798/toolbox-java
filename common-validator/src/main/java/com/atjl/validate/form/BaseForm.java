package com.atjl.validate.form;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.validate.api.*;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.exception.ValidateNotRecMsgException;
import com.atjl.validate.util.ValidateCheckUtil;
import org.apache.http.annotation.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单基类
 *
 * @author jasonliu
 */
@NotThreadSafe
public class BaseForm implements ValidateForm {
    private static final Logger logger = LoggerFactory.getLogger(BaseForm.class);

    private Class formClz;//自定义form
    private Map<String, ValidateField> fieldMap;//
    private Map<String, String> labelMap;//
    private Map<String, String> errorMap;//错误信息

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
        List<Field> fields = ReflectUtil.getFields(this.formClz, ReflectUtil.GetClzOpt.ALL, null, null);
        fields = ReflectUtil.filterField(fields, CollectionUtil.newArr(StringField.class));
        if (CollectionUtil.isEmpty(fields)) {
            logger.warn("init form,field null");
            return;
        }
        if (ValidateCheckUtil.existSameField(ReflectUtil.filed2string(fields))) {
            throw new ValidateInitException("存在相同名称字段");
        }

        fieldMap = new HashMap<>();
        this.labelMap = new HashMap<>();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                ValidateField vf = (ValidateField) f.get(null);
                fieldMap.put(f.getName(), vf);
                labelMap.put(f.getName(), vf.getLabel());
            } catch (IllegalAccessException e) {
                throw new ValidateInitException("表单字段无法访问");
            }
        }
        if (CollectionUtil.isEmpty(fieldMap)) {
            logger.warn("init form warning,empty field {}");
        }
        errorMap = new HashMap<>();
    }

    /**
     * 输入值 设入
     *
     * @param valueMap
     */
    public void setValue(Map<String, String> valueMap) {
        if (CollectionUtil.isEmpty(valueMap)) {
            return;
        }
        if (CollectionUtil.isEmpty(fieldMap)) {
            return;
        }
        for (Map.Entry<String, ValidateField> entry : fieldMap.entrySet()) {
            entry.getValue().setRawValue(valueMap.get(entry.getKey()));
        }
    }

    public void setValue(Object param) {
        Map<String, String> paramStrMap = ReflectUtil.getFieldMapAll(param);
        setValue(paramStrMap);
    }

    /**
     * @return 通过返回 ture,不通过返回 false
     */
    @Override
    public boolean validate() {
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
                } catch (ValidateNotRecMsgException e) {
                    //Optional null do nothing
                } catch (ValidateException e) {
                    existError = true;
                    errorMap.put(entry.getKey(), field.getLabel() + e.getMessage());
                }
            }
        }
        return !existError;
    }

    public ValidateField getField(String fieldKey) {
        return fieldMap.get(fieldKey);
    }
    public String getFieldRawVal(String fieldKey) {
        ValidateField vf = fieldMap.get(fieldKey);
        if (vf == null) {
            return null;
        }
        return vf.getRawValue();
    }

    @Override
    public Map<String, String> getErrors() {
        return this.errorMap;
    }

    public String getOneLineError() {
        if (CollectionUtil.isEmpty(this.errorMap)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.errorMap.entrySet()) {
            sb.append(this.labelMap.get(entry.getKey())).append(entry.getValue());
        }
        return sb.toString();
    }
}
