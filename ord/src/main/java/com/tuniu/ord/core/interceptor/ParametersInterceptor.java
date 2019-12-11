package com.tuniu.ord.core.interceptor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.tomcat.util.http.Parameters;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.SpringMVCUtil;
import com.tuniu.ord.core.wrapper.ParametersRequestWrapper;

/**
 * 只拦截AJAX请求参数，解码并设置到Request中（文件上传除外）
 * <p>
 * 下面列出的参考类用于创建Controller中方法参数，并获取Request参数赋值给入参。
 * </p>
 * 
 * @author fangzhongwei
 * 
 * @see {@link org.springframework.web.method.annotation.ModelAttributeMethodProcessor#resolveArgument}
 * @see {@link org.springframework.web.bind.ServletRequestDataBinder#bind}
 */
public class ParametersInterceptor extends HandlerInterceptorAdapter {
    private List<String> uncheckUrls;

    public List<String> getUncheckUrls() {
        return uncheckUrls;
    }

    public void setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (uncheckUrls.contains(request.getRequestURI())) {
            return true;
        }

        if (SpringMVCUtil.isMultipart(request)) {
            return true;
        }

        Map<String, Object> inputMap = (Map<String, Object>) request.getAttribute(Constants.AJAX_PARAMETER);
        Map<String, String[]> outputMap = new HashMap<String, String[]>();
        resolveMap(null, inputMap, outputMap);

        Field field = getField(request, Constants.CATALINA_CONNECTOR_REQUEST);
        Request originalRequest = getOriginalRequest(request, field);

        org.apache.coyote.Request coyoteRequest = originalRequest.getCoyoteRequest();
        Parameters parameters = coyoteRequest.getParameters();
        for (String key : outputMap.keySet()) {
            String[] value = outputMap.get(key);
            if (null != key && null != value) {
                parameters.addParameterValues(key, value);
            }
        }

        return true;
    }

    /**
     * 递归解析请求参数
     * 
     * @param keyAncestor
     *            祖先key，用点号拼装
     * @param inputMap
     *            入参
     * @param outputMap
     *            出参
     */
    @SuppressWarnings("unchecked")
    private void resolveMap(String keyAncestor, Map<String, Object> inputMap, Map<String, String[]> outputMap) {
        if (null != inputMap) {
            for (String key : inputMap.keySet()) {
                Object value = inputMap.get(key);
                String currentKey = (null == keyAncestor ? key : keyAncestor + "." + key);

                if (value instanceof Map) {
                    resolveMap(currentKey, (Map<String, Object>) (value), outputMap);
                } else if (value instanceof List) {
                    resolveList(currentKey, (List<Object>) value, outputMap);
                } else {
                    outputMap.put(currentKey, null == value ? null : new String[] { value.toString() });
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void resolveList(String keyAncestor, List<Object> inputList, Map<String, String[]> outputMap) {
        if (CollectionUtils.isNotEmpty(inputList)) {
            for (int i = 0; i < inputList.size(); i++) {
                Object arrItem = inputList.get(i);
                String currentKey = keyAncestor + "[" + i + "]";

                if (arrItem instanceof Map) {
                    resolveMap(currentKey, (Map<String, Object>) arrItem, outputMap);
                } else if (arrItem instanceof List) {
                    resolveList(currentKey, (List<Object>) arrItem, outputMap);
                } else {
                    outputMap.put(currentKey, null == arrItem ? null : new String[] { arrItem.toString() });
                }
            }
        }
    }

    private Field getField(HttpServletRequest request, String name) throws NoSuchFieldException, SecurityException {
        return request.getClass().getDeclaredField(name);
    }

    private Request getOriginalRequest(HttpServletRequest request, Field field)
            throws SecurityException, IllegalArgumentException, IllegalAccessException {
        field.setAccessible(true);
        return (Request) field.get(request);
    }

    private void setWrapperRequest(HttpServletRequest request, Field field, ParametersRequestWrapper wrapper)
            throws IllegalArgumentException, IllegalAccessException {
        field.set(request, wrapper);
    }

}
