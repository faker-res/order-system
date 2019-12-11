package com.tuniu.operation.framework.base.rest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.tuniu.operation.framework.base.util.SystemConstants;

/**
 * @author fangzhongwei
 * 
 */
public class RestClient {

    private String serverUrl;
    private String httpMethod;
    private String clientData;
    private int connectTimeout;
    private int socketTimeout;

    public RestClient(String serverUrl) {
        this(serverUrl, SystemConstants.HTTP_GET, null, -1, -1);
    }

    public RestClient(String serverUrl, String clientData) {
        this(serverUrl, SystemConstants.HTTP_GET, clientData, -1, -1);
    }

    public RestClient(String serverUrl, String httpMethod, String clientData) {
        this(serverUrl, httpMethod, clientData, -1, -1);
    }

    public RestClient(String serverUrl, String httpMethod, String clientData, int connectTimeout, int socketTimeout) {
        this.serverUrl = serverUrl;
        this.httpMethod = httpMethod;
        this.clientData = clientData;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    /**
     * 发送rest请求到server端处理并返回结果
     * 
     * @return 请求返回的json字符串
     * @throws RestException
     */
    public String execute() throws RestException {
        if (null == this.serverUrl) {
            return null;
        }
        this.clientData = RestCodec.encodeBase64(this.clientData);
        String ret = httpExecute();
        return RestCodec.decodeBase64(ret);
    }
    
    public String executeToAPI() throws RestException {
        if (null == this.serverUrl) {
            return null;
        }
        String ret = httpExecuteToAPI();
        return ret;
    }
    

    private String httpExecute() throws RestException {
        DefaultHttpClient httpClient = getDefaultHttpClient();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String ret = null;

        try {
            if (SystemConstants.HTTP_GET.equalsIgnoreCase(this.httpMethod)) {
                HttpGet httpGet = new HttpGet(getQueryUrl());
                ret = httpClient.execute(httpGet, responseHandler);

            } else if (SystemConstants.HTTP_POST.equalsIgnoreCase(this.httpMethod)) {
                HttpPost httpPost = new HttpPost(this.serverUrl);
                if (null != this.clientData) {
                    httpPost.setEntity(new StringEntity(this.clientData));
                }
                ret = httpClient.execute(httpPost, responseHandler);

            } else if (SystemConstants.HTTP_PUT.equalsIgnoreCase(this.httpMethod)) {
                HttpPut httpPut = new HttpPut(this.serverUrl);
                httpPut.setEntity(new StringEntity(this.clientData));
                ret = httpClient.execute(httpPut, responseHandler);
            } else if (SystemConstants.HTTP_DELETE.equalsIgnoreCase(this.httpMethod)) {
                HttpDelete httpDelete = new HttpDelete(getQueryUrl());
                ret = httpClient.execute(httpDelete, responseHandler);
            }
        } catch (ClientProtocolException e) {
            throw RestException.withError(e);
        } catch (IOException e) {
            throw RestException.withError(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return ret;
    }
    
    private String httpExecuteToAPI() throws RestException {
        DefaultHttpClient httpClient = getDefaultHttpClient();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String ret = null;

        try {
            if (SystemConstants.HTTP_GET.equalsIgnoreCase(this.httpMethod)) {
                HttpGet httpGet = new HttpGet(getQueryUrl());
                ret = httpClient.execute(httpGet, responseHandler);

            } else if (SystemConstants.HTTP_POST.equalsIgnoreCase(this.httpMethod)) {
                HttpPost httpPost = new HttpPost(this.serverUrl);
                if (null != this.clientData) {
                    httpPost.setEntity(new StringEntity(this.clientData,"UTF-8"));
                }
                ret = httpClient.execute(httpPost, responseHandler);

            } else if (SystemConstants.HTTP_PUT.equalsIgnoreCase(this.httpMethod)) {
                HttpPut httpPut = new HttpPut(this.serverUrl);
                httpPut.setEntity(new StringEntity(this.clientData,"UTF-8"));
                ret = httpClient.execute(httpPut, responseHandler);
            } else if (SystemConstants.HTTP_DELETE.equalsIgnoreCase(this.httpMethod)) {
                HttpDelete httpDelete = new HttpDelete(getQueryUrl());
                ret = httpClient.execute(httpDelete, responseHandler);
            }
        } catch (ClientProtocolException e) {
            throw RestException.withError(e);
        } catch (IOException e) {
            throw RestException.withError(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return ret;
    }

    private String getQueryUrl() {
        String url = serverUrl;
        if (null != clientData) {
            if (serverUrl.endsWith(SystemConstants.INTERROGATION_MARK)) {
                url += clientData;
            }
            url += SystemConstants.INTERROGATION_MARK + clientData;
        }
        return url;
    }

    private DefaultHttpClient getDefaultHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.protocol.content-charset", SystemConstants.UTF8_ENCODING);
        if (this.connectTimeout > 0) {
            httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(this.connectTimeout * 1000));
        }
        if (this.socketTimeout > 0) {
            httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(this.socketTimeout * 1000));
        }
        return httpClient;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

}
