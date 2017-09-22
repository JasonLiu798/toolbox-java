package com.atjl.retry.api;


/**
 * execute的后置操作，也可以直接在execute内实现相关操作
 *
 * @param <D> 自定义数据
 */
public interface AfterService<D> {

    /**
     * 重试成功
     *
     * @param context 重试上下文数据
     */
    void succ(DataContext<D> context);

    /**
     * 重试失败
     *
     * @param context 重试上下文数据
     */
    void fail(DataContext<D> context);

}
