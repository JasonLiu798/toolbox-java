package com.atjl.validate.validator.param;

import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.validator.base.FieldIfEqualBase;

/**
 * refField字段值为refVal时，spring bean 是否实现指定接口
 * !!! 需要spring
 *
 * @author jasonliu
 */
public class BeanImplementIf extends FieldIfEqualBase {
    public static final String DFT_MSG = "%s值为%s时，bean未实现接口";

    private Class intf;

    public BeanImplementIf(String refField, String refVal, Class intf) {
        init(refField, refVal, DFT_MSG, true);
        innerInit(intf);
    }

    public BeanImplementIf(String refField, String refVal, Class intf, String msg) {
        init(refField, refVal, msg, false);
        innerInit(intf);
    }

    private void innerInit(Class intf) {
        this.intf = intf;
        innerChk();
        this.msg += this.intf.getSimpleName();
    }

    private void innerChk() {
        if (this.intf == null) {
            throw new ValidateInitException("指定接口为空");
        }
    }

    public void validate(ValidateForm form, ValidateField field) {
        if(needChk(form)){
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
    }

    @Override
    public Validator parse(String str) {

        return null;
    }
}
