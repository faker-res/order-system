/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.service.OrdDealOrderService;
import com.tuniu.ord.service.OrdSalesOrderService;
import com.tuniu.ord.vo.OrderBasicQueryVo;
import com.tuniu.ord.vo.OrderBasicStatisticsVo;
import com.tuniu.ord.vo.OrderDetailOutputVo;
import com.tuniu.ord.vo.OrderDetailQueryVo;
import com.tuniu.ord.vo.OrderHeadInfoVo;
import com.tuniu.ord.vo.OrderListOutputVo;
import com.tuniu.ord.vo.OrderOueryVo;

/**
 * @author zhairongping
 * 
 */
@Service
public class OrdDealOrderServiceImpl implements OrdDealOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrdDealOrderServiceImpl.class);

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdDealOrderService ordDealOrderServiceImpl;

    @Resource
    private OrdSalesOrderService ordSalesOrderServiceImpl;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrdDealOrderService#selectByOrderId(java.lang.Integer)
     */
    @Override
    public OrderHeadInfoVo getOrdDealOrdeByOrderId(Integer OrderId) {
        OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(OrderId);
        OrderHeadInfoVo orderHeadInfoVo = new OrderHeadInfoVo();
        orderHeadInfoVo.setOrderId(ordDealOrder.getOrderId());
        orderHeadInfoVo.setStatusCode(ordDealOrder.getStatusCode());
        orderHeadInfoVo.setStatusName(OrderStateEnum.getOrderStateEnum(ordDealOrder.getStatusCode()).getStatusName());
        orderHeadInfoVo.setProductStaffId(ordDealOrder.getProductStaffId());
        orderHeadInfoVo.setProductStaffName(ordDealOrder.getProductStaffName());
        orderHeadInfoVo.setProductManagerId(ordDealOrder.getProductManagerId());
        orderHeadInfoVo.setProductManagerName(ordDealOrder.getProductManagerName());
        return orderHeadInfoVo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrdDealOrderService#selectOrderListByPage(com.tuniu.ord.vo.OrderOueryVo)
     */
    @Override
    public List<OrderListOutputVo> getOrderListByPage(OrderOueryVo orderOueryVo) {
        List<OrdDealOrder> list1 = ordDealOrderMapper.selectOrderListByPage(orderOueryVo);
        List<OrderListOutputVo> list2 = new ArrayList<OrderListOutputVo>();
        if (CollectionUtils.isNotEmpty(list1)) {
            for (OrdDealOrder odo : list1) {
                OrderListOutputVo orderListOutputVo = new OrderListOutputVo();
                orderListOutputVo.setId(odo.getId());
                orderListOutputVo.setOrderId(odo.getOrderId());
                orderListOutputVo.setOrderDate(odo.getAddTime());
                orderListOutputVo.setProductName(odo.getProductName());
                orderListOutputVo.setStatusCode(odo.getStatusCode());
                OrderStateEnum ose = OrderStateEnum.getOrderStateEnum(odo.getStatusCode());
                orderListOutputVo.setStatusName((ose == null ? "" : ose.getStatusName()));

                OrderBasicQueryVo orderBasicQueryVo = new OrderBasicQueryVo();
                orderBasicQueryVo.setOrderId(odo.getOrderId());
                orderListOutputVo.setNum(getNum(orderBasicQueryVo));

                if (null != odo.getDepartDate()) {
                    orderListOutputVo.setDepartDate(df.format(odo.getDepartDate()));
                }
                orderListOutputVo.setProductId(odo.getProductId());
                orderListOutputVo.setProductManagerName(odo.getProductManagerName());

                list2.add(orderListOutputVo);
            }
            return list2;
        }
        return null;
    }

    /**
     * 处理D订单num:无销售单,为null;有销售单,显示x大y小
     * 
     * @param orderBasicQueryVo
     * @return
     */
    public String getNum(OrderBasicQueryVo orderBasicQueryVo) {
        String numStr = null;
        List<OrdSalesOrder> ordSalesOrders = ordSalesOrderMapper.getOrderBasicStatistics(orderBasicQueryVo);
        if (CollectionUtils.isNotEmpty(ordSalesOrders)) {
            Integer adultCount = new Integer(0);
            Integer childCount = new Integer(0);
            for (OrdSalesOrder oso : ordSalesOrders) {
                adultCount = new Integer(adultCount.intValue() + oso.getAdultCount().intValue());
                childCount = new Integer(childCount.intValue() + oso.getChildCount().intValue());
            }
            numStr = adultCount.intValue() + "大" + childCount.intValue() + "小";
        }
        return numStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrdDealOrderService#count(com.tuniu.ord.vo.OrderOueryVo)
     */
    @Override
    public Integer count(OrderOueryVo orderOueryVo) {
        Integer count = new Integer(0);
        count = ordDealOrderMapper.count(orderOueryVo);
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrdDealOrderService#getOrderDetail(com.tuniu.ord.vo.OrderDetailQueryVo)
     */
    @Override
    public OrderDetailOutputVo getOrderDetail(OrderDetailQueryVo orderDetailQueryVo) {
        // TODO Auto-generated method stub
        OrderDetailOutputVo orderDetailOutputVo = new OrderDetailOutputVo();
        // 取查询标志
        String option = orderDetailQueryVo.getOption();
        String[] op = option.split(";");
        // 有什么标志就查询什么
        for (String str : op) {
            if ("orderHeadInfo".equals(str)) {
                OrderHeadInfoVo orderHeadInfoVo = new OrderHeadInfoVo();
                orderHeadInfoVo = ordDealOrderServiceImpl.getOrdDealOrdeByOrderId(orderDetailQueryVo.getOrderId());
                orderDetailOutputVo.setOrderHeadInfo(orderHeadInfoVo);
            }
            if ("orderBasicStatistics".equals(str)) {
                OrderBasicQueryVo orderBasicQueryVo = null;
                List<OrderBasicStatisticsVo> obsv = new ArrayList<OrderBasicStatisticsVo>();
                for (OrderStateEnum ose : OrderStateEnum.values()) {
                    orderBasicQueryVo = new OrderBasicQueryVo();
                    orderBasicQueryVo.setOrderId(orderDetailQueryVo.getOrderId());
                    orderBasicQueryVo.setStatusCode(ose.getStatusCode());
                    OrderBasicStatisticsVo orderBasicStatisticsVo = ordSalesOrderServiceImpl
                            .getOrderBasicStatistics(orderBasicQueryVo);
                    if (null != orderBasicStatisticsVo) {
                        obsv.add(orderBasicStatisticsVo);
                    }
                }
                orderDetailOutputVo.setOrderBasicStatistics(obsv);
            }
        }
        return orderDetailOutputVo;
    }

}
