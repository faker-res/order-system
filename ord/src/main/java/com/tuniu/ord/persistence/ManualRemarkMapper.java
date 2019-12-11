package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualRemark;

public interface ManualRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualRemark record);

    int insertSelective(ManualRemark record);

    ManualRemark selectByPrimaryKey(Integer id);
    
    List<ManualRemark> queryByManualOrderId(@Param("manualOrderId") Integer manualOrderId);

    int updateByPrimaryKeySelective(ManualRemark record);

    int updateByPrimaryKey(ManualRemark record);
}