package com.atjl.retry.api;

import com.atjl.retry.api.option.InitOption;

/**
 * 重试服务
 * 调度程序
 *
 * @param <D> 重试上下文数据，execute ,
 */
public interface RetryService<D> {

    /**
     * 执行 重试操作
     * <p>
     * 每条数据调用 execute 并传入 context
     * 自定义获取数据的则，使用List<DataContext<D>> 中的数据
     *
     * @param context context
     * @return 执行成功/失败
     */
    boolean execute(DataContext<D> context);

    /**
     * 获取重试配置，获取的为配置拷贝，注册后不可修改
     */
    InitOption getInitOption();

}
