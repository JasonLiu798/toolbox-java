package com.atjl.retry.mapper;

import com.atjl.common.api.req.PageIntReqV1;
import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RetryMapper {

    int updateRetryResult(@Param("option") RetryOption initOption, @Param("meta") RetryTableMetaConf meta, @Param("data") RetryData data);

    List<Map<String, Object>> selectDatas(@Param("option") RetryOption initOption, @Param("meta") RetryTableMetaConf meta, @Param("pageReq") PageIntReqV1 pageReq);

    int getDataCount(@Param("option") RetryOption initOption, @Param("meta") RetryTableMetaConf meta);

}
