package com.atjl.retry.api;

import java.util.List;

/**
 * 取数服务
 */
public interface CustomGetSimpleDatas<D, C> extends CustomGetCount<C> {

    /**
     * 获取数据列表
     *
     * @param page 页数
     *             # pageSize 页大小，自行从 option中取
     */
    List<D> getRetryDataContextListPaged(int page,int pagesize,C cond);

}
