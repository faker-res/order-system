/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.service.ContactDMSService;
import com.tuniu.ord.validator.ContactDMSValidator;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.createOrder.ContactQueryVo;
import com.tuniu.ord.vo.createOrder.ResultInfer;

/**
 * 联系人
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/rest/dms/contact")
public class ContactDMSController {
    private static final Logger LOG = LoggerFactory.getLogger(ContactDMSController.class);

    @Resource
    private ContactDMSValidator contactDMSValidatorImpl;

    @Resource
    private ContactDMSService contactDMSServiceImpl;

    /**
     * 保存联系人
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/save-contact", method = RequestMethod.POST)
    public void saveContact(HttpServletRequest request, HttpServletResponse response, ContactQueryVo input) {
        ResponseVo responseVo = new ResponseVo();
        LOG.info("保存联系人接口入参:{}", JsonUtil.toString(input));

        ResultInfer result = contactDMSValidatorImpl.validateSaveContact(input);
        if (!result.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(result.getMsg());
            RestUtil.write(response, responseVo);
            return;
        }

        contactDMSServiceImpl.saveContact(input);
        responseVo.setMsg("保存联系人成功");
        RestUtil.write(response, responseVo);
    }

    /**
     * 删除联系人
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/del-contact", method = RequestMethod.POST)
    public void delContact(HttpServletRequest request, HttpServletResponse response, ContactQueryVo input) {
        ResponseVo responseVo = new ResponseVo();

        ResultInfer result = contactDMSValidatorImpl.validateDelContact(input);
        if (!result.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(result.getMsg());
            RestUtil.write(response, responseVo);
            return;
        }

        contactDMSServiceImpl.delContact(input);
        responseVo.setMsg("删除联系人成功");
        RestUtil.write(response, responseVo);
    }

    /**
     * 修改联系人
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/upd-contact", method = RequestMethod.POST)
    public void updContact(HttpServletRequest request, HttpServletResponse response, ContactQueryVo input) {
        ResponseVo responseVo = new ResponseVo();

        ResultInfer result = contactDMSValidatorImpl.validateuUpdContact(input);
        if (!result.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(result.getMsg());
            RestUtil.write(response, responseVo);
            return;
        }

        contactDMSServiceImpl.updContact(input);
        responseVo.setMsg("修改联系人成功");
        RestUtil.write(response, responseVo);
    }

    /**
     * 查询联系人
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/get-contacts", method = RequestMethod.GET)
    public void getContacts(HttpServletRequest request, HttpServletResponse response, ContactQueryVo input) {
        ResponseVo responseVo = new ResponseVo();

        ResultInfer result = contactDMSValidatorImpl.validateGetContacts(input);
        if (!result.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(result.getMsg());
            RestUtil.write(response, responseVo);
            return;
        }

        responseVo.setData(contactDMSServiceImpl.getContacts(input));
        responseVo.setMsg("查询联系人成功");
        RestUtil.write(response, responseVo);
    }

}
