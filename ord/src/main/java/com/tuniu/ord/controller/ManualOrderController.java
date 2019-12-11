package com.tuniu.ord.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.domain.ManualContact;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.service.CommonOrderService;
import com.tuniu.ord.service.ManualContactService;
import com.tuniu.ord.service.ManualOrderService;
import com.tuniu.ord.service.ManualProductQueryService;
import com.tuniu.ord.service.ManualRemarkService;
import com.tuniu.ord.service.ManualRequirementService;
import com.tuniu.ord.vo.BaseManualVo;
import com.tuniu.ord.vo.ChangeProductVo;
import com.tuniu.ord.vo.ManualOrderPriceVo;
import com.tuniu.ord.vo.ManualOrderVo;
import com.tuniu.ord.vo.RequirementVo;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/rest/manual-order")
public class ManualOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManualOrderController.class);

    @Resource
    private ManualOrderService manualOrderService;

    @Resource
    private ManualRequirementService manualRequirementService;

    @Resource
    private CommonOrderService commonOrderService;

    @Resource
    private ManualContactService contactService;

    @Resource
    private ManualProductQueryService manualProductQueryService;

    @Resource
    private ManualRemarkService manualRemarkService;

    @RequestMapping(value = "/order-detail", method = RequestMethod.GET)
    public void queryOrderDetail(BaseManualVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();

        Integer manualOrderId = vo.getManualOrderId();
        if (manualOrderId == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        ManualOrderVo orderVo = new ManualOrderVo();

        ManualOrder order = commonOrderService.getManualOrder(manualOrderId);
        if (order == null) {
            responseVo.setSuccess(false);
            responseVo.setErrorMsg("订单不存在!");
            RestUtil.write(response, responseVo);
            return;
        }
        orderVo.setOrder(order);

        ManualRequirement requirement = commonOrderService.getRequirement(manualOrderId);
        orderVo.setRequirement(requirement);

        List<ManualContact> contacts = contactService.queryContactList(manualOrderId);
        orderVo.setContact(contacts);

        List<ManualRemark> remarks = manualRemarkService.queryRemarkByManualOrderId(manualOrderId);
        orderVo.setRemark(remarks);

        responseVo.setData(orderVo);
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/query-selected-product", method = RequestMethod.GET)
    public void querySelectedProduct(RequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if (vo.getMemberId() == null || vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            responseVo.setData(manualOrderService.querySelectdProduct(vo));
        } catch (OrdCustomException e) {
            LOGGER.error("查询已选产品出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (ParseException e) {
            LOGGER.error("查询已选产品出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("查询已选产品出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/occupy", method = RequestMethod.POST)
    public void occupy(RequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if (vo.getMemberId() == null || vo.getRequirementId() == null || vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        manualOrderService.newOccupy(vo);
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/reoccupy", method = RequestMethod.POST)
    public void reoccupy(RequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if (vo.getMemberId() == null || vo.getRequirementId() == null || vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            manualOrderService.reoccupy(vo);
        } catch (OrdCustomException e) {
            LOGGER.error("订单重新占位出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("订单重新占位出错", e);
            responseVo = ResponseUtil.buildFailure(OrdError.OCCUPY_ERROR);
        }

        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/cancelOccupy", method = RequestMethod.POST)
    public void cancelOccupy(RequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if (vo.getRequirementId() == null || vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            manualOrderService.cancelOccupy(vo.getManualOrderId(), vo.getRequirementId(), vo.getdOrderId());
        } catch (OrdCustomException e) {
            LOGGER.error("订单取消占位出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("订单取消占位出错", e);
            responseVo = ResponseUtil.buildFailure(OrdError.CANCEL_OCCUPY_ERROR);
        }
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/change-product", method = RequestMethod.POST)
    public void changeRequire(ChangeProductVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if (vo.getProductId() == null || vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            manualOrderService.changeProduct(vo);
        } catch (OrdCustomException e) {
            LOGGER.error("更换产品出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("更换产品出错", e);
            responseVo = ResponseUtil.buildFailure(OrdError.CHANGE_PRODUCT_ERROR);
        }
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/getOrderPrice", method = RequestMethod.GET)
    public void getOrderPrice(Integer manualOrderId, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        try {
            ManualOrderPriceVo orderPrice = manualOrderService.getOrderPrice(manualOrderId);
            responseVo.setData(orderPrice);
        } catch (OrdCustomException e) {
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }
}
