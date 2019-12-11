/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Fri May 20 11:17:51 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.vo.ConfirmRequestQueryVo;
import com.tuniu.ord.vo.ConfirmSalesQueryVo;
import com.tuniu.ord.vo.OrdSaleOrderSeleVo;
import com.tuniu.ord.vo.OrdSalesPriceRelationVo;
import com.tuniu.ord.vo.OrdSalesPriceVo;
import com.tuniu.ord.vo.OrderBasicQueryVo;
import com.tuniu.ord.vo.OrderRelationResultVo;

public interface OrdSalesOrderMapper {
    /**
     * deleteByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     * 
     * @param OrdSalesOrder
     *            record
     * @return int
     */
    int insert(OrdSalesOrder record);

    /**
     * insertSelective
     * 
     * @param OrdSalesOrder
     *            record
     * @return int
     */
    int insertSelective(OrdSalesOrder record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return OrdSalesOrder
     */
    OrdSalesOrder selectByPrimaryKey(Integer id);

    /**
     * 根据切位单和状态查询销售单
     * 
     * @param OrderBasicQueryinputVo
     * @return
     */
    List<OrdSalesOrder> getOrderBasicStatistics(OrderBasicQueryVo OrderBasicQueryinputVo);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param OrdSalesOrder
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(OrdSalesOrder record);

    /**
     * updateByPrimaryKey
     * 
     * @param OrdSalesOrder
     *            record
     * @return int
     */
    int updateByPrimaryKey(OrdSalesOrder record);

    List<OrdSalesOrder> selectByFields(OrdSalesPriceVo ordSalesPriceVo);

    int countByFields(OrdSalesPriceVo ordSalesPriceVo);

    List<OrderRelationResultVo> selectRelation(OrdSalesPriceRelationVo ordSalesPriceRelationVo);

    int countByRelation(OrdSalesPriceRelationVo ordSalesPriceRelationVo);

    /**
     * 统计发起确认请求的次数(需求号是一样)
     * 
     * @param confirmRequestQueryVo
     * @return
     */
    int countConfirmRequest(ConfirmRequestQueryVo confirmRequestQueryVo);

    /**
     * 根据A资源入库批次、售卖订单号和需求ID查询D的销售单
     * 
     * @param confirmSalesQueryVo
     * @return
     */
    OrdSalesOrder getSaleByConfirmQuery(ConfirmSalesQueryVo confirmSalesQueryVo);

    /**
     * 根据需求id和A销售订单号 查询销售订单
     */
    List<OrdSalesOrder> selectBySaleOrdleIdAndRequirement(OrdSaleOrderSeleVo ordSaleOrderSeleVo);

    /**
     * 查询数据迁移成功的A类订单
     * 
     * @param aOrderIds
     * @return
     */
    public List<OrdSalesOrder> getTransferedAOrderIds(List<Integer> aOrderIds);

    /**
     * 根据订单号 查询销售单号信息
     * 
     * @param orderId
     * @return
     */
    List<OrdSalesOrder> getSalesInfoByManualOrderId(@Param("manualOrderId") Integer manualOrderId);

}
