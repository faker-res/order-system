package com.tuniu.ord.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualOrderMapper;
import com.tuniu.ord.service.ManualReqireConfirmService;

@Service
public class ManualReqireConfirmServiceImpl extends CommonOrderServiceImpl implements ManualReqireConfirmService {

    @Resource
    private ManualOrderMapper orderMapper;
    
    private static Logger logger = LoggerFactory.getLogger(ManualOrderServiceImpl.class);
    
    @Override
    public boolean requireConfirm(Integer manualOrderId, Integer requirementId, StringBuffer msg) {
        ManualOrder manualOrder = orderMapper.selectByPrimaryKey(manualOrderId);
        if(manualOrder == null) {
            logger.error("query manualOrder failure,[ManualOrderId]:{},[Msg]:{}", manualOrderId, "manual order is not exist.");
            throw new OrdCustomException(OrdError.NO_MANUAL_ORDER);
        }
        ManualRequirement requirement = requirementMapper.selectByPrimaryKey(requirementId);
        if(!requirement.getManualOrderId().equals(manualOrder.getId())) {
            logger.error("requirementId is not correct.");
            throw new OrdCustomException(OrdError.REQUIRE_CONFIRM_ERROR);
        }
        
        if(requirement.getAdultNum() == null || requirement.getChildNum() == null) {
            msg.append("成人数或儿童数为空.");
            return false;
        }
        Integer totalNum = requirement.getAdultNum() + requirement.getChildNum();
        if(!totalNum.equals(getOccupiedNum(manualOrder.getId()))) {
            msg.append("订单未占位");
            return false;
        }
        return true;
    }

}
