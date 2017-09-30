package com.atjl.validate.api.field;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.api.Validator;

import java.io.Serializable;

/**
 * 集合字段，字段类型实现了List接口
 * 目前支持：
 * List
 * Set
 *
 * @author jasonliu
 */
public class ListField extends AbstractField implements Serializable {

    private static final long serialVersionUID = -7623439851764606594L;
    private ValidateForm refForm;//参考校验器

    /**
     * 初始化 label，校验器列表
     */
    public ListField(String label, Class refForm, Validator... validators) {
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
