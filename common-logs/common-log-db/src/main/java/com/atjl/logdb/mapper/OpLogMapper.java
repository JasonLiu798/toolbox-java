package com.atjl.logdb.mapper;

import com.atjl.logdb.api.domain.OpLog;
import com.atjl.logdb.api.req.OpLogReq;

import java.util.List;


/**
 * 操作日志 dao
 *
 * @author 01174606
 */
public interface OpLogMapper {
    int insertSelective(OpLog record);

    /**
     * 查询 ，按操作时间段，userid，操作类型
     * @param param
     * @return
     */
    List<OpLog> select(OpLogReq param);

    /**
     * 分页用
     * @param param
     * @return
     */
    long count(OpLogReq param);
}