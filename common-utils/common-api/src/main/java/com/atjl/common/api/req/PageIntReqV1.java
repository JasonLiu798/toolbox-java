package com.atjl.common.api.req;


import com.atjl.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求 类型2
 *
 * @author jasondliu
 */
@ApiModel(value = "分页基础请求对象类型2")
public class PageIntReqV1 extends PageBaseReq {

    @ApiModelProperty(value = "起始对象主键", required = true, example = "查询需分页时传递")
    private String startItemId;

    @ApiModelProperty(value = "页大小", example = "查询需分页时传递，默认10")
    private Integer pageSize = CommonConstant.DFT_PAGE_SIZE_INT;


    public PageIntReqV1() {
        super();
    }

    public PageIntReqV1(Integer pageSize) {
        this.startItemId = null;
        this.pageSize = pageSize;
    }

    public PageIntReqV1(String startItemId, Integer pageSize) {
        this.startItemId = startItemId;
        this.pageSize = pageSize;
    }

    public String getStartItemId() {
        return startItemId;
    }

    public void setStartItemId(String startItemId) {
        this.startItemId = startItemId;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE_INT : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
