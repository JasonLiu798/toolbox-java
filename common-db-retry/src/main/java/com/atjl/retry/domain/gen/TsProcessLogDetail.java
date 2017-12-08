package com.atjl.retry.domain.gen;

import java.io.Serializable;
import java.util.Date;

public class TsProcessLogDetail implements Serializable {
    private Long processLogDetailId;

    private Long processLogId;

    private Date crtTm;

    private Date updTm;

    private String basic;

    private static final long serialVersionUID = 1L;

    public Long getProcessLogDetailId() {
        return processLogDetailId;
    }

    public void setProcessLogDetailId(Long processLogDetailId) {
        this.processLogDetailId = processLogDetailId;
    }

    public Long getProcessLogId() {
        return processLogId;
    }

    public void setProcessLogId(Long processLogId) {
        this.processLogId = processLogId;
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

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic == null ? null : basic.trim();
    }
}