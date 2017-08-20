package com.atjl.common.api.resp;


import com.atjl.common.constant.RespConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对外response dto
 * <p>
 * 默认创建成功对象
 *
 * @author atjl
 * @since 1.0
 */
@ApiModel
public class ResponseDto {
    @ApiModelProperty(value = "错误码")
    protected int code = RespConstant.SUCCESS_CODE;
    @ApiModelProperty(value = "提示信息")
    protected String msg = RespConstant.SUCC_MSG;

    public ResponseDto() {
    }

    public ResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isOk() {
        return code == RespConstant.SUCCESS_CODE;
    }

}
