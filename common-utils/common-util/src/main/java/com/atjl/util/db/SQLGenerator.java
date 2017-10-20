package com.atjl.util.db;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;
import com.atjl.util.file.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * batch SQL generator
 *
 * @author JasonLiu798
 * @date 2015/10/23 10:20
 */
public class SQLGenerator {
    public static final boolean log = true;

    private static final String SPACE = " ";

    private static final String FROM = "FROM";
    private static final String FROMS = SPACE + FROM + SPACE;
    private static final String WHERE = "WHERE";
    private static final String WHERES = SPACE + WHERE + SPACE;
    private static final String SELECT = "SELECT";
    private static final String SELECTS = SPACE + SELECT + SPACE;
    private static final String ORDER = "ORDER";
    private static final String ORDERS = SPACE + ORDER + SPACE;
    private static final String GROUP = "GROUP";
    private static final String GROUPS = SPACE + GROUP + SPACE;

    private static final String UPDATE = "UPDATE";
    private static final String UPDATES = SPACE + UPDATE + SPACE;
    private static final String SET = "SET";
    private static final String SETS = SPACE + SET + SPACE;

    private static final String INSERT = "INSERT";
    private static final String INSERTS = SPACE + INSERT + SPACE;
    private static final String INTO = "INTO";
    private static final String INTOS = SPACE + INTO + SPACE;
    private static final String INSERTINTO = INSERT + SPACE + INTO;
    private static final String INSERTINTOS = SPACE + INSERT + SPACE + INTO + SPACE;

    private static final String UNION = "UNION";
    private static final String UNIONS = SPACE + UNION + SPACE;
    private static final String UNIONALL = UNION + SPACE + "ALL";
    private static final String UNIONALLS = SPACE + UNIONALL + SPACE;


    /**
     * generate insert
     *
     * @param sql
     * @return
     */
    public static String generateInsert(String sql) {
        String res = "";
        int widx = sql.indexOf("where");
        int sidx = 7;
        String tbname = sql.substring(sidx, widx - 1);
        String remain = sql.substring(widx + WHERE.length() + 1, sql.length());
//        System.out.println("tbname:"+tbname+"|");
        int dIdx = tbname.lastIndexOf("_");
        String insTbname = tbname.substring(0, dIdx) + "_bak";
//        System.out.println("insTbname:"+insTbname+"|");
        StringBuilder ins = new StringBuilder();
        ins.append(INSERTINTOS).append(insTbname).append(SELECTS).append("*").append(FROMS).append(tbname).append
                (WHERES).append(remain).append(";");
//        System.out.println(ins.toString());
        return ins.toString();
    }


    /**
     * batch format sql
     *
     * @param inputPath
     * @param writepath
     */
    public static void batchGenerateInsert(String inputPath, String writepath) {
        List<String> reads = FileUtil.cat2list(inputPath);
        String targetSql = null;
        List<String> writes = new ArrayList<>();
        for (String sql : reads) {
            targetSql = generateInsert(sql);
            System.out.println("src:" + sql);
            System.out.println("target:" + targetSql);
            writes.add(targetSql);
            writes.add(sql.replace("rm", "rm from ") + ";");
        }
        FileUtil.append(writepath, writes);
    }


    /**
     * generate batch rm sql
     *
     * @param sql
     * @return
     */
    public static String generateBatchDel(String sql) {
//        StringBuilder select = new StringBuilder();
//        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();
//        StringBuilder order = new StringBuilder();
        StringBuilder res = new StringBuilder();
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);

        SQLDeleteStatement delParser = parser.parseDeleteStatement();
//        parser.parseDeleteStatement();//SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
        SQLName tabName = delParser.getTableName();
        SQLExpr whereExpr = delParser.getWhere();
//        delParser.get
        List<SQLStatement> stmtList = parser.parseStatementList();

