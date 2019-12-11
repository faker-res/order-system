package com.tuniu.ord.core.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.tuniu.ord.common.util.ExceptionHandler;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.core.Logging.LogMessageIdentifier;
import com.tuniu.ord.core.exception.SaaSApplicationException;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 捕获RuntimeException，记录日志
 * 
 * @author fangzhongwei
 * 
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Log4jLogger LOG = LogFactory.getLogger(ExceptionResolver.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.error("程序异常", ex);
        if (ex instanceof SaaSApplicationException) {
            ExceptionHandler.execute((SaaSApplicationException) ex, response);

        } else if (ex instanceof SaaSSystemException) {
            LOG.error(Log4jLogger.SYSTEM_LOG, LogMessageIdentifier.ERROR_RUNTIME_EXCEPTION, ex, (String[]) null);
            ExceptionHandler.execute((SaaSSystemException) ex, response);

        } else {
            LOG.error(Log4jLogger.SYSTEM_LOG, LogMessageIdentifier.ERROR_RUNTIME_EXCEPTION, ex, (String[]) null);
            ResponseVo responseVo = new ResponseVo();
            responseVo.setSuccess(false);
            responseVo.setMsg("系统异常！");
            RestUtil.write(response, responseVo);
        }
        return null;
    }

}
