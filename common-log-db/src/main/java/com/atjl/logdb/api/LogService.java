package com.atjl.logdb.api;

import com.atjl.common.api.resp.PageResp;
import com.atjl.logdb.api.domain.OpLog;
import com.atjl.logdb.api.req.OpLogReq;

public interface LogService {
    /**
     * 新增
     *
     * @param record 记录
     * @return int 成功条数
     */
    int insert(OpLog record);

    /**
     * 查询
     *
     * @param param 条件
     * @return 查询结果
     */
    PageResp<OpLog> searchPage(OpLogReq param);

//    void sendLog2Kafka(String v);

//    void rebuildProducer();

//    void initKafka();

//    boolean producerNull();
}
