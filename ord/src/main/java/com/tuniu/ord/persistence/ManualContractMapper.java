package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.ManualContract;

public interface ManualContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualContract record);

    int insertSelective(ManualContract record);

    ManualContract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualContract record);

    int updateByPrimaryKey(ManualContract record);

    List<ManualContract> selectByManualOrderId(Integer manualOrderId);
}