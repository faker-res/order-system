/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月23日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.BeanUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.SessionUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.core.exception.IllegalArgumentException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.core.mail.client.ORDMailClient;
import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.domain.CheckLossDetail;
import com.tuniu.ord.domain.CheckLossTourist;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.CheckModeEnum;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.enums.ResellFlagEnum;
import com.tuniu.ord.enums.SupplierEnum;
import com.tuniu.ord.enums.TouristTypeEnum;
import com.tuniu.ord.persistence.CheckLossDetailMapper;
import com.tuniu.ord.persistence.CheckLossMapper;
import com.tuniu.ord.persistence.CheckLossTouristMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.persistence.OrdPriceDetailMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.APIProxy;
import com.tuniu.ord.proxy.DPSProxy;
import com.tuniu.ord.proxy.GRPProxy;
import com.tuniu.ord.proxy.PRDProxy;
import com.tuniu.ord.proxy.SCSProxy;
import com.tuniu.ord.proxy.StockProxy;
import com.tuniu.ord.service.ConfirmService;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.vo.CancelConfirmInputVo;
import com.tuniu.ord.vo.CheckLossTouristVo;
import com.tuniu.ord.vo.ConfirmFeedBackInputVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.ConfirmRequestQueryVo;
import com.tuniu.ord.vo.ConfirmSalesQueryVo;
import com.tuniu.ord.vo.ConsumerVo;
import com.tuniu.ord.vo.DPSDeptInfo;
import com.tuniu.ord.vo.DProductStock;
import com.tuniu.ord.vo.DepartDate;
import com.tuniu.ord.vo.DepartDateAPI;
import com.tuniu.ord.vo.DepartDateStock;
import com.tuniu.ord.vo.DetailVo;
import com.tuniu.ord.vo.LossApplyInfoDataVo;
import com.tuniu.ord.vo.LossApplyInfoVo;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.PeopleTouristVo;
import com.tuniu.ord.vo.Product;
import com.tuniu.ord.vo.ProductAPI;
import com.tuniu.ord.vo.ProductStockVo;
import com.tuniu.ord.vo.QueryRelationsIdOutputVo;
import com.tuniu.ord.vo.QueryRelationsIdVo;
import com.tuniu.ord.vo.QueryUpgradeInputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.SaleVo;
import com.tuniu.ord.vo.SalesConfirmVo;
import com.tuniu.ord.vo.Tourist;
import com.tuniu.ord.vo.TuniuInfo;

import net.sf.json.JSONObject;

/**
 * @author zhairongping
 * 
 */
@Service
public class ConfirmServiceImpl implements ConfirmService {
    private static Log4jLogger logger = LogFactory.getLogger(ConfirmServiceImpl.class);

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @Resource
    private StockProxy stockProxy;

    @Resource
    private APIProxy aPIProxy;

    @Resource
    private GRPProxy gRPProxy;

    @Resource
    private CheckLossDetailMapper checkLossDetailMapper;

    @Resource
    private CheckLossMapper checkLossMapper;

    @Resource
    private CheckLossTouristMapper checkLossTouristMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private PRDProxy pRDProxy;

    @Resource
    private SCSProxy sCSProxy;

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    @Resource
    private DPSProxy dPSProxy;

