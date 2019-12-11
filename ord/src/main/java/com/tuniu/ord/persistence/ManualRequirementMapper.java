package com.tuniu.ord.persistence;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualRequirement;

public interface ManualRequirementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualRequirement record);

    int insertSelective(ManualRequirement record);

    ManualRequirement selectByPrimaryKey(Integer id);
    
    ManualRequirement selectByManualOrderId(@Param("manualOrderId") Integer manualOrderId);

    int updateByPrimaryKeySelective(ManualRequirement record);

    int updateByPrimaryKey(ManualRequirement record);
}