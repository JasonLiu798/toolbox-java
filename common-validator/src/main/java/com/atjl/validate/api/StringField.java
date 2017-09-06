package com.atjl.validate.api;


import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验字段
 * String类型字段
 * <p>
 * 目前z只支持 string类型
 *
 * @author jasonliu
 */
public class StringField implements ValidateField {

    private String rawValue;//原始值
    private String label;//标签
    private List<Validator> validators;//校验器列表

    /**
     * 初始化 label，校验器列表
     *
     * @param label
     * @param validators
     */
    public StringField(String label, Validator... validators) {
        this.label = label;
        if (!CollectionUtil.isEmpty(validators)) {
            this.validators = new ArrayList<>(validators.length);
            for (Validator v : validators) {
                if (v instanceof Require || v instanceof Optional) {
                    this.validators.add(0, v);//require 和 optional 放到第一
                } else {
                    this.validators.add(v);
                }
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getRawValue() {
        return rawValue;
    }

    @Override
    public void setRawValue(String val) {
        this.rawValue = val;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }
}
