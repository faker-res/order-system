package com.tuniu.ord.persistence;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualTask;

public interface ManualTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualTask record);

    int insertSelective(ManualTask record);

    ManualTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualTask record);

    int updateByPrimaryKey(ManualTask record);
    
    ManualTask selectByManualOrderIdAndTaskCode(@Param("manualOrderId") Integer manualOrderId, @Param("taskCode") Integer taskCode);
}