package com.tuniu.operation.framework.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangzhongwei
 * 
 */
public final class SystemConstants {

    private SystemConstants() {
    }

    public static final String UTF8_ENCODING = "UTF-8";

    /**
     * HTTP请求方法
     */
    public static final String HTTP_GET = "GET";

    public static final String HTTP_POST = "POST";

    public static final String HTTP_PUT = "PUT";

    public static final String HTTP_DELETE = "DELETE";

    public static final String INTERROGATION_MARK = "?";

    private static final String TUNIU_A = "tuniuA";

    public static final Integer SYSTEM_ID = 39;

    public static final String SYSTEM_NAME = "系统";

    public static final Integer vendorId = 33768;

    public static final String vendorName = "途牛D类供应商";

    /**
     * 获取销售渠道ID
     */
    public static final Map<String, Integer> PRODUCT_SELL_CHANNEL_ID = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
        {
            put(TUNIU_A, 10002);
        }
    };

    /**
     * 获取销售渠道ID
     */
    public static final Map<String, String> PRODUCT_SELL_CHANNEL_NAME = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            put("10002", "自营");
        }
    };

    /**
     * http异步ajax请求头中的名称
     */
    public static final String HTTP_AJAX_REQUEST_HEADER_NAME = "X-Requested-With";

    /**
     * http异步ajax请求头中的值
     */
    public static final String HTTP_AJAX_REQUEST_HEADER_VALUE = "XMLHttpRequest";

    /**
     * 签约出库接口
     */
    public static final String PRODUCT_CONTRACT_SIGN = "STK.{tenantId}.StockProductController.productContractSign";

    /**
     * 取消签约接口
     */
    public static final String PRODUCT_CANCEL_CONTRACT_SIGN = "STK.{tenantId}.StockProductController.productCancelContractSign";

    /**
     * 根据切位单号查询占位记录
     */
    public static final String QUERY_OCCUPY_INFO = "STK.{tenantId}.StockWholesaleCommonController.queryOccupyInfo";

    /**
     * 根据销售单号查询出库信息
     */
    public static final String QUERY_STOCK_OUT_INFO = "STK.{tenantId}.StockOutController.slaveQueryStockOutList";

    /**
     * D类产品占位
     */
    public static final String DPRD_STOCK_OCCUPY_SIGN = "STK.{tenantId}.StockProductController.productOccupy";

    /**
     * D类产品取消占位
     */
    public static final String DPRD_STOCK_CANCEL_OCCUPY_SIGN = "STK.{tenantId}.StockProductController.cancelProductOccupy";

    /**
     * D产品入即占
     */
    public static final String PRODUCT_OCCUPY_SYN_APPLY = "STK.{tenantId}.StockProductController.productOccupySynApply";

    /**
     * 内部邮件
     */
    public static final String EMAIL_SEND_INTERNAL_EMAIL = "PLA.EMAIL.EdmController.sendInternalEmail";

    /**
     * 同步订单信息
     */
    public static final String SYN_ORDER = "ICS.SCS.OrderController.synOrder";

    /**
     * 更新订单信息
     */
    public static final String UPDATE_ORDER = "ICS.SCS.OrderController.updateOrder";

    /**
     * 根据产品线id、部门id查询关联关系
     */
    public static final String QUERY_RELATIONS_ID = "DPS.relation.RelationController.queryRelationsId";

    /**
     * 临时标志符
     */
    public static final boolean OPE_FLAG = true;

    /**
     * 根据切位单查询产品占位信息
     */
    public static final String QUERY_PRD_OCCUPY_INFO = "STK.{tenantId}.StockWholesaleCommonController.queryOccupyInfo";

    /**
     * 发信人Email
     */
    public static final String FROM_EMAIL = "rtx@tuniu.com";

    /**
     * 发信人名称
     */
    public static final String FROM_NAME = "rtxSys";

    /**
     * 发RTX地址
     */
    public static final String SEND_RTX_URL = "PLA.RTX.RtxController.send";

}
