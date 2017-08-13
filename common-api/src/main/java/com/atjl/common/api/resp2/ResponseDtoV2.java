package com.atjl.common.api.resp2;

import com.atjl.common.constant.RespConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对外response dto
 * <p>
 * 默认创建成功对象
 *
 * @author 01174604
 * @since 1.0
 */
@ApiModel
public class ResponseDtoV2 {
    @ApiModelProperty(value = "错误码")
    protected int returnCode = RespConstant.SUCCESS_CODE;
    @ApiModelProperty(value = "消息")
    protected String returnMsg = RespConstant.SUCC_MSG;

    public ResponseDtoV2() {
    }

    public ResponseDtoV2(int code, String msg) {
        this.returnCode = code;
        this.returnMsg = msg;
    }

    public boolean isOk() {
        return this.returnCode == RespConstant.SUCCESS_CODE;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
