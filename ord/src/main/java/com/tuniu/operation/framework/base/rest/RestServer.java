package com.tuniu.operation.framework.base.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuniu.operation.framework.base.util.SystemConstants;

/**
 * @author fangzhongwei
 * 
 */
public class RestServer {

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    public RestServer(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String getRestData() throws RestException {
        if (null == request) {
            return null;
        }

        String httpMethod = request.getMethod();
        String ret = null;
        if (SystemConstants.HTTP_GET.equalsIgnoreCase(httpMethod) || SystemConstants.HTTP_DELETE.equalsIgnoreCase(httpMethod)) {
            ret = request.getQueryString();
        } else {
            ret = getBodyData();
        }

        return RestCodec.decodeBase64(ret);
    }

    private String getBodyData() throws RestException {
        BufferedReader reader = null;
        StringBuilder buffer = new StringBuilder();
        String line;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (IOException e) {
            throw RestException.withError(e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw RestException.withError(e);
                }
            }
        }
    }

    public boolean sendRestData(String data) throws RestException {
        if (null == response) {
            return false;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding(SystemConstants.UTF8_ENCODING);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(RestCodec.encodeBase64(data));
            return true;
        } catch (IOException e) {
            throw RestException.withError(e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

}
