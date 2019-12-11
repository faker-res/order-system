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
import com.tuniu.ord.service.CheckLossDetailService;
import com.tuniu.ord.service.CheckLossService;
import com.tuniu.ord.service.CheckLossTouristService;
import com.tuniu.ord.vo.CheckLossDetailShowVo;
import com.tuniu.ord.vo.CheckLossDetailUpdateVo;
import com.tuniu.ord.vo.CheckLossIdParamVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 
 * @author guojun3
 * 
 */
@Controller
@RequestMapping("/rest/checklossdetail")
public class CheckLossDetailController {

    private static Logger logger = LoggerFactory.getLogger(CheckLossDetailController.class);

    @Resource
    private CheckLossDetailService checkLossDetailServiceImpl;

    @Resource
    private CheckLossService checkLossServiceImpl;

    @Resource
    private CheckLossTouristService checkLossTouristServiceImpl;

    /**
     * 核损处理（修改核损详情信息）
     * 
     * @param request
     * @param response
     */
    @RequestMapping(path = "/update-checklossdetail", method = RequestMethod.POST)
    public void updateCheckLossDetail(CheckLossDetailUpdateVo checkLossDetailUpdateVo, HttpServletRequest request,
            HttpServletResponse response) {
        logger.debug("*************checkLossDetailUpdateVo=" + JsonUtil.toString(checkLossDetailUpdateVo));
        boolean flag = checkLossDetailServiceImpl.updateCheckLossDetail(checkLossDetailUpdateVo);
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(checkLossDetailUpdateVo);
        responseVo.setSuccess(flag);
        RestUtil.write(response, responseVo);
    }

    /**
     * 根据核损id查询核损详情
     * 
     * @param request
     * @param response
     */
    @RequestMapping(path = "/query-checklossdetail-by-id", method = { RequestMethod.GET, RequestMethod.POST })
    public void queryCheckLossDetailById(CheckLossIdParamVo input, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("*************checkLossIdParam=" + JsonUtil.toString(input));

        CheckLossDetailShowVo checkLossDetailShowVo = checkLossDetailServiceImpl.queryCheckLossDetailById(input);

        ResponseVo responseVo = new ResponseVo();

        responseVo.setData(checkLossDetailShowVo);
        RestUtil.write(response, responseVo);

    }

    /**
     * 核损反馈
     * 
     * @param request
     * @param response
     */
    @RequestMapping(path = "/resp-checklossdetail-to-api", method = RequestMethod.POST)
    public void respCheckLossDetailToAPI(CheckLossIdParamVo input, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("*************checkLossIdParam=" + JsonUtil.toString(input));
        ResponseVo responseVo = new ResponseVo();
        try {
            checkLossDetailServiceImpl.respCheckLossDetailToAPI(input);
        } catch (Exception e) {
            logger.error("核损反馈错误", e);
            responseVo.setData(e.getMessage());
            responseVo.setSuccess(false);
        }
        RestUtil.write(response, responseVo);
    }

}
