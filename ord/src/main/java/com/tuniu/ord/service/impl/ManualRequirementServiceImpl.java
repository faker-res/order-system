package com.tuniu.ord.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.domain.ManualTourist;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualRequirementMapper;
import com.tuniu.ord.service.ManualRequirementService;
import com.tuniu.ord.vo.ManualRequirementVo;

@Service
public class ManualRequirementServiceImpl extends CommonOrderServiceImpl implements ManualRequirementService {

    private static Logger logger = LoggerFactory.getLogger(ManualRequirementServiceImpl.class);
    
    @Resource
    private ManualRequirementMapper requirementMapper;
    
    @Override
    public void saveRequirement(ManualRequirementVo vo) throws ParseException {
        ManualOrder manualOrder = getManualOrder(vo.getManualOrderId());
        boolean orderChg = false;
        if(manualOrder == null) {
            logger.error("manual order is not exist.");
            throw new OrdCustomException(OrdError.NO_MANUAL_ORDER);
        }
        ManualRequirement requirement = getRequirement(vo.getManualOrderId());
        if(requirement == null) {
            logger.error("manual order's requirement is not exist.");
            throw new OrdCustomException(OrdError.REQUIREMENT_NOT_EXIST);
        }
        requirement.setProductId(vo.getProductId());
        requirement.setProductName(vo.getProductName());
        if(!vo.getAdultNum().equals(requirement.getAdultNum())) {
            if(vo.getAdultNum() < requirement.getAdultNum()) {
                checkTourist(manualOrder, Constants.ADULT, vo.getAdultNum());
            }
            requirement.setAdultNum(vo.getAdultNum());
            manualOrder.setAdultCount(vo.getAdultNum());
            orderChg = true;
        }
        if(!vo.getChildNum().equals(requirement.getChildNum())) {
            if(vo.getChildNum() < requirement.getChildNum()) {
                checkTourist(manualOrder, Constants.CHILD, vo.getChildNum());
            }
            requirement.setChildNum(vo.getChildNum());
            manualOrder.setChildCount(vo.getChildNum());
            orderChg = true;
        }
        requirement.setOlderNum(vo.getOlderNum());
        requirement.setStartDate(vo.getStartDate());
        requirement.setEndDate(vo.getEndDate());
        if(vo.getStartDate()!=null && vo.getEndDate()!= null) {
            requirement.setDays(DateUtils.daysBetween(vo.getStartDate(), vo.getEndDate()));
        }
        requirement.setDestCategoryId(vo.getDestCategoryId());
        requirement.setDestCategoryName(vo.getDestCategoryName());
        requirement.setFirstDestGroupId(vo.getFirstDestGroupId());
        requirement.setFirstDestGroupName(vo.getFirstDestGroupName());
        requirement.setSecDestGroupId(vo.getSecDestGroupId());
        requirement.setSecDestGroupName(vo.getSecDestGroupName());
        
        BaseDomainUtil.setUpdatePropertyValue(requirement);
        requirementMapper.updateByPrimaryKeySelective(requirement);
        if(orderChg) {
            BaseDomainUtil.setUpdatePropertyValue(manualOrder);
            orderMapper.updateByPrimaryKeySelective(manualOrder);
        }
    }

    private void checkTourist(ManualOrder manualOrder, Integer touristType, Integer changNum) {
        if(manualOrder == null) {
            throw new IllegalArgumentException("manual order is null");
        }
        if(touristType != Constants.ADULT && touristType != Constants.CHILD) {
            throw new IllegalArgumentException("tourist type is not correct.");
        }
        List<ManualTourist> tourists = getTourist(manualOrder.getId());
        if(touristType.equals(Constants.ADULT)) {
            Integer adultNum = getNumByTouristType(tourists, Constants.ADULT);
            if(changNum < adultNum) {
                logger.error("tourist's adult number is bigger than chang adult number.");
                throw new OrdCustomException(OrdError.TOURIST_ADULTNUM_CHANGE_ERROR);
            }
        } else if(touristType.equals(Constants.CHILD)) {
            Integer childNum = getNumByTouristType(tourists, Constants.CHILD);
            if(changNum < childNum) {
                logger.error("tourist's child number is bigger than chang child number.");
                throw new OrdCustomException(OrdError.TOURIST_CHILDNUM_CHANGE_ERROR);
            }
        }
    }

    @Override
    public void removeRequirement(ManualRequirement requirement) {
        if(requirement == null || requirement.getId() == null) {
            // do nothing if record is null;
            return;
        }
        removeRequirement(requirement.getId());
    }
    
    @Override
    public void removeRequirement(Integer requirementId) {
        if(requirementId == null) {
            // do nothing if record is null;
            return;
        }
        requirementMapper.deleteByPrimaryKey(requirementId);
    }
}
