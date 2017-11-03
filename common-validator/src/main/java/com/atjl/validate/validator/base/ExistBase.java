package com.atjl.validate.validator.base;

import com.atjl.util.character.RegexUtil;
import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.util.ValidateDbUtil;

/**
 * 存在基类
 * <p>
 * !!! 需要spring和 dataSource
 *
 * @author jasonliu
 */
public abstract class ExistBase extends ValidatorBase {
    protected String table;//所在表
    protected String column;//所在列
    /**
     * 附加条件
     */
    protected String otherConds;

    public ExistBase() {
    }

    public ExistBase(String table, String column, String msg) {
        init(table, column, null, msg);
    }

    public ExistBase(String table, String column, String otherConds, String msg) {
        init(table, column, otherConds, msg);
    }

    protected void init(String table, String column, String otherConds, String msg) {
        this.table = table;
        this.column = column;
        this.otherConds = otherConds;
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
        if (!ValidateDbUtil.tableColumnExist(table, column, otherConds)) {
            throw new ValidateInitException("表名、列名或附加条件数据库执行异常");
        }
    }


    public abstract void validate(ValidateForm form, ValidateField field);

}
