package com.atjl.validate.api.field;


import com.atjl.validate.api.Validator;

import java.util.List;

public interface ValidateField {
    /**
     * 获取字符串值
     * 不为空，返回 toString，如果为空 返回 ""
     */
    String getStrValue();

    /**
     * 获取原始值
     */
    Object getRawValue();


    String getErrorMsg();

    void setErrorMsg(String msg);

    /**
     * 设置原始值
     *
     * @param val 值
     */
    void setRawValue(Object val);

    /**
     * 获取 label
     */
    String getLabel();

    /**
     * 获取校验器列表
     */
    List<Validator> getValidators();

    void clearValue();

}