        SQLASTOutputVisitor whereVisitor = SQLUtils.createFormatOutputVisitor(where, stmtList, JdbcUtils.MYSQL);

        whereExpr.accept(whereVisitor);
        System.out.println("tab name:" + tabName);
        System.out.println("where:" + where);

//        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
//        parser.getTableName() ;
//        if(log)
//            System.out.println(stmtList);

//        SQLASTOutputVisitor fromVisitor = SQLUtils.createFormatOutputVisitor(from, stmtList, JdbcUtils.MYSQL);
//
//        SQLASTOutputVisitor orderVisitor = SQLUtils.createFormatOutputVisitor(order,stmtList,JdbcUtils.MYSQL);

        /*
        for (SQLStatement stmt : stmtList) {
//       stmt.accept(visitor);
            if(stmt instanceof SQLSelectStatement){
                SQLSelectStatement sstmt = (SQLSelectStatement)stmt;
//                sstmt.accept(mvisitor);
//                System.out.println("visitor :");
//                visitor.println();
                SQLSelect sqlselect = sstmt.getSelect();

//                System.out.println("sqlselect "+sqlselect);
                SQLSelectQueryBlock query = (SQLSelectQueryBlock)sqlselect.getQuery();
//                query.getSelectList();
                List<SQLSelectItem> sqlItemL = query.getSelectList();
                int i=0;
                for(SQLSelectItem it:sqlItemL){
                    if(i==sqlItemL.size()-1){
                        select.append(it);
                    }else{
                        select.append(it+",");
                    }
                    i++;
                }
//                query.getFrom().accept(fromVisitor);
                query.getWhere().accept(whereVisitor);
//                query.getGroupBy().accept(orderVisitor);
            }
        }

        if(log) {
            System.out.println(SELECTS+":"+select);
            System.out.println(FROMS+":"+from.toString());
            System.out.println(WHERES+":"+where);
//            System.out.println(ORDERS+":"+order);
        }
        */
//        for(int i=0;i<tableCount;i++){
//            res.append(SELECTS).append(select)
//                    .append(FROMS).append(from.toString()+"_"+(i+1))
//                    .append(WHERES).append(where);
//            if(i!=tableCount-1){
//                res.append(UNIONALLS);
//            }
//            res.append("\n");
//        }
        return res.toString();
    }


    /**
     * generate update SQL
     *
     * @param sql
     * @return
     */
    public static String generateBatchUpdate(String sql) {
        StringBuilder where = new StringBuilder();
        StringBuilder setStr = new StringBuilder();
        StringBuilder setVisitStr = new StringBuilder();
        StringBuilder res = new StringBuilder();

        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);

        SQLUpdateStatement upParser = parser.parseUpdateStatement();
        SQLName tabName = upParser.getTableName();

        SQLExpr whereExpr = upParser.getWhere();
        List<SQLStatement> stmtList = parser.parseStatementList();
        SQLASTOutputVisitor whereVisitor = SQLUtils.createFormatOutputVisitor(where, stmtList, JdbcUtils.MYSQL);
        whereExpr.accept(whereVisitor);

        List<SQLUpdateSetItem> setItems = upParser.getItems();
        for (SQLUpdateSetItem setItem : setItems) {
            setStr.append(setItem.getColumn());
            SQLExpr setItemExpr = setItem.getValue();
            SQLASTOutputVisitor setVisitor = SQLUtils.createFormatOutputVisitor(setVisitStr, stmtList, JdbcUtils.MYSQL);
            setItemExpr.accept(setVisitor);
            setStr.append("=").append(setVisitStr.toString()).append("\n");
        }

        for (int i = 0; i < 10; i++) {
            res.append(UPDATES).append(tabName).append("_").append(i + 1).append(SPACE).append(SETS).append(setStr)
                    .append(WHERES).append(where).append(";\n");
        }
