package com.tuniu.ord.service;

import java.util.List;
import java.util.Map;

import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.domain.ManualTourist;

public interface CommonOrderService {

    /**
     * 查询需求详情
     * @param manualOrderId
     * @return
     */
    ManualRequirement getRequirement(Integer manualOrderId);
    
    /**
     * 获取订单已占位数量
     * @param manualOrderId
     * @return
     */
    public int getOccupiedNum(Integer manualOrderId);
    
    /**
     * 获取订单已占位数量
     * @param orderOccupies
     * @return
     */
    public int getOccupiedNum(List<ManualOrderOccupy> orderOccupies);
    
    /**
     * 获取订单占位成人数，儿童数
     * @param manualOrderId
     * @return
     */
    public Map<Integer, Integer> getOccupiedAdultChildNum(Integer manualOrderId);
    
    /**
     * 根据人工订单编号获取人工订单
     * @param manualOrderId
     * @return
     */
    public ManualOrder getManualOrder(Integer manualOrderId);
    
    /**
     * 查询游客
     * @param manualOrderId
     * @return
     */
    public List<ManualTourist> getTourist(Integer manualOrderId);
    
    /**
     * 查询成人数，儿童数
     * @param tourists
     * @return
     */
    public Integer getNumByTouristType(List<ManualTourist> tourists, Integer touristType);
    
    /**
     * 添加备注
     * @param record
     */
    void addRemarkBySystem(Integer manualOrderId, String content);
}
