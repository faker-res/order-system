/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.tuniu.ord.service.ManualContactService;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhoujie8
 * 
 */
@Controller
@RequestMapping("/rest/contact")
public class ManualContactController {
    private static final Logger logger = LoggerFactory.getLogger(ManualContactController.class);
    @Resource
    private ManualContactService contactService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void queryContactList(Integer manualOrderId, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        List<ManualContact> list = new ArrayList<ManualContact>();
        try {
            list = contactService.queryContactList(manualOrderId);
        } catch (OrdCustomException e) {
            logger.error("查询合同失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("count", list.size());
        vo.setData(map);
        RestUtil.write(response, vo);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveContact(ManualContact manualContact, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            contactService.saveContact(manualContact);
        } catch (OrdCustomException e) {
            logger.error("保存联系人失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void delContact(Integer id, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            contactService.delContact(id);
        } catch (OrdCustomException e) {
            logger.error("删除联系人失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }
}
