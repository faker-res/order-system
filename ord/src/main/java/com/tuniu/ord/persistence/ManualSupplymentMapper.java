package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.ManualSupplyment;

public interface ManualSupplymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualSupplyment record);

    int insertSelective(ManualSupplyment record);

    ManualSupplyment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualSupplyment record);

    int updateByPrimaryKey(ManualSupplyment record);

    /**
     * 根据订单id查询增补项列表
     * 
     * @param manualOrderId
     * @return
     */
    List<ManualSupplyment> queryByManualOrderId(Integer manualOrderId);
}