package com.tuniu.ord.common.task;

import com.tuniu.ord.domain.ManualTask;

public class ManualTaskWapper {

    private ManualTask task;
    
    private OrderTaskEnum taskEnum;

    /**
     * @return the task
     */
    public ManualTask getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(ManualTask task) {
        this.task = task;
    }

    /**
     * @return the taskEnum
     */
    public OrderTaskEnum getTaskEnum() {
        return taskEnum;
    }

    /**
     * @param taskEnum the taskEnum to set
     */
    public void setTaskEnum(OrderTaskEnum taskEnum) {
        this.taskEnum = taskEnum;
    }
    
    
    public OrderTaskEnum getPreTask() {
        return taskEnum.getPreTask();
    }
    
    public OrderTaskEnum getSuffixTask() {
        return taskEnum.getSuffixTask();
    }
    
    public Integer getTaskId(){
        return task.getId();
    }
    
    public Integer getManualOrderId(){
        return task.getManualOrderId();
    }
    
    public Integer getTaskCode() {
        return task.getTaskCode();
    }

    public String getTaskName() {
        return task.getTaskName();
    }
    
    public String getUrl() {
        return task.getUrl();
    } 
}
