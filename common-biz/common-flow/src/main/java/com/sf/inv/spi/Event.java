package com.sf.inv.spi;


public class Event {
    protected transient Object source;

    public Event() {
    }

    public Event(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
