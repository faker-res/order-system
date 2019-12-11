package com.tuniu.ord.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.domain.ManualTourist;
import com.tuniu.ord.persistence.ManualOrderMapper;
import com.tuniu.ord.persistence.ManualOrderOccupyMapper;
import com.tuniu.ord.persistence.ManualRemarkMapper;
import com.tuniu.ord.persistence.ManualRequirementMapper;
import com.tuniu.ord.persistence.ManualTouristMapper;
import com.tuniu.ord.service.CommonOrderService;

@Service("commonOrderService")
public class CommonOrderServiceImpl implements CommonOrderService {

    @Resource
    protected ManualRequirementMapper requirementMapper;
    
    @Resource
    protected ManualOrderOccupyMapper occupyMapper;
    
    @Resource
    protected ManualOrderMapper orderMapper;
    
    @Resource
    protected ManualTouristMapper touristMapper;
    
    @Resource
    protected ManualRemarkMapper remarkMapper;
    
    @Override
    public ManualRequirement getRequirement(Integer manualOrderId) {
        if(manualOrderId == null) {
            return null;
        }
        return requirementMapper.selectByManualOrderId(manualOrderId);
    }
    
    @Override
    public int getOccupiedNum(List<ManualOrderOccupy> orderOccupies) {
        if(CollectionUtils.isEmpty(orderOccupies)) {
            return 0;
        }
        int totalNum = 0;
        for (ManualOrderOccupy occupy : orderOccupies) {
            if(!occupy.getStatus().equals(Constants.MANUAL_ORDER_UNOCCUPY)) {
                totalNum += occupy.getAdultNum();
                totalNum += occupy.getChildNum();
            }
        }
        return totalNum;
    }
    
    @Override
    public int getOccupiedNum(Integer manualOrderId) {
        Map<Integer, Integer> occupyNumMap = getOccupiedAdultChildNum(manualOrderId);
        return occupyNumMap.get(Constants.ADULT) + occupyNumMap.get(Constants.CHILD);
    }
    
    @Override
    public Map<Integer, Integer> getOccupiedAdultChildNum(Integer manualOrderId) {
        Map<Integer, Integer> occupyNumMap = new HashMap<Integer, Integer>();
        occupyNumMap.put(Constants.ADULT, 0);
        occupyNumMap.put(Constants.CHILD, 0);
        List<ManualOrderOccupy> orderOccupies = occupyMapper.queryOccupySuccessByManualOrderId(manualOrderId);
        if(!CollectionUtils.isNotEmpty(orderOccupies)) {
            return occupyNumMap;
        }
        int totalAdultNum = 0, totalChildNum = 0;
        for (ManualOrderOccupy orderOccupy : orderOccupies) {
            totalAdultNum += orderOccupy.getAdultNum();
            totalChildNum += orderOccupy.getChildNum();
        }
        occupyNumMap.put(Constants.ADULT, totalAdultNum);
        occupyNumMap.put(Constants.CHILD, totalChildNum);
        return occupyNumMap;
    }
    
    
    /**
     * 查询订单信息
     * @param orderId
     * @return
     */
    @Override
    public ManualOrder getManualOrder(Integer manualOrderId) {
        if(manualOrderId == null) {
            throw new IllegalArgumentException("manualOrderId is null");
        }
        return orderMapper.selectByPrimaryKey(manualOrderId);
    }

    @Override
    public List<ManualTourist> getTourist(Integer manualOrderId) {
        if(manualOrderId == null) {
            throw new IllegalArgumentException("manualOrderId is null.");
        }
        return touristMapper.selectByManualOrderId(manualOrderId);
    }

    @Override
    public Integer getNumByTouristType(List<ManualTourist> tourists, Integer touristType) {
        if(!CollectionUtils.isNotEmpty(tourists)) {
            return 0;
        }
        if(touristType != Constants.ADULT && touristType != Constants.CHILD) {
            throw new IllegalArgumentException("tourist type is not correct.");
        }
        
        int num = 0;
        for (ManualTourist tourist : tourists) {
            if(tourist.getTouristType().equals(touristType)) {
                num ++;
            }
        }
        return num;
    }
    
    @Override
    public void addRemarkBySystem(Integer manualOrderId, String content) {
        ManualRemark record = new ManualRemark();
        record.setManualOrderId(manualOrderId);
        record.setContent(content);
        BaseDomainUtil.setBasePropertyValueBySystem(record);
        remarkMapper.insertSelective(record);
    }
}
