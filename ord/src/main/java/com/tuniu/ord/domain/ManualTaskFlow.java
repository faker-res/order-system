package com.tuniu.ord.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.jsondeseralize.JackJsonDateTimeFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateTimeParse;

public class ManualTaskFlow extends BaseDomain{

    /**
     * 
     */
    private static final long serialVersionUID = 4510403887630755415L;

    private Integer taskId;

    private Integer preTaskId;

    private Integer postTaskId;

    private Integer taskStatus;

    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getPreTaskId() {
        return preTaskId;
    }

    public void setPreTaskId(Integer preTaskId) {
        this.preTaskId = preTaskId;
    }

    public Integer getPostTaskId() {
        return postTaskId;
    }

    public void setPostTaskId(Integer postTaskId) {
        this.postTaskId = postTaskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}