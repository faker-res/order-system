package com.tuniu.operation.framework.base.util;

/**
 * 创建线程安全的StringBuilder
 * 
 * @author fangzhongwei
 * 
 */
public class SafeStringBuilder extends ThreadLocal<StringBuilder> {

    private static final int DEFAULT_CAPACITY = 16;
    private int capacity = DEFAULT_CAPACITY;

    public SafeStringBuilder() {
    }

    public SafeStringBuilder(int capacity) {
        this.capacity = capacity;
    }

    public StringBuilder getBuffer() {
        return get();
    }

    public StringBuilder getClearBuffer() {
        StringBuilder buffer = get();
        buffer.delete(0, buffer.length());
        return buffer;
    }

    @Override
    protected StringBuilder initialValue() {
        return new StringBuilder(capacity);
    }

}
