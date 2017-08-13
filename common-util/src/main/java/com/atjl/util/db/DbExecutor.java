package com.atjl.util.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * Db
 */
public class DbExecutor {

    private static Logger log = LoggerFactory.getLogger(DbExecutor.class);

    private static DataSource dataSource;

    public void init(DataSource dataSource){
        DbExecutor.dataSource = dataSource;
    }

    public static List<Map<String, Object>> getTableData(String sql) {
        List<Map<String, Object>> list = new LinkedList<>();
//        Map<String, Object> res = new HashMap<>();
        //Iterator<String> it = param.keySet().iterator();
//        while (it.hasNext()) {
//            String key = it.next();
//            String sql = param.get(key);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection() ;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
//                metaData.getColumnType();
                log.debug("column count {}",columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
//                    metaData.getColumnClassName(i);
                    Object val = rs.getObject(i);
                    log.debug("column name {},val {}",columnName,val);
                    row.put(columnName, val);
                }
                list.add(row);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
