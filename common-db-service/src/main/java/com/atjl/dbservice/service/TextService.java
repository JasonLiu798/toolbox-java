package com.atjl.dbservice.service;

import com.atjl.common.api.exception.ParamFormatException;
import com.atjl.common.api.resp1.ResponseDataDtoV1;
import com.atjl.dbservice.mapper.biz.SysDao;
import com.atjl.util.character.StringCheckUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 查库
 */
@Component
public class TextService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    SysDao sysDao;
    //private static final String OP_LOG_TAB = "ts_op_log";

    static String[] NOT_ALLOW = {"insert ", "update ", "delete ", "create ", "alter", "drop",
            "grant", "revoke", "truncate"};

    private boolean containX(String text) {
        if (StringCheckUtil.isEmpty(text)) {
            return false;
        }
        for (String s : NOT_ALLOW) {
            if (text.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 处理数据
     */
    @Transactional
    public ResponseDataDtoV1 process(String text, String type) {
        if (!sqlSessionFactory.getConfiguration().isCallSettersOnNulls()) {
            sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true);
        }
        if (StringCheckUtil.isEmpty(type)) {
            type = "j";
        }
        try {
            if (!StringCheckUtil.isEmpty(text)) {
                String trimedText = text.toLowerCase().trim();
                if (containX(trimedText)) {
                    throw new ParamFormatException("语句不合法！");
                } else {
//                    if (trimedText.contains(OP_LOG_TAB)) {
//                        return new ResponseDataDtoV1("ts_op_log permission deny");
//                    }

                    if (trimedText.contains("insert") || trimedText.contains("delete") || trimedText.contains("update")) {
                        return new ResponseDataDtoV1(sysDao.insertUpdate(text));
                    } else {
                        return processSelect(text, type);
                    }
                }
            } else {
                return new ResponseDataDtoV1("null param");
            }
        } catch (Exception t) {
            throw new ParamFormatException("查询失败！" + t);
        }
    }


    /**
     * 拼接 table
     */
    private ResponseDataDtoV1 processSelect(String text, String type) {
        List<LinkedHashMap<String, Object>> data = sysDao.search("select * from ( " + text + " ) x limit 500");
        if (StringCheckUtil.equal(type, "j")) {
            ResponseDataDtoV1 resp = new ResponseDataDtoV1();
            resp.setResult(data);
            return resp;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("<table id='table1' border=0px>");
            sb.append("<tr bgcolor=blue>");
            if (data != null && !data.isEmpty()) {
                processTable(sb, data);
                ResponseDataDtoV1 resp = new ResponseDataDtoV1();
                resp.setResult(sb.toString());
                return resp;
            } else {
                ResponseDataDtoV1 resp = new ResponseDataDtoV1();
                resp.setResult("没有匹配的行");
                return resp;
            }
        }
    }

    /**
     * 处理 table
     *
     * @param sb
     * @param data
     */
    private static void processTable(StringBuilder sb, List<LinkedHashMap<String, Object>> data) {
        List<String> title = new ArrayList<>();
        for (Map<String, Object> map : data) {
            for (Entry<String, Object> entry : map.entrySet()) {
                sb.append("<th>" + entry.getKey() + "</th>");
                title.add(entry.getKey());
            }
            break;
        }
        sb.append("</tr>");
        //row
        for (Map<String, Object> map : data) {
            sb.append("<tr>");
            for (String col : title) {
                Object coldata = map.get(col);
                if (coldata == null) {
                    coldata = "";
                }
                sb.append("<td>" + coldata + "</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
    }


}
