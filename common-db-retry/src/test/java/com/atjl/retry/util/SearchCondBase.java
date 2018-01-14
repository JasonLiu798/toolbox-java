package com.atjl.retry.util;


import com.atjl.common.api.req.PageIntReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("默认条件对象，分页参数如果传递")
public class SearchCondBase extends PageIntReq {
    @ApiModelProperty(value = "loadtm 起始时间，没有load_tm的表，不填，否则报错")
    private String startLoadTm;

    @ApiModelProperty(value = "loadtm 结束时间，没有load_tm的表，不填，否则报错")
    private String endLoadTm;

    @ApiModelProperty(value = "其他条件")
    private String otherCond;

    public String getStartLoadTm() {
        return startLoadTm;
    }

    public void setStartLoadTm(String startLoadTm) {
        this.startLoadTm = startLoadTm;
    }

    public String getEndLoadTm() {
        return endLoadTm;
    }

    public void setEndLoadTm(String endLoadTm) {
        this.endLoadTm = endLoadTm;
    }

    public String getOtherCond() {
        return otherCond;
    }

    public void setOtherCond(String otherCond) {
        this.otherCond = otherCond;
    }
}
