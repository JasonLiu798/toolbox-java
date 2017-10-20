package com.atjl.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用参数  corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount 初始化
 */
public class ThreadManualManager extends ThreadManager {
    public ThreadManualManager(String name, String param) {
        super(name, param);
    }

    @Override
    public boolean initFromParam(String param) {
        String[] params = param.split(ThreadConstant.PARAM_SEP);
        //corePoolSize,maxPoolSize,keepAliveTime,maxTaskCount
        //PoolName,UD,12,12,30000,200000
        ExecutorService execFixed = new ThreadPoolExecutor(
                Integer.parseInt(params[2]),//corePoolSize	5
                Integer.parseInt(params[3]),//maximumPoolSize	10
                Long.parseLong(params[4]),//maxTasksCount	200000
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(Integer.parseInt(params[5]))
        );
        if (execFixed == null) {
            return false;
        }
        this.executorService = execFixed;
        return true;
    }

    @Override
    public boolean validParam() {
        if (!ThreadInnerUtil.validParam(param)) {
            return false;
        }
        String[] arr = this.param.split(ThreadInnerUtil.PARAM_SEP);
        if (arr.length != 6) {
            return false;
        }
        return true;
    }

}
