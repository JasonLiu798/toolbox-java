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
public class PageIntReq implements Serializable {
    @ApiModelProperty(value = "当前页码", example = "查询需分页时传递，默认1")
    private Integer currentPage = CommonConstant.DFT_PAGE_START_INT;
    @ApiModelProperty(value = "页大小", example = "查询需分页时传递，默认10")
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
