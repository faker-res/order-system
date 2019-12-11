package com.tuniu.ord.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tuniu.ord.common.excel.TouristUpload;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.service.ManualTouristService;
import com.tuniu.ord.vo.AddTouristsVo;
import com.tuniu.ord.vo.BaseManualVo;
import com.tuniu.ord.vo.ManualTouristVo;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/rest/manual-order/tourist")
public class ManualTouristController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualTouristController.class);
    
    @Resource
    private ManualTouristService manualTouristService;
    
    @Resource
    private TouristUpload touristUpload;
    
    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    public void batchAddTourist(AddTouristsVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null || CollectionUtils.isEmpty(vo.getTourists())) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            manualTouristService.saveTourists(vo.getTourists());
        } catch (OrdCustomException e) {
            LOGGER.error("批量保存游客信息出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("批量保存游客信息出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/batchDel", method = RequestMethod.POST)
    public void batchDelTourist(AddTouristsVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null || CollectionUtils.isEmpty(vo.getTourists())) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            manualTouristService.batchRemoveTourist(vo.getTourists());
        } catch (OrdCustomException e) {
            LOGGER.error("批量删除出游人出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("批量删除出游人出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addTourist(ManualTouristVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            if(vo.getId() == null) {
                manualTouristService.checkTourist(vo);
            }
            manualTouristService.saveTourist(vo);
        } catch (OrdCustomException e) {
            LOGGER.error("保存游客信息出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("保存游客信息出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public void queryTourist(BaseManualVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        responseVo.setData(manualTouristService.queryTourists(vo.getManualOrderId()));
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteTourist(ManualTouristVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        manualTouristService.removeTourist(vo.getId());
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadTourist(@RequestParam MultipartFile touristFile, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        try {
            //取得上传的文件  
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            String tenantId = multipartRequest.getParameter("tenantId");
            if(tenantId == null || tenantId == "") {
                LOGGER.error("导入出游人出错:[Msg]:{}", "tenantId为空");
                responseVo = ResponseUtil.buildFailure(OrdError.TENANTID_NULL_ERROR);
                RestUtil.write(response, responseVo);
                return;
            }
            Integer manualOrderId = Integer.valueOf(multipartRequest.getParameter("manualOrderId"));
            if(manualOrderId == null) {
                LOGGER.error("导入出游人出错:[Msg]:{}", "manualOrderId为空");
                responseVo = ResponseUtil.buildFailure(OrdError.MANUALORDERID_NULL_ERROR);
                RestUtil.write(response, responseVo);
                return;
            }
            touristUpload.uploadTourist(tenantId, manualOrderId, touristFile);
        } catch (IOException e) {
            LOGGER.error("导入出游人出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(OrdError.UPLOAD_TOURIST_ERROR);
            RestUtil.write(response, responseVo);
            return;
        } catch (ParseException e) {
            LOGGER.error("导入出游人出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(OrdError.UPLOAD_TOURIST_ERROR);
            RestUtil.write(response, responseVo);
            return;
        } catch (Exception e) {
            LOGGER.error("导入出游人出错:[Msg]:{}", e);
            responseVo = ResponseUtil.buildFailure(OrdError.UPLOAD_TOURIST_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        RestUtil.write(response, responseVo);
    }
}
