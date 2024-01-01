package com.atjl.common.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "基础请求对象")
public class BaseReqDto implements Serializable {

    private static final long serialVersionUID = -8872536162131760715L;
    @ApiModelProperty(value = "当前用户", required = false, hidden = true)
    private String currentUser;
    @ApiModelProperty(value = "当前请求来源", required = false, hidden = true)
    private String source;

    public static BaseReqDto build(String cur) {
        return new BaseReqDto(cur);
    }

    public static BaseReqDto build(String cur, String src) {
        return new BaseReqDto(cur, src);
    }

    public BaseReqDto(String currentUser, String source) {
        this.currentUser = currentUser;
        this.source = source;
    }
    public BaseReqDto(String currentUser) {
        this.currentUser = currentUser;
    }

    public BaseReqDto(){}

}