//        System.out.println("tab name:" + tabName);
//        System.out.println("where:" + where);
//        System.out.println("set :"+setStr);
        return res.toString();
    }


    /**
     * generate format:
     *
     * @param sql
     * @return
     */
    public static String select2union(String sql, int start, int end) {
//        StringBuilder selectBuf = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder select = new StringBuilder();
        StringBuilder whereBuf = new StringBuilder();
        StringBuilder groupByBuf = new StringBuilder();
        StringBuilder orderByBuf = new StringBuilder();
        StringBuilder res = new StringBuilder();
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
//        SQLExprParser parser1 = new SQLExprParser(sql,JdbcUtils.MYSQL);
//        SQLOrderBy exprA = (SQLOrderBy) parser1.parseOrderBy();
//        System.out.println("order by :"+exprA);

        List<SQLStatement> stmtList = parser.parseStatementList();
        if (log)
            System.out.println("sql stmt list " + stmtList);

        SQLASTOutputVisitor selectVisitor = SQLUtils.createFormatOutputVisitor(select, stmtList, JdbcUtils.MYSQL);
        SQLASTOutputVisitor fromVisitor = SQLUtils.createFormatOutputVisitor(from, stmtList, JdbcUtils.MYSQL);
        SQLASTOutputVisitor whereVisitor = SQLUtils.createFormatOutputVisitor(whereBuf, stmtList, JdbcUtils.MYSQL);
        SQLASTOutputVisitor groupbyVisitor = SQLUtils.createFormatOutputVisitor(groupByBuf, stmtList, JdbcUtils.MYSQL);
        SQLASTOutputVisitor orderbyVisitor = SQLUtils.createFormatOutputVisitor(orderByBuf, stmtList, JdbcUtils.MYSQL);


        for (SQLStatement stmt : stmtList) {
            if (stmt instanceof SQLSelectStatement) {
                SQLSelectStatement sstmt = (SQLSelectStatement) stmt;

                SQLSelect sqlselect = sstmt.getSelect();

                SQLOrderBy orderByClause = sqlselect.getOrderBy();

                SQLSelectQueryBlock query = (SQLSelectQueryBlock) sqlselect.getQuery();

                SQLSelectQuery query1 = sqlselect.getQuery();
                sqlselect.getQuery();
                Map map = query.getAttributes();
                System.out.println("map " + map);

                List<SQLSelectItem> sqlItemL = query.getSelectList();

                System.out.println("order by " + orderByClause);
//                orderByClause.accept(orderbyVisitor);
                int i = 0;
                for (SQLSelectItem it : sqlItemL) {
                    if (i == sqlItemL.size() - 1) {
                        it.accept(selectVisitor);
                    } else {
                        it.accept(selectVisitor);
                        select.append(",");
                    }
                    i++;
                }

                query.getFrom().accept(fromVisitor);

                SQLExpr whereClause = query.getWhere();
                if (whereClause != null) {
                    whereClause.accept(whereVisitor);
                }
                SQLSelectGroupByClause groupByClause = query.getGroupBy();
                if (groupByClause != null) {
                    groupByClause.accept(groupbyVisitor);
                }

            }
        }
        if (log) {
            System.out.println(SELECTS + ":" + select);
            System.out.println(FROMS + ":" + from.toString());
            System.out.println(WHERES + ":" + whereBuf);
            System.out.println(GROUPS + ":" + groupByBuf);
            System.out.println(ORDERS + ":" + orderByBuf);
        }

        for (int i = start; i < end + 1; i++) {
            res.append(SELECTS).append(select);
            res.append(FROMS).append(from.toString()).append("_").append(i);
            if (whereBuf.length() > 0) {
                res.append(WHERES).append(whereBuf);
            }
            if (groupByBuf.length() > 0) {
                res.append(SPACE).append(groupByBuf);
            }
            if (i != end) {
                res.append(UNIONALLS);
            }
            res.append("\n");
        }
        return res.toString();
    }


    /**
     * string upper ,not include string in quote
     *
     * @return public static String stringUpper(String sql){
     * String res = null;
     * boolean in = false;
     * int idxQuote = sql.indexOf("'");
     * int idxDQuote = sql.indexOf("\"");
     * //        StringBuilder sb = new StringBuilder();
     * int idxS = 0;
     * while(idxQuote>0 || idxDQuote >0 ){
     * if(!in){
     * <p/>
     * }
     * if(idxQuote>0){
     * in = true;
     * res += sql.substring(idxS,idxQuote-1);
     * idxS = idxQuote;
     * //                sb.append(substring(idxS,))
     * }
     * }
     * return res;
     * }
     */

    /**
     * generate format:
     * select * from users where uid = 57024
     * ==>
     * select * from users_1 where uid = 57024
     * union all
     * select * from users_2 where uid = 57024
     * union all
     * select * from users_3 where uid = 57024
     *
     * @param sql
     * @return
     */
    public static String singleGenerate(String sql, int tableCount) {
        StringBuilder select = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder();
        StringBuilder order = new StringBuilder();
        StringBuilder res = new StringBuilder();

        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);

        List<SQLStatement> stmtList = parser.parseStatementList();
        if (log)
            System.out.println(stmtList);

        SQLASTOutputVisitor fromVisitor = SQLUtils.createFormatOutputVisitor(from, stmtList, JdbcUtils.MYSQL);
        SQLASTOutputVisitor whereVisitor = SQLUtils.createFormatOutputVisitor(where, stmtList, JdbcUtils.MYSQL);
        SQLASTOutputVisitor orderVisitor = SQLUtils.createFormatOutputVisitor(order, stmtList, JdbcUtils.MYSQL);

        for (SQLStatement stmt : stmtList) {
//       stmt.accept(visitor);
            if (stmt instanceof SQLSelectStatement) {
                SQLSelectStatement sstmt = (SQLSelectStatement) stmt;
//                sstmt.accept(mvisitor);
//                System.out.println("visitor :");
//                visitor.println();
                SQLSelect sqlselect = sstmt.getSelect();
//                System.out.println("sqlselect "+sqlselect);
                SQLSelectQueryBlock query = (SQLSelectQueryBlock) sqlselect.getQuery();
//                query.getSelectList();
                List<SQLSelectItem> sqlItemL = query.getSelectList();
                int i = 0;
                for (SQLSelectItem it : sqlItemL) {
                    if (i == sqlItemL.size() - 1) {
                        select.append(it);
                    } else {
                        select.append(it + ",");
                    }
                    i++;
                }
                query.getFrom().accept(fromVisitor);
                query.getWhere().accept(whereVisitor);
                SQLSelectGroupByClause gcl = query.getGroupBy();
                if (gcl != null)
                    gcl.accept(orderVisitor);
            }
        }

        if (log) {
//            System.out.println(SELECTS+":"+select);
//            System.out.println(FROMS+":"+from.toString());
//            System.out.println(WHERES+":"+where);
//            System.out.println(ORDERS+":"+order);
        }

        for (int i = 0; i < tableCount; i++) {
            res.append(SELECTS).append(select)
                    .append(FROMS).append(processTableName(from.toString(), i + 1))
                    .append(WHERES).append(where);
            if (i != tableCount - 1) {
                res.append("\n");
                res.append(UNIONALLS);
                res.append("\n");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public static String processTableName(String tableName, int num) {
        StringBuilder res = new StringBuilder();
        if (tableName.indexOf(SPACE) > 0) {
            String[] tnarr = tableName.split(SPACE);
            res.append(tnarr[0]).append("_").append(num + SPACE);
            for (int i = 1; i < tnarr.length; i++) {
                res.append(tnarr[i]).append(SPACE);
            }
        } else {
            res.append(tableName);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(select2union("select * from js_users_login_openuid where ulo_uid=25651839", 1, 10));
    }


}
