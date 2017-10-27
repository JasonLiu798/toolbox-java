package com.atjl.dbfront.domain.gen;

import java.io.Serializable;
import java.util.Date;

public class TsContent implements Serializable {
    private String cname;

    private String ctype;

    private String cversion;

    private String preVersion;

    private Date crtTm;

    private Date updTm;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    public String getCversion() {
        return cversion;
    }

    public void setCversion(String cversion) {
        this.cversion = cversion == null ? null : cversion.trim();
    }

    public String getPreVersion() {
        return preVersion;
    }

    public void setPreVersion(String preVersion) {
        this.preVersion = preVersion == null ? null : preVersion.trim();
    }

    public Date getCrtTm() {
        return crtTm;
    }

    public void setCrtTm(Date crtTm) {
        this.crtTm = crtTm;
    }

    public Date getUpdTm() {
        return updTm;
    }

    public void setUpdTm(Date updTm) {
        this.updTm = updTm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}