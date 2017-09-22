package com.atjl.retry.mapper.biz;


import com.atjl.common.api.req.PageReqV1;
import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RetryMapper {

    int updateRetryResult(@Param("option") InitOption initOption, @Param("meta") RetryTableMetaConf meta, @Param("data") RetryData data);

    List<Map<String, Object>> selectDatas(@Param("option") InitOption initOption, @Param("meta") RetryTableMetaConf meta, @Param("pageReq") PageReqV1 pageReq);

    int getDataCount(@Param("option") InitOption initOption, @Param("meta") RetryTableMetaConf meta);

}
