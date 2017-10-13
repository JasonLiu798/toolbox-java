package com.atjl.dbtiming.helper;

import com.atjl.dbtiming.api.TimingService;
import com.atjl.dbtiming.api.req.DynamicTaskParam;
import com.atjl.dbtiming.domain.biz.TaskDomain;
import com.atjl.dbtiming.domain.constant.TimingConstant;
import com.atjl.dbtiming.domain.gen.*;
import com.atjl.dbtiming.mapper.biz.TaskMapper;
import com.atjl.dbtiming.mapper.gen.GenTaskHistoryMapper;
import com.atjl.dbtiming.mapper.gen.GenTaskManagerMapper;
import com.atjl.dbtiming.mapper.gen.GenTaskMapper;
import com.atjl.dbtiming.mapper.gen.GenTaskRunedMapper;
import com.atjl.dbtiming.task.TimingTaskBase;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.net.IPUtil;
import com.atjl.utilex.ApplicationContextHepler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * timing db helper
 * for get DataSourceTransactionManager,TransactionStatus
 */
@Component
public class TimingDbHelper {
    private static Logger LOG = LoggerFactory.getLogger(TimingDbHelper.class);
    @Resource
    private TimingService timingManager;
    @Resource
    private GenTaskMapper genTaskMapper;
    @Resource
    private GenTaskHistoryMapper genTaskHistoryMapper;
    @Resource
    TimingCommonHelper timingCommonHelper;
    @Resource
    TimingLockHelper timingLockHelper;
    @Resource
    private GenTaskManagerMapper genTaskManagerMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private GenTaskRunedMapper genTaskRunedMapper;

    /**
     * ################# manager ##########################
     */
    /**
     * db registe
     */
    public String registeManager() {
        Long ipLong = IPUtil.getOneRandomIpLong();
        String pid = SystemUtil.getPid();

        String id = String.format("ip-%s,pid-%s", ipLong, pid);

        DataSourceTransactionManager tm = getTransManager();
        TransactionStatus status = getDefaultTrans(tm);
        if (status == null) {
            return null;
        }
        try {
            GenTaskManager m = new GenTaskManager();
            m.setName(id);
            m.setLastUpdateTm(DateUtil.getNowTS());
            genTaskManagerMapper.insertSelective(m);
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
            throw e;
        }
        return id;
    }

