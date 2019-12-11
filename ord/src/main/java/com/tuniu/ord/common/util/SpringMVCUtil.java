package com.tuniu.ord.common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.vo.ResponseVo;

/**
 * MVC工具类
 * 
 * @author fangzhongwei
 * 
 */
public final class SpringMVCUtil {

    private SpringMVCUtil() {
    }

    /**
     * 设置信息提示内容到ResponseVo中
     * 
     * @param messageKey
     * @param params
     * @param request
     */
    public static void addMessage(String messageKey, String[] params, ResponseVo responseVo) {
        String text = getText(messageKey, params);
        responseVo.setSuccess(false);
        responseVo.setMsg(text);
    }

    /**
     * 根据key和通配符参数获取提示信息
     * 
     * @param messageKey
     * @param params
     * @return String
     */
    public static String getText(String messageKey, String[] params) {
        ResourceBundle bundle = getResourceBundle(Constants.MSG_RES_BUNDLE_BASE_NAME);
        String text = "";
        try {
            text = bundle.getString(messageKey);
            params = escapeParams(params);
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(params, new StringBuffer(), null).toString();
        } catch (MissingResourceException e) {
            text = "?? key " + messageKey + " ?? not found.";
        }

        return text;
    }

    /**
     * 根据资源文件名称和默认区域语言创建资源绑定对象
     * 
     * @param baseName
     * @return ResourceBundle
     */
    public static ResourceBundle getResourceBundle(String baseName) {
        return getResourceBundle(baseName, Locale.getDefault());
    }

    /**
     * 根据资源文件名称和区域语言创建资源绑定对象
     * 
     * @param baseName
     * @param locale
     * @return ResourceBundle
     */
    public static ResourceBundle getResourceBundle(String baseName, Locale locale) {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(baseName, locale);
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle(baseName);
        }

        return bundle;
    }

    public static String[] escapeParams(String[] params) {
        if (null != params) {
            for (int i = 0; i < params.length; i++) {
                params[i] = escapeParam(params[i]);
            }
        }
        return params;
    }

    public static String escapeParam(String param) {
        if (null != param) {
            param.replaceAll("\n", "\\\\n");
        }
        return param;
    }

    /**
     * 判断请求是否是文件上传
     * 
     * @param request
     * @return boolean
     */
    public static boolean isMultipart(HttpServletRequest request) {
        if (!SystemConstants.HTTP_POST.equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String contentType = request.getContentType();
        return (null != contentType && contentType.toLowerCase().startsWith(Constants.MULTIPART_CONTENT_TYPE_PREFIX));
    }

    /**
     * 判断请求是否是ajax请求
     * 
     * @param request
     * @return boolean
     */
    public static boolean isAjaxReq(HttpServletRequest request) {
        return SystemConstants.HTTP_AJAX_REQUEST_HEADER_VALUE.equalsIgnoreCase(request
                .getHeader(SystemConstants.HTTP_AJAX_REQUEST_HEADER_NAME));
    }

}
