package com.atjl.validate.validator.param;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * spring bean 存在性校验
 *
 * @author jasonliu
 */
public class BeanExist extends ValidatorBase {
    public static final String DFT_MSG = "%s spring bean 不存在";

    private String beanName;

    public BeanExist(String beanName) {
        init(beanName, DFT_MSG, true);
    }

    public BeanExist(String beanName, String msg) {
        init(beanName, msg, false);
    }

    private void chk() {
        if (StringCheckUtil.isEmpty(beanName)) {
            throw new ValidateInitException("BeanExist beanName null");
        }
    }

    public void init(String beanName, String msg, boolean fmt) {
        this.beanName = beanName;
        chk();
        if (fmt) {
            this.msg = String.format(msg, this.beanName);
        } else {
            this.msg = msg;
        }
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();

//        if (!refs.contains(raw)) {
//            throw new ValidateException(this.msg);
//        }
    }

    @Override
    public Validator parse(String str) {

        return null;
    }
}
