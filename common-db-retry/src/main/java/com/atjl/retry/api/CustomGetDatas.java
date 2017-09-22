package com.atjl.retry.api;

import java.util.List;

/**
 * 重试服务
 */
public interface CustomGetDatas<D> extends CustomGetCount {

    /**
     * 获取重试上下文数据列表
     * 数据必须为 重试次数小于x，指定时间段内，失败的需要重试的列表
     * !!!!! 注 !!!!!
     * 必填字段：已经发送的次数，上次执行时间
     *
     * @param page     页数
     * @param pageSize 页大小，传递initOption中的pagesize
     * @return
     */
    List<DataContext<D>> getRetryDataContextListPaged(int page, int pageSize);


}
