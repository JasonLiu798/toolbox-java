package com.atjl.util.common.domain;

/**
 *
 */
public class ExcepResDto {
    /**
     * execute succ = true
     * fail = false
     */
    private boolean res = true;
    /**
     * execute fail's exception
     */
    private Exception e;

    public ExcepResDto() {
    }

    public ExcepResDto(boolean res) {
        this.res = res;
    }

    public ExcepResDto(boolean res, Exception e) {
        this.res = res;
        this.e = e;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
