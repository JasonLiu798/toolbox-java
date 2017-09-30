package com.atjl.validate.validator.param;

import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateDbUtil;
import com.atjl.validate.validator.base.ExistBase;

/**
 * 存在则报错
 */
public class Exist extends ExistBase {
    private static final String DFT_MSG = "已经存在";

    public Exist(String table, String column) {
        super();
        init(table, column, DFT_MSG);
    }

    public Exist(String table,String column,String msg) {
        super(table,column,msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
        if (ValidateDbUtil.exist(table, column, raw)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}
