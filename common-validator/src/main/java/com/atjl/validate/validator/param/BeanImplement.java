package com.atjl.validate.validator.param;

import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.ValidatorBase;

/**
 * spring bean 是否实现指定接口
 *
 * @author jasonliu
 */
public class BeanImplement extends ValidatorBase {
    public static final String DFT_MSG = "bean未实现接口";

    private Class intf;

    public BeanImplement(Class intf) {
        init(intf, DFT_MSG, true);
    }

    public BeanImplement(Class intf, String msg) {
        init(intf, msg, false);
    }

    private void init(Class intf, String msg, boolean fmt) {
        this.intf = intf;
        chk();
        if (fmt) {
            this.msg += this.intf.getSimpleName();
        } else {
            this.msg = msg;
        }
    }

    private void chk() {
        if (this.intf == null) {
            throw new ValidateInitException("指定接口为空");
        }
    }

    public void validate(ValidateForm form, ValidateField field) {
        boolean getBean = false;
        Object bean = null;
        try {
            bean = ApplicationContextHepler.getBeanByName(field.getStrValue());
            getBean = true;
        } catch (Exception e) {
        }
        if (bean == null || !getBean) {
            throw new ValidateException(this.msg);
        }
        if (!ReflectClassUtil.chkAImplementB(bean, intf)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {

        return null;
    }
}
