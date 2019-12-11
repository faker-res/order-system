/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月24日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.domain.OrdCommonRemark;
import com.tuniu.ord.service.RemarkService;
import com.tuniu.ord.vo.OrdCommonRemarkVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author fangzhongwei
 * 
 */
@Controller
@RequestMapping("/rest/memo")
public class RemarkController {

    @Resource
    private RemarkService remarkServiceImpl;

    @RequestMapping(path = "/list/query", method = { RequestMethod.GET })
    public void getRemarkList(OrdCommonRemarkVo ordCommonRemarkVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("ordCommonRemarkVo", ordCommonRemarkVo);

        List<OrdCommonRemark> productChannelList = remarkServiceImpl.getRemarkList(ordCommonRemarkVo);

        ResponseVo responseVo = new ResponseVo();
        if (CollectionUtils.isNotEmpty(productChannelList)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("count", Integer.valueOf(remarkServiceImpl.count(ordCommonRemarkVo)));
            map.put("rows", productChannelList);
            responseVo.setData(map);
        }

        RestUtil.write(response, responseVo);
    }

    @RequestMapping(path = "/save", method = { RequestMethod.POST })
    public void saveRemark(OrdCommonRemarkVo ordCommonRemarkVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("ordCommonRemarkVo", ordCommonRemarkVo);

        remarkServiceImpl.saveRemark(ordCommonRemarkVo);

        ResponseVo responseVo = new ResponseVo();
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(path = "/delete", method = { RequestMethod.POST })
    public void deleteRemarkl(OrdCommonRemarkVo ordCommonRemarkVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("ordCommonRemarkVo", ordCommonRemarkVo);

        remarkServiceImpl.deleteRemark(ordCommonRemarkVo);

        ResponseVo responseVo = new ResponseVo();
        RestUtil.write(response, responseVo);
    }

}
