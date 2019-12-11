/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-23                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.exception.ValidationException;
import com.tuniu.ord.service.IOrderService;
import com.tuniu.ord.vo.AddExtBatchIdVo;
import com.tuniu.ord.vo.AddExtDbatchIdVo;
import com.tuniu.ord.vo.AddOrderResDepartDate;
import com.tuniu.ord.vo.BookingOrderVo;
import com.tuniu.ord.vo.CancelOrderVo;
import com.tuniu.ord.vo.QueryOrdBatchIdsVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * <Description> 下/取消（D类）订单、<br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-21 <br>
 */
@Controller
@RequestMapping("/rest/ord")
public class BookingController {

    /**
     * 记录日志信息
     */
    private static Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    @Resource
    private IOrderService orderServiceImpl;

    /**
     * Description: 下单<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param bookingOrderVo
     * @param response
     * <br>
     */
    @RequestMapping(path = "/booking-order", method = RequestMethod.POST)
    public void bookingOrder(BookingOrderVo bookingOrderVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("bookingOrderVo", bookingOrderVo);
        ArgumentValidator.isNotNull("productId", bookingOrderVo.getProductId());
        ArgumentValidator.isNotNull("groupId", bookingOrderVo.getGroupId());

        ResponseVo responseVo = new ResponseVo();
        try {
            List<AddOrderResDepartDate> respList = orderServiceImpl.addOrder(bookingOrderVo);
            responseVo.setData(respList);
        } catch (Exception e) {
            LOGGER.error("生成切位单失败", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }

    /**
     * Description: 取消订单<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param cancelOrderVo
     * @param response
     * <br>
     */
    @RequestMapping(path = "/cancel-order", method = RequestMethod.POST)
    public void cancelOrder(CancelOrderVo cancelOrderVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("cancelOrderVo", cancelOrderVo);
        ArgumentValidator.isNotNull("orderIds", cancelOrderVo.getOrderIds());

        ResponseVo responseVo = new ResponseVo();
        try {
            orderServiceImpl.cancelOrder(cancelOrderVo);
        } catch (SaaSSystemException e) {
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        } catch (Exception e) {
            responseVo.setSuccess(false);
            responseVo.setMsg("系统错误");
        }
        RestUtil.write(response, responseVo);
    }

    /**
     * Description: 维护A类资源入库批次号<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param addExtBatchIdVo
     * @throws ValidationException
     * <br>
     */
    @RequestMapping(path = "/add-extbatchid", method = RequestMethod.POST)
    public void addExtBatchId(AddExtBatchIdVo addExtBatchIdVo, HttpServletResponse response) throws ValidationException {
        ArgumentValidator.isNotNull("addExtBatchIdVo", addExtBatchIdVo);
        ArgumentValidator.isPositiveAndNonZeroNumber("dOrderId", addExtBatchIdVo.getOrderId().intValue());
        orderServiceImpl.addExtBatchId(addExtBatchIdVo);
        ResponseVo responseVo = new ResponseVo();
        RestUtil.write(response, responseVo);
    }

    /**
     * Description: 维护D类产品入库批次号<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param addExtDbatchIdVo
     * @param response
     * <br>
     * @throws ValidationException
     */
    @RequestMapping(path = "/add-extdbatchid", method = RequestMethod.POST)
    public void addExtDbatchId(AddExtDbatchIdVo addExtDbatchIdVo, HttpServletResponse response) throws ValidationException {
        ArgumentValidator.isNotNull("addExtDbatchIdVo", addExtDbatchIdVo);
        ArgumentValidator.isPositiveAndNonZeroNumber("orderId", addExtDbatchIdVo.getOrderId());
        ResponseVo responseVo = orderServiceImpl.addExtDbatchId(addExtDbatchIdVo);
        RestUtil.write(response, responseVo);
    }

    /**
     * Description: 查询订单批次信息<br>
     * 
     * @author gaofei<br>
     * @taskId <br>
     * @param queryOrdBatchIdsVo
     * @param response
     * @throws ValidationException
     * <br>
     */
    @RequestMapping(path = "/query-ord-batchid", method = RequestMethod.POST)
    public void queryOrdBatchIds(QueryOrdBatchIdsVo queryOrdBatchIdsVo, HttpServletResponse response)
            throws ValidationException {
        ArgumentValidator.isPositiveAndNonZeroNumber("dordId", queryOrdBatchIdsVo.getDordId());
        ResponseVo responseVo = new ResponseVo();
        responseVo = orderServiceImpl.queryOrdBatchIds(queryOrdBatchIdsVo);
        RestUtil.write(response, responseVo);
    }
}
