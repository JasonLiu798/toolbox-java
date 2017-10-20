package com.atjl.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ThreadFixManager extends ThreadManager {
    public ThreadFixManager(String name, String param) {
        super(name, param);
    }

    @Override
    public boolean initFromParam(String param) {
        // name,Fix,123
        String[] params = param.split(ThreadConstant.PARAM_SEP);
        ExecutorService execFixed = Executors.newFixedThreadPool(Integer.parseInt(params[2]));
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
        if (arr.length != 3) {
            return false;
        }
        return true;
    }

}
