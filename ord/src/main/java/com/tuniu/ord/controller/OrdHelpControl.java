package com.tuniu.ord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/api/help")
public class OrdHelpControl {
    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @RequestMapping(value = "/update-people-tourist", method = RequestMethod.POST)
    public void updatePeopleTourist(OrdPeopleTourist ordPeopleTourist, HttpServletRequest request,
            HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        try {
            ordPeopleTouristMapper.updateByPrimaryKeySelective(ordPeopleTourist);
        } catch (Exception e) {
            responseVo.setSuccess(false);
        }
        RestUtil.write(response, responseVo);
    }
}
