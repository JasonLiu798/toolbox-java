package com.atjl.common.api.resp2;

import com.atjl.common.constant.RespConstant;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对外response dto,包含附加数据
 *
 * @author 01174604
 * @since 1.0
 */
public class ResponseDataDtoV2 extends ResponseDtoV2 {
    @ApiModelProperty(value = "数据")
    private Object returnData = null;

    public ResponseDataDtoV2() {
        super();
    }

    public ResponseDataDtoV2(int code, String msg) {
        this.returnCode = code;
        this.returnMsg = msg;
    }

    public ResponseDataDtoV2(Object data) {
        super();
        this.returnData = data;
    }

    public static ResponseDataDtoV2 buildOk() {
        return new ResponseDataDtoV2(RespConstant.SUCCESS_CODE, RespConstant.SUCC_MSG);
    }

    public static ResponseDataDtoV2 buildOk(Object obj) {
        ResponseDataDtoV2 resp = new ResponseDataDtoV2();
        resp.setReturnData(obj);
        return resp;
    }

    public static ResponseDataDtoV2 buildFail(String msg) {
        ResponseDataDtoV2 resp = new ResponseDataDtoV2();
        resp.setReturnCode(RespConstant.UNKNOWN_ERROR_CODE);
        resp.setReturnMsg(msg);
        return resp;
    }

    public static ResponseDataDtoV2 buildFail(int code, String msg) {
        ResponseDataDtoV2 resp = new ResponseDataDtoV2();
        resp.setReturnCode(code);
        resp.setReturnMsg(msg);
        return resp;
    }

    public static ResponseDataDtoV2 buildFail(int code, String msg, Object data) {
        ResponseDataDtoV2 resp = new ResponseDataDtoV2();
        resp.setReturnData(data);
        resp.setReturnCode(code);
        resp.setReturnMsg(msg);
        return resp;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

}

