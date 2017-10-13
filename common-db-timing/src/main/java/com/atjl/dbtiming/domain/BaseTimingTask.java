package com.atjl.dbtiming.domain;


import com.atjl.dbtiming.api.req.TaskConf;

public abstract class BaseTimingTask {
    private Long tid;
    private String tkey;
    private Object serviceBean;
    private String serviceName;

    private State
    private TaskConf taskConf;

    public abstract long getNextExecuteTime();

    //public abstract Long persist(TimingDbHelper helper);

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey;
    }

    public Object getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(Object serviceBean) {
        this.serviceBean = serviceBean;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public TaskConf getTaskConf() {
        return taskConf;
    }

    public void setTaskConf(TaskConf taskConf) {
        this.taskConf = taskConf;
    }
}
