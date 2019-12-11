package com.tuniu.ord.domain;

public class ManualTask extends BaseManualDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 623133523659729257L;

    private Integer taskCode;

    private String taskName;
    
    private String url;

    public Integer getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(Integer taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
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
}