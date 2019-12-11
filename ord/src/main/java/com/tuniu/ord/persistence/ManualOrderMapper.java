package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.vo.ManualOrderQueryInputVo;
import com.tuniu.ord.vo.ManualOrderQueryOutputVo;
import com.tuniu.ord.vo.createOrder.ManualOrderQueryInter;

public interface ManualOrderMapper {

    int insertSelective(ManualOrder record);

    ManualOrder selectByPrimaryKey(Integer id);

    ManualOrder selectByManualOrderId(@Param("manualOrderId") Integer manualOrderId);

    int updateByPrimaryKeySelective(ManualOrder record);

    List<ManualOrderQueryInter> getManualOrderList(ManualOrderQueryInter input);

    Integer countManualOrderList(ManualOrderQueryInter input);

    List<ManualOrderQueryOutputVo> getNewManualOrder(ManualOrderQueryInputVo input);

    Integer countNewManualOrder(ManualOrderQueryInputVo input);

}