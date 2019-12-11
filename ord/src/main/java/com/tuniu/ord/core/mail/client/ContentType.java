package com.tuniu.ord.core.mail.client;

public enum ContentType {

    TEXT_PLAIN("text/plain;charset=UTF-8"),

    TEXT_HTML("text/html;charset=UTF-8");

    private final String value;

    private ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
