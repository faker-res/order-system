package com.tuniu.ord.common.constant;

/**
 * 错误码定义接口</br>
 * 
 * <strong>D类产品系统 错误码值范围 1700000~1799999</strong>
 * 
 * @author zhairongping
 * 
 */
public enum ProductResultCode {

    SUCCESS_CODE(1700000, "执行成功"),

    BASE64_DECODE_FAIL_CODE(731001, "传参错误，Base64解密不成功"),

    JSON_DECODE_FAIL_CODE(731002, "传参错误，JSON解密不成功"),

    PARAM_NOT_NULL_FAIL_CODE(731003, "传参错误，没有拿到必填的参数名"),

    PARAM_FORMAT_FAIL_FAIL_CODE(731004, "传参错误，参数格式和预定义的不符"),

    DB_FAIL_CODE(731005, "内部错误，数据库服务当前不可用"),

    CLIENT_NOT_REGISTER_FAIL_CODE(731006, "约束错误，客户端未经注册"),

    TIMEOUT_FAIL_CODE(731007, "约束错误，接口调用间隔时间已到"),

    DATA_EXCEED_FAIL_CODE(731008, "约束错误，查找数据量超过限制"),

    CACH_FAIL_CODE(731009, "内部错误，缓存服务当前不可用"),

    DATAS_COUNT_UNMATCHED_FAIL_CODE(731010, "约束错误，返回的数据条数只有部分"),

    NOT_UNIQUE_FAIL_CODE(731011, "约束错误，二义性错误查询结果不是唯一"),

    NOT_FOUND_FAIL_CODE(731012, "约束错误，按照条件没有查找到数据"),

    UNDEFINED_SERVICE_FAIL_CODE(731013, "内部错误，没有定义此API服务"),

    PRODUCT_PUBLISH_FAIL_CODE(731014, "当前产品状态是审核中，禁止发布"),

    NO_SELL_CHANNEL_FAIL_CODE(731015, "尚未维护售卖渠道信息!"),

    UNKNOW_FAIL_CODE(731999, "未知错误");

    private int key;

    private String message;

    private ProductResultCode(int key, String message) {
        this.key = key;
        this.message = message;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
