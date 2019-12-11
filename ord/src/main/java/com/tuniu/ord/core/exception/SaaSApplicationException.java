package com.tuniu.ord.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.tuniu.ord.core.exception.bean.ApplicationExceptionBean;

/**
 * 非运行时业务异常基类
 * 
 * @author fangzhongwei
 * 
 */
public class SaaSApplicationException extends Exception {

    private static final long serialVersionUID = 7927164313067566406L;

    private static final String MESSAGE_PREFIX = "ex.";

    private static final String ENUM_PREFIX = "enum.";

    private ApplicationExceptionBean bean;

    public SaaSApplicationException() {
        super();
        init();
    }

    public SaaSApplicationException(Object[] params) {
        super();
        init(params);
    }

    public SaaSApplicationException(String message, Object[] params) {
        super(message);
        init(params);
    }

    public SaaSApplicationException(Throwable cause, Object[] params) {
        super(cause);
        init(params);
        this.setCauseStackTrace(cause);
    }

    public SaaSApplicationException(String message, Throwable cause, Object[] params) {
        super(message, cause);
        init(params);
        this.setCauseStackTrace(cause);
    }

    public SaaSApplicationException(String message, ApplicationExceptionBean bean) {
        super(message);
        this.bean = bean;
    }

    public SaaSApplicationException(String message, ApplicationExceptionBean bean, Throwable cause) {
        super(message, cause);
        this.bean = bean;
        this.setCauseStackTrace(cause);
    }

    private void init() {
        init(null);
    }

    private void init(Object[] parameters) {
        this.bean = new ApplicationExceptionBean();
        this.setId(Long.valueOf(System.currentTimeMillis() % Long.MAX_VALUE).toString());
        String className = this.getClass().getName();
        this.setMessageKey(MESSAGE_PREFIX + className.substring(className.lastIndexOf(".") + 1));
        this.setMessageParams(toStringArray(parameters));
    }

    private String[] toStringArray(Object[] parameters) {
        List<String> result = new ArrayList<String>();
        if (null != parameters) {
            for (Object p : parameters) {
                String s = (null == p ? null : String.valueOf(p));
                if (p instanceof Enum<?>) {
                    s = ENUM_PREFIX + p.getClass().getSimpleName() + "." + p;
                }

                result.add(s);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    protected static String[] escapeParams(String[] params) {
        if (null != params) {
            for (int i = 0; i < params.length; i++) {
                params[i] = escapeParam(params[i]);
            }
        }
        return params;
    }

    protected static String escapeParam(String param) {
        if (null != param) {
            param = param.replaceAll("\n", "\\\\n");
        }
        return param;
    }

    public String getId() {
        return bean.getId();
    }

    public void setId(String id) {
        bean.setId(id);
    }

    public String getCauseStackTrace() {
        return bean.getCauseStackTrace();
    }

    public void setCauseStackTrace(String causeStackTrace) {
        bean.setCauseStackTrace(causeStackTrace);
    }

    public void setCauseStackTrace(Throwable cause) {
        if (null != cause) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            cause.printStackTrace(pw);
            setCauseStackTrace(sw.getBuffer().toString());
        }
    }

    public String getMessageKey() {
        return bean.getMessageKey();
    }

    public void setMessageKey(String messageKey) {
        bean.setMessageKey(messageKey);
    }

    public String[] getMessageParams() {
        return bean.getMessageParams();
    }

    public void setMessageParams(String[] messageParams) {
        bean.setMessageParams(messageParams);
    }

    public ApplicationExceptionBean getBean() {
        return bean;
    }

    public void setBean(ApplicationExceptionBean bean) {
        this.bean = bean;
    }

    @Override
    public String getMessage() {
        return "EXCEPTIONID " + getId() + " :" + super.getMessage();
    }

}
