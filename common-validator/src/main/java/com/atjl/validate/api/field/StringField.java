package com.atjl.validate.api.field;

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
public class StringField extends AbstractField implements Serializable {

    private static final long serialVersionUID = 3806390407723904107L;

    public StringField(String label, Validator... validators) {
        super(label, validators);
    }

}
