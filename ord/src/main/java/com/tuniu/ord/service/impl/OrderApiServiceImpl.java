/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.ExternalRestUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.service.OrdSalesOrderService;
import com.tuniu.ord.service.OrderApiService;
import com.tuniu.ord.vo.OrdSalesPriceRelationVo;
import com.tuniu.ord.vo.OrderInfoQueryInputVo;
import com.tuniu.ord.vo.OrderInfoQueryOutputVo;
import com.tuniu.ord.vo.OrderInfoVo;
import com.tuniu.ord.vo.OrderRelationResultVo;
import com.tuniu.ord.vo.PeopleTouristVo;
import com.tuniu.ord.vo.TourResourceVo;
import com.tuniu.ord.vo.external.MemberInfoVo;

/**
 * @author zhairongping
 *
 */
@Service
public class OrderApiServiceImpl implements OrderApiService {
    private static Log4jLogger logger = LogFactory.getLogger(OrderApiServiceImpl.class);

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @Resource
    private OrdSalesOrderService ordSalesOrderService;

    @Override
    public OrderInfoQueryOutputVo getOrdInfoByIds(OrderInfoQueryInputVo input) {
        validateOrderInfoQueryInputVo(input);
        OrderInfoQueryOutputVo orderInfoQueryOutputVo = new OrderInfoQueryOutputVo();
        List<OrderInfoVo> orderList = new ArrayList<OrderInfoVo>();
        for (Integer orderId : input.getOrderIds()) {
            OrderInfoVo orderInfoVo = new OrderInfoVo();
            OrdSalesOrder ordSalesOrder = ordSalesOrderMapper.selectByPrimaryKey(orderId);
            OrdDealOrder ordDealOrder = null;
            List<TourResourceVo> tourResourceList = new ArrayList<TourResourceVo>();
            if (null != ordSalesOrder) {
                orderInfoVo.setOrderId(orderId);
                orderInfoVo.setChannelOrderId(ordSalesOrder.getExtOrderId());

                OrdSalesPriceRelationVo ordSalesPriceRelationVo = new OrdSalesPriceRelationVo();
                ordSalesPriceRelationVo.setExtOrderId(ordSalesOrder.getExtOrderId());
                ordSalesPriceRelationVo.setId(orderId);
                List<OrderRelationResultVo> relation = ordSalesOrderService.getRelation(ordSalesPriceRelationVo);
                if (CollectionUtils.isNotEmpty(relation)) {
                    orderInfoVo.setTourId(relation.get(0).getTourId());
                }
                orderInfoVo.setAdultCnt(ordSalesOrder.getAdultCount());
                orderInfoVo.setChildCnt(ordSalesOrder.getChildCount());
                ordDealOrder = ordDealOrderMapper.selectByOrderId(ordSalesOrder.getOrderId());
                if (null != ordDealOrder) {
                    orderInfoVo.setProductId(String.valueOf(ordDealOrder.getProductId()));
                }

                try {
                    com.tuniu.ord.vo.common.ResponseVo<MemberInfoVo> memberInfo = ExternalRestUtil
                            .getMemberInfoById(Integer.valueOf(ordDealOrder.getDistributorId()));
                    if (memberInfo.getData() != null) {
                        orderInfoVo.setChannelName(memberInfo.getData().getFullName());
                    }
                } catch (Exception e) {
                    orderInfoVo.setChannelName(ordSalesOrder.getSaleChannel());
                }

                PeopleTouristVo peopleTouristVo = new PeopleTouristVo();
                List<Integer> list = new ArrayList<Integer>();
                list.add(orderId);
                peopleTouristVo.setSellOrderIds(list);
                List<OrdPeopleTourist> peopleTouristList = ordPeopleTouristMapper.selectByFields(peopleTouristVo);
                TourResourceVo tourResourceVo = null;
                if (CollectionUtils.isNotEmpty(peopleTouristList)) {
                    for (OrdPeopleTourist opt : peopleTouristList) {
                        tourResourceVo = new TourResourceVo();
                        tourResourceVo.setName(opt.getName());
                        tourResourceList.add(tourResourceVo);
                    }
                }
                orderInfoVo.setTourResourceList(tourResourceList);
                orderList.add(orderInfoVo);
            }
        }
        orderInfoQueryOutputVo.setOrderList(orderList);
        return orderInfoQueryOutputVo;
    }

    /**
     * 检验订单id查询订单详情接口参数
     * 
     * @param input
     */
    public void validateOrderInfoQueryInputVo(OrderInfoQueryInputVo input) {
        ArgumentValidator.isNotNullEmpty("订单id查询订单详情接口参数的订单列表", input.getOrderIds());
    }

}
