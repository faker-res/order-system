/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.proxy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.vo.Node;
import com.tuniu.ord.vo.ProBaseVo;
import com.tuniu.ord.vo.ProductIdParamVo;
import com.tuniu.ord.vo.QueryUpgradeInputVo;
import com.tuniu.ord.vo.ResProVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.TourBaseInputVo;
import com.tuniu.ord.vo.common.BaseVo;

/**
 * 【产品系统代理类】
 * 
 * @author zhairongping
 *
 */
@Service
public class PRDProxy {

    /**
     * 查询升级方案
     * 
     * @param confirmFeedBackInputVo
     * @return
     */
    public ResponseVo getUpgrade(QueryUpgradeInputVo queryUpgradeInputVo) {
        BaseVo.initTenantId(queryUpgradeInputVo);
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(queryUpgradeInputVo);
        String result = RestUtil.execute(SystemInitParameter.getUpgrade, SystemConstants.HTTP_GET, clientData);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("查询升级方案接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 针对订单系统查询产品信息
     * 
     * @param productIdParamVo
     * @return
     */
    public ResponseVo getProductInfoToOrd(ProductIdParamVo productIdParamVo) {
        BaseVo.initTenantId(productIdParamVo);
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(productIdParamVo);
        String result = RestUtil.execute(SystemInitParameter.getProductInfoToOrd, SystemConstants.HTTP_GET, clientData);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("针对订单系统查询产品信息接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 根据产品获取出游天数
     * 
     * @param productIdParamVo
     * @return
     */
    public ResponseVo getProductTourDay(ProductIdParamVo productIdParamVo) {
        BaseVo.initTenantId(productIdParamVo);
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(productIdParamVo);
        String result = RestUtil.execute(SystemInitParameter.productResAddress, SystemConstants.HTTP_POST, clientData);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("根据产品获取出游天数接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 根据产品获取出游天数
     * 
     * @param responseVo
     * @return
     */
    public String getTourDay(ResponseVo responseVo) {
        String tourDay = null;
        if (responseVo != null && responseVo.isSuccess() && responseVo.getData() != null) {
            List<Node> nodes = (List<Node>) responseVo.getData();
            for (int i = 0; i < nodes.size(); i++) {
                Node node = JsonUtil.toBean(JsonUtil.toString(nodes.get(i)), Node.class);
                // 地接
                if ("local_guiding".equals(node.getCode())) {
                    List<Node> subNodes = (List<Node>) node.getValue();
                    for (int j = 0; j < subNodes.size(); j++) {
                        Node subNode = JsonUtil.toBean(JsonUtil.toString(subNodes.get(j)), Node.class);
                        // 白天
                        if ("tour_day".equals(subNode.getCode())) {
                            tourDay = (String) subNode.getValue();
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return tourDay;
    }

    public String getProductInfo(ProductIdParamVo productVo) {
        BaseVo.initTenantId(productVo);
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(productVo);
        return RestUtil.execute(SystemInitParameter.getQueryProductInfo(), SystemConstants.HTTP_POST, clientData);
    }

    /**
     * 根据资源查询产品
     * 
     * @param resProVo
     * @return
     */
    public ResponseVo queryProByResId(ResProVo resProVo) {
        BaseVo.initTenantId(resProVo);
        ResponseVo responseVo = new ResponseVo();
        String result = RestUtil.execute(SystemInitParameter.queryProByResId, SystemConstants.HTTP_GET,
                JsonUtil.toString(resProVo));
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("根据资源查询产品接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 查询产品基本信息
     * 
     * @param proBaseVo
     * @return
     */
    public ResponseVo queryProByProId(ProBaseVo proBaseVo) {
        BaseVo.initTenantId(proBaseVo);
        ResponseVo responseVo = new ResponseVo();
        String result = RestUtil.execute(SystemInitParameter.queryProByProId, SystemConstants.HTTP_GET,
                JsonUtil.toString(proBaseVo));
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("查询产品基本信息接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 查询团信息
     * 
     * @param tourBaseInputVo
     * @return
     */
    public ResponseVo queryTour(TourBaseInputVo tourBaseInputVo) {
        BaseVo.initTenantId(tourBaseInputVo);
        ResponseVo responseVo = new ResponseVo();
        String result = RestUtil.execute(SystemInitParameter.queryTour, SystemConstants.HTTP_GET,
                JsonUtil.toString(tourBaseInputVo));
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("查询团信息接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

}
