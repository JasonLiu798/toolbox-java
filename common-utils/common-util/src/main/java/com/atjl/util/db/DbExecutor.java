package com.atjl.util.db;

//import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

/**
 * Db
 */
@Slf4j
public class DbExecutor {

    public static List<Map<String, Object>> getTableData(DataSource ds, String sql) {
        log.info("execute sql {}", sql);
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object val = rs.getObject(i);
                    row.put(columnName, val);
                }
                list.add(row);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            log.error("DbExecutor select ex {}",sql, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error("datasource close ex {}",sql, e);
            }
        }
        return list;
    }

}
