package com.tuniu.ord.core.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuniu.operation.framework.base.util.SystemConstants;

/**
 * 对非http请求和请求方法不是GET、POST、PUT和DELETE的HTTP请求进行拦截
 * 
 * @author fangzhongwei
 * 
 */
public class HttpMethodsFilter implements Filter {

    private final Set<String> allowedHttpMethods = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedHttpMethods.add(SystemConstants.HTTP_GET);
        allowedHttpMethods.add(SystemConstants.HTTP_POST);
        allowedHttpMethods.add(SystemConstants.HTTP_PUT);
        allowedHttpMethods.add(SystemConstants.HTTP_DELETE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            response.getWriter().print(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String method = httpServletRequest.getMethod();
        if (!allowedHttpMethods.contains(method)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, method + " is not allowed.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
