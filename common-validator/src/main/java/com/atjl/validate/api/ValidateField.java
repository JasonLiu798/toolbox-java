package com.atjl.validate.api;


import java.util.List;

public interface ValidateField {

    /**
     * 获取原始值
     *
     * @return
     */
    String getRawValue();

    /**
     * 设置原始值
     *
     * @param val
     */
    void setRawValue(String val);

    /**
     * 获取
     * @return
     */
    String getLabel();

    /**
     * 获取校验器列表
     *
     * @return
     */
    List<Validator> getValidators();
}
