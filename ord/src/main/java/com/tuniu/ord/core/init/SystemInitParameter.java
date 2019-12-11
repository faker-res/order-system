package com.tuniu.ord.core.init;

/**
 * @author fangzhongwei
 * 
 */
public final class SystemInitParameter {

    /**
     * 以下是邮件配置参数
     */
    public static String mailHost;

    public static String mailRobot;

    public static String mailUserName;

    public static String mailPassword;

    public static String mailProtocol;

    public static int mailPort;

    public static Integer mailTimeout;

    /**
     * 以下是日志配置参数
     */
    public static String level;
    public static String logFilePath;
    public static String logConfigPath;

    public static String checkRespToAPI;

    public static String appSecretForAPI;

    public static String productContractSign;

    public static String productCancelContractSign;

    public static String confirmFeedBackURL;

    /**
     * 查询产品信息
     */
    public static String queryPrdInfo;

    /**
     * D类产品占位
     */
    public static String dprdStockOccupyUrl;

    /**
     * D类产品取消占位
     */
    public static String dprdStockCancelOccupyUrl;

    /**
     * 销售单下单
     */
    public static String sendSalesConfirm;

    /**
     * D类向GRP发起核损申请地址
     */
    public static String applyLossInfo;

    /**
     * 查询升级方案
     */
    public static String getUpgrade;

    /**
     * 针对订单系统查询产品信息
     */
    public static String getProductInfoToOrd;

    /**
     * 环境标志 :开发和测试环境-1 生产环境-2
     */
    public static Integer envFlag;

    public static String getProductCombinationInfo;

    public static String getOrderTourInfo;

    /**
     * 发起确认
     */
    public static String newInitiateConfirmAddress;

    /**
     * 步骤
     */
    public static Integer stepId;

    /**
     * 处理核损的页面
     */
    public static String doCheckLossException;

    /**
     * 核损页面
     */
    public static String doCheckLossPage;

    /**
     * 产品资源信息地址
     */
    public static String productResAddress;

    /**
     * 根据公司名称模糊搜索
     */
    public static String queryMemberByFullName;

    /**
     * 根据会员ID查询信息
     */
    public static String queryMemberById;

    /**
     * 查询对接人信息接口
     */
    public static String queryMemberContacts;

    /**
     * 人工下单查询产品信息
     */
    public static String queryManualProductInfo;

    public static String queryGroupBaseUrl;

    /**
     * 查看产品基本信息和类目信息接口
     */
    private static String queryProductInfo;

    /**
     * 根据资源查询产品
     */
    public static String queryProByResId;

    /**
     * 查询产品基本信息
     */
    public static String queryProByProId;

    /**
     * 查询团信息
     */
    public static String queryTour;

    /**
     * @return the queryProByProId
     */
    public static String getQueryProByProId() {
        return queryProByProId;
    }

    /**
     * @param queryProByProId
     *            the queryProByProId to set
     */
    public static void setQueryProByProId(String queryProByProId) {
        SystemInitParameter.queryProByProId = queryProByProId;
    }

    /**
     * @return the queryTour
     */
    public static String getQueryTour() {
        return queryTour;
    }

    /**
     * @param queryTour
     *            the queryTour to set
     */
    public static void setQueryTour(String queryTour) {
        SystemInitParameter.queryTour = queryTour;
    }

    /**
     * @return the queryProByResId
     */
    public static String getQueryProByResId() {
        return queryProByResId;
    }

    /**
     * @param queryProByResId
     *            the queryProByResId to set
     */
    public static void setQueryProByResId(String queryProByResId) {
        SystemInitParameter.queryProByResId = queryProByResId;
    }

    public static String getDoCheckLossPage() {
        return doCheckLossPage;
    }

    public static void setDoCheckLossPage(String doCheckLossPage) {
        SystemInitParameter.doCheckLossPage = doCheckLossPage;
    }

    public static String getDoCheckLossException() {
        return doCheckLossException;
    }

    public static void setDoCheckLossException(String doCheckLossException) {
        SystemInitParameter.doCheckLossException = doCheckLossException;
    }

    public static Integer getStepId() {
        return stepId;
    }

    public static void setStepId(Integer stepId) {
        SystemInitParameter.stepId = stepId;
    }

    public static Integer getEnvFlag() {
        return envFlag;
    }

