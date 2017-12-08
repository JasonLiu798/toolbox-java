package com.atjl.retry.api;

import com.atjl.retry.api.domain.ExecuteStatusResp;

import java.util.List;

/**
 * 重试服务
 * 调度程序
 *
 * @param <D> 重试上下文数据，execute ,
 */
public interface ExecuteBatchService<D> {

    /**
     * 执行 重试操作
     * <p>
     * 每条数据调用 execute 并传入 context
     * 自定义获取数据的则，使用List<DataContext<D>> 中的数据
     *
     * @return 执行成功/失败
     */
    ExecuteStatusResp execute(List<D> datas);//, C cond);

}
