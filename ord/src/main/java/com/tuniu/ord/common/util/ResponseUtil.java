/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import com.tuniu.ord.common.constant.ProductResultCode;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 用来生成成功或失败的responsevo对象
 * 
 * @author zhoujie8
 * 
 */
public class ResponseUtil {
    public static ResponseVo buildSuccess(Object data) {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(true);
        vo.setData(data);
        return vo;
    }

    public static ResponseVo buildFailure(OrdCustomException e) {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(false);
        vo.setErrorCode(e.getErrCode());
        vo.setMsg(e.getMessage());
        return vo;
    }
    
    public static ResponseVo buildFailure(OrdError err) {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(false);
        vo.setErrorCode(err.getCode());
        vo.setMsg(err.getMsg());
        return vo;
    }

    public static ResponseVo buildSuccess() {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(true);
        return vo;
    }

    public static ResponseVo buildFailure(Exception e) {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(false);
        vo.setErrorCode(ProductResultCode.UNKNOW_FAIL_CODE.getKey());
        vo.setMsg(e.getMessage());
        return vo;
    }
}
