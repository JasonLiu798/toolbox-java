package com.atjl.validate.util;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.config.ConfigPropUtil;
import com.atjl.util.db.DbExecutor;
import com.atjl.util.db.DbExecutorSyntaxException;
import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.validate.domain.constants.ValidateConstants;
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
        String dataSource = ConfigPropUtil.getAndInit(ValidateConstants.VALIDATE_PROP_FILE, ValidatePropConstants.DATA_SOURCE_KEY);
        DataSource ds = ApplicationContextHepler.getBean(dataSource, DataSource.class);
        if (ds == null) {
            throw new ValidateComponentException("get dataSource null");
        }
        return ds;
    }

    public static boolean tableColumnExist(String table, String column) {
        String sql = ValidateSqlUtil.genTabColChkSql(table, column);
        try {
            DbExecutor.getTableData(getDs(), sql);
        } catch (DbExecutorSyntaxException e) {
            return false;
        }
        return true;
    }


    public static boolean exist(String table, String column, String val) {
        String sql = ValidateSqlUtil.genExistSql(table, column, val);
        List<Map<String, Object>> res = DbExecutor.getTableData(getDs(), sql);
        return !CollectionUtil.isEmpty(res);
    }
}
