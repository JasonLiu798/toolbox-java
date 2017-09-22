package com.atjl.retry.api;

import com.atjl.retry.api.domain.RetryStatusDto;

import java.util.List;

/**
 * 重试调度服务
 */
public interface RetryDispatch {

    /**
     * 注册 重试服务
     *
     * @param serviceName spring bean 名，需要实现 RetryService接口
     */
    void registe(String serviceName);

    /**
     *
     */
    void timeUp();

    List<RetryStatusDto> getOptions();

}
