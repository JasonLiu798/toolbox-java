package com.jason798.dto;

import com.jason798.exception.ErrorCode;

import java.io.Serializable;


public class ResponseDataDto extends ResponseDto implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private Object data = null;

    public ResponseDataDto() {
        super();
    }

    public ResponseDataDto(String code, String msg) {
    	super(code,msg);
    }

    public ResponseDataDto(ErrorCode errorCode){
    	this.code = errorCode.code;
    	this.msg = errorCode.msg;
	}

    public static ResponseDataDto buildOk() {
        return new ResponseDataDto();
    }

    public static ResponseDataDto buildOk(Object obj) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setData(obj);
        return resp;
    }

    public static ResponseDataDto buildFail(ErrorCode er, Object data) {
        ResponseDataDto resp = new ResponseDataDto();
        resp.setData(data);
        resp.setCode(er.getCode());
        resp.setMsg(er.getMsg());
        return resp;
    }


    public ResponseDataDto(Object data) {
        super();
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public ResponseDataDto clone() {
        ResponseDataDto res = new ResponseDataDto();
        res.setData(this.data);
        res.setCode(this.code);
        res.setMsg(this.msg);
        return res;
    }

    @Override
    public String toString() {
        return "ResponseDataDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                "data=" + data +
                '}';
    }
}

