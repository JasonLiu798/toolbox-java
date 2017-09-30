package com.atjl.logdb.whole;


import com.atjl.logdb.api.LogService;
import com.atjl.logdb.api.domain.OpLog;
import com.atjl.logdb.task.AbstractLogWriteTask;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.queue.IQueue;

public class WriteTask extends AbstractLogWriteTask {

    public WriteTask(String id, LogService logService, IQueue queue, Long srvIp) {
        super(id, logService, queue, srvIp);
    }

    @Override
    public void beforeWrite(OpLog log) {
        log.setUserName("xxcxc");
        System.out.println("bf " + JSONFastJsonUtil.objectToJson(log));
    }

    @Override
    public void afterWrite(OpLog log) {
        System.out.println("af " + JSONFastJsonUtil.objectToJson(log));
    }
}
