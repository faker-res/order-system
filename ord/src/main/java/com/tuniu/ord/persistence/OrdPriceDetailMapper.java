/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 16:27:50 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import com.tuniu.ord.domain.OrdPriceDetail;
import com.tuniu.ord.vo.DetailVo;

public interface OrdPriceDetailMapper {
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
     * @param OrdPriceDetail
     *            record
     * @return int
     */
    int insert(OrdPriceDetail record);

    /**
     * insertSelective
     * 
     * @param OrdPriceDetail
     *            record
     * @return int
     */
    int insertSelective(OrdPriceDetail record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return OrdPriceDetail
     */
    OrdPriceDetail selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param OrdPriceDetail
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(OrdPriceDetail record);

    /**
     * updateByPrimaryKey
     * 
     * @param OrdPriceDetail
     *            record
     * @return int
     */
    int updateByPrimaryKey(OrdPriceDetail record);

    /**
     * Description: <br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param orderId
     * @return <br>
     */
    OrdPriceDetail selectByOrderId(Integer orderId);

    /**
     * 修改价格表的确认成人数或者核损成人数
     * 
     * @param detailVo
     * @return
     */
    int updateOrderDetail(DetailVo detailVo);
}