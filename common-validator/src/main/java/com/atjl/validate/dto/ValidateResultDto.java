package com.atjl.validate.dto;

/**
 * 结果对象
 * Created by liujianlong on 16/4/7.
 */
public class ValidateResultDto {
    //code
    private Integer code;
    //message
    private String msg;
    //for logic check return soma data
    private Object data;

    public ValidateResultDto() {
        this.code = 0;
    }

    public ValidateResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ValidateResultDto(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ValidateResultDto(Object data) {
        this.code = 0;
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    @Override
    public String toString() {
        return "ValidateResultDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
