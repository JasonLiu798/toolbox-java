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
 * 存在则报错(条件包含参考字段)
 * !!! 需要spring和 dataSource
 */
public class ExistWith extends ExistWithBase {
    private static final String DFT_MSG = "%s已经存在";

    /**
     * input:
     * refFieldName1:columnName1|refFieldName1:columnName1
     * key: field
     * value: column
     */
//    protected List<KeyValue> refs;

//    protected Map<String,String> refFieldColumn;

    // no other condition
    public ExistWith(String table, String column, List<KeyValue> refs) {
        super(table, column, refs, DFT_MSG);
    }

    // add other condition
    public ExistWith(String table, String column, List<KeyValue> refs, String otherConds) {
        super(table, column, refs, otherConds, DFT_MSG);
    }

    // add msg
    public ExistWith(String table, String column, List<KeyValue> refs, String otherConds, String msg) {
        super(table, column, refs, otherConds, msg);
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();
        String sqlPart = getConds(form);//refSqlPart.toString();
        if (ValidateDbUtil.exist(table, column, sqlPart, raw)) {
            throw new ValidateException(String.format(this.msg, raw));
        }
    }


    @Override
    public Validator parse(String str) {
        return null;
    }
}
