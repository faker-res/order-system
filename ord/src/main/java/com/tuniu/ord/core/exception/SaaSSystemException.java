package com.tuniu.ord.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 运行时业务异常基类
 * 
 * @author fangzhongwei
 * 
 */
public class SaaSSystemException extends RuntimeException {

    private static final long serialVersionUID = 7905248641311707088L;

    /**
     * 唯一标识异常
     */
    private String id;

    /**
     * 异常堆栈信息的字符串表示
     */
    private String causeStackTrace;

    public SaaSSystemException() {
        super();
        this.genId();
    }

    public SaaSSystemException(String message) {
        super(message);
        this.genId();
    }

    public SaaSSystemException(Throwable cause) {
        super(cause);
        this.genId();
        this.setCauseStackTrace(cause);
    }

    public SaaSSystemException(String message, Throwable cause) {
        super(message, cause);
        this.genId();
        this.setCauseStackTrace(cause);
    }

    private void genId() {
        setId(Long.valueOf(System.currentTimeMillis() % Long.MAX_VALUE).toString());
    }

    private void setCauseStackTrace(Throwable cause) {
        if (null != cause) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            cause.printStackTrace(pw);
            setCauseStackTrace(sw.getBuffer().toString());
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCauseStackTrace() {
        return causeStackTrace;
    }

    public void setCauseStackTrace(String causeStackTrace) {
        this.causeStackTrace = causeStackTrace;
    }

    @Override
    public String toString() {
        return "EXCEPTIONID  " + getId() + " :" + super.getMessage();
    }

}
