/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-25                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.vo.AddExtBatchIdVo;
import com.tuniu.ord.vo.AddExtDbatchIdVo;
import com.tuniu.ord.vo.AddOrderResDepartDate;
import com.tuniu.ord.vo.BookingOrderVo;
import com.tuniu.ord.vo.CancelOrderVo;
import com.tuniu.ord.vo.QueryOrdBatchIdsVo;
import com.tuniu.ord.vo.QueryRealTimeAskVo;
import com.tuniu.ord.vo.RealTimeAskResponse;
import com.tuniu.ord.vo.RealTimeAskStockVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-23 <br>
 */

public interface IOrderService {

    List<AddOrderResDepartDate> addOrder(BookingOrderVo bookingOrderVo);

    void cancelOrder(CancelOrderVo cancelOrderVo);

    void addExtBatchId(AddExtBatchIdVo addExtBatchIdVo);

    ResponseVo queryOrdBatchIds(QueryOrdBatchIdsVo queryOrdBatchIdsVo);

    ResponseVo addExtDbatchId(AddExtDbatchIdVo addExtDbatchIdVo);

    /**
     * 现询 实时查询库存生成切位单
     * 
     * @param askStockVo
     */
    RealTimeAskResponse addStockRealTimeAsk(RealTimeAskStockVo askStockVo);

    /**
     * 查询现询数量
     * 
     * @param queryRealTimeAskVo
     * @return
     */
    List<OrdDealOrder> queryRealAskNumber(QueryRealTimeAskVo queryRealTimeAskVo);

}
