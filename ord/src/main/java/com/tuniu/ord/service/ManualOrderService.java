package com.tuniu.ord.service;

import java.text.ParseException;

import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.vo.ChangeProductVo;
import com.tuniu.ord.vo.ManualOrderPriceVo;
import com.tuniu.ord.vo.RequirementVo;
import com.tuniu.ord.vo.common.ListVo;

public interface ManualOrderService {

    /**
     * 添加备忘
     * 
     * @param record
     */
    public void addRemark(ManualRemark record);

    /**
     * 查询已选产品
     * 
     * @param manualOrderId
     * @return
     * @throws ParseException
     */
    public ListVo querySelectdProduct(RequirementVo vo) throws ParseException;

    /**
     * 订单占位
     * 
     * @param manualOrderId
     * @param requirementId
     */
    void occupy(RequirementVo vo);

    /**
     * 订单占位
     * 
     * @param manualOrderId
     * @param requirementId
     */
    void newOccupy(RequirementVo vo);

    /**
     * 订单重新占位
     * 
     * @param manualOrderId
     * @param requirementId
     */
    void reoccupy(RequirementVo vo);

    /**
     * 订单取消占位
     * 
     * @param manualOrderId
     * @param requirementId
     */
    public void cancelOccupy(Integer manualOrderId, Integer requirementId, Integer dOrderId);

    /**
     * 变更订单需求
     * 
     * @param vo
     */
    public void changeProduct(ChangeProductVo vo);
    
    /**
     * 获取订单价格
     * @param manualOrderId
     * @return
     */
    ManualOrderPriceVo getOrderPrice(Integer manualOrderId);
}
