package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.ManualOrderOccupyNum;

public interface ManualOrderOccupyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManualOrderOccupy record);

    int insertSelective(ManualOrderOccupy record);

    ManualOrderOccupy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualOrderOccupy record);

    int updateByPrimaryKey(ManualOrderOccupy record);

    /**
     * 根据订单查询 占位记录
     * 
     * @param orderId
     * @return
     */
    List<ManualOrderOccupy> queryInfoByManualOrderId(@Param("manualOrderId") Integer manualOrderId);
    
    /**
     * 根据订单查询占位成功记录
     * 
     * @param orderId
     * @return
     */
    List<ManualOrderOccupy> queryOccupySuccessByManualOrderId(@Param("manualOrderId") Integer manualOrderId);

    /**
     * 查询切位单占位数量
     * 
     * @param dOrderId
     * @return
     */
    int queryOccupyCount(Integer dOrderId);

    List<ManualOrderOccupyNum> queryOccupyPriceList(@Param("manualOrderId") Integer manualOrderId);
}