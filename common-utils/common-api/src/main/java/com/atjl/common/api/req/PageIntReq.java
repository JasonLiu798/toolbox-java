package com.atjl.common.api.req;

import com.atjl.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 默认分页请求
 *
 * @author jasondliu
 */
@ApiModel(value = "分页int请求对象")
@Data
public class PageIntReq extends PageBaseReq implements Serializable {

    private static final long serialVersionUID = -436192848478957632L;
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

    public Integer getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE_INT : pageSize;
    }
    public Integer getCurrentIndex() {
        return getCurrentPage() == null || getCurrentPage() <= 0 ? 0 : (getCurrentPage() - 1) * getPageSize();
    }
}
