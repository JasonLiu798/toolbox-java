package com.atjl.validate.validator.multiparam;

import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateDbUtil;
import com.atjl.validate.validator.base.ExistBase;

/**
 * 不存在则报错
 */
public class NotExist extends ExistBase {
    public static final String DFT_MSG = "不存在";

    public NotExist(String table, String column) {
        super(table, column, DFT_MSG);
    }

    public NotExist(String table, String column, String msg) {
        init(table, column, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        if (ValidateDbUtil.exist(table, column, raw)) {
            throw new ValidateException(this.msg);
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}