package com.atjl.common.api.resp1;

import com.atjl.common.constant.RespConstant;
import com.atjl.common.api.resp.ResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 01174604
 * @since 1.0
 */
@ApiModel
public class ResponseDtoV1 extends ResponseDto {
    @ApiModelProperty(value = "是否成功")
    protected String succ = RespConstant.V1_OK_CODE;

    public ResponseDtoV1() {
    }

    public boolean isOk() {
        return RespConstant.V1_OK_CODE.equals(this.succ);
    }

    public String getSucc() {
        return succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }
}
