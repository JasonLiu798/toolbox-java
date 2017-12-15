package com.atjl.common.api.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "分页基础请求对象,以下字段支持单列排序")
public class PageBaseReq extends BaseReqDto implements Serializable {

    @ApiModelProperty(value = "排序栏位")
    private String orderBy;
    @ApiModelProperty(value = "顺序")
    private String sequence;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
