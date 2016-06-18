package com.jason798.xml;

import java.util.*;

public class XmlException extends Exception {

    public XmlException() {
        this.errors = new ArrayList(0);
    }

    public XmlException(String msg) {
        super(msg);
        this.errors = new ArrayList(1);
        this.errors.add(msg);
    }

    public XmlException(Collection errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public Collection getErrors() {
        return this.errors;
    }

    private Collection errors = null;
}
