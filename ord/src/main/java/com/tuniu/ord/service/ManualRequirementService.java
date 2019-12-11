package com.tuniu.ord.service;

import java.text.ParseException;

import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.vo.ManualRequirementVo;

public interface ManualRequirementService {

    void saveRequirement(ManualRequirementVo vo) throws ParseException;
    
    void removeRequirement(ManualRequirement requirement);
    
    void removeRequirement(Integer requirementId);
}
