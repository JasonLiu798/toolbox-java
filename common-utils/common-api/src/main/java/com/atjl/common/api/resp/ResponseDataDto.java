package com.atjl.common.api.resp;

import com.atjl.common.constant.RespConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对外response dto,包含附加数据
 *
 * @author atjl
 * @since 1.0
 */
@ApiModel(value = "结果对象")
public class ResponseDataDto extends ResponseDto {

    @ApiModelProperty(value = "结果数据")
    private Object result = null;

    public ResponseDataDto() {
        super();
    }

    public ResponseDataDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseDataDto(Object data) {
        super();
        this.result = data;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public static ResponseDataDto buildOk() {
        return new ResponseDataDto(RespConstant.SUCCESS_CODE, RespConstant.SUCC_MSG);
    }

    public static ResponseDataDto buildOk(Object obj) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setResult(obj);
        return resp;
    }

    public static ResponseDataDto buildFail(String msg) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setCode(RespConstant.UNKNOWN_ERROR_CODE);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataDto buildFail(int code, String msg) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataDto buildFail(int code, String msg, Exception e) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setResult(e.getStackTrace());
        return resp;
    }

    public static ResponseDataDto buildFail(int code, String msg, Object data) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setResult(data);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

}

