/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-25                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateFormatUtils;
import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.TspUtil;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.core.datasource.TSPEnumUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdPriceDetail;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdPriceDetailMapper;
import com.tuniu.ord.service.IOrderService;
import com.tuniu.ord.vo.AddExtBatchIdVo;
import com.tuniu.ord.vo.AddExtDbatchIdVo;
import com.tuniu.ord.vo.AddOrderResDepartDate;
import com.tuniu.ord.vo.BookingOrderVo;
import com.tuniu.ord.vo.CancelOccupyReqDepartDates;
import com.tuniu.ord.vo.CancelOccupyReqDproducts;
import com.tuniu.ord.vo.CancelOrderVo;
import com.tuniu.ord.vo.OccupyReqDepartDates;
import com.tuniu.ord.vo.OccupyReqDproducts;
import com.tuniu.ord.vo.OccupyResDepartDates;
import com.tuniu.ord.vo.OrderDepartDates;
import com.tuniu.ord.vo.ProductCancelOccupyReqData;
import com.tuniu.ord.vo.ProductOccupyReqData;
import com.tuniu.ord.vo.ProductOccupyResData;
import com.tuniu.ord.vo.QueryOrdBatchIdsVo;
import com.tuniu.ord.vo.QueryRealTimeAskVo;
import com.tuniu.ord.vo.RealTimeAskResponse;
import com.tuniu.ord.vo.RealTimeAskStockVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.common.ExternalResponseObj;
import com.tuniu.ord.vo.external.ProductBase;
import com.tuniu.ord.vo.external.ProductDepartureCity;
import com.tuniu.ord.vo.external.ProductDestination;
import com.tuniu.ord.vo.external.ProductOccpuyInfo;
import com.tuniu.ord.vo.external.ProductQueryOutputVo;
import com.tuniu.ord.vo.external.ProductRelatePerson;
import com.tuniu.ord.vo.external.ProductSellChannel;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-23 <br>
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private OrdPriceDetailMapper ordPriceDetailMapper;

    /**
     * Description: 下单<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param bookingOrderVo
     *            <br>
     * @throws ParseException
     */
    @Override
    public List<AddOrderResDepartDate> addOrder(BookingOrderVo bookingOrderVo) {
        List<AddOrderResDepartDate> respList = new ArrayList<AddOrderResDepartDate>();

        OrderDepartDates[] ordDates = bookingOrderVo.getOrderDates();
        for (int i = 0; i < ordDates.length; i++) {
            // 生成切位单
            String departDate = ordDates[i].getDepartDate();
            String closingDate = ordDates[i].getClosingDate();
            OrdDealOrder ordDealOrder = initOrdDealOrder(bookingOrderVo, departDate, closingDate);
            ordDealOrderMapper.insertSelective(ordDealOrder);
            ordDealOrder.setOrderId(ordDealOrder.getId());
            ordDealOrderMapper.updateByPrimaryKeySelective(ordDealOrder);

            // 产品占位
            ProductOccupyReqData productOccupyReqData = initProductOccupyReqData(bookingOrderVo, ordDates[i].getDepartDate(),
                    ordDealOrder);
            String productOccupyResStr = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.DPRD_STOCK_OCCUPY_SIGN),
                    JsonUtil.toString(productOccupyReqData), SystemConstants.HTTP_POST);
            LOGGER.info("产品占位返回信息：{}", productOccupyResStr);

            ProductOccupyResData productOccupyResData = JsonUtil.toBean(productOccupyResStr, ProductOccupyResData.class);
            if (productOccupyResData != null && productOccupyResData.isSuccess()) {
                // 占位成功，记录占位批次信息
                OrdDealOrder dealOrderInfo = new OrdDealOrder();
                dealOrderInfo.setId(ordDealOrder.getId());
                dealOrderInfo.setStatusCode(OrderStateEnum.OCCUPIED.getStatusCode());
                dealOrderInfo.setClosingDate(
                        getMinReleaseTime(productOccupyResData.getData().getdProducts().get(0).getDepartDates()));

                ordDealOrderMapper.updateByPrimaryKeySelective(dealOrderInfo);

                OrdPriceDetail ordPriceDetail = initOrdPriceDetail(ordDealOrder.getOrderId(),
                        bookingOrderVo.getStockAdultCount(), bookingOrderVo.getAdultPrice(),
                        bookingOrderVo.getStockChildCount(), bookingOrderVo.getChildPrice(), bookingOrderVo.getCurrencyType());
                ordPriceDetailMapper.insertSelective(ordPriceDetail);

                AddOrderResDepartDate addOrderResDepartDate = new AddOrderResDepartDate();
                addOrderResDepartDate.setDepartDate(ordDates[i].getDepartDate());
                addOrderResDepartDate.setOrderId(ordDealOrder.getOrderId());
                respList.add(addOrderResDepartDate);
            } else {
                String message = MessageFormat.format("生成切位单失败：【{0}】，产品编号：【{1}】， 团期：【{2}】。", productOccupyResData.getMsg(),
                        String.valueOf(bookingOrderVo.getProductId()), ordDates[i].getDepartDate());
                // 占位失败，生成D类类订单失败
                throw new SaaSSystemException(message);
            }
        }
        return respList;
    }

    /**
     * Description: <br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param bookingOrderVo
     * @param bookingOrderQueryVo
     * @param ordDepartDates
     * @param i
     * @param ordDealOrder
     * @param sessionId
     * @param productOccupyReqData
     *            <br>
     */
    private ProductOccupyReqData initProductOccupyReqData(BookingOrderVo bookingOrderVo, String departDate,
            OrdDealOrder ordDealOrder) {
        ProductOccupyReqData productOccupyReqData = new ProductOccupyReqData();

        productOccupyReqData.setClientFlag("DOTA_ORD");

        Random rand = new Random();
        int sessionId = rand.nextInt(100000000);
        productOccupyReqData.setSessionId(Integer.valueOf(sessionId));
        productOccupyReqData.setIsGreedy(false);
        productOccupyReqData.setOrderId(ordDealOrder.getOrderId());
        productOccupyReqData.setChannelId(Integer.valueOf(bookingOrderVo.getDistributorId()));
        productOccupyReqData.setChannelName(bookingOrderVo.getDistributorName());
        productOccupyReqData.setRevertServiceName(null);
        OccupyReqDproducts dProduct = new OccupyReqDproducts();
        dProduct.setdProductId(bookingOrderVo.getProductId());
        dProduct.setdProductName(bookingOrderVo.getProductName());
        OccupyReqDepartDates ccupyReqDepartDate = new OccupyReqDepartDates();
        ccupyReqDepartDate.setDepartDate(departDate.substring(0, 10));
        Integer stockAdultCount = bookingOrderVo.getStockAdultCount() == null ? 0 : bookingOrderVo.getStockAdultCount();
        Integer stockChildCount = bookingOrderVo.getStockChildCount() == null ? 0 : bookingOrderVo.getStockChildCount();
        Integer count = stockAdultCount + stockChildCount;
        ccupyReqDepartDate.setAdultNum(count);
        List<OccupyReqDepartDates> departDates = new ArrayList<OccupyReqDepartDates>();
        departDates.add(ccupyReqDepartDate);
        dProduct.setDepartDates(departDates);
        List<OccupyReqDproducts> dProducts = new ArrayList<OccupyReqDproducts>();
        dProducts.add(dProduct);
        productOccupyReqData.setdProducts(dProducts);

        return productOccupyReqData;
    }

    /**
     * Description: <br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param bookingOrderVo
     * @param ordDealOrder
     * @param ordPriceDetail
     *            <br>
     */
    private OrdPriceDetail initOrdPriceDetail(Integer orderDealId, Integer stockAdultCount, BigDecimal adultPrice,
            Integer stockChildCount, BigDecimal childPrice, String currencyType) {
        OrdPriceDetail ordPriceDetail = new OrdPriceDetail();
        ordPriceDetail.setOrderId(orderDealId);
        ordPriceDetail.setStockAdultCount(stockAdultCount);
        ordPriceDetail.setAdultPrice(adultPrice);
        ordPriceDetail.setStockChildCount(stockChildCount);
        ordPriceDetail.setChildPrice(childPrice);
        ordPriceDetail.setCurrencyType(currencyType);

        BaseDomainUtil.setBasePropertyValue(ordPriceDetail);
        return ordPriceDetail;
    }

    /**
     * Description: <br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param bookingOrderVo
     * @param ordDealOrder
     *            <br>
     */
    private OrdDealOrder initOrdDealOrder(BookingOrderVo bookingOrderVo, String departDate, String closingDate) {
        OrdDealOrder ordDealOrder = new OrdDealOrder();
        // 查找ord_deal_order表中最大的order_id
        ordDealOrder.setProductId(bookingOrderVo.getProductId());
        ordDealOrder.setProductName(bookingOrderVo.getProductName());
        ordDealOrder.setFirstDestGroupId(bookingOrderVo.getFirstDestGroupId());
        ordDealOrder.setFirstDestGroupName(bookingOrderVo.getFirstDestGroupName());
        ordDealOrder.setSecDestGroupId(bookingOrderVo.getSecDestGroupId());
        ordDealOrder.setSecDestGroupName(bookingOrderVo.getSecDestGroupName());
        ordDealOrder.setDestCategoryId(bookingOrderVo.getDestCategoryId());
        ordDealOrder.setDestCategoryName(bookingOrderVo.getDestCategoryName());
        ordDealOrder.setGroupId(bookingOrderVo.getGroupId());
        ordDealOrder.setGroupName(bookingOrderVo.getGroupName());

        ordDealOrder.setDepartureCityCode(bookingOrderVo.getDepartureCityCode());
        ordDealOrder.setDepartureCityName(bookingOrderVo.getDepartureCityName());
        ordDealOrder.setProductCategory(bookingOrderVo.getProductCategory());
        ordDealOrder.setProductCategoryName(bookingOrderVo.getProductCategoryName());
        ordDealOrder.setProductSubCategory(bookingOrderVo.getProductSubCategory());
        ordDealOrder.setProductSubCategoryName(bookingOrderVo.getProductSubCategoryName());
        ordDealOrder.setDestId(bookingOrderVo.getDestId());
        ordDealOrder.setDestName(bookingOrderVo.getDestName());
        ordDealOrder.setProductLineId(bookingOrderVo.getProductLineId());
        ordDealOrder.setContinentCode(bookingOrderVo.getContinentCode());
        ordDealOrder.setContinentName(bookingOrderVo.getContinentName());
        ordDealOrder.setCountryCode(bookingOrderVo.getCountryCode());
        ordDealOrder.setCountryName(bookingOrderVo.getCountryName());
        ordDealOrder.setProvinceCode(bookingOrderVo.getProvinceCode());
        ordDealOrder.setProvinceName(bookingOrderVo.getProvinceName());
        ordDealOrder.setCityCode(bookingOrderVo.getCityCode());
        ordDealOrder.setCityName(bookingOrderVo.getCityName());
        ordDealOrder.setDistrictCode(bookingOrderVo.getDistrictCode());
        ordDealOrder.setDistrictName(bookingOrderVo.getDistrictName());

        try {
            ordDealOrder.setDepartDate(DateFormatUtils.parseDate(departDate));
            ordDealOrder.setBackDate(DateUtils.addDay(DateFormatUtils.parseDate(departDate), bookingOrderVo.getDayNum() - 1));
            if (closingDate != null) {
                ordDealOrder.setClosingDate(DateFormatUtils.parseDate(closingDate));
            }
        } catch (ParseException e) {
            LOGGER.error("时间设置错误", e);
        }
        ordDealOrder.setDistributorId(bookingOrderVo.getDistributorId());
        ordDealOrder.setProductManagerId(bookingOrderVo.getProductManagerId());
        ordDealOrder.setProductManagerName(bookingOrderVo.getProductManagerName());

        ordDealOrder.setDayNum(bookingOrderVo.getDayNum());
        ordDealOrder.setDealOrderType(bookingOrderVo.getChannelType());
        ordDealOrder.setStatusCode(OrderStateEnum.INITIAL.getStatusCode());
        ordDealOrder.setProductStaffId(bookingOrderVo.getProductStaffId());
        ordDealOrder.setProductStaffName(bookingOrderVo.getProductStaffName());

        ordDealOrder.setAdultPrice(bookingOrderVo.getAdultPrice());
        ordDealOrder.setChildPrice(bookingOrderVo.getChildPrice());
        ordDealOrder.setSellChannelCode(bookingOrderVo.getSellChannelCode());
        ordDealOrder.setDealOrderNum(bookingOrderVo.getStockAdultCount());
        BaseDomainUtil.setBasePropertyValue(ordDealOrder);

        return ordDealOrder;
    }

    /**
     * Description: 取消订单 <br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param cancelOrderVo
     *            <br>
     */
    @Override
    public void cancelOrder(CancelOrderVo cancelOrderVo) {
        validateData(cancelOrderVo);
        // 取消占位
        Integer[] orderIds = cancelOrderVo.getOrderIds();
        for (Integer orderId : orderIds) {
            OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(orderId);
            if (ordDealOrder == null) {
                throw new SaaSSystemException("占位订单" + orderId + "不存在！");
            }

            ExternalResponseObj<ProductOccpuyInfo> productOccpuyInfo = getProductOccpuyInfo(orderId);
            if (productOccpuyInfo == null || !productOccpuyInfo.isSuccess() || productOccpuyInfo.getData() == null) {
                throw new SaaSSystemException("根据切位单查询产品占位信息错误");
            }

            int num = 0;
            List<ProductOccpuyInfo> resultInfo = productOccpuyInfo.getData().getRows();
            Integer[] outIds = new Integer[resultInfo.size()];
            for (int i = 0; i < resultInfo.size(); i++) {
                outIds[i] = resultInfo.get(i).getId();
                num = num + resultInfo.get(i).getLeftNumber();
            }

            // 没有填写取消数量 默认取消全部
            if (cancelOrderVo.getCancelCount() != null) {
                num = cancelOrderVo.getCancelCount();
            }

            ProductCancelOccupyReqData cancelProductOccupyReqData = initCancelProductParam(orderId, ordDealOrder.getProductId(),
                    ordDealOrder.getDepartDate(), num, outIds);
            String productCancelOccupyResStr = TspUtil.callTspService(
                    TSPEnumUtil.changeTSPName(SystemConstants.DPRD_STOCK_CANCEL_OCCUPY_SIGN),
                    JsonUtil.toString(cancelProductOccupyReqData), SystemConstants.HTTP_POST);

            ProductOccupyResData productOccupyResData = JSON.parseObject(productCancelOccupyResStr, ProductOccupyResData.class);
            if (productOccupyResData == null) {
                throw new SaaSSystemException("json数据转换错误");
            }
            if (productOccupyResData.isSuccess()) {
                // 取消占位成功，订单置为无效
                OrdDealOrder ordDealOrder2 = new OrdDealOrder();
                ordDealOrder2.setId(ordDealOrder.getId());
                ordDealOrder2.setStatusCode(OrderStateEnum.CANCLE_OVER.getStatusCode());
                ordDealOrderMapper.updateByPrimaryKeySelective(ordDealOrder2);
            } else {
                throw new SaaSSystemException("订单" + cancelProductOccupyReqData.getOrderId() + "取消占位失败！");
            }
        }
    }

    private void validateData(CancelOrderVo cancelOrderVo) {
        Assert.notNull(cancelOrderVo, "取消订单对象不能为空");
    }

    private ExternalResponseObj<ProductOccpuyInfo> getProductOccpuyInfo(Integer dealOrderId) {
        Map<String, Object> occpuyParam = new HashMap<String, Object>();
        occpuyParam.put("clientFlag", "DOTA_ORD");
        occpuyParam.put("occupyObjId", dealOrderId);
        occpuyParam.put("businessType", 1111);

        String response = TspUtil.callTspService(TSPEnumUtil.changeTSPName(SystemConstants.QUERY_PRD_OCCUPY_INFO),
                JsonUtil.toString(occpuyParam), SystemConstants.HTTP_GET);

        return JsonUtil.toBean(response, ExternalResponseObj.class, ProductOccpuyInfo.class);

    }

    /**
     * Description: 初始化取消占位参数<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param productId
     * @param orderId
     * @param cancelProductOccupyReqData
     * @param ordDealOrder
     * @param ordPriceDetail
     *            <br>
     */
    private ProductCancelOccupyReqData initCancelProductParam(Integer orderId, Integer productId, Date departDate, int num,
            Integer[] outIds) {
        ProductCancelOccupyReqData cancelProductOccupyReqData = new ProductCancelOccupyReqData();
        Random rand = new Random();
        int sessionId = rand.nextInt(100000000);
        cancelProductOccupyReqData.setClientFlag("DOTA_ORD");
        cancelProductOccupyReqData.setSessionId(sessionId);
        cancelProductOccupyReqData.setOrderId(orderId);
        cancelProductOccupyReqData.setRevertServiceName(null);

        CancelOccupyReqDproducts cancelOccupyReqDproducts = new CancelOccupyReqDproducts();
        CancelOccupyReqDepartDates cancelOccupyReqDepartDates = new CancelOccupyReqDepartDates();
        cancelOccupyReqDepartDates.setDepartDate(DateFormatUtils.formatDate(departDate));
        cancelOccupyReqDepartDates.setOutIds(outIds);
        cancelOccupyReqDepartDates.setAdultNum(num);
        cancelOccupyReqDproducts.setDepartDates(Collections.singletonList(cancelOccupyReqDepartDates));
        cancelOccupyReqDproducts.setdProductId(productId);
        cancelOccupyReqDproducts.setdProductType(null);
        cancelProductOccupyReqData.setdProducts(Collections.singletonList(cancelOccupyReqDproducts));

        return cancelProductOccupyReqData;
    }

    @Override
    public void addExtBatchId(AddExtBatchIdVo addExtBatchIdVo) {
        OrdDealOrder ordDealOrder = new OrdDealOrder();
        ordDealOrder = ordDealOrderMapper.selectByOrderId(addExtBatchIdVo.getOrderId());
        if (ordDealOrder != null) {
            OrdDealOrder tempOrd = new OrdDealOrder();
            tempOrd.setId(ordDealOrder.getId());
            tempOrd.setExtBatchId(addExtBatchIdVo.getRoundId()[0]);
            ordDealOrderMapper.updateByPrimaryKeySelective(tempOrd);
        } else {
            throw new SaaSSystemException("订单" + addExtBatchIdVo.getOrderId() + "维护异常。");
        }
    }

    @Override
    public ResponseVo addExtDbatchId(AddExtDbatchIdVo addExtDbatchIdVo) {
        ResponseVo responseVo = new ResponseVo();
        OrdDealOrder ordDealOrder = new OrdDealOrder();
        ordDealOrder = ordDealOrderMapper.selectByOrderId(addExtDbatchIdVo.getOrderId());
        if (ordDealOrder != null) {
            OrdDealOrder tempOrd = new OrdDealOrder();
            tempOrd.setId(ordDealOrder.getId());
            tempOrd.setExtDbatchId(addExtDbatchIdVo.getDroundId()[0]);
            ordDealOrderMapper.updateByPrimaryKeySelective(tempOrd);
        } else {
            responseVo.setSuccess(false);
            responseVo.setMsg("订单" + addExtDbatchIdVo.getDroundId() + "维护异常。");
            throw new SaaSSystemException("订单" + addExtDbatchIdVo.getDroundId() + "维护异常。");
        }
        return responseVo;
    }

    @Override
    public ResponseVo queryOrdBatchIds(QueryOrdBatchIdsVo queryOrdBatchIdsVo) {
        ResponseVo responseVo = new ResponseVo();
        int ordId = queryOrdBatchIdsVo.getDordId();
        OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(Integer.valueOf(ordId));
        if (null != ordDealOrder) {
            OrdPriceDetail ordPriceDetail = ordPriceDetailMapper.selectByOrderId(Integer.valueOf(ordId));
            if (null == ordPriceDetail) {
                responseVo.setSuccess(false);
                responseVo.setMsg("订单" + ordId + "在ord_price_detail表中维护异常。");
                return responseVo;
            }
            int number = ordPriceDetail.getStockAdultCount().intValue() + ordPriceDetail.getStockChildCount().intValue()
                    - ordPriceDetail.getConfirmedAdultCount().intValue() - ordPriceDetail.getConfirmedChildCount().intValue()
                    + ordPriceDetail.getLossAdultCount().intValue() + ordPriceDetail.getLossChildCount().intValue();
            Map<String, Integer> data = new HashMap<String, Integer>();
            data.put("extBatchId", ordDealOrder.getExtBatchId());
            data.put("extDbatchId", ordDealOrder.getExtDbatchId());
            data.put("number", Integer.valueOf(number));
            responseVo.setData(data);
        } else {
            responseVo.setSuccess(false);
            responseVo.setMsg("订单" + ordId + "在ord_deal_order表中维护异常。");
        }
        return responseVo;
    }

    @Override
    public RealTimeAskResponse addStockRealTimeAsk(RealTimeAskStockVo askStockVo) {
        // HTTP 调用组团查询团相关信息
        String tourBasicId = askStockVo.getAgencyProductId();
        String departDate = askStockVo.getDate();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("tourBasicId", tourBasicId);
        map.put("departDate", departDate);
        map.put("tenantId", DataSourceSwitch.getTenantId());
        String response = RestUtil.execute(SystemInitParameter.getProductCombinationInfo, SystemConstants.HTTP_GET,
                JsonUtil.toString(map));

        ResponseVo responseVo = JsonUtil.toBean(response, ResponseVo.class);
        if (responseVo == null || responseVo.getData() == null) {
            throw new SaaSSystemException("查询产品无返回信息");
        }

        ProductQueryOutputVo productQueryOutputVo = JsonUtil.toBean(JsonUtil.toString(responseVo.getData()),
                ProductQueryOutputVo.class);
        if (productQueryOutputVo == null) {
            throw new SaaSSystemException("查询产品无返回信息");
        }

        if (productQueryOutputVo.getProductSellChannel() == null) {
            throw new SaaSSystemException("此资源【" + tourBasicId + "】无法发起现询操作");
        }

        Integer productId = productQueryOutputVo.getProductSellChannel().getProductId();

        OrdDealOrder ordDealOrder = initOrdDealOrderForRealAsk(productQueryOutputVo, askStockVo);
        LOGGER.info("ordDealOrder:{}", JsonUtil.toString(ordDealOrder));
        LOGGER.info("现询生成切位单。产品编号：{}", productId);
        ordDealOrderMapper.insertSelective(ordDealOrder);
        ordDealOrder.setOrderId(ordDealOrder.getId());
        // 产品占位
        String productName = productQueryOutputVo.getProductBase().getProductName();
        LOGGER.info("现询产品占位。产品编号：{}", productId);
        ProductOccupyReqData productOccupyReqData = getProdOccupyReqData(askStockVo, productId, productName, ordDealOrder);
        String productOccupyResStr = TspUtil.callTspService(TSPEnumUtil.changeTSPName(SystemConstants.DPRD_STOCK_OCCUPY_SIGN),
                JsonUtil.toString(productOccupyReqData), SystemConstants.HTTP_POST);
        LOGGER.info("产品占位返回信息：{}", productOccupyResStr);

        ProductOccupyResData productOccupyResData = JsonUtil.toBean(productOccupyResStr, ProductOccupyResData.class);
        if (productOccupyResData == null) {
            throw new SaaSSystemException("json数据转化错误");
        }
        if (productOccupyResData != null && productOccupyResData.isSuccess()) {
            ordDealOrder.setStatusCode(OrderStateEnum.OCCUPIED.getStatusCode());
        } else {
            String message = MessageFormat.format("下单失败：【{0}】，产品编号：【{1}】， 团期：【{2}】。", productOccupyResData.getMsg(),
                    String.valueOf(productId), departDate);
            LOGGER.error(message);
            throw new SaaSSystemException(message);
        }

        Map<String, BigDecimal> price = getPrice(productQueryOutputVo.getAdultPrice(), productQueryOutputVo.getChildPrice(),
                productQueryOutputVo.getProductSellChannel());
        OrdPriceDetail ordPriceDetail = initOrdPriceDetail(ordDealOrder.getId(), askStockVo.getAdultNum(), price.get("adult"),
                askStockVo.getChildNum(), price.get("child"), String.valueOf(askStockVo.getCostCurrencyType()));
        ordPriceDetailMapper.insertSelective(ordPriceDetail);

        List<OccupyResDepartDates> departDates = productOccupyResData.getData().getdProducts().get(0).getDepartDates();
        ordDealOrder.setClosingDate(getMinReleaseTime(departDates));
        ordDealOrder.setStatusCode(OrderStateEnum.OCCUPIED.getStatusCode());
        ordDealOrderMapper.updateByPrimaryKeySelective(ordDealOrder);

        return generateResponseParam(askStockVo, productQueryOutputVo, ordDealOrder, departDates);
    }

    private RealTimeAskResponse generateResponseParam(RealTimeAskStockVo askStockVo, ProductQueryOutputVo productQueryOutputVo,
            OrdDealOrder ordDealOrder, List<OccupyResDepartDates> departDates) {
        RealTimeAskResponse askResponse = new RealTimeAskResponse();
        askResponse.setTuniuSerialId(askStockVo.getTuniuSerialId());
        askResponse.setTuniuOrderId(askStockVo.getTuniuOrderId());
        askResponse.setExtPurchaseId(ordDealOrder.getId());
        askResponse.setReserveNum(askStockVo.getAdultNum() + askStockVo.getChildNum());
        askResponse.setReserveTime(getMinReleaseTime(departDates));
        Map<String, BigDecimal> price = getPrice(productQueryOutputVo.getAdultPrice(), productQueryOutputVo.getChildPrice(),
                productQueryOutputVo.getProductSellChannel());
        askResponse.setReserveAdultPrice(price.get("adult"));
        askResponse.setReserveChildPrice(price.get("child"));
        askResponse.setReserveRoomAddPrice(productQueryOutputVo.getProductSellChannel().getSingleRoomPrice());
        askResponse.setCostCurrencyType(String.valueOf(departDates.get(0).getCostCurrencyType()));
        askResponse.setAgencyOrderId(String.valueOf(ordDealOrder.getId()));
        askResponse.setAgencyProductId(askStockVo.getAgencyProductId());
        return askResponse;
    }

    /**
     * 算库存价格
     * 
     * @param departDates
     * @return
     */
    private Map<String, BigDecimal> getPrice(BigDecimal adultPrice, BigDecimal childPrice,
            ProductSellChannel productSellChannel) {
        Map<String, BigDecimal> ret = new HashMap<String, BigDecimal>();
        if (1 == productSellChannel.getPriceType().intValue()) {
            ret.put("adult", productSellChannel.getAdultPrice());
            ret.put("child", productSellChannel.getChildPrice());
        } else if (2 == productSellChannel.getPriceType().intValue()) { // *
            ret.put("adult", adultPrice.multiply(productSellChannel.getAdultPrice()));
            ret.put("child", childPrice.multiply(productSellChannel.getChildPrice()));
        } else if (3 == productSellChannel.getPriceType().intValue()) { // +
            ret.put("adult", adultPrice.add(productSellChannel.getAdultPrice()));
            ret.put("child", adultPrice.add(productSellChannel.getChildPrice()));
        }

        return ret;
    }

    private Date getMinReleaseTime(List<OccupyResDepartDates> departDates) {
        Collections.sort(departDates, new Comparator<OccupyResDepartDates>() {
            @Override
            public int compare(OccupyResDepartDates o1, OccupyResDepartDates o2) {
                return o1.getReleaseTime().compareTo(o2.getReleaseTime());
            }
        });

        return departDates.get(0).getReleaseTime();
    }

    private ProductOccupyReqData getProdOccupyReqData(RealTimeAskStockVo askStockVo, Integer productId, String productName,
            OrdDealOrder ordDealOrder) {
        ProductOccupyReqData productOccupyReqData = new ProductOccupyReqData();
        productOccupyReqData.setClientFlag("DOTA_ORD");
        Random rand = new Random();
        int sessionId = rand.nextInt(100000000);
        productOccupyReqData.setSessionId(Integer.valueOf(sessionId));
        productOccupyReqData.setIsGreedy(false);
        productOccupyReqData.setOrderId(ordDealOrder.getOrderId());
        // TODO
        productOccupyReqData.setChannelId(Integer.valueOf(ordDealOrder.getDistributorId()));
        productOccupyReqData.setChannelName("途牛A");
        productOccupyReqData.setRevertServiceName(null);
        OccupyReqDproducts dProduct = new OccupyReqDproducts();
        dProduct.setdProductId(productId);
        dProduct.setdProductName(productName);
        OccupyReqDepartDates ccupyReqDepartDate = new OccupyReqDepartDates();
        ccupyReqDepartDate.setDepartDate(askStockVo.getDate());
        ccupyReqDepartDate.setAdultNum(askStockVo.getAdultNum() + askStockVo.getChildNum());
        dProduct.setDepartDates(Collections.singletonList(ccupyReqDepartDate));
        productOccupyReqData.setdProducts(Collections.singletonList(dProduct));
        return productOccupyReqData;
    }

    private OrdDealOrder initOrdDealOrderForRealAsk(ProductQueryOutputVo productQueryOutputVo, RealTimeAskStockVo askStockVo) {
        ProductBase productBase = productQueryOutputVo.getProductBase();
        ProductDepartureCity productDepartureCity = productQueryOutputVo.getProductDepartureCity();
        ProductDestination productDestination = productQueryOutputVo.getProductDestination();
        ProductSellChannel productSellChannel = productQueryOutputVo.getProductSellChannel();

        OrdDealOrder ordDealOrder = new OrdDealOrder();
        ordDealOrder.setProductId(productBase.getId());
        ordDealOrder.setProductName(productBase.getProductName());
        ordDealOrder.setFirstDestGroupId(productBase.getFirstDestGroupId());
        ordDealOrder.setFirstDestGroupName(productBase.getFirstDestGroupName());
        ordDealOrder.setSecDestGroupId(productBase.getSecDestGroupId());
        ordDealOrder.setSecDestGroupName(productBase.getSecDestGroupName());
        ordDealOrder.setDestCategoryId(productBase.getDestCategoryId());
        ordDealOrder.setDestCategoryName(productBase.getDestCategoryName());
        ordDealOrder.setGroupId(productQueryOutputVo.getTourBasicId());
        ordDealOrder.setGroupName(productQueryOutputVo.getTourGroupName());

        try {
            ordDealOrder.setDepartDate(DateFormatUtils.parseDate(productSellChannel.getTourDate()));
            ordDealOrder.setBackDate(DateUtils.addDay(DateFormatUtils.parseDate(productSellChannel.getTourDate()),
                    productQueryOutputVo.getDayNum() - 1));
        } catch (ParseException e) {
            throw new SaaSSystemException("时间转化格式错误");
        }

        ordDealOrder.setDepartureCityCode(productDepartureCity.getDepartureCityCode());
        ordDealOrder.setDepartureCityName(productDepartureCity.getDepartureCityName());
        ordDealOrder.setProductCategory(Integer.valueOf(1));
        ordDealOrder.setProductCategoryName("跟团游");
        ordDealOrder.setProductSubCategory(Integer.valueOf(0));
        ordDealOrder.setProductSubCategoryName("常规跟团");
        ordDealOrder.setDestId(productBase.getDestId());
        ordDealOrder.setDestName(productBase.getDestName());
        ordDealOrder.setProductLineId(productBase.getProductLineId());
        ordDealOrder.setContinentCode(productDestination.getContinentCode());
        ordDealOrder.setContinentName(productDestination.getContinentName());
        ordDealOrder.setCountryCode(productDestination.getCountryCode());
        ordDealOrder.setCountryName(productDestination.getCountryName());
        ordDealOrder.setProvinceCode(productDestination.getProvinceCode());
        ordDealOrder.setProvinceName(productDestination.getProvinceName());
        ordDealOrder.setCityCode(productDestination.getCityCode());
        ordDealOrder.setCityName(productDestination.getCityName());
        ordDealOrder.setDistrictCode(productDestination.getDistrictCode());
        ordDealOrder.setDistrictName(productDestination.getDistrictName());

        // TODO
        ordDealOrder.setDistributorId(String.valueOf(askStockVo.getChannelId()));
        ProductRelatePerson productRelatePerson = productQueryOutputVo.getProductRelatePerson();
        ordDealOrder.setProductManagerId(productRelatePerson.getUserId());
        ordDealOrder.setProductManagerName(productRelatePerson.getName());
        ordDealOrder.setStatusCode(OrderStateEnum.INITIAL.getStatusCode());

        ordDealOrder.setDayNum(productQueryOutputVo.getDayNum());
        ordDealOrder.setDealOrderType(Integer.valueOf(productSellChannel.getChannelType()));
        productQueryOutputVo.getProductRelatePerson();
        ordDealOrder.setProductStaffId(productRelatePerson.getUserId());
        ordDealOrder.setProductStaffName(productRelatePerson.getName());
        ordDealOrder.setDealOrderNum(askStockVo.getAdultNum() + askStockVo.getChildNum());

        Map<String, BigDecimal> price = getPrice(productQueryOutputVo.getAdultPrice(), productQueryOutputVo.getChildPrice(),
                productQueryOutputVo.getProductSellChannel());
        ordDealOrder.setAdultPrice(price.get("adult"));
        ordDealOrder.setChildPrice(price.get("child"));
        // ordDealOrder.setSellChannelCode(productQueryOutputVo.getProductSellChannel().getSellChannel());

        return ordDealOrder;
    }

    @Override
    public List<OrdDealOrder> queryRealAskNumber(QueryRealTimeAskVo queryRealTimeAskVo) {
        return ordDealOrderMapper.queryRealAskNumber(queryRealTimeAskVo.getProductId(), queryRealTimeAskVo.getDepartDates());
    }
}
