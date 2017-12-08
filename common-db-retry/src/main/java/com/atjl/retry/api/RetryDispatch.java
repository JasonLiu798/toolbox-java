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
     * @param bean 传递 spring bean，bean id 必须为默认名
     */
    void registeBean(Object bean);

    /**
     *
     */
    void executeAll();

    /**
     * 只处理指定 服务
     * cond为自定义条件，如果 cond继承了 PageIntReq，且传递了值，则会用 cond内的pageSize值 覆盖 option内的pageSize
     */
    void executeService(String service, Object cond);

    List<RetryStatusDto> getOptions();

}
