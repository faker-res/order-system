package com.tuniu.operation.framework.base.rest;

import java.io.IOException;

/**
 * @author fangzhongwei
 * 
 */
public class RestException extends IOException {

    private static final long serialVersionUID = 6480457029009010493L;

    public RestException() {
        super();
    }

    public RestException(String msg) {
        super(msg);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * 静态方法代替构建器
     * 
     * @param cause
     * @return RestException
     */
    public static RestException withError(Throwable cause) {
        return new RestException(cause.getMessage(), cause.getCause());
    }

}
