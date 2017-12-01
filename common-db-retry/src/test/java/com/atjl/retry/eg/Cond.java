package com.atjl.retry.eg;


public class Cond {

    private String cond;

    public Cond(String cond) {
        this.cond = cond;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    @Override
    public String toString() {
        return "Cond{" +
                "cond='" + cond + '\'' +
                '}';
    }
}
