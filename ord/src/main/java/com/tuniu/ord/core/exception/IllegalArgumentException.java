package com.tuniu.ord.core.exception;

/**
 * @author fangzhongwei
 * 
 */
public class IllegalArgumentException extends SaaSSystemException {

    private static final long serialVersionUID = 9064892386120360827L;

    public IllegalArgumentException() {
        super();
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

}
