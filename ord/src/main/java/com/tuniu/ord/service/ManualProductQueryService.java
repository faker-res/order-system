package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.ManualPrdouctResultVo;
import com.tuniu.ord.vo.ManualProductQueryVo;
import com.tuniu.ord.vo.common.ListVo;

public interface ManualProductQueryService {

    /**
     * 根据条件查询可以下单的产品
     * 
     * @param manualProductQueryVo
     * @return
     */
    ListVo queryManualProduct(ManualProductQueryVo manualProductQueryVo);

    /**
     * 获取产品可售总数
     * 
     * @param prdoucts
     * @return
     */
    Integer getAllCanSaleNum(List<ManualPrdouctResultVo> products);
    
    /**
     * 获取产品已售总数
     * 
     * @param prdoucts
     * @return
     */
    Integer getAllSaledNum(List<ManualPrdouctResultVo> products);
    
    /**
     * 获取产品已占总数
     * 
     * @param prdoucts
     * @return
     */
    Integer getAllOccupyNum(List<ManualPrdouctResultVo> products);

    /**
     * 查询已选产品
     * 
     * @param manualOrderId
     * @return
     */
    ListVo queryPrdSelect(Integer manualOrderId);
}
