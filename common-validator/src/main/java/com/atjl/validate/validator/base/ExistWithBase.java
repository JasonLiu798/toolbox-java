package com.atjl.validate.validator.base;

import com.atjl.common.domain.KeyValue;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.field.ValidateField;

import java.util.ArrayList;
import java.util.List;

/**
 * 存在则报错(条件包含参考字段)
 * !!! 需要spring和 dataSource
 */
public abstract class ExistWithBase extends ExistBase {
//    private static final String DFT_MSG = "%s已经存在";

    /**
     * input:
     * refFieldName1:columnName1|refFieldName1:columnName1
     * key: field
     * value: column
     */
    protected List<KeyValue> refs;

    protected boolean refExist;
//    protected Map<String,String> refFieldColumn;

    private void initRefs(List<KeyValue> raw) {
        if (!StringCheckUtil.isEmpty(raw)) {
            this.refs = raw;
            refExist = true;
        } else {
            this.refs = new ArrayList<>();
            refExist = false;
        }
    }

//    public ExistWithBase(String table, String column, List<KeyValue> refs,String otherCond,String msg) {
//        super(table, column, otherCond,msg);
//        initRefs(refs);
//    }

    //        super(table, column, otherConds, DFT_MSG);

    public ExistWithBase(String table, String column, List<KeyValue> refs, String msg) {
        super(table, column, msg);
        initRefs(refs);
    }

    public ExistWithBase(String table, String column, List<KeyValue> refs, String otherConds, String msg) {
        super(table, column, otherConds, msg);
        initRefs(refs);
    }

    public abstract void validate(ValidateForm form, ValidateField field);

    public String getConds(ValidateForm form) {
        StringBuilder refSqlPart = new StringBuilder();
        if (refExist) {
            for (KeyValue kv : refs) {
                ValidateField f = form.getField(kv.getKey());
                String val = "";
                if (f != null) {
                    //!! 不为空，不抛异常
                    val = f.getStrValue();
                }
                refSqlPart.append(" and ").append(kv.getValue()).append("='").append(val).append("'");
            }
        }
        refSqlPart.append(" ");
        if (otherConds != null) {
            refSqlPart.append(otherConds);
        }
        return refSqlPart.toString();
    }


    /*
    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getStrValue();

        StringBuilder refSqlPart = new StringBuilder();
        if (refExist) {
            for (KeyValue kv : refs) {
                ValidateField f = form.getField(kv.getKey());
                String val = "";
                if (f != null) {
                    //!! 不为空，不抛异常
                    val = f.getStrValue();
                }
                refSqlPart.append(" and ").append(kv.getValue()).append("='").append(val).append("'");
            }
        }
        refSqlPart.append(" ");
        if (otherConds != null) {
            refSqlPart.append(otherConds);
        }
        String sqlPart = refSqlPart.toString();

        if (ValidateDbUtil.exist(table, column, sqlPart, raw)) {
            throw new ValidateException(String.format(this.msg, raw));
        }
    }*/


//    abstract public Validator parse(String str) {
//        return null;
//    }
}
