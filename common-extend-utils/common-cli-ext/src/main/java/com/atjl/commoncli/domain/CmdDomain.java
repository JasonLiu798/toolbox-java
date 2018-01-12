package com.atjl.commoncli.domain;


public class CmdDomain {

    private String cmd;

    private String[] opts;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String[] getOpts() {
        return opts;
    }

    public void setOpts(String[] opts) {
        this.opts = opts;
    }
}
