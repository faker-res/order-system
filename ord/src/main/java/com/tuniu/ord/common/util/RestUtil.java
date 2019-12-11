package com.tuniu.ord.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.rest.RestClient;
import com.tuniu.operation.framework.base.rest.RestCodec;
import com.tuniu.operation.framework.base.rest.RestException;
import com.tuniu.operation.framework.base.rest.RestServer;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author fangzhongwei
 * 
 */
public final class RestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(RestUtil.class);

    private RestUtil() {
    }

    public static void write(HttpServletResponse response, String data) {
        setRespHeader(response);
        try {
            PrintWriter writer = getWriter(response);
            writer.write(RestCodec.encodeBase64(data));
            flush(writer);
        } catch (IOException e) {
            LOG.error("Write response data failed.", e);
        }
    }

    public static void write(HttpServletResponse response, ResponseVo responseVo) {
        setRespHeader(response);
        try {
            PrintWriter writer = getWriter(response);
            writer.write(RestCodec.encodeBase64(JsonUtil.toString(responseVo)));
            flush(writer);
        } catch (IOException e) {
            LOG.error("Write response data failed.", e);
        }
    }

    public static void print(HttpServletResponse response, String data) {
        setRespHeader(response);
        try {
            PrintWriter writer = getWriter(response);
            writer.write(RestCodec.encodeBase64(data));
            flush(writer);
        } catch (IOException e) {
            LOG.error("Write response data failed.", e);
        }
    }

    public static String getRestData(HttpServletRequest request, HttpServletResponse response) {
        try {
            return new RestServer(request, response).getRestData();
        } catch (RestException e) {
            LOG.error("Get request rest data failed.", e);
            return null;
        }
    }

    public static void print(HttpServletResponse response, ResponseVo responseVo) {
        setRespHeader(response);
        try {
            PrintWriter writer = getWriter(response);
            writer.write(RestCodec.encodeBase64(JsonUtil.toString(responseVo)));
            flush(writer);
        } catch (IOException e) {
            LOG.error("Write response data failed.", e);
        }
    }

    public static String execute(String serverUrl) {
        return execute(serverUrl, null);
    }

    public static String execute(String serverUrl, String clientData) {
        return execute(serverUrl, SystemConstants.HTTP_GET, clientData);
    }

    public static String execute(String serverUrl, String httpMethod, String clientData) {
        return execute(serverUrl, httpMethod, clientData, Constants.REST_CONNCTION_TIMEOUT, Constants.REST_SOCKET_TIMEOUT);
    }

    public static String executeWithTenantId(String serverUrl, String httpMethod, String clientData) {
        String newCilentData = clientData.substring(0, clientData.length() - 1) + ",\"tenantId\":\""
                + DataSourceSwitch.getTenantId() + "\"}";
        return execute(serverUrl, httpMethod, newCilentData, Constants.REST_CONNCTION_TIMEOUT, Constants.REST_SOCKET_TIMEOUT);
    }

    public static String executeToAPI(String serverUrl, String httpMethod, String clientData) {
        return executeToAPI(serverUrl, httpMethod, clientData, Constants.REST_CONNCTION_TIMEOUT, Constants.REST_SOCKET_TIMEOUT);
    }

    public static String execute(String serverUrl, String httpMethod, String clientData, int connectTimeout,
            int socketTimeout) {
        LOG.info(
                "###=>Out Request Url:[{}], Http Method:[{}], Client Data:[{}], Connection Timeout:[{}], Socket Timeout:[{}]###",
                serverUrl, httpMethod, clientData, Integer.valueOf(connectTimeout), Integer.valueOf(socketTimeout));
        try {
            String value = new RestClient(serverUrl, httpMethod, clientData, connectTimeout, socketTimeout).execute();
            LOG.info("###<=Out Response value:[{}]###", value);
            return value;
        } catch (RestException e) {
            LOG.error("Call Url: [" + socketTimeout + "] failed.", e);
            return null;
        }
    }

    public static String executeToAPI(String serverUrl, String httpMethod, String clientData, int connectTimeout,
            int socketTimeout) {
        LOG.info(
                "###=>Out Request Url:[{}], Http Method:[{}], Client Data:[{}], Connection Timeout:[{}], Socket Timeout:[{}]###",
                serverUrl, httpMethod, clientData, Integer.valueOf(connectTimeout), Integer.valueOf(socketTimeout));
        try {
            String value = new RestClient(serverUrl, httpMethod, clientData, connectTimeout, socketTimeout).executeToAPI();
            LOG.info("###<=Out Response value:[{}]###", value);
            return value;
        } catch (RestException e) {
            LOG.error("Call Url: [" + socketTimeout + "] failed.", e);
            return null;
        }
    }

    private static PrintWriter getWriter(HttpServletResponse response) throws IOException {
        return response.getWriter();
    }

    private static void flush(PrintWriter writer) {
        writer.flush();
    }

    private static void setRespHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
    }
}
