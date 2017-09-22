package com.atjl.common.api.req;

import com.atjl.common.constant.CommonConstant;

import java.io.Serializable;

/**
 * 默认分页请求
 *
 * @author jasondliu
 */
public class PageReq implements Serializable {

    // 页码
    private Long currentPage = CommonConstant.DFT_PAGE_START;

    //页大小
    private Long pageSize = CommonConstant.DFT_PAGE_SIZE;


    public PageReq() {
        super();
    }

    public PageReq(Long pageSize) {
        this.pageSize = pageSize;
    }

    public PageReq(Long currentPage, Long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCurrentIndex() {
        return getCurrentPage() == null || getCurrentPage() <= 0 ? 0 : (getCurrentPage() - 1) * getPageSize();
    }
}
