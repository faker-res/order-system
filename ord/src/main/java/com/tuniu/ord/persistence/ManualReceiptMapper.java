package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.ManualReceipt;

public interface ManualReceiptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualReceipt record);

    int insertSelective(ManualReceipt record);

    ManualReceipt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualReceipt record);

    int updateByPrimaryKey(ManualReceipt record);

    /**
     * @param manualOrderId
     * @return
     */
    List<ManualReceipt> queryManualReceiptByManualOrderId(Integer manualOrderId);
}