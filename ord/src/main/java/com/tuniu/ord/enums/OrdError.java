package com.tuniu.ord.enums;

public enum OrdError {
    TOURIST_NUM_NO_DIFFERENCE_WHEN_CHECKLOSS(10001, "核损人员数量对应不上"),

    NO_PRE_TASK(10002, "无前置任务"),

    NO_SUFFIX_TASK(10002, "无后置任务"),

    NO_SUCH_TASK(10003, "无此任务"),

    FINISH_TASK_ERROR(10004, "结束任务失败"),

    UNKNOW_TASK_ERROR(10005, "未知任务"),

    PARAM_NULL_ERROR(10006, "请求参数为空"),

    REQUIREMENT_ALREADY_EXIST(10007, "出游需求已经存在"), SUPPLYMENT_NUM_ERROR(10008, "订单增补项数量格式错误！"), SUPPLYMENT_PRICE_ERROR(10009,
            "订单增补项单价格式错误！"), SUPPLYMENT_PARAM_ERROR(10010, "订单增补项参数错误！"), RECEIPT_PARAM_ERROR(10011, "合同参数错误！"), RECEIPT_PRICE_ERROR(
            10012, "合同价格格式错误！"), OCCUPY_ERROR(10013, "订单占位失败"),

    NO_ORDER_REQUIREMENT(10014, "无订单需求"),

    NO_D_ORDER(10015, "无切位单记录"),
    
    CANCEL_OCCUPY_ERROR(10016, "订单取消占位失败"),
    
    NO_TOURIST_ERROR(10017, "无此游客信息"),
    

    REQUIRE_CONFIRM_ERROR(10018, "需求确认失败"),
    
    NO_MANUAL_ORDER(10019, "无订单"),
    
    REQUIREMENT_NOT_EXIST(10020, "出游需求不存在"),
    
    TOURIST_ADULTNUM_ERROR(10021, "出游人成人数不能超出游需求成人数！"),
    
    TOURIST_CHILDNUM_ERROR(10022, "出游人儿童数不能超出游需求儿童数！"),
    
    TOURIST_ADULTNUM_CHANGE_ERROR(10023, "出游需求成人数比出游人成人数少"),
    
    TOURIST_CHILDNUM_CHANGE_ERROR(10024, "出游需求儿童数比出游人儿童数少"),
    
    ORIGINAL_PRODUCT_BEEN_OCCUPIED(10025, "原产品已经占位，请先将原产品取消占位！"),
    
    QUERY_PRODUCT_ERROR(10026, "查询产品出错"),
    
    NO_NEED_OCCUPY(10027, "订单已经占位完成"),
    
    CHANGE_PRODUCT_ERROR(10028, "更换产品失败"),
    
    UPLOAD_TOURIST_ERROR(10029, "导入出游人出错"),
    
    TENANTID_NULL_ERROR(10030, "tenantId为空"),
    
    MANUALORDERID_NULL_ERROR(10031, "manualOrderId为空");
    
    private Integer code;
    private String msg;

    private OrdError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
