package com.tuniu.ord.core.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Session;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.CometEventImpl;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.mapper.MappingData;

/**
 * 包装Request，重新设置ajax请求参数到request的parameter中
 * 
 * @author fangzhongwei
 * 
 */
public class ParametersRequestWrapper extends Request {

    private final Request request;

    private final Map<String, String[]> params;

    public ParametersRequestWrapper(Request request, Map<String, String[]> params) {
        super();
        this.request = request;
        this.params = params;
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        return null == values ? null : values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Enumeration<String> getParameterNames() {
        Vector vector = new Vector(params.keySet());
        return vector.elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    @Override
    public void addCookie(Cookie arg0) {

        request.addCookie(arg0);
    }

    @Override
    public void addHeader(String name, String value) {

        request.addHeader(name, value);
    }

    @Override
    public void addLocale(Locale locale) {

        request.addLocale(locale);
    }

    @Override
    public void addParameter(String name, String[] values) {

        request.addParameter(name, values);
    }

    @Override
    public void changeSessionId(String arg0) {

        request.changeSessionId(arg0);
    }

    @Override
    public void clearCookies() {

        request.clearCookies();
    }

    @Override
    public void clearEncoders() {

        request.clearEncoders();
    }

    @Override
    public void clearHeaders() {

        request.clearHeaders();
    }

    @Override
    public void clearLocales() {

        request.clearLocales();
    }

    @Override
    public void clearParameters() {

        request.clearParameters();
    }

    @Override
    public void cometClose() {

        request.cometClose();
    }

    @Override
    public ServletInputStream createInputStream() throws IOException {

        return request.createInputStream();
    }

    @Override
    public void finishRequest() throws IOException {

        request.finishRequest();
    }

    @Override
    public Object getAttribute(String name) {

        return request.getAttribute(name);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getAttributeNames() {

        return request.getAttributeNames();
    }

    @Override
    public String getAuthType() {

        return request.getAuthType();
    }

    @Override
    public boolean getAvailable() {

        return request.getAvailable();
    }

    @Override
    public String getCharacterEncoding() {

        return request.getCharacterEncoding();
    }

    @Override
    public Connector getConnector() {

        return request.getConnector();
    }

    @Override
    public int getContentLength() {

        return request.getContentLength();
    }

    @Override
    public String getContentType() {

        return request.getContentType();
    }

    @Override
    public Context getContext() {

        return request.getContext();
    }

    @Override
    public String getContextPath() {

        return request.getContextPath();
    }

    @Override
    public MessageBytes getContextPathMB() {

        return request.getContextPathMB();
    }

    @Override
    public Cookie[] getCookies() {

        return request.getCookies();
    }

    @Override
    public org.apache.coyote.Request getCoyoteRequest() {

        return request.getCoyoteRequest();
    }

    @Override
    public long getDateHeader(String name) {

        return request.getDateHeader(name);
    }

    @Override
    public String getDecodedRequestURI() {

        return request.getDecodedRequestURI();
    }

    @Override
    public MessageBytes getDecodedRequestURIMB() {

        return request.getDecodedRequestURIMB();
    }

    @Override
    public CometEventImpl getEvent() {

        return request.getEvent();
    }

    @Override
    public FilterChain getFilterChain() {

        return request.getFilterChain();
    }

    @Override
    public String getHeader(String name) {

        return request.getHeader(name);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getHeaderNames() {

        return request.getHeaderNames();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getHeaders(String name) {

        return request.getHeaders(name);
    }

    @Override
    public Host getHost() {

        return request.getHost();
    }

    @Override
    public String getInfo() {

        return request.getInfo();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        return request.getInputStream();
    }

    @Override
    public int getIntHeader(String name) {

        return request.getIntHeader(name);
    }

    @Override
    public String getLocalAddr() {

        return request.getLocalAddr();
    }

    @Override
    public String getLocalName() {

        return request.getLocalName();
    }

    @Override
    public int getLocalPort() {

        return request.getLocalPort();
    }

    @Override
    public Locale getLocale() {

        return request.getLocale();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getLocales() {

        return request.getLocales();
    }

    @Override
    public MappingData getMappingData() {

        return request.getMappingData();
    }

    @Override
    public String getMethod() {

        return request.getMethod();
    }

    @Override
    public Object getNote(String name) {

        return request.getNote(name);
    }

    @Override
    public String getPathInfo() {

        return request.getPathInfo();
    }

    @Override
    public MessageBytes getPathInfoMB() {

        return request.getPathInfoMB();
    }

    @Override
    public String getPathTranslated() {

        return request.getPathTranslated();
    }

    @Override
    public Principal getPrincipal() {

        return request.getPrincipal();
    }

    @Override
    public String getProtocol() {

        return request.getProtocol();
    }

    @Override
    public String getQueryString() {

        return request.getQueryString();
    }

    @Override
    public BufferedReader getReader() throws IOException {

        return request.getReader();
    }

    @SuppressWarnings("deprecation")
    @Override
    public String getRealPath(String arg0) {

        return request.getRealPath(arg0);
    }

    @Override
    public String getRemoteAddr() {

        return request.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {

        return request.getRemoteHost();
    }

    @Override
    public int getRemotePort() {

        return request.getRemotePort();
    }

    @Override
    public String getRemoteUser() {

        return request.getRemoteUser();
    }

    @Override
    public HttpServletRequest getRequest() {

        return request.getRequest();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {

        return request.getRequestDispatcher(path);
    }

    @Override
    public MessageBytes getRequestPathMB() {

        return request.getRequestPathMB();
    }

    @Override
    public String getRequestURI() {

        return request.getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {

        return request.getRequestURL();
    }

    @Override
    public String getRequestedSessionId() {

        return request.getRequestedSessionId();
    }

    @Override
    public Response getResponse() {

        return request.getResponse();
    }

    @Override
    public String getScheme() {

        return request.getScheme();
    }

    @Override
    public String getServerName() {

        return request.getServerName();
    }

    @Override
    public int getServerPort() {

        return request.getServerPort();
    }

    @Override
    public String getServletPath() {

        return request.getServletPath();
    }

    @Override
    public MessageBytes getServletPathMB() {

        return request.getServletPathMB();
    }

    @Override
    public HttpSession getSession() {

        return request.getSession();
    }

    @Override
    public HttpSession getSession(boolean create) {

        return request.getSession(create);
    }

    @Override
    public Session getSessionInternal() {

        return request.getSessionInternal();
    }

    @Override
    public Session getSessionInternal(boolean create) {

        return request.getSessionInternal(create);
    }

    @Override
    public InputStream getStream() {

        return request.getStream();
    }

    @Override
    public Principal getUserPrincipal() {

        return request.getUserPrincipal();
    }

    @Override
    public Wrapper getWrapper() {

        return request.getWrapper();
    }

    @Override
    public boolean isComet() {

        return request.isComet();
    }

    @Override
    public boolean isParametersParsed() {

        return request.isParametersParsed();
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {

        return request.isRequestedSessionIdFromCookie();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {

        return request.isRequestedSessionIdFromURL();
    }

    @Override
    public boolean isRequestedSessionIdValid() {

        return request.isRequestedSessionIdValid();
    }

    @Override
    public boolean isSecure() {

        return request.isSecure();
    }

    @Override
    public boolean isUserInRole(String arg0) {

        return request.isUserInRole(arg0);
    }

    @Override
    public boolean read() throws IOException {

        return request.read();
    }

    @Override
    public void recycle() {

        request.recycle();
    }

    @Override
    public void removeAttribute(String arg0) {

        request.removeAttribute(arg0);
    }

    @Override
    public void removeNote(String name) {

        request.removeNote(name);
    }

    @Override
    public void setAttribute(String arg0, Object arg1) {

        request.setAttribute(arg0, arg1);
    }

    @Override
    public void setAuthType(String type) {

        request.setAuthType(type);
    }

    @Override
    public void setCharacterEncoding(String enc) throws UnsupportedEncodingException {

        request.setCharacterEncoding(enc);
    }

    @Override
    public void setComet(boolean comet) {

        request.setComet(comet);
    }

    @Override
    public void setCometTimeout(long timeout) {

        request.setCometTimeout(timeout);
    }

    @Override
    public void setConnector(Connector connector) {

        request.setConnector(connector);
    }

    @Override
    public void setContentLength(int length) {

        request.setContentLength(length);
    }

    @Override
    public void setContentType(String type) {

        request.setContentType(type);
    }

    @Override
    public void setContext(Context context) {

        request.setContext(context);
    }

    @Override
    public void setContextPath(String path) {

        request.setContextPath(path);
    }

    @Override
    public void setCookies(Cookie[] cookies) {

        request.setCookies(cookies);
    }

    @Override
    public void setCoyoteRequest(org.apache.coyote.Request coyoteRequest) {

        request.setCoyoteRequest(coyoteRequest);
    }

    @Override
    public void setDecodedRequestURI(String uri) {

        request.setDecodedRequestURI(uri);
    }

    @Override
    public void setFilterChain(FilterChain filterChain) {

        request.setFilterChain(filterChain);
    }

    @Override
    public void setHost(Host host) {

        request.setHost(host);
    }

    @Override
    public void setMethod(String method) {

        request.setMethod(method);
    }

    @Override
    public void setNote(String name, Object value) {

        request.setNote(name, value);
    }

    @Override
    public void setPathInfo(String path) {

        request.setPathInfo(path);
    }

    @Override
    public void setProtocol(String protocol) {

        request.setProtocol(protocol);
    }

    @Override
    public void setQueryString(String query) {

        request.setQueryString(query);
    }

    @Override
    public void setRemoteAddr(String remoteAddr) {

        request.setRemoteAddr(remoteAddr);
    }

    @Override
    public void setRemoteHost(String remoteHost) {

        request.setRemoteHost(remoteHost);
    }

    @Override
    public void setRequestURI(String uri) {

        request.setRequestURI(uri);
    }

    @Override
    public void setRequestedSessionCookie(boolean flag) {

        request.setRequestedSessionCookie(flag);
    }

    @Override
    public void setRequestedSessionId(String id) {

        request.setRequestedSessionId(id);
    }

    @Override
    public void setRequestedSessionURL(boolean flag) {

        request.setRequestedSessionURL(flag);
    }

    @Override
    public void setResponse(Response response) {

        request.setResponse(response);
    }

    @Override
    public void setScheme(String scheme) {

        request.setScheme(scheme);
    }

    @Override
    public void setSecure(boolean secure) {

        request.setSecure(secure);
    }

    @Override
    public void setServerName(String name) {

        request.setServerName(name);
    }

    @Override
    public void setServerPort(int port) {

        request.setServerPort(port);
    }

    @Override
    public void setServletPath(String path) {

        request.setServletPath(path);
    }

    @Override
    public void setStream(InputStream stream) {

        request.setStream(stream);
    }

    @Override
    public void setUserPrincipal(Principal arg0) {

        request.setUserPrincipal(arg0);
    }

    @Override
    public void setWrapper(Wrapper wrapper) {

        request.setWrapper(wrapper);
    }

}
