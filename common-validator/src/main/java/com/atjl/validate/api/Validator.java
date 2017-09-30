package com.atjl.validate.api;


import com.atjl.validate.api.field.ValidateField;

public interface Validator {

    void validate(ValidateForm form, ValidateField field);

    /**
     * 通过字符串 初始化 校验器
     * 可不实现，Feild初始化时，只能新建对象
     *
     * @param str
     * @return
     */
    Validator parse(String str);
}
