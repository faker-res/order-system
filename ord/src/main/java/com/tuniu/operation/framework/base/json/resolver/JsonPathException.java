package com.tuniu.operation.framework.base.json.resolver;

/**
 * @author fangzhongwei
 * 
 */
public class JsonPathException extends RuntimeException {

    private static final long serialVersionUID = -3560154644608019111L;

    public JsonPathException() {
    }

    public JsonPathException(String message) {
        super(message);
    }

    public JsonPathException(Throwable cause) {
        super(cause);
    }

    public JsonPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public static JsonPathException withError(String message, Throwable cause) {
        return new JsonPathException(message, cause);
    }

    public static JsonPathException withError(String message) {
        return new JsonPathException(message);
    }
}
