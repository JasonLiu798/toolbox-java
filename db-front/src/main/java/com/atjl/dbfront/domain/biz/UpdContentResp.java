package com.atjl.dbfront.domain.biz;


public class UpdContentResp {

    private String fileRes;//文件写入结果

    private String addRes;//新增结果

    private String updRes;//修改结果

    public UpdContentResp() {
        super();
    }

    public UpdContentResp(String fileRes) {
        this.fileRes = fileRes;
    }

    public String getFileRes() {
        return fileRes;
    }

    public void setFileRes(String fileRes) {
        this.fileRes = fileRes;
    }

    public String getAddRes() {
        return addRes;
    }

    public void setAddRes(String addRes) {
        this.addRes = addRes;
    }

    public String getUpdRes() {
        return updRes;
    }

    public void setUpdRes(String updRes) {
        this.updRes = updRes;
    }
}
