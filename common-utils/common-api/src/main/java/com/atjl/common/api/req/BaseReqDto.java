package com.atjl.common.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "基础请求对象")
public class BaseReqDto {

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BaseReqDto(String currentUser, String source) {
        this.currentUser = currentUser;
        this.source = source;
    }

    public BaseReqDto(String currentUser) {
        this.currentUser = currentUser;
    }

    public BaseReqDto() {
        super();
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
