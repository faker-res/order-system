package com.tuniu.ord.common.task;

import java.util.Date;

import com.tuniu.ord.domain.ManualTaskFlow;

public class ManualTaskFlowWapper {

    private ManualTaskFlow taskFlow;
    
    private TaskStatusEnum status;

    /**
     * @return the taskFlow
     */
    public ManualTaskFlow getTaskFlow() {
        return taskFlow;
    }

    /**
     * @param taskFlow the taskFlow to set
     */
    public void setTaskFlow(ManualTaskFlow taskFlow) {
        this.taskFlow = taskFlow;
    }

    /**
     * @return the status
     */
    public TaskStatusEnum getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(TaskStatusEnum status) {
        this.status = status;
        this.taskFlow.setTaskStatus(status.getKey());
    }
    
    public void setPreTaskId(Integer preTaskId) {
        taskFlow.setPreTaskId(preTaskId);
    }
    
    public void setPostTaskId(Integer postTaskId) {
        taskFlow.setPostTaskId(postTaskId);
    }
    
    public void setTaskId(Integer taskId) {
        taskFlow.setTaskId(taskId);
    }

    public Integer getPreTaskId() {
        return taskFlow.getPreTaskId();
    }

    public Integer getPostTaskId() {
        return taskFlow.getPostTaskId();
    }

    public Integer getTaskStatus() {
        return taskFlow.getTaskStatus();
    }

    public void setTaskStatus(Integer taskStatus) {
        taskFlow.setTaskStatus(taskStatus);
    }

    public Date getStartTime() {
        return taskFlow.getStartTime();
    }

    public void setStartTime(Date startTime) {
        taskFlow.setStartTime(startTime);
    }

    public Date getEndTime() {
        return taskFlow.getEndTime();
    }

    public void setEndTime(Date endTime) {
        taskFlow.setEndTime(endTime);
    }
}
