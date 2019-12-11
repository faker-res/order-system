/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.proxy;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.TspUtil;
import com.tuniu.ord.core.datasource.TSPEnumUtil;
import com.tuniu.ord.vo.ProductStockVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.StockInputFSVo;
import com.tuniu.ord.vo.StockOccupyQueryVo;
import com.tuniu.ord.vo.StockOutQueryVo;

/**
 * D类产品库存代理类
 * 
 * @author zhairongping
 *
 */
@Service
public class StockProxy {

    /**
     * 签约出库接口
     * 
     * @param departDateContractSign
     * @return
     */
    public ResponseVo productContractSign(ProductStockVo productStockVo) {
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(productStockVo);
        String result = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_CONTRACT_SIGN), clientData,
                SystemConstants.HTTP_POST);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("调用D产品库存系统的签约出库接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 取消签约接口
     * 
     * @param productStockVo
     * @return
     */
    public ResponseVo productCancelContractSign(ProductStockVo productStockVo) {
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(productStockVo);
        String result = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_CANCEL_CONTRACT_SIGN), clientData,
                SystemConstants.HTTP_POST);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("调用D产品库存系统的取消签约接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 根据切位单号查询占位记录
     * 
     * @param stockOccupyQueryVo
     * @return
     */
    public ResponseVo queryOccupyInfo(StockOccupyQueryVo stockOccupyQueryVo) {
        ResponseVo responseVo = new ResponseVo();
        String param = JsonUtil.toString(stockOccupyQueryVo);
        String result = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO), param,
                SystemConstants.HTTP_GET);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("根据切位单号查询占位记录接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        // StockOccupyOutputVo stockOccupyOutputVo;
        return responseVo;
    }

    /**
     * 根据销售单号查询出库信息
     * 
     * @param stockOutQueryVo
     * @return
     */
    public ResponseVo queryStockOutInfo(StockOutQueryVo stockOutQueryVo) {
        ResponseVo responseVo = new ResponseVo();
        String param = JsonUtil.toString(stockOutQueryVo);
        String result = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.QUERY_STOCK_OUT_INFO), param,
                SystemConstants.HTTP_GET);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("根据销售单号查询出库信息接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        // StockOutOutputVo stockOutOutputVo;
        return responseVo;
    }

    /**
     * D产品入即占(FS)
     * 
     * @param stockInputFSVo
     * @return
     */
    public ResponseVo productOccupySynApply(StockInputFSVo stockInputFSVo) {
        ResponseVo responseVo = new ResponseVo();
        String result = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_OCCUPY_SYN_APPLY),
                JsonUtil.toString(stockInputFSVo), SystemConstants.HTTP_POST);
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("D产品入即占接口超时或者网络异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }
}
