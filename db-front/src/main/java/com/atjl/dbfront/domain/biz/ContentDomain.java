package com.atjl.dbfront.domain.biz;

import java.io.Serializable;

public class ContentDomain implements Serializable{
	
	private static final long serialVersionUID = 3807318977050002077L;
	private String cname;
    private String ctype;
    private String cversion;
    private String content;
    private String preVersion;

    public ContentDomain(){
    	super();
    }

    public String getPreVersion() {
        return preVersion;
    }

    public void setPreVersion(String preVersion) {
        this.preVersion = preVersion;
    }

    public String getCversion() {
        return cversion;
    }

    public void setCversion(String cversion) {
        this.cversion = cversion;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
