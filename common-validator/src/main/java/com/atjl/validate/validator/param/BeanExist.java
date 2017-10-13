package com.atjl.validate.validator.param;

import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * spring bean 存在性校验
 * <p>
 * !!! 需要spring
 *
 * @author jasonliu
 */
public class BeanExist extends ValidatorBase {
    public static final String DFT_MSG = "spring bean 不存在";

    //private String beanName;

    public BeanExist() {
        super(DFT_MSG);
    }

    public BeanExist(String msg) {
        super(msg);
    }

//    private void chk() {
//        if (StringCheckUtil.isEmpty(beanName)) {
//            throw new ValidateInitException("BeanExist beanName null");
//        }
//    }
//    public void init(String msg, boolean fmt) {
//    }

    public void validate(ValidateForm form, ValidateField field) {
        boolean succ = false;
        Object bean = null;
        try {
            bean = ApplicationContextHepler.getBeanByName(field.getStrValue());
            succ = true;
        } catch (Exception e) {
        }
        if (bean == null || !succ) {
            //this.msg = String.format(msg, field.getStrValue());
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {

        return null;
    }
}
