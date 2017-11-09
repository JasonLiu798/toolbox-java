package com.atjl.common.api.req;

import com.atjl.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 默认分页请求
 *
 * @author jasondliu
 */
@ApiModel(value = "分页基础请求对象")
public class PageLongReq implements Serializable {
	@ApiModelProperty(value = "当前页码",example = "默认1")
    private Long currentPage = CommonConstant.DFT_PAGE_START;
	@ApiModelProperty(value = "页大小",example = "默认10")
    private Long pageSize = CommonConstant.DFT_PAGE_SIZE;

    public PageLongReq() {
        super();
    }

    public PageLongReq(Long pageSize) {
        this.pageSize = pageSize;
    }

    public PageLongReq(Long currentPage, Long pageSize) {
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
