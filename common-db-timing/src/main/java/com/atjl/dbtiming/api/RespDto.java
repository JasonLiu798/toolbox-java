package com.atjl.dbtiming.api;

/**
 * @author JasonLiu
 */
public class RespDto<T> {
    private boolean succ = true;
    private T data;

    public RespDto(T data) {
        this.data = data;
    }

    public RespDto(boolean succ, T data) {
        this.succ = succ;
        this.data = data;
    }

    public static RespDto buildFail() {
        RespDto respDto = new RespDto(false, null);
        return respDto;
    }

    public static RespDto buildFail(RetCode code) {
        RespDto respDto = new RespDto(false, code);
        return respDto;
    }

    public static RespDto<Long> buildOkLong(Long val) {
        return new RespDto(val);
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
