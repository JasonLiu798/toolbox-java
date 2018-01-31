package com.atjl.validate.validator.param;

import com.atjl.common.domain.KeyValue;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.util.ValidateDbUtil;
import com.atjl.validate.validator.base.ExistWithBase;

import java.util.List;

/**
 * 不存在则报错
 * <p>
 * !!! 需要spring和 dataSource
 */
public class NotExistWith extends ExistWithBase {
    public static final String DFT_MSG = "%s不存在";

    public NotExistWith(String table, String column, List<KeyValue> refs) {
        super(table, column, refs, DFT_MSG);
    }

    public NotExistWith(String table, String column, List<KeyValue> refs, String otherConds) {
        super(table, column, refs, otherConds, DFT_MSG);
    }

    public NotExistWith(String table, String column, List<KeyValue> refs, String otherConds, String msg) {
        super(table, column, refs, otherConds, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
//        if (!ValidateDbUtil.exist(table, column, otherConds, raw)) {
//            throw new ValidateException(String.format(this.msg, raw));
//        }
        String sqlPart = getConds(form);//refSqlPart.toString();
        if (!ValidateDbUtil.exist(table, column, sqlPart, raw)) {
            throw new ValidateException(String.format(this.msg, raw));
        }
    }

    @Override
    public Validator parse(String str) {
        return null;
    }
}