    /**
     * manager alive update
     *
     * @param mid
     */
    public boolean updateManagerAlive(String mid) {
        boolean res = false;
        DataSourceTransactionManager tm = getTransManager();
        TransactionStatus status = getDefaultTrans(tm);
        if (status == null) {
            return res;
        }
        try {
            GenTaskManagerExample param = new GenTaskManagerExample();
            param.createCriteria().andNameEqualTo(mid);

            GenTaskManager manager = new GenTaskManager();
            Long now = DateUtil.getNowTS();
            manager.setLastUpdateTm(now);
            int cnt = genTaskManagerMapper.updateByExampleSelective(manager, param);
            if (cnt == 1) {
                res = true;
            } else {
                res = false;
            }
            tm.commit(status);
        } catch (Exception e) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "update manager alive db exception", e);
            tm.rollback(status);
            res = false;
        }
        return res;
    }

    /**
     * ################# check method #########################
     */
    /**
     * task exist
     *
     * @param key
     * @return
     */
    public boolean taskExistByKey(String key) {
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(TimingConstant.VALID).andTkeyEqualTo(key);
        if (genTaskMapper.countByExample(param) > 0) {
            return true;
        }
        return false;
    }


    /**
     * ###################### get method ##############################
     */
    /**
     * get dynamic task by key
     *
     * @return public GenTask getTaskByKey(String key) {
     * if (StringCheckUtil.isEmpty(key)) {
     * return null;
     * }
     * GenTaskExample param = new GenTaskExample();
     * param.createCriteria().andValidEqualTo(CommonConstant.VALID).andTkeyEqualTo(key);
     * List<GenTask> l = genTaskMapper.selectByExample(param);
     * if (!CollectionUtil.isEmpty(l)) {
     * return l.get(0);
     * }
     * return null;
     * }
     */

    public List<GenTask> getTasks(GenTaskExample param) {
        return genTaskMapper.selectByExample(param);
    }

    public List<GenTask> getValidCronTasks() {
        GenTaskExample param = new GenTaskExample();
//        param.createCriteria().andConfTypeEqualTo(TimingConstant.TP_CRON)
//                .andValidEqualTo(TimingConstant.Y);
        return genTaskMapper.selectByExample(param);
    }


    /**
     * get task by id
     *
     * @param tid
     * @return
     */
    public TaskDomain getTaskById(Long tid) {
        return taskMapper.getTask(tid);
    }

    public GenTask getTask(Long tid) {
        return genTaskMapper.selectByPrimaryKey(tid);
    }


    public GenTask getTaskGenById(Long tid) {
        return genTaskMapper.selectByPrimaryKey(tid);
    }

    /**
     * get task domain list
     *
     * @param valid
     * @param showEnd
     * @return
     */
    public List<TaskDomain> getTaskDomains(String valid, String showEnd) {
        return taskMapper.getTasks(valid, showEnd);
    }

    /**
     * get task not end,but no manager processing
     * 1.valid = Y
     * 2.status != END
     * 3.now - live tm > not live interval
     *
     * @return
     */
    public List<GenTask> getTaskNotEndButNoManagerProcessing() {
        GenTaskExample param = new GenTaskExample();
        //thread live tm less than now - interval
        long aliveTmFloor = DateUtil.getNowTS() - TimingConstant.NOT_ALIVE_REF;
//        param.createCriteria().andTstatusNotEqualTo(TimingConstant.STATUS_END).andValidEqualTo(TimingConstant.Y).andAliveTmLessThan(aliveTmFloor);
        return getTasks(param);
    }

    /**
     * ###################### db insert/update methods ########################
     */
    public Long addTask(DynamicTaskParam task) {
        DataSourceTransactionManager tm = getTransManager();
        TransactionStatus status = getDefaultTrans(tm);
        if (status == null) {
            return null;
        }
        GenTask r = new GenTask();
        try {
            r.setTkey(task.getTkey());
            r.setTaskType(task.getTaskConf().getTaskType().toString());
            r.setDatas(JSONFastJsonUtil.objectToJson(task.getTaskConf()));

            r.setTstatus(TimingConstant.STATUS_FREE);
            r.setTmutex(TimingConstant.N);
            r.setValid(TimingConstant.Y);
            r.setCrtTm(DateUtil.getNowTS());

            genTaskMapper.insertSelective(r);
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
            if (LOG.isDebugEnabled()) {
                LOG.debug("add cron exception {}", e);
            }
            return null;
        }
        return r.getTid();
    }

    /**
     * add cron task to db
     *
     * @param key
     * @param service
     * @param cronExpression
     * @return
     *
    public Long addCronTask(String key, String service, String cronExpression, int maxCnt, int runCnt) {
    DataSourceTransactionManager tm = getTransManager();
    TransactionStatus status = getDefaultTrans(tm);
    if (status == null) {
    return null;
    }
    GenTask r = new GenTask();
    try {
    r.setTservice(service);
    r.setTkey(key);
    r.setTstatus(TimingConstant.STATUS_FREE);
    r.setTmutex(TimingConstant.N);
    r.setValid(TimingConstant.Y);
    r.setConfCronExpression(cronExpression);
    r.setConfType(TaskType.CRON.toString());
    genTaskMapper.insertSelective(r);
    tm.commit(status);
    } catch (Exception e) {
    tm.rollback(status);
    if (LOG.isDebugEnabled()) {
    LOG.debug("add cron exception {}", e);
    }
    return null;
    }
    return r.getTid();
    }*/

    /**
     * add fix rate cond task to db
     * PS:must use manual transaction
     *
     * @param delay
     * @param interval
     * @param maxTime
     * @return
     *
    public Long addTask(String type, String service, String param, Long delay, Long interval, Long maxTime) {
    if (!TimingConstant.validDynTp(type)) {
    LOG.error("add task,type {} unspport", type);
    return null;
    }
    DataSourceTransactionManager tm = getTransManager();
    TransactionStatus status = getDefaultTrans(tm);
    if (status == null) {
    return null;
    }

    GenTask r = new GenTask();
    try {
    r.setTservice(service);
    r.setTstatus(TimingConstant.STATUS_FREE);
    r.setTmutex(TimingConstant.N);
    r.setValid(TimingConstant.Y);
    r.setConfDelayTm(delay);
    r.setConfIntervalTm(interval);
    r.setParam(param);
    r.setConfExeTimes(maxTime);
    r.setConfType(type);
    r.setCrtTm(DateUtil.getNowTS());
    genTaskMapper.insertSelective(r);

    GenTask nr = new GenTask();
    nr.setTid(r.getTid());
    nr.setTkey(String.valueOf(r.getTid()));
    genTaskMapper.updateByPrimaryKeySelective(nr);
    tm.commit(status);
    } catch (Exception e) {
    tm.rollback(status);
    if (LOG.isDebugEnabled()) {
    LOG.debug("add task exception {}", e);
    }
    }
    return r.getTid();
    }*/

    /**
     * set task invalid
     *
     * @param tid
     * @return
     */
    public boolean setInValid(Long tid) {
        GenTask t = new GenTask();
        t.setTid(tid);
        t.setValid(TimingConstant.N);
        return updateTaskByPk(t);
    }

    /**
     * update task status
     *
     * @param tid
     * @param status
     * @return
     */
    public boolean updateTaskStatus(Long tid, String status) {
        GenTask t = new GenTask();
        t.setTid(tid);
        t.setTstatus(status);
        //t.setAliveTm(DateUtil.getNowTS());
        return updateTaskByPk(t);
    }

    /**
     * update task's alive tm and processor
     *
     * @param manager
     * @param taskid
     * @return
     */
    public boolean updateTaskLiveTm(String manager, Long taskid) {
        GenTask t = new GenTask();
        t.setTid(taskid);
//        t.setProcessor(manager);
//        t.setAliveTm(DateUtil.getNowTS());
        return updateTaskByPk(t);
    }

    /**
     * batch update task's live time and processor
     *
     * @return
     */
    public boolean updateTaskLiveTm(List<Long> taskIds, String manager) {
        DataSourceTransactionManager tm = getTransManager();
        TransactionStatus status = getDefaultTrans(tm);
        boolean res = false;
        if (status == null) {
            return res;
        }
        //construct param
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andTidIn(taskIds).andValidEqualTo(TimingConstant.Y);
        //construct object
        GenTask t = new GenTask();
//        t.setAliveTm(DateUtil.getNowTS());
//        t.setProcessor(manager);
        try {
            genTaskMapper.updateByExampleSelective(t, param);
            res = true;
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
            if (LOG.isDebugEnabled()) {
                LOG.debug("updateTaskLiveTm {}", e);
            }
        }
        return res;
    }

    /**
     * update task
     *
     * @param t
     * @return
     */
    public boolean updateTaskByPk(GenTask t) {
        DataSourceTransactionManager tm = getTransManager();
        TransactionStatus status = getDefaultTrans(tm);
        boolean res = false;
        if (status == null) {
            return res;
        }
        try {
            genTaskMapper.updateByPrimaryKeySelective(t);
            res = true;
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
            if (LOG.isDebugEnabled()) {
                LOG.debug("updateTaskByPk {}", e);
            }
        }
        return res;
    }


    /**
     * ################## ts_task_history ##########################
     */
    /**
     * save execute history to db
     *
     * @param t
     */
    public boolean saveHistory(TimingTaskBase t) {
        DataSourceTransactionManager tm = getTransManager();
        TransactionStatus status = getDefaultTrans(tm);
        boolean res = false;
        if (status == null) {
            return res;
        }
        try {
            GenTaskHistory h = new GenTaskHistory();
            h.setEndTm(t.getLastStopTime());
            h.setStartTm(t.getLastStartTime());
            h.setExeCnt(t.getRunnedCounter());
            h.setExeStatus(String.valueOf(t.isLastExeSucc()));
            h.setProcessor(timingManager.getManagerId());
            h.setTid(t.getTid());
            genTaskHistoryMapper.insertSelective(h);
            tm.commit(status);
            res = true;
        } catch (Exception e) {
            tm.rollback(status);
            res = false;
            if (LOG.isDebugEnabled()) {
                LOG.debug("saveHistory {}", e);
            }
        }
        return res;
    }

    /**
     * ##################### db helper #########################
     */
    /**
     * get transaction manager
     *
     * @return
     */
    public DataSourceTransactionManager getTransManager() {
        DataSourceTransactionManager transactionManager = null;
        try {
            transactionManager = ApplicationContextHepler.getBean(
                    "transactionManager", DataSourceTransactionManager.class);
            return transactionManager;
        } catch (Exception e1) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "get transaction manager error", e1);
        }
        return null;
    }

    /**
     * get serial transaction status
     *
     * @return
     */
    public TransactionStatus getDefaultTrans(DataSourceTransactionManager tm) {
        return getTransactionStatus(tm, TransactionDefinition.ISOLATION_DEFAULT);
    }

    /**
     * get serial transaction status
     *
     * @return
     */
    public TransactionStatus getSerialTrans(DataSourceTransactionManager tm) {
        return getTransactionStatus(tm, TransactionDefinition.ISOLATION_SERIALIZABLE);
    }


    /**
     * get transaction status
     *
     * @param tm
     * @param level
     * @return
     */
    public TransactionStatus getTransactionStatus(DataSourceTransactionManager tm, int level) {
        if (tm == null) {
            return null;
        }
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        def.setIsolationLevel(level);
        return tm.getTransaction(def);
    }

    /**
     * get end or invalid tasks
     *
     * @return
     */
    public List<GenTask> getTasksEndOrInvalid() {
        //clear dead task,invalid task
        List<GenTask> delTasks = new LinkedList<>();
        //get end task
        List<GenTask> endTask = getTasksByStatus(TimingConstant.STATUS_END);
        if (!CollectionUtil.isEmpty(endTask)) {
            for (GenTask t : endTask) {
                delTasks.add(t);
            }
        }

        //get invalid task
        List<GenTask> invalidTask = getTasksByValid(TimingConstant.N);
        if (!CollectionUtil.isEmpty(invalidTask)) {
            for (GenTask t : invalidTask) {
                delTasks.add(t);
            }
        }
        return delTasks;
    }

    /**
     * @param status
     * @return
     */
    public List<GenTask> getTasksByStatus(String status) {
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andTstatusEqualTo(status);
        return genTaskMapper.selectByExample(param);
    }

    /**
     * get invalid tasks
     *
     * @return
     */
    public List<GenTask> getTasksByValid(String valid) {
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(valid);
        return genTaskMapper.selectByExample(param);
    }

    /**
     * delete tasks
     *
     * @param tids
     */
    public void deleteTasks(List<Long> tids) {
        if (CollectionUtil.isEmpty(tids)) {
            return;
        }
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andTidIn(tids);
        genTaskMapper.deleteByExample(param);

    }

    /**
     * delete task histories
     *
     * @param tids
     */
    public int deleteTaskHistories(List<Long> tids) {
        if (CollectionUtil.isEmpty(tids)) {
            return 0;
        }
        GenTaskHistoryExample param = new GenTaskHistoryExample();
        param.createCriteria().andTidIn(tids);
        return genTaskHistoryMapper.deleteByExample(param);
    }

    public int deleteTaskHistories(List<Long> tids, Long lessDate) {
        if (CollectionUtil.isEmpty(tids)) {
            return 0;
        }
        GenTaskHistoryExample param = new GenTaskHistoryExample();
        param.createCriteria().andTidIn(tids).andStartTmLessThan(lessDate);
        return genTaskHistoryMapper.deleteByExample(param);
    }

    /**
     * check concurrent insert same task
     *
     * @param param
     * @param tid
     * @return
     *
    public RetCode checkConcurrentTask(AddTaskParam param, Long tid) {
    RetCode res = RetCode.SUCCESS;
    if (param.isCheckUnique()) {
    GenTaskExample listParam = new GenTaskExample();
    Long now = DateUtil.getNowTS();
    listParam.createCriteria()
    //.andConfTypeEqualTo(param.getType())
    //                    .andTserviceEqualTo(param.getService())
    //                    .andParamEqualTo(param.getParam())
    //                    .andConfDelayTmEqualTo(param.getDelay())
    //                    .andConfIntervalTmEqualTo(param.getInterval())
    //                    .andConfExeTimesEqualTo(param.getMaxTime())
    .andCrtTmBetween(now - param.getUniqueChkInterval(), now + param.getUniqueChkInterval());
    List<GenTask> sameTaskList = getTasks(listParam);
    if (CollectionUtil.isEmpty(sameTaskList)) {
    res = RetCode.RE_CHECK_ADD_DYN_TASK_DB_FAIL;
    } else {
    if (sameTaskList.size() > 1) {
    //concurent insert
    //get min tid
    Long minTid = tid;
    for (GenTask st : sameTaskList) {
    if (st.getTid() < minTid) {
    minTid = st.getTid();
    }
    }
    if (minTid != tid) {
    res = RetCode.CONCURRENT_DB_INSERT_NOT_MIN;
    }
    }
    }
    }
    return res;
    }*/

    /**
     * get task histories
     *
     * @param tid
     * @return
     */
    public List<GenTaskHistory> getTaskHistory(Long tid) {
        GenTaskHistoryExample param = new GenTaskHistoryExample();
        param.createCriteria().andTidEqualTo(tid);
        return genTaskHistoryMapper.selectByExample(param);
    }


    /**
     * add task runned to backup table
     *
     * @param t
     */
    public void addTaskRuned(GenTaskRuned t) {
        genTaskRunedMapper.insertSelective(t);
    }
}
