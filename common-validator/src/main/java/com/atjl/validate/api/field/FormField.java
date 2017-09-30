package com.atjl.validate.api.field;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.api.Validator;

import java.io.Serializable;

/**
 * 校验字段
 * String类型字段
 * <p>
 * 目前z只支持 string类型
 *
 * @author jasonliu
 */
public class FormField extends AbstractField implements Serializable {

    private static final long serialVersionUID = 492747577377798033L;
    private ValidateForm refForm;//参考校验器

    /**
     * 初始化 label，校验器列表
     */
    public FormField(String label, Class refForm, Validator... validators) {
        super(label, validators);
        init(refForm);
    }

    private void init(Class refForm) {
        /**
         * 递归创建 form
         */
        this.refForm = ValidateFormFactory.build(refForm);
    }

    /**
     * 内容是否为非空
     *
     * @return
     */
    public boolean isRefForm() {
        return this.refForm != null;
    }


    public ValidateForm getRefForm() {
        return refForm;
    }


}
