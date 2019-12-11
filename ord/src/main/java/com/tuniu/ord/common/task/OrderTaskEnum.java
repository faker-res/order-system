package com.tuniu.ord.common.task;

public enum OrderTaskEnum {
    
    ORDER(0, "接单", "/ORD/html/view/orderAccept.html"),
    
    REQ_CONFIRM(10, "需求确认", "/ORD/html/view/requirement.html"),
    
    PAY(20, "签约付款", "/ORD/html/view/sign.html"),
    
    CONFIRM(30, "确认", "/ORD/html/view/confirm.html"),
    
    DIPART(40, "出团通知", "/ORD/html/view/depart.html"),
    
    BACK(50, "出游归来", "/ORD/html/view/back.html");
    
    private Integer key;
    private String name;
    private String url;

    private OrderTaskEnum(Integer key, String name, String url) {
        this.key = key;
        this.name = name;
        this.url = url;
    }

    /**
     * @return the key
     */
    public Integer getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(Integer key) {
        this.key = key;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * obtain the pre task node.
     * @return
     */
    public OrderTaskEnum getPreTask() {
        if (this.getKey().equals(ORDER.getKey())) {
            return null;
        }
        return getTaskByKey(this.key - 10);
    } 
    
    /**
     * obtain the suffix task node.
     * @return
     */
    public OrderTaskEnum getSuffixTask() {
        if (this.getKey().equals(BACK.getKey())) {
            return null;
        }
        return getTaskByKey(this.key + 10);
    } 
    
    
    /**
     * get order task by the key.
     * @param key
     * @return
     */
    public static OrderTaskEnum getTaskByKey(Integer key) {
        if (null == key) {
            return null;
        }
        for (OrderTaskEnum task : OrderTaskEnum.values()) {
            if (task.getKey().intValue() == key) {
                return task;
            }
        }
        return null;
    }
    
    public static OrderTaskEnum getNextTask(OrderTaskEnum task) {
        if (task == null) {
            return null;
        }
        
        switch (task) {
        case ORDER:
            return REQ_CONFIRM;
        case REQ_CONFIRM:
            return PAY;
        case PAY:
            return CONFIRM;
        case CONFIRM:
            return DIPART;
        case DIPART:
            return BACK;
        default:
            return null;
        }
    }
}
