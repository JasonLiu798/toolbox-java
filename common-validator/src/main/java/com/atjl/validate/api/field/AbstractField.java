package com.atjl.validate.api.field;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.Validator;
import com.atjl.validate.domain.constants.ValueMsg;
import com.atjl.validate.validator.param.RequireWith;
import com.atjl.validate.validator.param.RequireWithAll;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础字段类型
 * <p>
 * 1.标签
 * 2.原始值
 * 3.校验器列表初始化
 *
 * @author jasonliu
 */
public abstract class AbstractField implements ValidateField, Serializable {

    private static final long serialVersionUID = -7960782421148768598L;
    /**
     * 原始值和错误信息
     * 各线程需要隔离
     */
    transient protected ThreadLocal<ValueMsg> rawValue;
    /**
     * 标签
     */
    protected String label;
    /**
     * 校验器列表
     */
    protected List<Validator> validators;

    public AbstractField(String label, Validator... validators) {
        init(label, validators);
    }

    protected void init(String label, Validator... validators) {
        this.rawValue = new ThreadLocal<>();
        this.label = label;

        if (!CollectionUtil.isEmpty(validators)) {
            this.validators = new ArrayList<>(validators.length);
            for (Validator v : validators) {
                if (v instanceof Require || v instanceof Optional || v instanceof RequireWith || v instanceof RequireWithAll) {
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
    public String getStrValue() {
        ValueMsg vm = rawValue.get();
        if (vm != null) {
            return vm.getRawVal() == null ? "" : String.valueOf(vm.getRawVal());
        } else {
            return "";
        }
    }

    @Override
    public Object getRawValue() {
        return rawValue.get().getRawVal();
    }

    @Override
    public void setRawValue(Object val) {
        ValueMsg v = new ValueMsg();
        v.setRawVal(val);
        this.rawValue.set(v);
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }


    public void setMsg(String msg) {
        this.rawValue.get().setErrmsg(msg);
    }

    /**
     * 注：同时会清除错误信息
     */
    @Override
    public void clearValue() {
        this.rawValue.remove();
    }

    @Override
    public String getErrorMsg() {
        return this.rawValue.get().getErrmsg();
    }

    @Override
    public void setErrorMsg(String msg) {
        this.rawValue.get().setErrmsg(msg);
    }
}
