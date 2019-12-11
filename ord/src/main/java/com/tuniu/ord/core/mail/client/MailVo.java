package com.tuniu.ord.core.mail.client;

import java.util.List;

import com.tuniu.ord.core.init.SystemInitParameter;

/**
 * @author fangzhongwei
 * 
 */
public class MailVo {

    private String from = SystemInitParameter.mailRobot;

    private List<String> toList;

    private List<String> ccList;

    private String title;

    private String content;

    private ContentType type = ContentType.TEXT_HTML;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

}
