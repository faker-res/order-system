/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Fri May 20 09:16:40 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.vo.OrderOueryVo;

public interface OrdDealOrderMapper {
    /**
     * deleteByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insertSelective
     * 
     * @param OrdDealOrder
     *            record
     * @return int
     */
    int insertSelective(OrdDealOrder record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return OrdDealOrder
     */
    OrdDealOrder selectByPrimaryKey(Integer id);

    /**
     * 根据订单号查询订单信息
     * 
     * @param OrderId
     * @return
     */
    OrdDealOrder selectByOrderId(Integer OrderId);

    List<OrdDealOrder> selectOrderListByPage(OrderOueryVo orderOueryVo);

    Integer count(OrderOueryVo orderOueryVo);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param OrdDealOrder
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(OrdDealOrder record);

    /**
     * updateByPrimaryKey
     * 
     * @param OrdDealOrder
     *            record
     * @return int
     */
    int updateByPrimaryKey(OrdDealOrder record);

    /**
     * 由A资源入库批次查询团期获取出库单ID
     * 
     * @param roundId
     * @return
     */
    OrdDealOrder getOrdDealOrderByRoundId(Integer roundId);

    /**
     * 由A资源入库批次修改D出库单ID
     * 
     * @param roundId
     * @return
     */
    int updateOrdDealOrderOutLibraryId(OrdDealOrder record);

    /**
     * Description: 查找表中最大的order_id<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @return <br>
     */
    Integer getMaxOrdId();

    List<OrdDealOrder> queryRealAskNumber(@Param("productId") Integer productId, @Param("departDates") List<String> departDates);
}