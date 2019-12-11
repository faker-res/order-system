package com.tuniu.ord.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.persistence.ManualTaskMapper;
import com.tuniu.ord.service.ConfirmRequirementService;

@Service
public class ConfirmRequirementServiceImp implements ConfirmRequirementService {

    @Resource
    private ManualTaskMapper taskMapper;
    
    @Override
    public void confirmRequirement(Integer orderId) {
        
    }

}
