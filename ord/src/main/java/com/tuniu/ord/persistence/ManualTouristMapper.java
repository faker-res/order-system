package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualTourist;

public interface ManualTouristMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualTourist record);

    int insertSelective(ManualTourist record);

    ManualTourist selectByPrimaryKey(Integer id);
    
    List<ManualTourist> selectByManualOrderId(@Param("manualOrderId") Integer manualOrderId);

    int updateByPrimaryKeySelective(ManualTourist record);

    int updateByPrimaryKey(ManualTourist record);
}