package com.atjl.validate.util;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CovertUtil;
import com.atjl.util.config.ConfigPropUtil;
import com.atjl.util.db.DbExecutor;
import com.atjl.util.db.DbExecutorSyntaxException;
import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.validate.domain.constants.ValidateInnerConstant;
import com.atjl.validate.domain.constants.ValidatePropConstants;
import com.atjl.validate.api.exception.ValidateComponentException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * db语句执行 辅助类
 *
 * @author jasonliu
 */
public class ValidateDbUtil {
    private ValidateDbUtil() {
        super();
    }

    /**
     * @return
     */
    public static DataSource getDs() {
        String dataSource = ConfigPropUtil.getAndInit(ValidateInnerConstant.VALIDATE_PROP_FILE, ValidatePropConstants.DATA_SOURCE_KEY);
        DataSource ds = ApplicationContextHepler.getBean(dataSource, DataSource.class);
        if (ds == null) {
            throw new ValidateComponentException("get dataSource null");
        }
        return ds;
    }

    public static boolean tableColumnExist(String table, String column, String otherCond) {
        String sql = ValidateSqlUtil.genTabColChkSql(table, column, otherCond);
        try {
            DbExecutor.getTableData(getDs(), sql);
        } catch (DbExecutorSyntaxException e) {
            return false;
        }
        return true;
    }


    public static boolean exist(String table, String column, String otherConds, String val) {
        String sql = ValidateSqlUtil.genExistSql(table, column, otherConds, val);
        List<Map<String, Object>> res = DbExecutor.getTableData(getDs(), sql);
        if (CollectionUtil.isEmpty(res)) {
            return false;
        }
        Map<String, Object> item = res.get(0);
        Integer cnt = CovertUtil.covertObj(item.get(ValidateSqlUtil.CNT_COL), Integer.class);
        if (cnt == null) {
            return false;
        }
        if (cnt == 0) {
            return false;
        }
        return true;
    }
}
