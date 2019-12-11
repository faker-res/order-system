package com.tuniu.ord.core.Logging;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 集合日志类：用于封装多种日志，e.g.系统日志、访问日志、审计日志等
 * 
 * @author fangzhongwei
 * 
 */
public class Log4jLogger {

    private final Locale locale;

    private static final String LOG_RESOURCE_BUNDLE_BASE_NAME = "logMessages";

    private static final String PREFIX_SYSTEM_LOG_NAME = "sys.";
    private static final String PREFIX_ACCESS_LOG_NAME = "acc.";

    public static final int SYSTEM_LOG = 1;

    public static final int ACCESS_LOG = 2;

    /**
     * 系统日志：本系统接口日志
     */
    Logger systemLog;

    /**
     * 访问日志：记录TSP和AMQ等日志
     */
    Logger accessLog;

    /**
     * logBack
     */
    org.slf4j.Logger logBack;

    Log4jLogger(Class<?> category, Locale locale) {
        this.locale = locale;
        /**
         * 调用Log4j的方法生成log，参数必须要区分，否则创建的log是相同的引用
         */
        systemLog = Logger.getLogger(PREFIX_SYSTEM_LOG_NAME + category.getName());
        systemLog.setAdditivity(false);
        accessLog = Logger.getLogger(PREFIX_ACCESS_LOG_NAME + category.getName());
        accessLog.setAdditivity(false);
        /** logBack初始化 **/
        logBack = LoggerFactory.getLogger(category);
    }

    public void debug(String text) {
        /*
         * if (systemLog.isDebugEnabled()) { text = addMsgIdToText(LogMessageIdentifier.DEBUG, text); systemLog.debug(text); }
         */
        logBack.info(text);
    }

    public void debug(int logTarget, String text) {
        /*
         * text = addMsgIdToText(LogMessageIdentifier.DEBUG, text); if (logToSystem(logTarget)) { if
         * (systemLog.isDebugEnabled()) { systemLog.debug(text); } } if (logToAccess(logTarget)) { if
         * (accessLog.isDebugEnabled()) { accessLog.debug(text); } }
         */

        /*
         * text = addMsgIdToText(LogMessageIdentifier.INFO_REGISTER_SUCCESS, text); if (logToSystem(logTarget)) { if
         * (systemLog.isInfoEnabled()) { systemLog.info(text); } } if (logToAccess(logTarget)) { if (accessLog.isInfoEnabled())
         * { accessLog.info(text); } }
         */
        logBack.info(text);
    }

    public void info(int logTarget, LogMessageIdentifier logMsgIdentifier, String... params) {
        /*
         * String text = getText(logMsgIdentifier, params);
         * 
         * if (logToSystem(logTarget)) { systemLog.info(text); } if (logToAccess(logTarget)) { accessLog.info(text); }
         */
        String text = transfer(params);
        logBack.info(text);
    }

    public void warn(int logTarget, LogMessageIdentifier logMsgIdentifier, String... params) {
        /*
         * String text = getText(logMsgIdentifier, params);
         * 
         * if (logToSystem(logTarget)) { systemLog.warn(text); } if (logToAccess(logTarget)) { accessLog.warn(text); }
         */
        String text = transfer(params);
        logBack.warn(text);
    }

    public void warn(int logTarget, Exception e, LogMessageIdentifier logMsgIdentifier) {
        /*
         * String text = getText(logMsgIdentifier, (String[]) null);
         * 
         * if (logToSystem(logTarget)) { systemLog.warn(text, e); } if (logToAccess(logTarget)) { accessLog.warn(text, e); }
         */
        String text = e.getMessage();
        logBack.warn(text);
    }

    public void warn(int logTarget, Exception e, LogMessageIdentifier logMsgIdentifier, String... params) {
        /*
         * String text = getText(logMsgIdentifier, params);
         * 
         * if (logToSystem(logTarget)) { systemLog.warn(text, e); } if (logToAccess(logTarget)) { accessLog.warn(text, e); }
         */
        String text = transfer(params);
        logBack.warn(text);
    }

    public void error(int logTarget, LogMessageIdentifier logMsgIdentifier, Exception e, String... params) {
        /*
         * String text = getText(logMsgIdentifier, params);
         * 
         * if (logToSystem(logTarget)) { systemLog.error(text, e); } if (logToAccess(logTarget)) { accessLog.error(text, e); }
         */
        String text = transfer(params);
        logBack.error(text);
    }

    /**
     * 解析合并
     * 
     * @param params
     * @return
     */
    public String transfer(String... params) {
        StringBuffer sb = new StringBuffer();
        if (params == null || params.length == 0) {
            sb.append("&");
        } else {
            for (int i = 0; i < params.length; i++) {
                sb.append(params[i] + "&");
            }
        }
        return sb.toString().substring(0, sb.toString().lastIndexOf("&"));
    }

    private boolean logToSystem(int logTarget) {
        return (logTarget & SYSTEM_LOG) > 0;
    }

    private boolean logToAccess(int logTarget) {
        return (logTarget & ACCESS_LOG) > 0;
    }

    private ResourceBundle getResourceBundle() {
        ResourceBundle resourcebundle = null;
        try {
            resourcebundle = ResourceBundle.getBundle(LOG_RESOURCE_BUNDLE_BASE_NAME, locale);
        } catch (MissingResourceException e) {
            resourcebundle = ResourceBundle.getBundle(LOG_RESOURCE_BUNDLE_BASE_NAME);
        }
        return resourcebundle;
    }

    private String getText(LogMessageIdentifier logMsgIdentifier, String... params) {
        String messageId = logMsgIdentifier.getMessageId();
        ResourceBundle resourceBundle = getResourceBundle();
        String text = null;
        try {
            text = resourceBundle.getString(messageId);
            if (null != params) {
                params = escapeParams(params);
                MessageFormat mf = new MessageFormat(text, this.locale);
                text = mf.format(params, new StringBuffer(), null).toString();
            }

            text = addMsgIdToText(logMsgIdentifier, text);
        } catch (MissingResourceException e) {
            text = "?? key '" + logMsgIdentifier.name() + " not found ??";
        }
        return text;
    }

    private String addMsgIdToText(LogMessageIdentifier logMsgIdentifier, String text) {
        return logMsgIdentifier.getMessageId() + " : " + text;
    }

    private String[] escapeParams(String... params) {
        if (null != params) {
            for (int i = 0; i < params.length; i++) {
                if (null != params[i]) {
                    params[i] = params[i].replaceAll("\n", "\\\n");
                }
            }
        }
        return params;
    }
}
