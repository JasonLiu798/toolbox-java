package com.atjl.dbtiming.service;

import com.atjl.common.constant.Status;
import com.atjl.dbtiming.api.TimingService;
import com.atjl.dbtiming.api.domain.RetCode;
import com.atjl.dbtiming.core.TimingContext;
import com.atjl.dbtiming.domain.biz.TaskDomain;
import com.atjl.dbtiming.domain.biz.TimingManagerStatusDto;
import com.atjl.dbtiming.domain.constant.TimingConstant;
import com.atjl.dbtiming.domain.gen.GenTask;
import com.atjl.dbtiming.helper.TimingCommonHelper;
import com.atjl.dbtiming.helper.TimingDbHelper;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.helper.TimingLockHelper;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.utilex.ApplicationContextHepler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * timing mansger
 * todo:clear time-out task
 * todo:test restart task
 */
@Component
public class TimingServiceImpl implements TimingService {
    private static final Logger logger = LoggerFactory.getLogger(TimingServiceImpl.class);
    @Resource
    private TimingCommonHelper timingCommonHelper;
    @Resource
    private TimingInnerManager timingInnerManager;
    @Resource
    private TimingLockHelper lockHelper;
    @Resource
    private TimingDbHelper timingDbHelper;
    //manager state
    private volatile Status state = Status.STOPPED;
    /**
     * unique id
     */
    private String managerId;

    public TimingServiceImpl() {
        if (logger.isDebugEnabled()) {
            logger.debug("init TimingManagerImpl ");
        }
    }

    /**
     * init core pool
     */
    public void init() {
        RetCode chkRes = canStart();
        if (chkRes != RetCode.SUCCESS) {
            LogDbUtil.warn(TimingConstant.MODULE_TIMING, "state not right");
            return;
        }
        state = Status.INIT;//state to starting

        String managerIdParam = timingDbHelper.registeManager();
        if (StringCheckUtil.isEmpty(managerIdParam)) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "registe timing manager fail" + managerIdParam);
            return;
        }
        this.managerId = managerIdParam;
        TimingContext.MID = managerIdParam;
        timingInnerManager.init();

        LogDbUtil.info(TimingConstant.MODULE_TIMING, "init task manager success,id " + managerIdParam);
        state = Status.RUNNING;//state to running
    }

//    @Override
//    public RetCode addDynamicTask(AddTaskParam param) {
//        return null;
//    }

