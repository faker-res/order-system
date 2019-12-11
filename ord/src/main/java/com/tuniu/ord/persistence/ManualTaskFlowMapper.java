package com.tuniu.ord.persistence;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualTaskFlow;

public interface ManualTaskFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualTaskFlow record);

    int insertSelective(ManualTaskFlow record);

    ManualTaskFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualTaskFlow record);

    int updateByPrimaryKey(ManualTaskFlow record);
    
    ManualTaskFlow selectByTaskId(@Param("taskId") Integer taskId);
}