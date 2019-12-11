/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import com.tuniu.operation.platform.tsg.client.proxy.tsg.TSPCommonClient;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.core.exception.SaaSSystemException;

/**
 * @author zhairongping
 * 
 */
public class TspUtil {
    private static Log4jLogger logger = LogFactory.getLogger(TspUtil.class);

    private static TSPCommonClient tspCommonClient;

    public static TSPCommonClient getTspCommonClient() {
        return tspCommonClient;
    }

    public static void setTspCommonClient(TSPCommonClient tspCommonClient) {
        TspUtil.tspCommonClient = tspCommonClient;
    }

    public static String getTspResp(String serviceName, String data) {
        logger.debug("serviceName:" + serviceName + " data:" + data);
        String resp = null;
        try {
            resp = tspCommonClient.call(serviceName, data);
        } catch (Exception e) {
            logger.debug(Log4jLogger.SYSTEM_LOG, "【TSPCommonClient-" + serviceName + "调用出错】==========" + e.getMessage());
        }
        logger.debug("tsp response:" + resp);
        return resp;
    }

    public static String getTspResp(String serviceName, String data, String method) {
        logger.debug("serviceName:" + serviceName + " data:" + data + " method:" + method);
        String resp = null;
        try {
            resp = tspCommonClient.call(serviceName, data, method);
        } catch (Exception e) {
            logger.debug(Log4jLogger.SYSTEM_LOG, "【TSPCommonClient-" + serviceName + "调用出错】=========" + e.getMessage());
        }
        logger.debug("tsp response:" + resp);
        return resp;
    }

    public static String callTspService(String serviceName, String data, String method) {
        logger.debug("serviceName:" + serviceName + " data:" + data + " method:" + method);
        String resp = null;
        try {
            resp = tspCommonClient.call(serviceName, data, method);
        } catch (Exception e) {
            logger.debug(Log4jLogger.SYSTEM_LOG, "【TSPCommonClient-" + serviceName + "调用出错】=========" + e.getMessage());
        }
        logger.debug("tsp response:" + resp);

        if (resp == null) {
            throw new SaaSSystemException("调用TSP服务【" + serviceName + "】无返回值");
        }

        return resp;
    }

}
