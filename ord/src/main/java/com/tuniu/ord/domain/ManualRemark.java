package com.tuniu.ord.domain;

public class ManualRemark extends BaseManualDomain{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1327328222983204813L;

    private Integer type;

    private String content;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}