//    @Override
//    public RetCode addCronTask(AddTaskParam param) {
//        return null;
//    }

    @Override
    public RetCode updateCronTask(Long tid, String cronExpression) {
        return null;
    }

    @Override
    public RetCode execCronTask(Long tid) {
        return null;
    }

    @Override
    public RetCode reExecDynamicTask(Long tid) {
        return null;
    }

    /**
     * add dynamic fix rate cond task with param
     *
     * @return
     *
     @Override public RetCode addDynamicTask(AddTaskParam param) {
     RetCode chkRet = timingCommonHelper.checkTaskCanProcess(param);
     if (chkRet != RetCode.SUCCESS) {
     return chkRet;
     }

     Object target = getTarget(param.getService());
     if (target == null) {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "get target null");
     return RetCode.GET_BEAN_NULL;
     }

     //db insert
     //String type,String service,String param,Long delay, Long interval, Long maxTime
     Long tid = timingDbHelper.addTask(param.getType(), param.getService(), param.getParam(), param.getDelay(), param.getInterval(), param.getMaxTime());
     if (tid == null) {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "insert task to db fail");
     return RetCode.ADD_DYN_TASK_DB_FAIL;
     }

     //check concurrent insert
     RetCode insConcurrentRes = timingDbHelper.checkConcurrentTask(param, tid);
     if (insConcurrentRes != RetCode.SUCCESS) {
     return insConcurrentRes;
     }

     //submit to pool
     RetCode subRes = timingInnerManager.submitFixRate(
     tid,//tid
     param.getTkey(),//key
     target,//target
     param.isForever(),//forever
     param.isHasParam(),//param
     param.isHasCond(),//cond
     true,//persist
     param.getParam(),//exe param
     param.getDelay(),
     param.getInterval(),
     param.getMaxTime(),
     0L //runed count
     );

     if (subRes == RetCode.SUCCESS) {//succ
     logger.debug("submit dynamic task succ,tid {},service {},ret {}", tid, param.getService(), subRes);
     timingDbHelper.updateTaskStatus(tid, TimingConstant.STATUS_WAITING);//change status to waiting
     return RetCode.SUCCESS;
     } else {//fail
     timingDbHelper.setInValid(tid);//set invalid
     LogDbUtil.error(TimingService.class.getSimpleName(), "submit dynamic task fail,tid " + tid + ",service " + param.getService());
     return RetCode.RE_ADD_DYN_TASK_TO_POOL_FAIL;
     }
     }*/

    /**
     * add a cron task
     *
     * @return
     *
     @Override public RetCode addCronTask(AddTaskParam param) {
     RetCode checkRet = timingCommonHelper.checkTaskCanProcess(param);
     if (checkRet != RetCode.SUCCESS) {
     return checkRet;
     }

     Object target = getTarget(param.getService());
     if (target == null) {
     return RetCode.GET_BEAN_FAIL;
     }

     //db insert
     Long tid = null;//timingDbHelper.addCronTask(param.getTkey(), param.getService(), param.getCronExpression());
     if (tid == null) {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "insert cron task to db fail");
     return RetCode.ADD_CRON_DB_FAIL;
     }

     //submit to pool
     //String key, Object target, String cronExpression
     RespDto<Long> subRes = timingInnerManager.submitCronTask(
     tid,
     param.getTkey(),
     target,
     param.getCronExpression(),
     0L, 0L, 0L
     );
     if (subRes.isSucc()) {
     logger.debug("submit cron task succ,tid {},service {}", tid, param.getService());
     return RetCode.SUCCESS;
     } else {
     timingDbHelper.setInValid(tid);
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "submit dynamic task fail,tid " + tid + ",service " + param.getService());
     return RetCode.ADD_CRON_POOL_FAIL;
     }
     }*/

    /**
     * update cron task
     *
     * @param tid
     * @param cronExpression
     * @return
     *
     @Override public RetCode updateCronTask(Long tid, String cronExpression) {
     if (timingInnerManager.taskExist(tid)) {//check exist in this manager
     if (!timingCommonHelper.validCronExpression(cronExpression)) {//check valid expression
     return RetCode.CRON_EXPESSION_INVALID;
     }
     //cancel task
     boolean cancelRes = timingInnerManager.cancelTask(tid);
     if (cancelRes) {
     //reexecute
     GenTask t = new GenTask();
     t.setTid(tid);
     t.setConfCronExpression(cronExpression);
     timingDbHelper.updateTaskByPk(t);
     return execCronTask(tid);
     } else {
     return RetCode.CANCEL_TASK_FAIL;
     }
     } else {
     throw new TimingException("task not exist,tid " + tid);
     }
     }*/


    /**
     * exec cron task
     *
     * @param tid
     *
    public RetCode execCronTask(Long tid) {
    TaskDomain rawTask = timingDbHelper.getTaskById(tid);
    //pre check can process
    RetCode chkRet = timingCommonHelper.checkTaskCanProcess(rawTask);
    if (chkRet != RetCode.SUCCESS) {
    LogDbUtil.info(TimingConstant.MODULE_TIMING, "cron task cannot start " + JSONFastJsonUtil.objectToJson(rawTask));
    return chkRet;
    }

    //get execute service
    Object target = getTarget(rawTask.getTservice());
    if (target == null) {
    LogDbUtil.error(TimingConstant.MODULE_TIMING, "rexec task fail,target null,service " + rawTask.getTservice());
    return RetCode.GET_BEAN_NULL;
    }

    Long runCnt = rawTask.getRunCnt();
    if (runCnt == null) {
    runCnt = 0L;
    }

    //submit to pool
    boolean lock = lockHelper.lock(tid);
    if (lock) {
    try {
    RespDto<Long> submitRes = timingInnerManager.submitCronTask(
    tid, rawTask.getTkey(),
    target, rawTask.getConfCronExpression(),
    runCnt, rawTask.getLastStartMs(), rawTask.getLastStopMs()
    );
    if (submitRes.isSucc()) {
    //update status and alive time
    GenTask ut = new GenTask();
    ut.setTid(tid);
    ut.setTstatus(TimingConstant.STATUS_WAITING);
    ut.setAliveTm(DateUtil.getNowTS());
    timingDbHelper.updateTaskByPk(ut);
    return RetCode.SUCCESS;
    } else {
    LogDbUtil.error(TimingConstant.MODULE_TIMING, "submit cron task fail,tid " + tid);
    return RetCode.ADD_CRON_TO_POOL_FAIL;
    }
    } catch (Exception e) {
    LogDbUtil.error(TimingConstant.MODULE_TIMING, "cron task init fail", e);
    return RetCode.ADD_CRON_TO_POOL_EXCEPTION;
    } finally {
    lockHelper.unlock(tid);
    }
    } else {
    return RetCode.GET_LOCK_FAIL;
    }
    }*/

    /**
     * get target service bean from spring
     *
     * @param service
     * @return
     */
    private Object getTarget(String service) {
        Object target = null;
        try {
            target = ApplicationContextHepler.getBeanByName(service);
        } catch (Exception e) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "get target service exception", e);
        }
        return target;
    }

    /**
     * re exe dynamic task
     *
     * @param tid
     *
     @Override public RetCode reExecDynamicTask(Long tid) {
     //get raw task from db
     TaskDomain rawTask = timingDbHelper.getTaskById(tid);
     RexeTaskParam param = timingCommonHelper.db2param(rawTask);
     if (!param.isSucc()) {
     return param.getFailReason();
     }
     RetCode checkRet = timingCommonHelper.checkTaskCanProcess(param);
     //pre check,before lock
     if (checkRet != RetCode.SUCCESS) {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "rexec task fail,check ret " + checkRet + ",task " + JSONFastJsonUtil.objectToJson(rawTask));
     if (checkRet == RetCode.RUN_CNT_REACH_MAX_CNT) {
     timingInnerManager.backupAndDelTask(tid);
     }
     return checkRet;
     }

     //get target
     Object target = getTarget(rawTask.getTservice());
     if (target == null) {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "rexec task fail,target null,service " + rawTask.getTservice());
     return RetCode.GET_BEAN_NULL;
     }

     long runCnt = param.getRunedCnt();
     long delay = param.getDelay();
     long dbCrtTm = param.getCrtTm();
     long nowTm = DateUtil.getNowTS();
     long interval = param.getInterval();

     //calc new delay

     if (dbCrtTm != 0) {//crt tm correct
     //not runned
     if (dbCrtTm < nowTm) {
     long alreadyDelayedMs = 0;
     if (runCnt == 0) {
     alreadyDelayedMs = (nowTm - dbCrtTm) * 1000;
     delay = delay - alreadyDelayedMs;
     } else {
     //already runned
     Long lastStop = param.getLastStopMs();
     if (lastStop == null) {
     //can't find run history
     } else {
     alreadyDelayedMs = (nowTm - lastStop / 1000) * 1000;
     if (alreadyDelayedMs < 0 || alreadyDelayedMs > interval) {
     alreadyDelayedMs = 0;
     } else {
     //error runned second
     }
     }
     delay = param.getInterval() - alreadyDelayedMs;
     }
     }//else error tm,not process
     } else {

     }

     if (delay <= 0) {//in case of
     delay = param.getDelay();
     }

     boolean lockRes = lockHelper.lock(tid);//lock
     if (lockRes) {
     try {
     //submit to pool
     RetCode subRes = timingInnerManager.submitFixRate(tid,
     param.getTkey(),
     target,
     param.isForever(),//forever
     param.isHasParam(),//param
     param.isHasCond(),//cond
     param.isPersist(),//persist
     param.getParam(),//exe param
     delay,
     param.getInterval(),
     param.getMaxTime(),
     param.getRunedCnt()
     );

     if (subRes == RetCode.SUCCESS) {
     logger.debug("reexe dynamic task succ,tid {},service {},delay {}", tid, rawTask.getTservice(), delay);
     //todo:if delay = 0,may dead lock
     GenTask ut = new GenTask();
     ut.setTid(tid);
     ut.setTstatus(TimingConstant.STATUS_WAITING);
     ut.setAliveTm(DateUtil.getNowTS());
     timingDbHelper.updateTaskByPk(ut);
     return RetCode.SUCCESS;
     } else {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "re exe dynamic task fail,tid " + tid + ",service " + rawTask.getTservice());
     return subRes;
     }
     } catch (Exception e) {
     LogDbUtil.error(TimingConstant.MODULE_TIMING, "submit fix rate task exception", e);
     return RetCode.RE_ADD_DYN_TASK_TO_POOL_EXCEPTION;
     } finally {
     lockHelper.unlock(tid);
     }
     } else {
     return RetCode.GET_LOCK_FAIL;
     }
     }
     */

    /**
     * manual stop all task
     *
     * @return
     */
    @Override
    public RetCode manualStopAll() {
        if (state != Status.RUNNING) {
            return RetCode.NOT_RUNNIG;
        }
        state = Status.STOPPING;
        timingInnerManager.shutDown();
        state = Status.STOPPED;
        return RetCode.SUCCESS;
    }

    /**
     * manual stop one task
     *
     * @param tid
     * @return
     */
    @Override
    public RetCode manualStop(Long tid, String type) {
        // type = T ,temp stop
        // type = F ,full stop,update db status->D
        if (!timingInnerManager.taskExist(tid)) {
            return RetCode.TASK_NOT_IN_THE_POOL;
        }
        boolean res = timingInnerManager.cancelTask(tid);
        if (res) {
            if (StringCheckUtil.equal(type, "F")) {
                //full stop task
                GenTask t = new GenTask();
                t.setTid(tid);
                t.setTstatus(TimingConstant.STATUS_END);
                boolean updRes = timingDbHelper.updateTaskByPk(t);
                if (updRes) {
                    return RetCode.SUCCESS;
                } else {
                    return RetCode.UPD_TASK_STATUS_DB_FAIL;
                }
            }
        } else {
            return RetCode.CANCEL_TASK_FAIL;
        }
        return RetCode.SUCCESS;
    }

    private RetCode canStart() {
        if (this.state == Status.STOPPED) {
            return RetCode.SUCCESS;
        }
        return RetCode.NOT_STOPPED;
    }

    private RetCode canStop() {
        if (this.state == Status.RUNNING || this.state == Status.INIT) {
            return RetCode.SUCCESS;
        }
        return RetCode.NOT_STOPPED;
    }


    /**
     * manual start pool
     *
     * @return
     */
    @Override
    public RetCode manualStart() {
        RetCode chkRes = canStart();
        if (chkRes == RetCode.SUCCESS) {
            TimingContext.clearAll();//clear context
            init();//start
            return RetCode.SUCCESS;
        } else {
            return chkRes;
        }
    }

    /**
     * manual restart pool
     *
     * @return
     */
    @Override
    public RetCode restart() {
        RetCode chkRes = canStop();
        if (chkRes == RetCode.SUCCESS) {
            state = Status.STOPPING;
            TimingContext.deatroyPool();
            TimingContext.clearAll();
            state = Status.STOPPED;
            init();
            return RetCode.SUCCESS;
        } else {
            return chkRes;
        }
    }

    /**
     * manual run task
     *
     * @return
     * @Override public RetCode manualRun(Long tid, String paramStr) {
     * TaskDomain td = timingDbHelper.getTaskById(tid);
     * if (td == null) {
     * return RetCode.TASK_NOT_EXIST_IN_DB;
     * //throw new TimingException("not exist");
     * }
     * AddTaskParam param = timingCommonHelper.db2param(td);
     * if (!param.isSucc()) {
     * return param.getFailReason();
     * }
     * <p>
     * Object target = getTarget(td.getTservice());
     * if (target == null) {
     * LogDbUtil.error(TimingConstant.MODULE_TIMING, "manual exec task fail,target null,service " + td.getTservice());
     * return RetCode.GET_BEAN_NULL;
     * }
     * <p>
     * Method exeMethod = timingCommonHelper.getExeMethod(target, param.isHasParam());
     * if (exeMethod == null) {
     * logger.debug("get exe {} exeMethod null", param.isHasParam());
     * }
     * <p>
     * try {
     * if (param.isHasParam()) {
     * exeMethod.invoke(target, paramStr);
     * } else {
     * exeMethod.invoke(target);
     * }
     * } catch (IllegalAccessException | InvocationTargetException e) {
     * return RetCode.MANUAL_RUN_REFLECT_EXCEPTION;
     * }
     * return RetCode.SUCCESS;
     * }
     */

    @Override
    public List<GenTask> manualClearTask() {
        List<GenTask> delTasks = timingDbHelper.getTasksEndOrInvalid();
        if (logger.isDebugEnabled()) {
            logger.debug("del tasks {}", JSONFastJsonUtil.objectToJson(delTasks));
        }
        //del stopped and invalid task,cancel task
        timingInnerManager.delTaskAndBackup(delTasks);
        return delTasks;
    }

    /**
     * ###################### db #############################
     */

    /**
     * get task list from db
     *
     * @param valid
     * @param showEnd
     * @return
     */
    @Override
    public List<TaskDomain> getTaskList(String valid, String showEnd) {
        valid = StringEscapeUtils.escapeHtml4(valid);
        showEnd = StringEscapeUtils.escapeHtml4(showEnd);
        return timingDbHelper.getTaskDomains(valid, showEnd);
    }

    /**
     * get status
     *
     * @return
     */
    @Override
    public TimingManagerStatusDto getStatus() {
        return TimingContext.getStatusRaw();
    }

    /**
     * ##################### getter & setter #####################
     */
    public TimingCommonHelper getTimingCommonHelper() {
        return timingCommonHelper;
    }

    public void setTimingCommonHelper(TimingCommonHelper timingCommonHelper) {
        this.timingCommonHelper = timingCommonHelper;
    }

    public String getManagerId() {
        return managerId;
    }

    @Override
    public RetCode manualRun(Long tid, String param) {
        return null;
    }


    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public TimingLockHelper getLockHelper() {
        return lockHelper;
    }

    public void setLockHelper(TimingLockHelper lockHelper) {
        this.lockHelper = lockHelper;
    }

    public TimingDbHelper getTimingDbHelper() {
        return timingDbHelper;
    }

    public void setTimingDbHelper(TimingDbHelper timingDbHelper) {
        this.timingDbHelper = timingDbHelper;
    }
}


