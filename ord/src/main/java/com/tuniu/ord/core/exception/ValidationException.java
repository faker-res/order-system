/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月18日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.exception;

import java.util.Arrays;

import com.tuniu.ord.core.exception.bean.ApplicationExceptionBean;
import com.tuniu.ord.core.exception.bean.ValidationExceptionBean;

/**
 * @author fangzhongwei
 * 
 */
public class ValidationException extends SaaSApplicationException {

    private static final long serialVersionUID = -1545643755823798120L;

    private final ValidationExceptionBean validationExceptionBean = new ValidationExceptionBean();

    public ValidationException() {
        super();
    }

    public ValidationException(Object[] params) {
        super(params);
    }

    public ValidationException(String message, ApplicationExceptionBean bean) {
        super(message, bean);
    }

    public ValidationException(String message, ApplicationExceptionBean bean, Throwable cause) {
        super(message, bean, cause);
    }

    public ValidationException(ReasonEnum reason, String member, Object[] params) {
        super(getMessage(reason, member, params), params);
        validationExceptionBean.setReason(reason);
        validationExceptionBean.setMember(member);
        setMessageKey(initMessageKey());
    }

    private static String getMessage(ReasonEnum reason, String member, Object[] params) {
        final String memberStr = (null == member ? "" : " for member " + member);
        final String paramStr = (null == params ? "" : escapeParam(String.format(" (parameters=%s)", Arrays.asList(params))));

        return String.format("Validation failed%s with reason %s%s", memberStr, reason, paramStr);
    }

    private String initMessageKey() {
        ReasonEnum reason = validationExceptionBean.getReason();
        if (null != reason.getMessageKey()) {
            return reason.getMessageKey();
        } else {
            return super.getMessageKey() + "." + reason.name();
        }
    }

    public ReasonEnum getReason() {
        return validationExceptionBean.getReason();
    }

    public String getMember() {
        return validationExceptionBean.getMember();
    }

    public static enum ReasonEnum {

        POSITIVE_NUMBER(),

        POSITIVE_NON_ZERO_NUMBER(),

        MIN_LENGTH(),

        MAX_LENGTH(),

        LENGTH(),

        INTEGER(),

        LONG(),

        BOOLEAN(),

        DOUBLE(),

        VALUE_NOT_IN_RANGE(),

        VALUE_NOT_EQ(),

        INVALID_CHAR(),

        INVALID_DATE_RANGE();

        /**
         * 定制的异常提示信息key
         */
        private String messageKey;

        private ReasonEnum() {

        }

        private ReasonEnum(String messageKey) {
            this.messageKey = messageKey;
        }

        public String getMessageKey() {
            return messageKey;
        }

        public void setMessageKey(String messageKey) {
            this.messageKey = messageKey;
        }

    }
}
