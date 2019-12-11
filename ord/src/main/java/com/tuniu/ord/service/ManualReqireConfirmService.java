package com.tuniu.ord.service;

public interface ManualReqireConfirmService {

    /**
     * 验证是否通过需求确认
     * @param manualOrderId
     * @param requirementId
     */
    public boolean requireConfirm(Integer manualOrderId, Integer requirementId, StringBuffer msg);
    
    
}
