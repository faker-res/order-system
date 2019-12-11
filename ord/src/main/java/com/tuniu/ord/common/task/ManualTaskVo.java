package com.tuniu.ord.common.task;

public class ManualTaskVo {

    private Integer manualOrderId;

    private Integer taskCode;

    private String taskName;
    
    private String url;
    
    private Integer taskStatusCode;
    
    private String taskStatus;
    
    private boolean currentTask;

    /**
     * @return the manualOrderId
     */
    public Integer getManualOrderId() {
        return manualOrderId;
    }

    /**
     * @param manualOrderId the manualOrderId to set
     */
    public void setManualOrderId(Integer manualOrderId) {
        this.manualOrderId = manualOrderId;
    }

    /**
     * @return the taskCode
     */
    public Integer getTaskCode() {
        return taskCode;
    }

    /**
     * @param taskCode the taskCode to set
     */
    public void setTaskCode(Integer taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus the taskStatus to set
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the taskStatusCode
     */
    public Integer getTaskStatusCode() {
        return taskStatusCode;
    }

    /**
     * @param taskStatusCode the taskStatusCode to set
     */
    public void setTaskStatusCode(Integer taskStatusCode) {
        this.taskStatusCode = taskStatusCode;
    }

    /**
     * @return the currentTask
     */
    public boolean isCurrentTask() {
        return currentTask;
    }

    /**
     * @param currentTask the currentTask to set
     */
    public void setCurrentTask(boolean currentTask) {
        this.currentTask = currentTask;
    }
}
