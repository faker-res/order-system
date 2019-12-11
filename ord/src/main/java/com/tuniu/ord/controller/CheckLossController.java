package com.tuniu.ord.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.core.Logging.LogMessageIdentifier;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.core.exception.ValidationException;
import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.service.CheckLossDetailService;
import com.tuniu.ord.service.CheckLossService;
import com.tuniu.ord.service.CheckLossTouristService;
import com.tuniu.ord.service.impl.CheckLossServiceThread;
import com.tuniu.ord.vo.CheckLossQueryVo;
import com.tuniu.ord.vo.CheckLossReqVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 
 * @author guojun3
 * 
 */
@Controller
@RequestMapping("/rest/checkloss")
public class CheckLossController {
    private static Log4jLogger logger = LogFactory.getLogger(CheckLossController.class);

    @Resource
    private CheckLossService checkLossServiceImpl;

    @Resource
    private CheckLossDetailService checkLossDetailServiceImpl;

    @Resource
    private CheckLossTouristService checkLossTouristServiceImpl;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 获取核损列表
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    @RequestMapping(path = "/list/query", method = { RequestMethod.GET })
    public void getCheckLossList(CheckLossQueryVo checkLossQueryVo, HttpServletRequest request, HttpServletResponse response)
            throws JsonGenerationException, JsonMappingException, IOException {
        ResponseVo responseVo = new ResponseVo();
        try {

            logger.debug("checkLossQueryVo:" + JsonUtil.toJsonAsString(checkLossQueryVo));
            // 判断接收值是否为null
            ArgumentValidator.isNotNull("checkLossQueryVo", checkLossQueryVo);
            ArgumentValidator.isPositiveNumber("start", checkLossQueryVo.getStart().longValue());
            ArgumentValidator.isPositiveAndNonZeroNumber("limit", checkLossQueryVo.getLimit().longValue());

            // 获取核损列表
            List<CheckLoss> checkLossList = checkLossServiceImpl.getCheckLossList(checkLossQueryVo);

            // 判断核损列表是否为空
            if (CollectionUtils.isNotEmpty(checkLossList)) {
                Map<String, Object> map = new HashMap<String, Object>();

                // 获取核损列表数量
                map.put("count", Integer.valueOf(checkLossServiceImpl.count(checkLossQueryVo)));
                map.put("rows", checkLossList);
                responseVo.setData(map);
            }
        } catch (ValidationException e) {
            logger.error(Integer.parseInt(LogMessageIdentifier.ERROR_RUNTIME_EXCEPTION.getMessageId()),
                    LogMessageIdentifier.ERROR_RUNTIME_EXCEPTION, e, e.getMessageParams());
        } finally {
            RestUtil.write(response, responseVo);
        }
    }

    /**
     * 接收API发送D类核损信息处理
     * 
     * @param request
     * @param response
     */
    @RequestMapping(path = "/do-checkloss", method = RequestMethod.POST)
    public void doCheckLossByAPI(CheckLossReqVo checkLossReqVo, HttpServletRequest request, HttpServletResponse response) {
        logger.debug(Log4jLogger.SYSTEM_LOG, "接受核损请求参数：" + JsonUtil.toString(checkLossReqVo));

        ResponseVo responseVo = new ResponseVo();
        responseVo.setMsg("成功");
        responseVo.setSuccess(true);
        responseVo.setErrorCode(241000);
        responseVo.setData("");
        try {
            executorService
                    .execute(new CheckLossServiceThread(checkLossServiceImpl, checkLossReqVo, DataSourceSwitch.getTenantId()));
        } catch (Exception e) {
            logger.error(Integer.parseInt(LogMessageIdentifier.ERROR_RUNTIME_EXCEPTION.getMessageId()),
                    LogMessageIdentifier.ERROR_RUNTIME_EXCEPTION, e, e.getMessage());
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }

        RestUtil.write(response, responseVo);
    }
}
