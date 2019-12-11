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

import com.tuniu.ord.common.constant.CardTypeEnum;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.service.PeopleTouristService;
import com.tuniu.ord.vo.PeopleTouristVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author fangzhongwei
 * 
 */
@Controller
@RequestMapping("/rest/tourist")
public class PeopleTouristController {

    @Resource
    private PeopleTouristService peopleTouristServiceImpl;

    @RequestMapping(path = "/list/query", method = { RequestMethod.GET })
    public void getTouristList(PeopleTouristVo peopleTouristVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("peopleTouristVo", peopleTouristVo);

        ResponseVo responseVo = new ResponseVo();
        if (CollectionUtils.isNotEmpty(peopleTouristVo.getSellOrderIds())) {
            List<OrdPeopleTourist> ordPeopleTouristList = peopleTouristServiceImpl.getOrdSalesPrice(peopleTouristVo);
            if (CollectionUtils.isNotEmpty(ordPeopleTouristList)) {
                for (OrdPeopleTourist ordPeopleTour : ordPeopleTouristList) {
                    if (ordPeopleTour.getPsptType() != null && !"".equals(ordPeopleTour.getPsptType())) {
                        ordPeopleTour.setPsptType(CardTypeEnum.getNameById(Integer.valueOf(ordPeopleTour.getPsptType())));
                    }
                }

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("count", Integer.valueOf(ordPeopleTouristList.size()));
                map.put("rows", ordPeopleTouristList);
                responseVo.setData(map);
            }
        }

        RestUtil.write(response, responseVo);
    }

}
