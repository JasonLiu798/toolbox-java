package com.atjl.observer.api;


import java.io.Serializable;

public abstract class Event implements Serializable {
    private static final long serialVersionUID = 3440555056777946566L;
    protected transient Object source;

    protected long crtTm;

    public Event() {
        crtTm = System.currentTimeMillis();
    }


    public long getCrtTm() {
        return crtTm;
    }

    public void setCrtTm(long crtTm) {
        this.crtTm = crtTm;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
