package com.jason798.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * for test get db data
 * @author JasonLiu798
 * @date 2015/10/15 14:53
 */
public class DataGetter {

    public static final Logger log = LoggerFactory.getLogger(DataGetter.class);

    public Connection conn = null;
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static DruidDataSource dataSource = null;

    public static void init(String url, String user, String password) {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
    }

    public static Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }


    public static List<Map<String, Object>> getTableData(String sql) {
        List<Map<String, Object>> list = new LinkedList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
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

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if(rs !=null){
                    rs.close();
                }
                if(pstmt!=null){
                    pstmt.close();
                }
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