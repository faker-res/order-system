/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.json.resolver.JsonContextImpl;
import com.tuniu.operation.framework.base.json.resolver.JsonPath;
import com.tuniu.operation.framework.base.json.resolver.JsonPathImpl;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.service.OrdSalesOrderService;
import com.tuniu.ord.vo.GroupOrderTourResultVo;
import com.tuniu.ord.vo.OrdSalesPriceRelationVo;
import com.tuniu.ord.vo.OrderBasicQueryVo;
import com.tuniu.ord.vo.OrderBasicStatisticsVo;
import com.tuniu.ord.vo.OrderRelationResultVo;

/**
 * @author zhairongping
 * 
 */
@Service
public class OrdSalesOrderServiceImpl implements OrdSalesOrderService {
    private static Log4jLogger logger = LogFactory.getLogger(OrdSalesOrderServiceImpl.class);

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrdSalesOrderService#getOrderBasicStatistics(com.tuniu.ord.vo.OrderBasicQueryVo)
     */
    @Override
    public OrderBasicStatisticsVo getOrderBasicStatistics(OrderBasicQueryVo orderBasicQueryinputVo) {
        // TODO Auto-generated method stub
        // list为null,结果为null;list不为null,返回结果
        List<OrdSalesOrder> list = ordSalesOrderMapper.getOrderBasicStatistics(orderBasicQueryinputVo);
        OrderBasicStatisticsVo orderBasicStatisticsVo = null;
        if (CollectionUtils.isNotEmpty(list)) {
            orderBasicStatisticsVo = new OrderBasicStatisticsVo();
            orderBasicStatisticsVo.setStatusCode(orderBasicQueryinputVo.getStatusCode());
            orderBasicStatisticsVo
                    .setStatusName(OrderStateEnum.getOrderStateEnum(orderBasicQueryinputVo.getStatusCode()).getStatusName());
            Integer adultCount = new Integer(0);
            Integer childCount = new Integer(0);
            BigDecimal totalPrice = new BigDecimal(0);
            // 处理成人数,儿童数和总价
            for (OrdSalesOrder ordSalesOrder : list) {
                adultCount = new Integer(adultCount.intValue() + ordSalesOrder.getAdultCount().intValue());
                childCount = new Integer(childCount.intValue() + ordSalesOrder.getChildCount().intValue());
                totalPrice = totalPrice
                        .add(ordSalesOrder.getAdultPrice().multiply(new BigDecimal(ordSalesOrder.getAdultCount()))
                                .add(ordSalesOrder.getChildPrice().multiply(new BigDecimal(ordSalesOrder.getChildCount()))));
            }
            orderBasicStatisticsVo.setAdultCount(adultCount);
            orderBasicStatisticsVo.setChildCount(childCount);
            orderBasicStatisticsVo.setTotalPrice(totalPrice);
        }
        return orderBasicStatisticsVo;
    }

    @Override
    public List<OrderRelationResultVo> getRelation(OrdSalesPriceRelationVo ordSalesPriceRelationVo) {
        List<OrderRelationResultVo> rows = ordSalesOrderMapper.selectRelation(ordSalesPriceRelationVo);
        if (CollectionUtils.isNotEmpty(rows)) {
            List<Integer> orderList = new LinkedList<Integer>();
            for (OrderRelationResultVo orderRelationResultVo : rows) {
                orderList.add(orderRelationResultVo.getId());
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("orderList", orderList);
            paramMap.put("tenantId", DataSourceSwitch.getTenantId());
            String groupTemplateResult = RestUtil.execute(SystemInitParameter.getOrderTourInfo, JsonUtil.toString(paramMap));

            if (StringUtils.isNotBlank(groupTemplateResult)) {
                JsonContextImpl jsonContext = new JsonContextImpl(groupTemplateResult);
                JsonPath jsonPath = new JsonPathImpl();
                if (jsonPath.getBooleanValue(jsonContext, "/success")) {
                    JsonNode jsonNodes = jsonPath.toNativeJson(jsonContext, "/data");
                    if (null != jsonNodes && jsonNodes.size() > 0) {
                        List<GroupOrderTourResultVo> groupOrderTourResultVos = JsonUtil.toList(JsonUtil.toString(jsonNodes),
                                GroupOrderTourResultVo.class);
                        if (CollectionUtils.isNotEmpty(groupOrderTourResultVos)) {
                            for (OrderRelationResultVo orderRelationResultVo : rows) {
                                for (GroupOrderTourResultVo groupOrderTourResultVo : groupOrderTourResultVos) {
                                    if (orderRelationResultVo.getId().intValue() == groupOrderTourResultVo.getOrderId()
                                            .intValue()) {
                                        orderRelationResultVo.setTourTemplateId(groupOrderTourResultVo.getTourBasicId());
                                        orderRelationResultVo.setTourId(groupOrderTourResultVo.getTourGroupId());
                                        // 针对迁移数据
                                        if (null == orderRelationResultVo.getDotaProductId()) {
                                            orderRelationResultVo.setDotaProductId(groupOrderTourResultVo.getProductId());
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return rows;
    }

    @Override
    public int countByRelation(OrdSalesPriceRelationVo ordSalesPriceRelationVo) {
        return ordSalesOrderMapper.countByRelation(ordSalesPriceRelationVo);
    }

}
