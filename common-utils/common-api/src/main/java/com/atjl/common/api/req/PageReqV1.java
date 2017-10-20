package com.atjl.common.api.req;


import com.atjl.common.constant.CommonConstant;

/**
 * 分页请求 类型2
 * @author jasondliu
 */
public class PageReqV1 {

    // 起始对象唯一标识
    private String startItemId;

    //页大小
    private Long pageSize = CommonConstant.DFT_PAGE_SIZE;


    public PageReqV1() {
        super();
    }

    public PageReqV1(Long pageSize) {
        this.startItemId = null;
        this.pageSize = pageSize;
    }

    public PageReqV1(String startItemId, Long pageSize) {
        this.startItemId = startItemId;
        this.pageSize = pageSize;
    }

    public String getStartItemId() {
        return startItemId;
    }

    public void setStartItemId(String startItemId) {
        this.startItemId = startItemId;
    }

    public Long getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

}