    public static void setEnvFlag(Integer envFlag) {
        SystemInitParameter.envFlag = envFlag;
    }

    public static String getGetProductInfoToOrd() {
        return getProductInfoToOrd;
    }

    public static void setGetProductInfoToOrd(String getProductInfoToOrd) {
        SystemInitParameter.getProductInfoToOrd = getProductInfoToOrd;
    }

    public static String getProductContractSign() {
        return productContractSign;
    }

    public static String getApplyLossInfo() {
        return applyLossInfo;
    }

    public static void setApplyLossInfo(String applyLossInfo) {
        SystemInitParameter.applyLossInfo = applyLossInfo;
    }

    public static void setProductContractSign(String productContractSign) {
        SystemInitParameter.productContractSign = productContractSign;
    }

    public static String getProductCancelContractSign() {
        return productCancelContractSign;
    }

    public static void setProductCancelContractSign(String productCancelContractSign) {
        SystemInitParameter.productCancelContractSign = productCancelContractSign;
    }

    public static String getConfirmFeedBackURL() {
        return confirmFeedBackURL;
    }

    public static void setConfirmFeedBackURL(String confirmFeedBackURL) {
        SystemInitParameter.confirmFeedBackURL = confirmFeedBackURL;
    }

    public static String getMailHost() {
        return mailHost;
    }

    public static void setMailHost(String mailHost) {
        SystemInitParameter.mailHost = mailHost;
    }

    public static String getMailRobot() {
        return mailRobot;
    }

    public static void setMailRobot(String mailRobot) {
        SystemInitParameter.mailRobot = mailRobot;
    }

    public static String getMailUserName() {
        return mailUserName;
    }

    public static void setMailUserName(String mailUserName) {
        SystemInitParameter.mailUserName = mailUserName;
    }

    public static String getMailPassword() {
        return mailPassword;
    }

    public static void setMailPassword(String mailPassword) {
        SystemInitParameter.mailPassword = mailPassword;
    }

    public static String getMailProtocol() {
        return mailProtocol;
    }

    public static void setMailProtocol(String mailProtocol) {
        SystemInitParameter.mailProtocol = mailProtocol;
    }

    public static int getMailPort() {
        return mailPort;
    }

    public static void setMailPort(int mailPort) {
        SystemInitParameter.mailPort = mailPort;
    }

    public static Integer getMailTimeout() {
        return mailTimeout;
    }

    public static void setMailTimeout(Integer mailTimeout) {
        SystemInitParameter.mailTimeout = mailTimeout;
    }

    public static String getLevel() {
        return level;
    }

    public static void setLevel(String level) {
        SystemInitParameter.level = level;
    }

    public static String getLogFilePath() {
        return logFilePath;
    }

    public static void setLogFilePath(String logFilePath) {
        SystemInitParameter.logFilePath = logFilePath;
    }

    public static String getLogConfigPath() {
        return logConfigPath;
    }

    public static void setLogConfigPath(String logConfigPath) {
        SystemInitParameter.logConfigPath = logConfigPath;
    }

    public static String getCheckRespToAPI() {
        return checkRespToAPI;
    }

    public static void setCheckRespToAPI(String checkRespToAPI) {
        SystemInitParameter.checkRespToAPI = checkRespToAPI;
    }

    public static String getAppSecretForAPI() {
        return appSecretForAPI;
    }

    public static void setAppSecretForAPI(String appSecretForAPI) {
        SystemInitParameter.appSecretForAPI = appSecretForAPI;
    }

    /**
     * get queryPrdInfo
     * 
     * @return Returns the queryPrdInfo.<br>
     */
    public static String getQueryPrdInfo() {
        return queryPrdInfo;
    }

    /**
     * set queryPrdInfo
     * 
     * @param queryPrdInfo
     *            The queryPrdInfo to set. <br>
     */
    public static void setQueryPrdInfo(String queryPrdInfo) {
        SystemInitParameter.queryPrdInfo = queryPrdInfo;
    }

    /**
     * get dprdStockOccupyUrl
     * 
     * @return Returns the dprdStockOccupyUrl.<br>
     */
    public static String getDprdStockOccupyUrl() {
        return dprdStockOccupyUrl;
    }

    /**
     * set dprdStockOccupyUrl
     * 
     * @param dprdStockOccupyUrl
     *            The dprdStockOccupyUrl to set. <br>
     */
    public static void setDprdStockOccupyUrl(String dprdStockOccupyUrl) {
        SystemInitParameter.dprdStockOccupyUrl = dprdStockOccupyUrl;
    }

