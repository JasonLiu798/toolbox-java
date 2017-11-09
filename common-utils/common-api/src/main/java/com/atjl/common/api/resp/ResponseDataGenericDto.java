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
@ApiModel
public class ResponseDataGenericDto<T> extends ResponseDto {
    public static final int UNKNOWN_ERROR = 1004;

    @ApiModelProperty(value = "结果数据")
    private T result = null;

    public ResponseDataGenericDto() {
        super();
    }

    public ResponseDataGenericDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseDataGenericDto(T data) {
        super();
        this.result = data;
    }

    public static ResponseDataGenericDto buildOk() {
        return new ResponseDataGenericDto(RespConstant.SUCCESS_CODE, RespConstant.SUCC_MSG);
    }

    public ResponseDataGenericDto<T> buildOk(T obj) {
        ResponseDataGenericDto<T> resp = new ResponseDataGenericDto<>();
        resp.setResult(obj);
        return resp;
    }

    public static ResponseDataGenericDto buildFail(String msg) {
        ResponseDataGenericDto resp = new ResponseDataGenericDto();
        resp.setCode(UNKNOWN_ERROR);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataGenericDto buildFail(int code, String msg) {
        ResponseDataGenericDto resp = new ResponseDataGenericDto();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> ResponseDataGenericDto<T> buildFail(int code, String msg, T data) {
        ResponseDataGenericDto<T> resp = new ResponseDataGenericDto<T>();
        resp.setResult(data);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}

