package com.tuniu.ord.common.constant;

import java.math.BigDecimal;

/**
 * @author fangzhongwei
 * 
 */
public class Constants {

    /**
     * 默认字符编码
     */
    public static final String CHARACTER_DEFAULT_ENCODING = "UTF-8";

    /**
     * 默认文档类型
     */
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    /**
     * 提示信息文件绑定名
     */
    public static final String MSG_RES_BUNDLE_BASE_NAME = "/config/msg/messages";

    /**
     * Rest请求设置request超时时间，单位S
     */
    public static final int REST_CONNCTION_TIMEOUT = 5;

    /**
     * Rest请求设置response超时时间，单位S
     */
    public static final int REST_SOCKET_TIMEOUT = 5;

    /**
     * RequestFacade中包装的request属性名称
     */
    public static final String CATALINA_CONNECTOR_REQUEST = "request";

    /**
     * 文件上传ContentType前缀
     */
    public static final String MULTIPART_CONTENT_TYPE_PREFIX = "multipart/";

    /**
     * 租户ID，用于确认数据源
     */
    public static final String TENANT_ID = "tenantId";

    /**
     * 多数据源tenant key前缀
     */
    public static final String DATA_SOURCE_PREFIX = "dota_ord_";

    /**
     * 多数据源tenant key master后缀
     */
    public static final String DATA_SOURCE_MASTER_SUFFIX = "_master";

    /**
     * 多数据源tenant key slave后缀
     */
    public static final String DATA_SOURCE_SLAVE_SUFFIX = "_slave";

    /**
     * 获取的ajax请求参数key
     */
    public static final String AJAX_PARAMETER = "ajax_parameter";

    /**
     * 客户端标识符
     */
    public static final String CLIENT_FLAG = "D_ORD";

    /**
     * D系统的秘钥
     */
    public static final String API_KEY = "5Hk5osI3Xz1EHqVNYw";

    /**
     * D系统校验密码
     */
    public static final String SECRET_KEY = "jcrpYDz4l7APfjcaUlJe";

    /**
     * 租户ID
     */
    public static final String tenantId = "tuniu";
    public static final String token = "ST-2645161-d3GcHV100odfq1DEJfDx-cas";
    /**
     * 系统三字码
     */
    public static final String systemCode = "DORD";
    /**
     * 业务id
     */
    public static final String businessId = "DOTA_ORD_20160718";
    /**
     * robot
     */
    public static final String senderName = "robot@tuniu.com";

    /**
     * 订单类型
     */
    public static final Integer ORDER_TYPE = 60;

    /**
     * 订单有效标识
     */
    public static final Integer USERFUL_MARK = 0;

    /**
     * 签约公司
     */
    public static final Integer SIGN_COMPANY = 4;

    /**
     * 签约公司名称
     */
    public static final String SIGN_COMPANY_NAME = "南京途牛国际旅行社有限公司";

    /**
     * 现付
     */
    public static final BigDecimal PAY_IMMEDIATE = new BigDecimal(1);

    /**
     * 系统编号
     */
    public static final Integer INTERVAL_TYPE_ID = 20020;

    /**
     * 常规跟团
     */
    public static final Integer PRODUCT_BRAND_ID = 1;

    /**
     * 自营
     */
    public static final Integer SALE_STYLE_ID = 1;

    /**
     * 已签约
     */
    public static final Integer SIGNED = 1;

    /**
     * 已确认
     */
    public static final Integer CONFIRMED_ORDERSTATUS = 5;

    /**
     * 占位符
     */
    public static final String REPLACE_SIGN = "{tenantId}";
    
    /**
     * 订单占位成功
     */
    public static final Integer MANUAL_ORDER_OCCUPY_SUCCESS = 2;
    
    /**
     * 订单占位失败
     */
    public static final Integer MANUAL_ORDER_OCCUPY_FAILURE = 1;
    
    /**
     * 订单未占位
     */
    public static final Integer MANUAL_ORDER_UNOCCUPY = 0;
    
    /**
     * 订单确认成功
     */
    public static final Integer MANUAL_ORDER_CONFIRM_SUCCESS = 3;
    
    /**
     * 订单确认失败
     */
    public static final Integer MANUAL_ORDER_CONFIRM_FAILURE = 4;
    
    /**
     * 逻辑删除
     */
    public static final Integer DELETE = 1;
    
    /**
     * 成人
     */
    public static final Integer ADULT = 0;
    
    /**
     * 儿童
     */
    public static final Integer CHILD = 1;
    
    /**
     * 请求成功标记
     */
    public static final String SUCCESS = "true";

    /**
     * 请求失败标记
     */
    public static final String FAILURE = "false";
}
