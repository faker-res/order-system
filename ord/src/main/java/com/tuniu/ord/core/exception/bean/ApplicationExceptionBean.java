package com.tuniu.ord.core.exception.bean;

import java.io.Serializable;

/**
 * @author fangzhongwei
 * 
 */
public class ApplicationExceptionBean implements Serializable {

    private static final long serialVersionUID = 416052386003831875L;

    private String id;
    private String causeStackTrace;

    /**
     * 提示信息key
     */
    private String messageKey;

    /**
     * 提示信息参数
     */
    private String[] messageParams;

    public ApplicationExceptionBean() {

    }

    public ApplicationExceptionBean(ApplicationExceptionBean bean) {
        this.setId(bean.getId());
        this.setCauseStackTrace(bean.getCauseStackTrace());
        this.setMessageKey(bean.getMessageKey());
        this.setMessageParams(bean.getMessageParams());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCauseStackTrace() {
        return causeStackTrace;
    }

    public void setCauseStackTrace(String causeStackTrace) {
        this.causeStackTrace = causeStackTrace;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String[] getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(String[] messageParams) {
        this.messageParams = messageParams;
    }

}
