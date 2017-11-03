package com.atjl.common.api.req;

import com.atjl.common.constant.CommonConstant;

import java.io.Serializable;

/**
 * 默认分页请求
 *
 * @author jasondliu
 */
public class PageIntReq implements Serializable {

    // 页码
    private Integer currentPage = CommonConstant.DFT_PAGE_START_INT;

    //页大小
    private Integer pageSize = CommonConstant.DFT_PAGE_SIZE_INT;


    public PageIntReq() {
        super();
    }

    public PageIntReq(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageIntReq(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE_INT : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentIndex() {
        return getCurrentPage() == null || getCurrentPage() <= 0 ? 0 : (getCurrentPage() - 1) * getPageSize();
    }
}
