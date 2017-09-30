package com.atjl.dbtiming.domain.biz;

import com.atjl.dbtiming.task.BaseTimingTask;
import com.atjl.dbtiming.task.CronTask;
import com.atjl.dbtiming.task.DelayTask;
import com.atjl.dbtiming.task.FixRateCondTask;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.cron.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * task status
 *
 * @author JasonLiu
 */
public class TimingManagerStatusDto implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TimingManagerStatusDto.class);

    /**
     * task status map
     */
    private TreeMap<Long, TaskStatusDto> taskStatusDtoMap = new TreeMap<>();
    // manual set
    private int taskCount;
    //unknown reason,dead task cnt
    private int deadCnt;
    //manager id
    private String mid;

    //private

    public TimingManagerStatusDto() {
        if (logger.isDebugEnabled()) {
            logger.debug("construct TimingManagerStatusDto");
        }
    }

    public String fmt() {
        StringBuilder sb = new StringBuilder();
        sb.append("MID:").append(this.mid).append(",").append("HEART_BEAT:").append(TimingConstant.DFT_HEART_BEAT).append(",NotAliveInterval:").append(TimingConstant.NOT_ALIVE_INTERVAL).append(",NotAliveRef:").append(TimingConstant.NOT_ALIVE_REF).append("\n");
        if (CollectionUtil.isEmpty(taskStatusDtoMap)) {
            return "no task runned";
        }
        sb.append(String.format("timing tasks count[%s],dead [%s]", taskCount, deadCnt)).append("\n");
        for (Map.Entry<Long, TaskStatusDto> entry : taskStatusDtoMap.entrySet()) {
            TaskStatusDto task = entry.getValue();
            sb.append("###TID ").append(task.getTid()).append(",");
            sb.append("KEY ").append(task.getTkey()).append(",");
            sb.append("TP ").append(task.getType()).append(",");
            sb.append("CNT ").append(task.getRunnedCounter()).append(",");


            sb.append("ADDTM ").append(task.getSubmitTm()).append(",\n");
            sb.append("LSTAR ").append(task.getLastStartTm()).append(",");
            sb.append("LSTOP ").append(task.getLastStopTm()).append(",");

            sb.append("DELAY ").append(task.getDelay()).append(",");
            sb.append("INTVL ").append(task.getInterval()).append(",");


            if (task.getMaxRunCount() != null) {
                sb.append("MAX ").append(task.getMaxRunCount()).append(",");
            }

            if (task.getCond() != null) {
                sb.append("COND ").append(task.getCond()).append(",");
            }

            if (task.isEnd() != null) {
                sb.append("END ").append(task.isEnd()).append(",");
            }

            sb.append("EXE ").append(task.isRunning()).append("###\n");

        }
        return sb.toString();
    }

    /**
     * add task status to map
     *
     * @param task
     */
    public void addTaskStatus(BaseTimingTask task) {
        TaskStatusDto statusDto = new TaskStatusDto();
        Long taskId = task.getTid();
        //basis info
        statusDto.setTid(taskId);
        statusDto.setTkey(task.getKey());
        statusDto.setType(String.valueOf(task.getType()));
        statusDto.setSubmitTm(DateUtil.format(DateUtil.tsms2Date(task.getSubmitTm()), DateUtil.DEFAULT_SHORT_FORMAT));
        //status info
        statusDto.setRunnedCounter(task.getRunnedCounter());

        statusDto.setDelay(DateUtil.msFmt(task.getDelayTime()));
        statusDto.setInterval(DateUtil.msFmt(task.getInterval()));
        Date now = new Date();

        if (task instanceof CronTask) {
            try {
                CronTask ct = (CronTask) task;
                CronExpression e = ct.getCronExpression();
                Date d = e.getNextValidTimeAfter(now);
                Long afterInter = d.getTime() - now.getTime();
                String afterInterStr = DateUtil.msFmt(afterInter);
                statusDto.setExeNextTm(afterInterStr);
            } catch (Exception e) {
                logger.debug("next format error {}", e);
            }
        }

        if (task instanceof DelayTask) {
            statusDto.setEnd(task.isEnd());
        }

        if (task instanceof FixRateCondTask) {
            FixRateCondTask fc = (FixRateCondTask) task;
            statusDto.setMaxRunCount(fc.getMaxTime());
            //first
            /*
            if(task.getRunnedCounter()==null || task.getRunnedCounter()==0){
                //now.getTime() - task.getSubmitTm();
                statusDto.setExeNextTm(DateUtil.msFmt( task.getDelayTime());
            }else{
                //task.getLastStopTime()
            }
            */
        }

        Long lastStart = task.getLastStartTime();
        Long lastStop = task.getLastStopTime();
        if (lastStart != null) {
            statusDto.setLastStartTm(
                    DateUtil.format(DateUtil.tsms2Date(task.getLastStartTime()), DateUtil.DEFAULT_SHORT_FORMAT));

//                    DateUtil.formatDefaultShort(DateUtil.tsms2Date(task.getLastStartTime())));
        }
        if (lastStop != null) {
            statusDto.setLastStopTm(
                    DateUtil.format(DateUtil.tsms2Date(task.getLastStopTime()), DateUtil.DEFAULT_SHORT_FORMAT));

//                    DateUtil.formatDefaultShort(DateUtil.tsms2Date(task.getLastStopTime())));
        }

        if (lastStart != null && lastStop != null) {
            long ssInterval = Math.abs(task.getLastStartTime() - task.getLastStopTime());
            statusDto.setExeTime(ssInterval);
        }

        statusDto.setRunning(task.isRunning());

        taskStatusDtoMap.put(taskId, statusDto);
    }

    public Map<Long, TaskStatusDto> getTaskStatusDtoMap() {
        return taskStatusDtoMap;
    }

    public String getMid() {
        return mid;
    }

    public int getDeadCnt() {
        return deadCnt;
    }

    public void setDeadCnt(int deadCnt) {
        this.deadCnt = deadCnt;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setTaskStatusDtoMap(TreeMap<Long, TaskStatusDto> taskStatusDtoMap) {
        this.taskStatusDtoMap = taskStatusDtoMap;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
}