    /**
     * get dprdStockCancelOccupyUrl
     * 
     * @return Returns the dprdStockCancelOccupyUrl.<br>
     */
    public static String getDprdStockCancelOccupyUrl() {
        return dprdStockCancelOccupyUrl;
    }

    /**
     * set dprdStockCancelOccupyUrl
     * 
     * @param dprdStockCancelOccupyUrl
     *            The dprdStockCancelOccupyUrl to set. <br>
     */
    public static void setDprdStockCancelOccupyUrl(String dprdStockCancelOccupyUrl) {
        SystemInitParameter.dprdStockCancelOccupyUrl = dprdStockCancelOccupyUrl;
    }

    public static String getSendSalesConfirm() {
        return sendSalesConfirm;
    }

    public static void setSendSalesConfirm(String sendSalesConfirm) {
        SystemInitParameter.sendSalesConfirm = sendSalesConfirm;
    }

    public static String getGetUpgrade() {
        return getUpgrade;
    }

    public static void setGetUpgrade(String getUpgrade) {
        SystemInitParameter.getUpgrade = getUpgrade;
    }

    public static String getGetProductCombinationInfo() {
        return getProductCombinationInfo;
    }

    public static void setGetProductCombinationInfo(String getProductCombinationInfo) {
        SystemInitParameter.getProductCombinationInfo = getProductCombinationInfo;
    }

    public static String getGetOrderTourInfo() {
        return getOrderTourInfo;
    }

    public static void setGetOrderTourInfo(String getOrderTourInfo) {
        SystemInitParameter.getOrderTourInfo = getOrderTourInfo;
    }

    /**
     * @return the newInitiateConfirmAddress
     */
    public static String getNewInitiateConfirmAddress() {
        return newInitiateConfirmAddress;
    }

    /**
     * @param newInitiateConfirmAddress
     *            the newInitiateConfirmAddress to set
     */
    public static void setNewInitiateConfirmAddress(String newInitiateConfirmAddress) {
        SystemInitParameter.newInitiateConfirmAddress = newInitiateConfirmAddress;
    }

    /**
     * @return the productResAddress
     */
    public static String getProductResAddress() {
        return productResAddress;
    }

    /**
     * @param productResAddress
     *            the productResAddress to set
     */
    public static void setProductResAddress(String productResAddress) {
        SystemInitParameter.productResAddress = productResAddress;
    }

    /**
     * @return the queryMemberByFullName
     */
    public static String getQueryMemberByFullName() {
        return queryMemberByFullName;
    }

    /**
     * @param queryMemberByFullName
     *            the queryMemberByFullName to set
     */
    public static void setQueryMemberByFullName(String queryMemberByFullName) {
        SystemInitParameter.queryMemberByFullName = queryMemberByFullName;
    }

    /**
     * @return the queryMemberContacts
     */
    public static String getQueryMemberContacts() {
        return queryMemberContacts;
    }

    /**
     * @param queryMemberContacts
     *            the queryMemberContacts to set
     */
    public static void setQueryMemberContacts(String queryMemberContacts) {
        SystemInitParameter.queryMemberContacts = queryMemberContacts;
    }

    public static String getQueryManualProductInfo() {
        return queryManualProductInfo;
    }

    public static void setQueryManualProductInfo(String queryManualProductInfo) {
        SystemInitParameter.queryManualProductInfo = queryManualProductInfo;
    }

    public static String getQueryGroupBaseUrl() {
        return queryGroupBaseUrl;
    }

    public static void setQueryGroupBaseUrl(String queryGroupBaseUrl) {
        SystemInitParameter.queryGroupBaseUrl = queryGroupBaseUrl;
    }

    /**
     * @return the queryProductInfo
     */
    public static String getQueryProductInfo() {
        return queryProductInfo;
    }

    /**
     * @param queryProductInfo
     *            the queryProductInfo to set
     */
    public static void setQueryProductInfo(String queryProductInfo) {
        SystemInitParameter.queryProductInfo = queryProductInfo;
    }

    public static String getQueryMemberById() {
        return queryMemberById;
    }

    public static void setQueryMemberById(String queryMemberById) {
        SystemInitParameter.queryMemberById = queryMemberById;
    }

}
