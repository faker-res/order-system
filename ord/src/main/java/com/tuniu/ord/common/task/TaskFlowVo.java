package com.tuniu.ord.common.task;

import com.tuniu.ord.vo.common.BaseVo;

public class TaskFlowVo extends BaseVo{

    private Integer manualOrderId;
    
    private Integer taskCode;

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
}
