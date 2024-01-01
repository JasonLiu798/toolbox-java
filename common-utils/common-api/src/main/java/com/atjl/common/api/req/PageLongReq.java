package com.atjl.common.api.req;

import com.atjl.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 默认分页请求
 *
 * @author atjl
 */

@Data
@ApiModel(value = "分页基础请求对象")
public class PageLongReq extends PageBaseReq implements Serializable {
    @ApiModelProperty(value = "当前页码", example = "查询需分页时传递，默认1")
    private Long currentPage = CommonConstant.DFT_PAGE_START;
    @ApiModelProperty(value = "页大小", example = "查询需分页时传递，默认10")
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
    public Long getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE : pageSize;
    }
    public Long getCurrentIndex() {
        return getCurrentPage() == null || getCurrentPage() <= 0 ? 0 : (getCurrentPage() - 1) * getPageSize();
    }
}
