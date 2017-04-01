package com.jason798.timing.entity;

import com.jason798.collection.CollectionUtil;
import com.jason798.common.DateUtil;
import com.jason798.timing.task.BaseTask;
import com.jason798.timing.task.DelayTask;
import com.jason798.timing.task.FixRateCondTask;
import com.jason798.timing.task.FixRateTask;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * task status
 *
 * @author JasonLiu
 */
public class TimingManagerStatusDto implements Serializable {
    private int taskCount;
    private Map<String, TaskStatusDto> taskStatusDtoMap = new HashMap<>();

    public TimingManagerStatusDto() {
    }

    public String fmt() {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtil.isEmpty(taskStatusDtoMap)) {
            return "no task runned";
        }
        sb.append("timing tasks count[").append(taskCount).append("]:").append("\n");
        for (Map.Entry<String, TaskStatusDto> entry : taskStatusDtoMap.entrySet()) {
            TaskStatusDto task = entry.getValue();
            sb.append("task ").append(entry.getKey()).append(",");
            sb.append("type ").append(task.getType()).append(",");
            sb.append("LastStart ").append(task.getLastStartTm()).append(",");
            sb.append("LastStop ").append(task.getLastStopTm()).append(",");
            sb.append("dealy ").append(task.getDelay()).append(",");
            sb.append("interval ").append(task.getInterval()).append(",");
            sb.append("isRunning ").append(task.isRunning()).append(",");
            sb.append("Runned ").append(task.isRunned()).append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param task
     */
    public void addTaskStatus(BaseTask task) {
        TaskStatusDto statusDto = new TaskStatusDto();
        String taskId = task.getTid();
        statusDto.setTaskId(taskId);
        statusDto.setType(String.valueOf(task.getType()));
        if(task instanceof DelayTask){
            DelayTask dtask = (DelayTask) task;
            statusDto.setRunned(dtask.isRunned());
        }else{
            statusDto.setRunned(null);
        }
        statusDto.setDelay(task.getDelayTime());
        if(task instanceof FixRateCondTask ){
            FixRateCondTask fctask = (FixRateCondTask) task;
            statusDto.setInterval(fctask.getInterval());
        }
        if(task instanceof FixRateTask){
            FixRateTask fctask = (FixRateTask) task;
            statusDto.setInterval(fctask.getInterval());
        }

        statusDto.setLastStartTm(DateUtil.formatDefault(DateUtil.tsms2Date(task.getLastStartTime())));
        statusDto.setLastStopTm(DateUtil.formatDefault(DateUtil.tsms2Date(task.getLastStopTime())));

        statusDto.setRunning(task.isRunning());
        long ssInterval = Math.abs(task.getLastStartTime() - task.getLastStopTime());
        statusDto.setStartStopInterval(ssInterval);
        taskStatusDtoMap.put(taskId, statusDto);
    }

    @Override
    public String toString() {
        return "TimingManagerStatusDto{" +
                "taskCount=" + taskCount +
                ", taskStatusDtoMap=" + taskStatusDtoMap +
                '}';
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public Map<String, TaskStatusDto> getTaskStatusDtoMap() {
        return taskStatusDtoMap;
    }

    public void setTaskStatusDtoMap(Map<String, TaskStatusDto> taskStatusDtoMap) {
        this.taskStatusDtoMap = taskStatusDtoMap;
    }
}
