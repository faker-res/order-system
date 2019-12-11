package com.tuniu.ord.core.Logging;

/**
 * @author fangzhongwei
 * 
 */
public enum LogMessageIdentifier {

    /**
     * Info级别日志
     */
    INFO_REGISTER_SUCCESS("10001"),

    /**
     * Warn级别日志
     */
    WARN_VALIDATION_FAILED("30001"),

    /**
     * Error级别日志
     */
    ERROR_LOGON_NAME_EXISTED("70001"),

    ERROR_RUNTIME_EXCEPTION("70002"),

    DEBUG("00000");

    private String messageId;

    private LogMessageIdentifier(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}
