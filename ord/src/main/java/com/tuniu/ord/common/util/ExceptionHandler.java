package com.tuniu.ord.common.util;

import javax.servlet.http.HttpServletResponse;

import com.tuniu.ord.core.exception.SaaSApplicationException;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author fangzhongwei
 * 
 */
public final class ExceptionHandler {

    private ExceptionHandler() {

    }

    public static void execute(SaaSApplicationException e, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        SpringMVCUtil.addMessage(e.getMessageKey(), e.getMessageParams(), responseVo);
        RestUtil.write(response, responseVo);
    }

    public static void execute(SaaSSystemException e, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setSuccess(false);
        responseVo.setMsg(e.getMessage());
        RestUtil.write(response, responseVo);
    }

}
