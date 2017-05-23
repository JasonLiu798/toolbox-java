package com.jason798.dto;

import com.jason798.exception.CommonErrorCode;
import java.io.Serializable;

/**
 *
 */
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String code = CommonErrorCode.SUCCESS_CODE;
    protected String msg = CommonErrorCode.SUCCESS_MSG;

    public ResponseDto() {
    }

    public ResponseDto(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "ResponseDto{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				'}';
	}
}
