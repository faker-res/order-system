package com.tuniu.ord.common.task;

public enum TaskStatusEnum {
    DISABLE(0, "未开始"),
    
    ENABLE(1, "进行中"),
    
    FINISH(2, "已完成");
    
    private Integer key;
    private String name;

    private TaskStatusEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
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
     * get order task by the key.
     * @param key
     * @return
     */
    public static TaskStatusEnum getTaskStatusByKey(Integer key) {
        if (null == key) {
            return null;
        }
        for (TaskStatusEnum task : TaskStatusEnum.values()) {
            if (task.getKey().intValue() == key) {
                return task;
            }
        }
        return null;
    }
}
