package com.atjl.common.api.resp;


import com.atjl.common.constant.RespConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 对外response dto
 * <p>
 * 默认创建成功对象
 *
 * @author atjl
 * @since 1.0
 */
@Data
@ApiModel
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = -4976381015792391830L;
    @ApiModelProperty(value = "错误码")
    protected Integer code = RespConstant.SUCCESS_CODE;
    @ApiModelProperty(value = "提示信息")
    protected String msg = RespConstant.SUCC_MSG;

    public ResponseDto() {
    }
    public ResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isOk() {
        return RespConstant.SUCCESS_CODE.equals(code);
    }

}
