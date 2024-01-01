package com.atjl.common.api.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "分页基础请求对象,以下字段支持单列排序")
@Data
public class PageBaseReq extends BaseReqDto implements Serializable {

    private static final long serialVersionUID = -2546348972504285652L;
    @ApiModelProperty(value = "排序栏位")
    private String orderBy;
    @ApiModelProperty(value = "顺序")
    private String sequence;

    public PageBaseReq(String currentUser, String source) {
        super(currentUser, source);
    }

    public PageBaseReq(String currentUser) {
        super(currentUser);
    }

    public PageBaseReq(){
        super();
    }
}