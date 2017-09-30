package com.atjl.logdb.task;

import com.atjl.common.api.task.PoolBaseTask;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.logdb.api.LogService;
import com.atjl.logdb.api.domain.LogDbConstant;
import com.atjl.logdb.api.domain.LogDbContext;
import com.atjl.logdb.api.domain.OpLog;
import com.atjl.util.character.StringUtil;
import com.atjl.util.queue.IQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * read data task and write
 */
public abstract class AbstractLogWriteTask extends PoolBaseTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(AbstractLogWriteTask.class);
    private static IQueue queue;
    private static Long srvIp;
    private LogService logService;

    public AbstractLogWriteTask() {
        super();
    }

    public AbstractLogWriteTask(String id, LogService logService, IQueue queue, Long srvIp) {
        super(id);
        this.logService = logService;
        AbstractLogWriteTask.srvIp = srvIp;
        AbstractLogWriteTask.queue = queue;
    }

    @Override
    public void run() {
        if (logService == null) {
            LogDbUtil.errorSync(AbstractLogWriteTask.class.getSimpleName(), "init log reader,logService null", null);
            return;
        }
        LogDbUtil.infoSync(AbstractLogWriteTask.class.getSimpleName(), "log writer start to run");
        while (start) {
            try {
                OpLog log = (OpLog) queue.receiveMessage(5000);
                if (log == null) {
                    continue;
                }
                beforeWrite(log);
                fmtLog(log);
                logService.insert(log);
                afterWrite(log);
            } catch (InterruptedException e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("log queue read time out,{}", e);
                }
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("write log exception occur,exception {}", e);
                }
                LogDbContext.WRITE_ERROR.incrementAndGet();
                if (LogDbContext.WRITE_ERROR.intValue() < 1000) {
                    logger.error("insert log exception,msg: {}", e.getMessage());
                }
            }
        }//loop end
        LogDbUtil.errorSync(AbstractLogWriteTask.class.getSimpleName(), "stop", null);
    }

    abstract public void beforeWrite(OpLog log);

    abstract public void afterWrite(OpLog log);

    private static void fmtLog(OpLog log) {
        log.setServiceIp(srvIp);
        StringUtil.filter2len("opParam", log, LogDbConstant.PARAM_MAX_SIZE);
        StringUtil.filter2len("opRes", log, LogDbConstant.RES_MAX_SIZE);
        StringUtil.filter2len("opRef", log, LogDbConstant.REF_MAX_SIZE);
    }

}
