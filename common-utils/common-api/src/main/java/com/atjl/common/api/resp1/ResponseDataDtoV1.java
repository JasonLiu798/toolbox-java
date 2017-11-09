package com.atjl.common.api.resp1;

import com.atjl.common.constant.RespConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对外response dto,包含附加数据
 *
 * @author atjl
 * @since 1.0
 */
@ApiModel("结果集对象V1")
public class ResponseDataDtoV1 extends ResponseDtoV1 {
    //    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "结果集数据")
    private Object result = null;

//    public static final int UNKNOWN_ERROR = 1004;

    public ResponseDataDtoV1() {
        super();
    }

    public ResponseDataDtoV1(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseDataDtoV1(Object data) {
        super();
        this.result = data;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public static ResponseDataDtoV1 buildOk() {
        return new ResponseDataDtoV1(RespConstant.SUCCESS_CODE, RespConstant.SUCC_MSG);
    }

    public static ResponseDataDtoV1 buildOk(Object obj) {
        ResponseDataDtoV1 resp = new ResponseDataDtoV1();
        resp.setResult(obj);
        return resp;
    }

    public static ResponseDataDtoV1 buildFail(String msg) {
        ResponseDataDtoV1 resp = new ResponseDataDtoV1();
        resp.setSucc(RespConstant.FAIL_MSG);
        resp.setCode(RespConstant.UNKNOWN_ERROR_CODE);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataDtoV1 buildFail(int code, String msg) {
        ResponseDataDtoV1 resp = new ResponseDataDtoV1();
        resp.setSucc(RespConstant.FAIL_MSG);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static ResponseDataDtoV1 buildFail(int code, String msg, Object data) {
        ResponseDataDtoV1 resp = new ResponseDataDtoV1();
        resp.setResult(data);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

}

