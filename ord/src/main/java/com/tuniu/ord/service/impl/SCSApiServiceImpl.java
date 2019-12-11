/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月1日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.enums.ProductCategoryEnum;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.DPSProxy;
import com.tuniu.ord.service.SCSApiService;
import com.tuniu.ord.vo.DPSDeptInfo;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.QueryRelationsIdOutputVo;
import com.tuniu.ord.vo.QueryRelationsIdVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhairongping
 *
 */
@Service
public class SCSApiServiceImpl implements SCSApiService {
    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private DPSProxy dPSProxy;

    private DateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.SCSApiService#initOrderSynInfo(java.lang.Integer, com.tuniu.ord.vo.OrderSynInfo)
     */
    @Override
    public OrderSynInfo initOrderSynInfo(Integer orderId, OrderSynInfo orderSynInfo) {
        OrdSalesOrder ordSalesOrder = ordSalesOrderMapper.selectByPrimaryKey(orderId);
        OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(ordSalesOrder.getOrderId());

        orderSynInfo.setId(orderId);
        orderSynInfo.setState(Constants.CONFIRMED_ORDERSTATUS);
        orderSynInfo.setStatusDesc(OrderStateEnum.CONFIRMED.getStatusName());

        BigDecimal adultAmount = ordSalesOrder.getAdultPrice().multiply(new BigDecimal(ordSalesOrder.getAdultCount()));
        BigDecimal childAmount = ordSalesOrder.getChildPrice().multiply(new BigDecimal(ordSalesOrder.getChildCount()));
        BigDecimal roomAmount = ordSalesOrder.getRoomAddPrice().multiply(new BigDecimal(ordSalesOrder.getRoomAddNum()));
        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount = totalAmount.add(adultAmount).add(childAmount).add(roomAmount);
        orderSynInfo.setContractAmount(totalAmount);

        orderSynInfo.setSyncTime(sdft.format(new Date()));
        orderSynInfo.setProduct(ordDealOrder.getProductId());
        // 出发城市
        orderSynInfo.setStartCityCode(ordDealOrder.getDepartureCityCode().toString());
        orderSynInfo.setStartCityName(ordDealOrder.getDepartureCityName());
        // 产品品类
        orderSynInfo.setProductCategory(ProductCategoryEnum.PRODUCT_CATAGORY.getKey());
        orderSynInfo.setProductCategoryName(ProductCategoryEnum.PRODUCT_CATAGORY.getName());
        orderSynInfo.setProductSubCategory(ProductCategoryEnum.PRODUCT_SUB_CATAGORY.getKey());
        orderSynInfo.setProductSubCategoryName(ProductCategoryEnum.PRODUCT_SUB_CATAGORY.getName());
        // 类目信息
        orderSynInfo.setDestinationFirstCategory(ordDealOrder.getDestCategoryId());
        orderSynInfo.setDestinationFirstCategoryName(ordDealOrder.getDestCategoryName());
        orderSynInfo.setFirstDestinationGrouping(ordDealOrder.getFirstDestGroupId());
        orderSynInfo.setFirstDestinationGroupingName(ordDealOrder.getFirstDestGroupName());
        orderSynInfo.setSecondDestinationGrouping(ordDealOrder.getSecDestGroupId());
        orderSynInfo.setSecondDestinationGroupingName(ordDealOrder.getSecDestGroupName());
        orderSynInfo.setDestination(ordDealOrder.getDestId());
        orderSynInfo.setDestinationName(ordDealOrder.getDestName());
        orderSynInfo.setSignCompany(Constants.SIGN_COMPANY);
        orderSynInfo.setSignCompanyName(Constants.SIGN_COMPANY_NAME);
        orderSynInfo.setIsSubOrder(new Integer(1));
        orderSynInfo.setPrimaryOrderId(ordSalesOrder.getExtOrderId());

        QueryRelationsIdVo queryRelationsIdVo = new QueryRelationsIdVo();
        Integer productLineId = ordDealOrder.getProductLineId();
        queryRelationsIdVo.setProductLineId(productLineId);
        // 访问部门类目管理系统获取部门信息
        ResponseVo dpsRes = dPSProxy.queryRelationsId(queryRelationsIdVo);
        QueryRelationsIdOutputVo queryRelationsIdOutputVo = null;
        DPSDeptInfo defaultDept = new DPSDeptInfo();
        if (dpsRes.isSuccess()) {
            queryRelationsIdOutputVo = JsonUtil.toBean(JsonUtil.toString(dpsRes.getData()), QueryRelationsIdOutputVo.class);
            if (null != queryRelationsIdOutputVo) {
                if (CollectionUtils.isNotEmpty(queryRelationsIdOutputVo.getRows())) {
                    for (DPSDeptInfo dept : queryRelationsIdOutputVo.getRows()) {
                        if (Constants.PRODUCT_BRAND_ID.intValue() == dept.getProductBrandId().intValue()
                                && Constants.SALE_STYLE_ID.intValue() == dept.getSaleStyleId().intValue()) {
                            defaultDept = dept;
                            break;
                        }
                    }
                }
            }
        }
        // 部门信息
        orderSynInfo.setRegionCode(defaultDept.getDivisionId());
        orderSynInfo.setRegionName(defaultDept.getDivisionName());
        orderSynInfo.setDepartmentCode(defaultDept.getDepartmentId());
        orderSynInfo.setDepartmentName(defaultDept.getDepartmentName());
        orderSynInfo.setGroupId(defaultDept.getGroupId());
        orderSynInfo.setGroupName(defaultDept.getGroupName());

        // 同步出游时间和归来时间
        if (ordDealOrder.getDepartDate() != null) {
            orderSynInfo.setStartDate(sdft.format(ordDealOrder.getDepartDate()));
            orderSynInfo.setCollectionTime(sdft.format(ordDealOrder.getDepartDate()));
        }
        if (ordDealOrder.getBackDate() != null) {
            orderSynInfo.setBackDate(sdft.format(ordDealOrder.getBackDate()));
        }
        return orderSynInfo;
    }

}