    @Resource
    private OrdPriceDetailMapper ordPriceDetailMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ConfirmService#initiateConfirm(com.tuniu.ord.vo. ConfirmInputVo)
     */
    @Override
    public ResponseVo initiateConfirm(ConfirmInputVo confirmInputVo) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setErrorCode(241000);
        responseVo.setMsg("成功");
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-1:发起确认参数校验开始");
        // 校验1:products不能为空
        ArgumentValidator.isNotNullEmpty("products产品信息", confirmInputVo.getProducts());
        Product product = confirmInputVo.getProducts().get(0);
        List<DepartDate> departDates = product.getDepartDates();
        // 校验2:departDates不能为空
        ArgumentValidator.isNotNullEmpty("departDatesA类团期信息", departDates);
        for (DepartDate departDate : departDates) {
            String roundId = departDate.getRoundId();
            if (null == roundId || "".equals(roundId)) {
                logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>A资源入库批次号不能为空");
                throw new IllegalArgumentException("A资源入库批次号不能为空");
            }
            // 根据A资源入库批次号查询D类订单、产品ID和产品名称以及责任人
            OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(roundId));
            if (null == ordDealOrder) {
                logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>A资源入库批次号:" + roundId + "查询不到D类订单");
                throw new IllegalArgumentException("A资源入库批次号:" + roundId + "查询不到D类订单");
            }
        }
        TuniuInfo tuniuInfo = product.getTuniuInfo();
        ArgumentValidator.isNotNull("tuniuInfo供应商", tuniuInfo);
        Integer tuniuOrderId = tuniuInfo.getTuniuOrderId();
        Integer requirementId = tuniuInfo.getRequirementId();
        ArgumentValidator.isNotNull("tuniuOrderId途牛订单号", tuniuOrderId);
        ArgumentValidator.isNotNull("requirementId需求id", requirementId);
        // 保证同一个发起确认请求只处理一次
        ConfirmRequestQueryVo confirmRequestQueryVo = new ConfirmRequestQueryVo();
        confirmRequestQueryVo.setTuniuOrderId(tuniuOrderId);
        confirmRequestQueryVo.setRequirementId(requirementId);
        int countNum = ordSalesOrderMapper.countConfirmRequest(confirmRequestQueryVo);
        if (countNum > 0) {// 已处理该请求，直接返回
            logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>已处理过请求;" + JsonUtil.toString(confirmInputVo));
            responseVo.setSuccess(false);
            responseVo.setMsg("已处理过请求");
            return responseVo;
        }
        // 校验3:判断所有批次总人数是否和出游总人数相等
        List<Tourist> tourist = product.getTourist();
        ArgumentValidator.isNotNullEmpty("出游人列表", tourist);
        Integer countDepartDateNumber = new Integer(0);
        Integer countTouristNumber = new Integer(tourist.size());
        for (DepartDate departDate : departDates) {
            countDepartDateNumber += departDate.getAffirmAdultNum() + departDate.getAffirmChildNum()
                    + departDate.getAffirmBabyNum();
        }
        if (countDepartDateNumber.intValue() != countTouristNumber.intValue()) {
            throw new IllegalArgumentException("所有批次总人数必须和出游总人数相等");
        }
        // 校验4:对出游人信息进行校验
        String touristName = null;
        Integer psptType = null;
        String touristId = null;
        for (Tourist tst : tourist) {
            touristName = tst.getTouristName();
            psptType = tst.getPsptType();
            touristId = tst.getTouristId();
            if (null == touristName || "".equals(touristName)) {
                throw new IllegalArgumentException("游客姓名必填");
            }
            if (null == psptType) {
                throw new IllegalArgumentException("游客证件类型必填");
            }
            if (null == touristId || "".equals(touristId)) {
                throw new IllegalArgumentException("游客ID必填");
            }
        }
        // 校验5:批次中成人数、儿童数与出游人列表中成人数、儿童数是否一致
        // boolean flag = validatePersonType(tourist, departDates);
        // if (flag == false) {
        // throw new IllegalArgumentException("批次中成人数、儿童数与出游人列表中成人数、儿童数不一致");
        // }
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-1:发起确认参数校验结束");
        String tenantId = DataSourceSwitch.get();
        // 启动异步线程
        startExecutor(confirmInputVo, tenantId);
        return responseVo;
    }

    /**
     * 校验批次中成人数、儿童数与出游人列表中成人数、儿童数是否一致
     * 
     * @param tourist
     * @param departDates
     * @return
     */
    private boolean validatePersonType(List<Tourist> tourist, List<DepartDate> departDates) {
        boolean flag = false;
        Integer countAdultDepartDateNumber = new Integer(0);
        Integer countChildDepartDateNumber = new Integer(0);
        for (DepartDate departDate : departDates) {
            countAdultDepartDateNumber += departDate.getAffirmAdultNum();
            countChildDepartDateNumber += departDate.getAffirmChildNum();
        }
        int countAdultTourist = 0;
        int countChildTourist = 0;
        for (Tourist tst : tourist) {
            if (tst.getTouristType().intValue() == TouristTypeEnum.ADULT.getKey().intValue()) {
                countAdultTourist++;
            }
            if (tst.getTouristType().intValue() == TouristTypeEnum.CHILD.getKey().intValue()) {
                countChildTourist++;
            }
        }
        if (countAdultDepartDateNumber.intValue() == countAdultTourist
                && countChildDepartDateNumber.intValue() == countChildTourist) {
            flag = true;
        }
        return flag;
    }

    /**
     * 启动异步线程
     * 
     * @param confirmInputVo
     */
    public void startExecutor(ConfirmInputVo confirmInputVo, String tenantId) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new ExecutorConfirm(confirmInputVo, tenantId));
    }

    /**
     * 异步线程负责处理发起确认业务逻辑
     * 
     * @author zhairongping
     * 
     */
    private class ExecutorConfirm implements Callable {
        private final ConfirmInputVo confirmInputVo;
        private final String tenantId;

        public ExecutorConfirm(ConfirmInputVo confirmInputVo, String tenantId) {
            this.confirmInputVo = confirmInputVo;
            this.tenantId = tenantId;
        }

        /*
         * (non-Javadoc) 处理发起确认业务逻辑
         * 
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Object call() throws Exception {
            logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-2:启动异步线程开始");
            // 记录处理开始时间、结束时间和所花费的总时间
            logger.debug(Log4jLogger.SYSTEM_LOG,
                    "异步线程" + Thread.currentThread().getName() + "开始时间【" + sdft.format(new Date()) + "】");
            long start = System.currentTimeMillis();
            // 处理发起确认业务逻辑
            dealInitiateConfirm(this.confirmInputVo, this.tenantId);
            logger.debug(Log4jLogger.SYSTEM_LOG,
                    "异步线程" + Thread.currentThread().getName() + "结束时间【" + sdft.format(new Date()) + "】");
            long end = System.currentTimeMillis();
            logger.debug(Log4jLogger.SYSTEM_LOG,
                    "异步线程" + Thread.currentThread().getName() + "所花费的总时间【" + (end - start) + "毫秒】");
            logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-2:启动异步线程结束");
            return null;
        }
    }

    /**
     * 处理发起确认业务逻辑
     * 
     * @param confirmInputVo
     */
    public void dealInitiateConfirm(ConfirmInputVo confirmInputVo, String tenantId) {
        DataSourceSwitch.set(tenantId);
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String sign = confirmInputVo.getSign();
        Integer sessionId = confirmInputVo.getSessionId();
        Product product = confirmInputVo.getProducts().get(0);
        TuniuInfo tuniuInfo = product.getTuniuInfo();
        List<DepartDate> departDates = product.getDepartDates();
        List<Tourist> tourist = product.getTourist();
        // 外部渠道订单号
        Integer channelOrderId = tuniuInfo.getTuniuOrderId();
        Integer requirementId = tuniuInfo.getRequirementId();
        Integer productId = tuniuInfo.getProductId();
        String productName = tuniuInfo.getProductName();
        // 销售渠道
        String name = tuniuInfo.getName();
        // 未处理的两种情况:加人加资源;初次发起确认
        // 初始批次起始编号
        Integer initSize = new Integer(0);
        // 初始批次总数
        Integer countSize = new Integer(0);
        // 存储所有批次的结果
        List<ResponseVo> responseList = new ArrayList<ResponseVo>();

        List<OrderSynInfo> orderSynInfoList = new ArrayList<OrderSynInfo>();

        // 销售单下单流程三部曲
        logger.debug(Log4jLogger.SYSTEM_LOG,
                ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-2-1:添加销售单和出游人以及调用D产品库存系统的签约出库接口开始");
        SalesConfirmVo salesConfirmVo = new SalesConfirmVo();
        // 销售单下单第一步:准备操作者基本信息
        salesConfirmVo.setTenantId(Constants.tenantId);
        salesConfirmVo.setUid(Integer.valueOf(UserSessionUtil.getUid()));
        salesConfirmVo.setNickname(UserSessionUtil.getNickname());
        salesConfirmVo.setToken(Constants.token);
        salesConfirmVo.setSessionId(SessionUtil.createSessionId());
        salesConfirmVo.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        List<SaleVo> sales = new ArrayList<SaleVo>();
        SaleVo sale = null;
        OrderSynInfo orderSynInfo = null;
        for (DepartDate departDate : departDates) {
            orderSynInfo = new OrderSynInfo();
            String roundId = departDate.getRoundId();
            // 根据A资源入库批次号查询D类订单、产品ID和产品名称以及责任人
            OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(roundId));
            // D占位单id
            Integer occupyBatchId = ordDealOrder.getOccupyBatchId();
            Integer dProductId = ordDealOrder.getProductId();
            // 添加销售单，取得主键
            OrdSalesOrder ordSalesOrder = new OrdSalesOrder();
            ordSalesOrder.setOrderId(ordDealOrder.getOrderId());
            ordSalesOrder.setExtOrderId(channelOrderId);
            ordSalesOrder.setRequirementId(requirementId);
            ordSalesOrder.setExtProductId(productId);
            ordSalesOrder.setExtProductName(productName);
            ordSalesOrder.setRemark(product.getRemark());
            ordSalesOrder.setStatusCode(OrderStateEnum.CONFIRMING.getStatusCode());
            ordSalesOrder.setCostCurrencyType(String.valueOf(departDate.getCostCurrencyType()));
            // 字符串转成日期
            try {
                ordSalesOrder.setStartDate(sdf.parse(departDate.getDate()));
            } catch (ParseException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            ordSalesOrder.setSaleChannel(name);
            ordSalesOrder.setAdultCount(departDate.getAffirmAdultNum());
            ordSalesOrder.setChildCount(departDate.getAffirmChildNum());
            ordSalesOrder.setAdultPrice(departDate.getAffirmAdultPrice());
            ordSalesOrder.setChildPrice(departDate.getAffirmChildPrice());
            ordSalesOrder.setRoomAddNum(departDate.getRoomAddNum());
            ordSalesOrder.setRoomAddPrice(departDate.getRoomAddPrice());
            ordSalesOrder.setResourceId(departDate.getResourceId());
            // 系统参数
            ordSalesOrder.setApiKey(confirmInputVo.getApiKey());
            ordSalesOrder.setTimestamp(confirmInputVo.getTimestamp());
            ordSalesOrder.setSign(confirmInputVo.getSign());
            // 访问POS客服端生产订单号
            Long orderNum = iOrderIdServiceImplClient.getOrderNum(Constants.INTERVAL_TYPE_ID);
            logger.debug(Log4jLogger.SYSTEM_LOG, "==================POS客服端生产订单号=" + orderNum);
            ordSalesOrder.setId(orderNum.intValue());
            ordSalesOrderMapper.insertSelective(ordSalesOrder);
            // 销售单下单第二步:准备D销售单基本信息
            sale = new SaleVo();
            sale.setAddNum(departDate.getRoomAddNum());
            sale.setAddPrice(departDate.getRoomAddPrice());
            sale.setAdultCost(departDate.getAffirmAdultPrice());
            sale.setAdultNum(departDate.getAffirmAdultNum());
            if (null != ordDealOrder.getClosingDate()) {
                sale.setBackDate(sdf.format(ordDealOrder.getClosingDate()));
            }
            // FIXME 此处写的 下期改动
            sale.setChannelId("1");
            sale.setChannelOrderId(String.valueOf(channelOrderId));
            sale.setChildCost(departDate.getAffirmChildPrice());
            sale.setChildNum(departDate.getAffirmChildNum());
            sale.setCurrencyType(String.valueOf(departDate.getCostCurrencyType()));
            sale.setOrderId(ordSalesOrder.getId());
            sale.setSpecialRemark(product.getRemark());
            if (null != ordDealOrder.getDepartDate()) {
                sale.setStartDate(sdf.format(ordDealOrder.getDepartDate()));
            }
            BigDecimal adultTotalPrice = departDate.getAffirmAdultPrice()
                    .multiply(new BigDecimal(departDate.getAffirmAdultNum()));
            BigDecimal childTotalPrice = departDate.getAffirmChildPrice()
                    .multiply(new BigDecimal(departDate.getAffirmChildNum()));
            BigDecimal roomTotalPrice = departDate.getRoomAddPrice().multiply(new BigDecimal(departDate.getRoomAddNum()));
            sale.setTotalCost(adultTotalPrice.add(childTotalPrice).add(roomTotalPrice));
            sale.setTourBasicId(ordDealOrder.getGroupId());
            // 升级方案处理
            // 此类已经废弃
            // Integer resId = departDate.getResourceId();
            Integer resId = new Integer(0);
            if (null != resId) {
                QueryUpgradeInputVo queryUpgradeInputVo = new QueryUpgradeInputVo();
                queryUpgradeInputVo.setResId(resId);
                ResponseVo resp = pRDProxy.getUpgrade(queryUpgradeInputVo);
                if (null != resp && resp.isSuccess()) {
                    Integer upgradeId = (Integer) resp.getData();
                    sale.setUpgradeId(upgradeId);
                }
            }
            /**** 根据批次的次数添加对应批次的出游人 start ********/
            List<ConsumerVo> consumers = new ArrayList<ConsumerVo>();
            ConsumerVo consumer = null;
            // 批量添加销售单出游人
            OrdPeopleTourist ordPeopleTourist = null;
            // 每批次总人数
            Integer batchSize = departDate.getAffirmAdultNum() + departDate.getAffirmChildNum() + departDate.getAffirmBabyNum();
            countSize += batchSize;
            Tourist tt = null;
            for (int i = initSize; i < countSize; i++) {
                System.out.println("【initSize=" + initSize + ",countSize=" + countSize + "】");
                tt = tourist.get(i);
                ordPeopleTourist = new OrdPeopleTourist();
                // 销售单主键ID
                ordPeopleTourist.setSellOrderId(ordSalesOrder.getId());
                ordPeopleTourist.setCountry(tt.getCountry());
                ordPeopleTourist.setFirstname(tt.getFirstName());
                ordPeopleTourist.setIsDefault(tt.getIsContactTourist());
                ordPeopleTourist.setLastname(tt.getLastName());
                ordPeopleTourist.setName(tt.getTouristName());
                if (null != tt.getPsptEndDate() && !"".equals(tt.getPsptEndDate())) {
                    try {
                        ordPeopleTourist.setPsptEndDate(sdf.parse(tt.getPsptEndDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                ordPeopleTourist.setPsptId(tt.getPsptId());
                ordPeopleTourist.setPsptType(String.valueOf(tt.getPsptType()));
                ordPeopleTourist.setSex(String.valueOf(tt.getSex()));
                ordPeopleTourist.setTel(tt.getTouristTel());
                ordPeopleTourist.setTouristType(String.valueOf(tt.getTouristType()));
                ordPeopleTourist.setFabContactId(tt.getTouristId());
                // 销售单下单第三步:准备出游人基本信息
                consumer = new ConsumerVo();
                if (null != tt.getBirthday() && !"".equals(tt.getBirthday())) {
                    Date birth_ = null;
                    try {
                        birth_ = sdf.parse(tt.getBirthday());
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    int year = birth_.getYear();
                    ordPeopleTourist.setBirthOfDate(birth_);
                    // 计算年龄规则
                    Date nowDay = Calendar.getInstance().getTime();
                    ordPeopleTourist.setAge(nowDay.getYear() - year + 1);
                    consumer.setConsumerBirthday(tt.getBirthday());
                    consumer.setConsumerAge(nowDay.getYear() - year + 1);
                }
                ordPeopleTouristMapper.insertSelective(ordPeopleTourist);
                // 销售单下单第三步:准备出游人基本信息
                consumer.setConsumerAgeSegment(tt.getTouristType());
                consumer.setConsumerCardNo(tt.getPsptId());
                consumer.setCardType(tt.getPsptType());
                consumer.setConsumerId(ordPeopleTourist.getId());
                consumer.setConsumerLevel(new Integer(0));
                consumer.setConsumerName(tt.getTouristName());
                consumer.setConsumerPhone(tt.getTouristTel());
                consumer.setSex(tt.getSex());
                consumers.add(consumer);
            }
            initSize += batchSize;
            sale.setConsumers(consumers);
            // 产品线ID
            Integer productLineId = ordDealOrder.getProductLineId();
            sale.setProductLineId(productLineId);
            sales.add(sale);
            /**** 根据批次的次数添加对应批次的出游人 end ********/

            /** 调用D产品库存系统的签约出库接口-开始 **/
            // 签约出库入参第一步
            DepartDateStock departDateStock = new DepartDateStock();
            List<Integer> outIds = new ArrayList<Integer>();
            outIds.add(occupyBatchId);
            departDateStock.setOutIds(outIds);
            departDateStock.setOutId(occupyBatchId);
            departDateStock.setDepartDate(departDate.getDate());
            departDateStock.setAdultCost(departDate.getAffirmAdultPrice());
            departDateStock.setAdultCostRMB(departDate.getAffirmAdultPrice());
            // 成人数 = 成人数 + 儿童数
            departDateStock.setAdultNum(departDate.getAffirmAdultNum() + departDate.getAffirmChildNum());
            departDateStock.setBabyCost(new BigDecimal(0));
            departDateStock.setBabyCostRMB(new BigDecimal(0));
            departDateStock.setBabyNum(new Integer(0));
            departDateStock.setChildCost(departDate.getAffirmChildPrice());
            departDateStock.setChildCostRMB(departDate.getAffirmChildPrice());
            departDateStock.setChildNum(0);
            List<DepartDateStock> dd = new ArrayList<DepartDateStock>();
            dd.add(departDateStock);
            // 签约出库入参第二步
            DProductStock dProductStock = new DProductStock();
            dProductStock.setdProductId(dProductId);
            dProductStock.setCostCurrencyType(departDate.getCostCurrencyType());
            dProductStock.setVendorId(SupplierEnum.TUNIU.getVendorId());
            dProductStock.setVendorName(SupplierEnum.TUNIU.getVendorName());
            dProductStock.setDepartDates(dd);
            List<DProductStock> dProducts = new ArrayList<DProductStock>();
            dProducts.add(dProductStock);
            // 签约出库入参第三步
            ProductStockVo productStockVo = new ProductStockVo();
            productStockVo.setOrderId(ordSalesOrder.getId());
            productStockVo.setProductId(productId);
            productStockVo.setProductName(productName);
            productStockVo.setClientFlag(Constants.CLIENT_FLAG);
            productStockVo.setSessionId(SessionUtil.createSessionId());
            productStockVo.setdProducts(dProducts);
            /* 签约出库接口 */
            ResponseVo result = stockProxy.productContractSign(productStockVo);

            // 修改价格表的确认成人数
            DetailVo detailVo = new DetailVo();
            detailVo.setOrderId(ordDealOrder.getOrderId());
            detailVo.setConfirmedAdultCount(departDate.getAffirmAdultNum() + departDate.getAffirmChildNum());
            if (result != null && result.isSuccess() == true) {
                ordPriceDetailMapper.updateOrderDetail(detailVo);
            } else {
                logger.debug(Log4jLogger.SYSTEM_LOG, "修改价格表的确认成人数失败====" + JsonUtil.toString(detailVo));
            }

            // 存储每个批次签约出库的结果
            responseList.add(result);
            /** 调用D产品库存系统的签约出库接口-结束 **/

            /** 构建同步订单信息-开始 **/
            orderSynInfo.setId(ordSalesOrder.getId());
            // orderSynInfo.setState(OrderStateEnum.CONFIRMED.getStatusCode());
            orderSynInfo.setState(Constants.CONFIRMED_ORDERSTATUS);
            orderSynInfo.setStatusDesc(OrderStateEnum.CONFIRMED.getStatusName());
            BigDecimal adultAmount = departDate.getAffirmAdultPrice().multiply(new BigDecimal(departDate.getAffirmAdultNum()));
            BigDecimal childAmount = departDate.getAffirmChildPrice().multiply(new BigDecimal(departDate.getAffirmChildNum()));
            BigDecimal roomAmount = departDate.getRoomAddPrice().multiply(new BigDecimal(departDate.getRoomAddNum()));
            BigDecimal totalAmount = new BigDecimal(0);
            totalAmount = totalAmount.add(adultAmount).add(childAmount).add(roomAmount);
            orderSynInfo.setContractAmount(totalAmount);
            orderSynInfo.setSyncTime(sdft.format(new Date()));
            orderSynInfo.setProduct(ordDealOrder.getProductId());
            // 产品一级品类id/产品二级品类id
            orderSynInfo.setProductCategory(ordDealOrder.getProductCategory());
            orderSynInfo.setProductCategoryName(ordDealOrder.getProductCategoryName());
            orderSynInfo.setProductSubCategory(ordDealOrder.getProductSubCategory());
            orderSynInfo.setProductSubCategoryName(ordDealOrder.getProductSubCategoryName());
            // 类目信息
            orderSynInfo.setDestinationFirstCategory(ordDealOrder.getDestCategoryId());
            orderSynInfo.setDestinationFirstCategoryName(ordDealOrder.getDestCategoryName());
            orderSynInfo.setFirstDestinationGrouping(ordDealOrder.getFirstDestGroupId());
            orderSynInfo.setFirstDestinationGroupingName(ordDealOrder.getFirstDestGroupName());
            // 二级目的地分组id
            orderSynInfo.setSecondDestinationGrouping(ordDealOrder.getSecDestGroupId());
            orderSynInfo.setSecondDestinationGroupingName(ordDealOrder.getSecDestGroupName());
            // 目的地id/目的地名称
            Integer destination = ordDealOrder.getDestId();
            String destinationName = ordDealOrder.getDestName();
            orderSynInfo.setDestination(destination);
            orderSynInfo.setDestinationName(destinationName);
            // 出发城市
            String startCityCode = ordDealOrder.getDepartureCityCode().toString();
            String startCityName = ordDealOrder.getDepartureCityName();
            orderSynInfo.setStartCityCode(startCityCode);
            orderSynInfo.setStartCityName(startCityName);

            orderSynInfo.setSignCompany(Constants.SIGN_COMPANY);
            orderSynInfo.setSignCompanyName(Constants.SIGN_COMPANY_NAME);
            orderSynInfo.setIsSubOrder(new Integer(1));
            orderSynInfo.setPrimaryOrderId(channelOrderId);

            /** 访问部门类目管理系统获取部门信息开始 **/
            QueryRelationsIdVo queryRelationsIdVo = new QueryRelationsIdVo();
            queryRelationsIdVo.setProductLineId(productLineId);
            // 访问部门类目管理系统获取部门信息
            ResponseVo dpsRes = dPSProxy.queryRelationsId(queryRelationsIdVo);
            QueryRelationsIdOutputVo queryRelationsIdOutputVo = null;
            DPSDeptInfo defaultDept = new DPSDeptInfo();
            if (dpsRes.isSuccess() == true) {
                queryRelationsIdOutputVo = JsonUtil.toBean(JsonUtil.toString(dpsRes.getData()), QueryRelationsIdOutputVo.class);
            } else {
                logger.debug(Log4jLogger.SYSTEM_LOG, "【产品线ID】" + productLineId + ";根据产品线id、部门id查询关联关系接口异常或者返回失败");
            }
            // 查找产品线与部门的结果标志
            boolean flag = true;
            if (null != queryRelationsIdOutputVo) {
                if (CollectionUtils.isNotEmpty(queryRelationsIdOutputVo.getRows())) {
                    for (DPSDeptInfo dept : queryRelationsIdOutputVo.getRows()) {
                        if (Constants.PRODUCT_BRAND_ID.intValue() == dept.getProductBrandId().intValue()
                                && Constants.SALE_STYLE_ID.intValue() == dept.getSaleStyleId().intValue()) {
                            defaultDept = dept;
                            logger.debug(Log4jLogger.SYSTEM_LOG,
                                    "【产品线ID】" + productLineId + ";遍历部门列表匹配的部门信息=====" + JsonUtil.toString(defaultDept));
                            flag = false;
                            break;
                        }
                    }
                } else {
                    logger.debug(Log4jLogger.SYSTEM_LOG, "【产品线ID】" + productLineId + "下无部门信息");
                    flag = false;
                }
                if (flag == true) {
                    logger.debug(Log4jLogger.SYSTEM_LOG, "【产品线ID】" + productLineId + "有部门列表，但是没有匹配成功");
                }
            }
            /** 访问部门类目管理系统获取部门信息 结束 **/
            // 部门信息
            Integer regionCode = defaultDept.getDivisionId();
            String regionName = defaultDept.getDivisionName();
            Integer departmentCode = defaultDept.getDepartmentId();
            String departmentName = defaultDept.getDepartmentName();
            Integer groupId = defaultDept.getGroupId();
            String groupName = defaultDept.getGroupName();
            orderSynInfo.setRegionCode(regionCode);
            orderSynInfo.setRegionName(regionName);
            orderSynInfo.setDepartmentCode(departmentCode);
            orderSynInfo.setDepartmentName(departmentName);
            orderSynInfo.setGroupId(groupId);
            orderSynInfo.setGroupName(groupName);

            orderSynInfoList.add(orderSynInfo);
            /** 构建同步订单信息 -结束 **/

            // 更改销售单状态
            OrdSalesOrder oso = new OrdSalesOrder();
            oso.setId(ordSalesOrder.getId());
            if (null != result && result.isSuccess() == true) {
                oso.setStatusCode(OrderStateEnum.CONFIRMED.getStatusCode());
            } else {
                oso.setStatusCode(OrderStateEnum.CONFIRMFAILED.getStatusCode());
                logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>签约出库失败导致销售单状态为确认失败,更改操作方式:销售单号【" + ordSalesOrder.getId() + "】状态【"
                        + OrderStateEnum.CONFIRMFAILED.getStatusCode() + "】");
                // 提供后门接口:更改销售单状态
            }
            try {
                // 由A资源入库批次修改D出库单ID
                OrdDealOrder odo = new OrdDealOrder();
                odo.setExtBatchId(Integer.valueOf(roundId));
                if (null != result && result.isSuccess() == true && null != result.getData()) {
                    ProductStockVo ps = JsonUtil.toBean(JsonUtil.toString(result.getData()), ProductStockVo.class);
                    Integer outId = ps.getdProducts().get(0).getDepartDates().get(0).getOutId();
                    // odo.setOutLibraryId(outId);
                    oso.setExtOrderBatchId(outId);
                }
                // ordDealOrderMapper.updateOrdDealOrderOutLibraryId(odo);
                ordSalesOrderMapper.updateByPrimaryKeySelective(oso);
            } catch (Exception e) {
                // 记录日志,发送邮件通知,走技术支持:发送邮件者,发送者以及发送给谁
                String title = "【D产品库存系统技术支持邮件通知】";
                StringBuffer sb = new StringBuffer();
                sb.append("订单系统由A资源入库批次【" + roundId + "】修改D出库单ID操作出现异常,原因详情如下:");
                sb.append("调用D产品库存系统的签约出库请求地址:" + SystemInitParameter.productContractSign + ";");
                sb.append("调用D产品库存系统的签约出库请求参数:" + JsonUtil.toString(productStockVo) + ";");
                if (result == null) {
                    sb.append("调用D产品库存系统的签约出库异常信息:返回结果为空;");
                } else {
                    sb.append("调用D产品库存系统的签约出库异常信息:" + result.getMsg() + ";");
                }
                // 记录日志
                logger.debug(Log4jLogger.SYSTEM_LOG, title + "=====" + sb.toString());
                // 发送邮件
                ORDMailClient.sendMail(title, sb.toString());
                // 提供后门接口:由A资源入库批次修改D出库单ID
            }
        }
        logger.debug(Log4jLogger.SYSTEM_LOG,
                ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-2-1:添加销售单和出游人以及调用D产品库存系统的签约出库接口结束");

        salesConfirmVo.setSales(sales);
        // 若所有批次签约出库成功,则反馈成功;若有一个批次签约出库失败,则反馈失败.
        /** 异步反馈API 开始 **/
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-2-2: 异步反馈API和调用组团系统销售单下单接口开始");
        boolean flag = isSign(responseList);
        if (flag == true) {// 若签约出库成功,则调用API的确认反馈接口，更改销售单状态为确认成功
            DepartDateAPI departDateAPI = null;
            List<DepartDateAPI> dda = new ArrayList<DepartDateAPI>();

            ProductAPI productAPI = new ProductAPI();
            List<ProductAPI> pa = new ArrayList<ProductAPI>();

            for (DepartDate departDate : departDates) {
                departDateAPI = new DepartDateAPI();
                departDateAPI.setAdultCost(departDate.getAffirmAdultPrice());
                departDateAPI.setAdultNum(departDate.getAffirmAdultNum());
                departDateAPI.setChildCost(departDate.getAffirmChildPrice());
                departDateAPI.setChildNum(departDate.getAffirmChildNum());
                departDateAPI.setDate(departDate.getDate());
                departDateAPI.setRoomAddNum(departDate.getRoomAddNum());
                departDateAPI.setRoomAddPrice(departDate.getRoomAddPrice());
                departDateAPI.setRoundId(Integer.parseInt(departDate.getRoundId()));
                dda.add(departDateAPI);
            }
            productAPI.setAgencyOrderId(String.valueOf(new Integer(123)));
            productAPI.setAgencyOwnerId(123);
            productAPI.setAgencyOwnerName("123");
            productAPI.setAgencyProductId(String.valueOf("123"));
            productAPI.setDepartDates(dda);
            pa.add(productAPI);
            /* 调用API确认反馈接口 */
            ResponseVo rv = lanuchConfirm(sign, pa, true, sessionId);
            /* 调用组团系统销售单下单接口 */
            gRPProxy.sendSalesConfirm(salesConfirmVo);

            /** 同步订单信息给结算中心 **/
            if (CollectionUtils.isNotEmpty(orderSynInfoList)) {
                ResponseVo sCsRes = new ResponseVo();
                for (OrderSynInfo os : orderSynInfoList) {
                    sCsRes = sCSProxy.synOrder(os);
                    if (sCsRes.isSuccess() == false) {
                        logger.debug(Log4jLogger.SYSTEM_LOG, "销售订单" + os.getId() + "同步订单信息接口异常=====" + JsonUtil.toString(os));
                        // 发邮件提醒
                        String title = "【结算平台系统中同步订单信息接口异常技术支持邮件通知】";
                        StringBuffer sb = new StringBuffer();
                        sb.append("【销售订单【" + os.getId() + "】同步订单信息接口异常,原因详情如下:" + sCsRes.getMsg() + ";");
                        sb.append("同步订单信息给结算中心请求地址:" + SystemConstants.SYN_ORDER + ";请求参数:" + JsonUtil.toString(os));
                        // 发送邮件
                        ORDMailClient.sendMailToSCS(title, sb.toString());
                    }
                }
            }
        } else {// 若签约出库失败,则调用API的确认反馈接口,更改销售单状态为确认失败
            ResponseVo rv = lanuchConfirm(sign, null, false, sessionId);
        }
        /** 同步反馈API 结束 **/
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1-2-2: 异步反馈API和调用组团系统销售单下单接口结束");
    }

    /**
     * 调用API确认反馈接口
     * 
     * @param sign
     * @param pa
     * @param isSuccess
     * @return
     */
    @Override
    public ResponseVo lanuchConfirm(String sign, List<ProductAPI> pa, boolean isSuccess, Integer sessionId) {
        ConfirmFeedBackInputVo confirmFeedBackInputVo = new ConfirmFeedBackInputVo();
        // 系统参数(保存)
        confirmFeedBackInputVo.setApiKey(Constants.API_KEY);
        confirmFeedBackInputVo.setSign(sign);
        confirmFeedBackInputVo
                .setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        confirmFeedBackInputVo.setSessionId(sessionId);
        confirmFeedBackInputVo.setSuccess(isSuccess);
        confirmFeedBackInputVo.setProducts(pa);

        /********************* API系统的安全机制start **********************/
        String jsonResult = JsonUtil.toString(confirmFeedBackInputVo);
        Map<String, Object> map = new HashMap<String, Object>();
        map = JsonUtil.toBean(jsonResult, HashMap.class);
        String sign2 = "";
        try {
            sign2 = BeanUtil.getSignature(JSONObject.fromObject(map), Constants.SECRET_KEY);
            map.put("sign", sign2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        confirmFeedBackInputVo = JsonUtil.toBean(JsonUtil.toString(map), ConfirmFeedBackInputVo.class);
        /********************* API系统的安全机制end ***********************/
        // 调用API确认反馈
        return aPIProxy.confirmFeedBack(confirmFeedBackInputVo);
    }

    /**
     * 判断是否签约出库成功
     * 
     * @param list
     * @return
     */
    public boolean isSign(List<ResponseVo> list) {
        for (ResponseVo rv : list) {
            if (null == rv || rv.isSuccess() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否取消签约成功
     * 
     * @param list
     * @return
     */
    public boolean isCancelSign(List<ResponseVo> list) {
        for (ResponseVo rv : list) {
            if (null == rv || rv.isSuccess() == false) {
                return false;
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ConfirmService#productCancelContractSign(com.tuniu. ord.vo.CancelConfirmInputVo)
     */
    @Override
    public ResponseVo cancelConfirm(CancelConfirmInputVo cancelConfirmInputVo) {
        // TODO Auto-generated method stub
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【cancelConfirm】step 1-1:取消确认参数校验开始");
        ResponseVo responseVo = new ResponseVo();
        // 核损方案号
        Integer agencyLossSchemeId = null;
        try {
            agencyLossSchemeId = cancelConfirmInputVo.getAgencyInfo().getAgencyLossSchemeId();
        } catch (Exception ex) {
            String title = "【API系统支持邮件通知】";
            StringBuffer sb = new StringBuffer();
            sb.append("【取消确认请求参数有问题,");
            sb.append("请核实取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            logger.debug(Log4jLogger.SYSTEM_LOG, title + "====" + sb.toString());
            ORDMailClient.sendMail(title, sb.toString());
            responseVo.setErrorCode(241000);
            responseVo.setMsg(sb.toString());
            return responseVo;
        }

        if (null == agencyLossSchemeId) {
            String title = "【API系统支持邮件通知】";
            StringBuffer sb = new StringBuffer();
            sb.append("【取消确认请求参数中核损方案号为空,");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            logger.debug(Log4jLogger.SYSTEM_LOG, title + "====" + sb.toString());
            ORDMailClient.sendMail(title, sb.toString());
            responseVo.setErrorCode(241000);
            responseVo.setMsg(sb.toString());
            return responseVo;
        }

        // 根据核损单号获取很损详情列表
        List<CheckLossDetail> list = checkLossDetailMapper.getCheckLossNum(agencyLossSchemeId);
        // 根据核损单号获取核损出游人列表
        CheckLossTouristVo checkLossTouristVo = new CheckLossTouristVo();
        checkLossTouristVo.setCheckLossId(agencyLossSchemeId);
        List<CheckLossTourist> lossTouristList = checkLossTouristMapper.getLossTouristList(checkLossTouristVo);
        if (!CollectionUtils.isNotEmpty(list)) {
            String title = "【API系统支持邮件通知】";
            StringBuffer sb = new StringBuffer();
            sb.append("【核损方案号:" + agencyLossSchemeId + "不存在;");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            logger.debug(Log4jLogger.SYSTEM_LOG, title + "====" + sb.toString());
            ORDMailClient.sendMail(title, sb.toString());
            responseVo.setErrorCode(241000);
            responseVo.setMsg(sb.toString());
            return responseVo;
        }

        CheckLoss checkLossCheck = checkLossMapper.selectByPrimaryKey(agencyLossSchemeId);
        if (null == checkLossCheck) {
            logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>【取消确认】根据核损核损方案号查询售卖订单号和需求ID出现异常");
            String title = "【API系统支持邮件通知】";
            StringBuffer sb = new StringBuffer();
            sb.append("【根据核损核损方案号查询售卖订单号和需求ID出现异常");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            ORDMailClient.sendMail(title, sb.toString());
            responseVo.setErrorCode(241000);
            responseVo.setMsg(sb.toString());
            return responseVo;
        }

        OrdDealOrder ordDealOrderCheck = null;
        for (CheckLossDetail checkLossDetail : list) {
            ordDealOrderCheck = ordDealOrderMapper.getOrdDealOrderByRoundId(checkLossDetail.getBatchNumber());
            if (null == ordDealOrderCheck) {
                logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>【取消确认】根据A资源入库批次查询团期和出库单ID出现异常");
                String title = "【API系统支持邮件通知】";
                StringBuffer sb = new StringBuffer();
                sb.append("【根据A资源入库批次查询团期和出库单ID出现异常");
                sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
                ORDMailClient.sendMail(title, sb.toString());
                responseVo.setErrorCode(241000);
                responseVo.setMsg(sb.toString());
                return responseVo;
            }

            ConfirmSalesQueryVo confirmSalesQueryCheck = new ConfirmSalesQueryVo();
            confirmSalesQueryCheck.setOrderId(ordDealOrderCheck.getOrderId());
            confirmSalesQueryCheck.setExtOrderId(checkLossCheck.getSellOrderId());
            confirmSalesQueryCheck.setRequirementId(checkLossCheck.getRequirementId());
            // 根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单
            OrdSalesOrder ordSalesOrderCheck = ordSalesOrderMapper.getSaleByConfirmQuery(confirmSalesQueryCheck);
            if (null == ordSalesOrderCheck) {
                logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>【取消确认】根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单出现异常");
                String title = "【API系统支持邮件通知】";
                StringBuffer sb = new StringBuffer();
                sb.append("【根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单出现异常");
                sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
                ORDMailClient.sendMail(title, sb.toString());
                responseVo.setErrorCode(241000);
                responseVo.setMsg(sb.toString());
                return responseVo;
            }
        }
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【cancelConfirm】step 1-1:取消确认参数校验结束");

        OrdDealOrder ordDealOrder = null;
        DepartDateStock departDateStock = null;
        List<DepartDateStock> dds = null;
        DProductStock dProductStock = null;
        List<DProductStock> dps = null;
        ProductStockVo productStockVo = null;
        CheckLoss checkLoss = null;
        OrdSalesOrder ordSalesOrder = null;
        ResponseVo result = null;
        List<ResponseVo> resultList = new ArrayList<ResponseVo>();

        // 与GRP交互发起核损申请
        LossApplyInfoVo lossApplyInfoVo = new LossApplyInfoVo();
        List<LossApplyInfoDataVo> data = new ArrayList<LossApplyInfoDataVo>();
        LossApplyInfoDataVo lossApplyInfoDataVo = null;
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【cancelConfirm】step 1-2:取消签约向GRP发起核损申请开始");
        // 分批次取消签约
        for (CheckLossDetail checkLossDetail : list) {
            departDateStock = new DepartDateStock();
            dds = new ArrayList<DepartDateStock>();
            dProductStock = new DProductStock();
            dps = new ArrayList<DProductStock>();
            productStockVo = new ProductStockVo();
            result = new ResponseVo();
            checkLoss = new CheckLoss();
            ordSalesOrder = new OrdSalesOrder();
            lossApplyInfoDataVo = new LossApplyInfoDataVo();
            Integer lossAdultNum = checkLossDetail.getAdultCountBefore() - checkLossDetail.getAdultCountAfter();
            Integer lossChildNum = checkLossDetail.getChildCountBefore() - checkLossDetail.getChildCountAfter();
            Integer lossBabyNum = checkLossDetail.getBabyCountBefore() - checkLossDetail.getBabyCountAfter();
            departDateStock.setAdultNum(lossAdultNum + lossChildNum);
            departDateStock.setChildNum(0);
            departDateStock.setBabyNum(lossBabyNum);
            departDateStock.setRecovery((int) checkLossDetail.getResellFlag());
            lossApplyInfoDataVo.setAdultNum(lossAdultNum);
            lossApplyInfoDataVo.setChildNum(lossChildNum);
            BigDecimal totalLoss = checkLossDetail.getLossAdultCustomer().multiply(new BigDecimal(lossAdultNum))
                    .add(checkLossDetail.getLossChildCustomer().multiply(new BigDecimal(lossChildNum)));
            lossApplyInfoDataVo.setTotalLoss(totalLoss);
            lossApplyInfoDataVo.setChargeUid(checkLossDetail.getAddUid());
            lossApplyInfoDataVo.setChargeUname(checkLossDetail.getAddUname());
            lossApplyInfoDataVo.setIsSaleAgain((int) checkLossDetail.getResellFlag());
            lossApplyInfoDataVo.setCheckMode(CheckModeEnum.AUTOMATIC_MODE.getKey());
            // 根据A资源入库批次查询团期和出库单ID
            ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(checkLossDetail.getBatchNumber());
            // 根据核损核损方案号查询售卖订单号和需求ID,
            checkLoss = checkLossMapper.selectByPrimaryKey(agencyLossSchemeId);

            ConfirmSalesQueryVo confirmSalesQueryVo = new ConfirmSalesQueryVo();
            if (null != ordDealOrder) {
                confirmSalesQueryVo.setOrderId(ordDealOrder.getOrderId());
            }
            if (null != checkLoss) {
                confirmSalesQueryVo.setExtOrderId(checkLoss.getSellOrderId());
                confirmSalesQueryVo.setRequirementId(checkLoss.getRequirementId());
            } else {
                logger.debug(Log4jLogger.SYSTEM_LOG, "根据核损核损方案号查询售卖订单号和需求ID出现异常>>>>>>>>>>>>>>>>>>>");
            }
            // 根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单
            ordSalesOrder = ordSalesOrderMapper.getSaleByConfirmQuery(confirmSalesQueryVo);
            DetailVo detailVo = new DetailVo();

            if (null != ordDealOrder) {
                detailVo.setOrderId(ordDealOrder.getOrderId());

                departDateStock.setDepartDate(sdf.format(ordDealOrder.getDepartDate()));
                // departDateStock.setOutId(ordDealOrder.getOutLibraryId());
                departDateStock.setOutId(ordSalesOrder.getExtOrderBatchId());
                dProductStock.setdProductId(ordDealOrder.getProductId());
            } else {
                logger.debug(Log4jLogger.SYSTEM_LOG, "根据A资源入库批次查询团期和出库单ID出现异常>>>>>>>>>>>>>>>");
            }
            dds.add(departDateStock);
            dProductStock.setVendorId(SupplierEnum.TUNIU.getVendorId());
            dProductStock.setDepartDates(dds);
            dps.add(dProductStock);
            productStockVo.setClientFlag(Constants.CLIENT_FLAG);
            productStockVo.setdProducts(dps);
            productStockVo.setSessionId(cancelConfirmInputVo.getSessionId());

            if (null != ordSalesOrder) {
                detailVo.setLossAdultCount(ordSalesOrder.getAdultCount() + ordSalesOrder.getChildCount());

                productStockVo.setOrderId(ordSalesOrder.getId());
                lossApplyInfoDataVo.setOrderId(ordSalesOrder.getId());
                // FIXME 先写死channelId：1
                lossApplyInfoDataVo.setChannelId(1);
                lossApplyInfoDataVo.setChannelOrderId(String.valueOf(ordSalesOrder.getExtOrderId()));
                lossApplyInfoDataVo.setCurrencyType(ordSalesOrder.getCostCurrencyType());
                // 获取D销售单下出游人列表
                PeopleTouristVo peopleTouristVo = new PeopleTouristVo();
                List<Integer> sellOrderIds = new ArrayList<Integer>();
                sellOrderIds.add(ordSalesOrder.getId());
                peopleTouristVo.setSellOrderIds(sellOrderIds);
                List<OrdPeopleTourist> ordPeopleTouristList = ordPeopleTouristMapper.selectByFields(peopleTouristVo);
                List<Integer> peopleIds = new ArrayList<Integer>();
                if (CollectionUtils.isNotEmpty(ordPeopleTouristList)) {
                    for (OrdPeopleTourist ordPeopleTourist : ordPeopleTouristList) {
                        peopleIds.add(ordPeopleTourist.getId());
                    }
                }
                List<Integer> consumerIds = new ArrayList<Integer>();
                if (CollectionUtils.isNotEmpty(lossTouristList)) {
                    // 判断该批次下的出游人
                    for (CheckLossTourist clt : lossTouristList) {
                        if (peopleIds.contains(clt.getOrdPeopleTouristId())) {
                            consumerIds.add(clt.getOrdPeopleTouristId());
                        }
                    }
                } else {
                    logger.debug(Log4jLogger.SYSTEM_LOG, "根据核损单号获取核损出游人列表为空>>>>>>>>>>>>>>>>>>>");
                }
                lossApplyInfoDataVo.setConsumerIds(consumerIds);
            } else {
                logger.debug(Log4jLogger.SYSTEM_LOG, "根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单出现异常>>>>>>>>>>>>>>>>>>>");
            }
            /* 取消签约接口 */
            result = stockProxy.productCancelContractSign(productStockVo);
            /* 调用D产品库存取消签约接口 结束 */
            if (null == result || result.isSuccess() == false) {
                String title = "【D类产品库存技术支持邮件通知】";
                StringBuffer sb = new StringBuffer();
                sb.append("【调用D产品库存取消签约接口异常,详情如下:");
                sb.append("调用D产品库存取消签约请求地址:" + SystemInitParameter.productCancelContractSign + ";");
                sb.append("调用D产品库存取消签约请求参数:" + JsonUtil.toString(productStockVo) + ";");
                if (result == null) {
                    sb.append("调用D产品库存系统的取消签约异常信息:返回结果为空;");
                } else {
                    sb.append("调用D产品库存系统的取消签约异常信息:" + result.getMsg() + ";");
                }
                sb.append("解决方案:D类产品库存技术人员重新调用该接口，确保取消签约成功】");
                logger.debug(Log4jLogger.SYSTEM_LOG, sb.toString());
                ORDMailClient.sendMail(title, sb.toString());
            }

            // 取消确认修改核损成人数
            if (result != null && result.isSuccess() == true) {
                // 回库情况下'
                if (ResellFlagEnum.IS_RESELL.getKey().intValue() == checkLossDetail.getResellFlag().intValue()) {
                    ordPriceDetailMapper.updateOrderDetail(detailVo);
                }
            } else {
                logger.debug(Log4jLogger.SYSTEM_LOG, "取消确认修改核损成人数失败====" + JsonUtil.toString(detailVo));
            }

            resultList.add(result);
            data.add(lossApplyInfoDataVo);
        }
        // 根据分批次取消签约结果，若是成功,向GRP发起核损申请
        if (CollectionUtils.isNotEmpty(resultList)) {
            // 向GRP发起核损申请代码
            lossApplyInfoVo.setData(data);
            gRPProxy.applyLossInfo(lossApplyInfoVo);
        }
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【cancelConfirm】step 1-2:取消签约和向GRP发起核损申请结束");
        // 同步反馈取消确认结果
        responseVo.setErrorCode(241000);
        responseVo.setMsg("成功");
        return responseVo;
    }

}
