package com.atjl.validate.validator.base;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateInitException;
import com.atjl.validate.util.ValidateDbUtil;

/**
 * 存在基类
 *
 * @author jasonliu
 */
public abstract class ExistBase extends ValidatorBase {
    protected String table;
    protected String column;
    public ExistBase(){}

    public ExistBase(String table, String column, String msg) {
        init(table, column, msg);
    }

    protected void init(String table, String column, String msg) {
        this.table = table;
        this.column = column;
        this.msg = msg;
        chk();
        dbChk();
    }

    private void chk() {
        if (!RegexUtil.isAlphaNumDash(table, column)) {
            throw new ValidateInitException("表名或列名异常");
        }
    }

    private void dbChk() {
        if (!ValidateDbUtil.tableColumnExist(table, column)) {
            throw new ValidateInitException("表名或列名数据库不存在异常");
        }
    }


    public abstract void validate(ValidateForm form, ValidateField field);

}
