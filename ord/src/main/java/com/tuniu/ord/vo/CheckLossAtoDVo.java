package com.tuniu.ord.vo;

import java.io.Serializable;

public class CheckLossAtoDVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4193165305902403177L;

    /**
     * 分配给供应商的唯一身份标识
     */
    private String apiKey;

    /**
     * 请求签名，生成规则参见签名机制
     */
    private String sign;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 会话Id
     */
    private Integer sessionId;

    /**
     * 需求Id
     */
    private Integer requirementId;

    /**
     * 途牛流水号
     */
    private Integer tuniuSerialId;

    /**
     * 途牛订单号
     */
    private Integer tuniuOrderId;

    /**
     * 供应商订单号
     */
    private String agencyOrderId;

    /**
     * 供应商id
     */
    private String agencyId;

    /**
     * 团期（出游日期）
     */
    private String date;

    /**
     * 核损成人数
     */
    private Integer lossAdultNum;

    /**
     * 核损儿童数
     */
    private Integer lossChildNum;

    /**
     * 核损婴儿数
     */
    private Integer lossBabyNum;

    /**
     * 核损游客姓名
     */
    private String touristName;

    /**
     * 核损游客证件类型
     */
    private Integer psptType;

    /**
     * 核损的游客证件号
     */
    private String psptId;

    /**
     * 核损的游客出生日期
     */
    private String birthday;

    /**
     * 核损的游客性别（0-女）
     */
    private Integer sex;

    /**
     * 批次号
     */
    private Integer roundId;
    /**
     * 切位单号
     */
    private Integer extPurchaseId;

    /**
     * 核损有效期
     */
    private String effectiveTime;

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getTuniuSerialId() {
        return tuniuSerialId;
    }

    public void setTuniuSerialId(Integer tuniuSerialId) {
        this.tuniuSerialId = tuniuSerialId;
    }

    public Integer getTuniuOrderId() {
        return tuniuOrderId;
    }

    public void setTuniuOrderId(Integer tuniuOrderId) {
        this.tuniuOrderId = tuniuOrderId;
    }

    public String getAgencyOrderId() {
        return agencyOrderId;
    }

    public void setAgencyOrderId(String agencyOrderId) {
        this.agencyOrderId = agencyOrderId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLossAdultNum() {
        return lossAdultNum;
    }

    public void setLossAdultNum(Integer lossAdultNum) {
        this.lossAdultNum = lossAdultNum;
    }

    public Integer getLossChildNum() {
        return lossChildNum;
    }

    public void setLossChildNum(Integer lossChildNum) {
        this.lossChildNum = lossChildNum;
    }

    public Integer getLossBabyNum() {
        return lossBabyNum;
    }

    public void setLossBabyNum(Integer lossBabyNum) {
        this.lossBabyNum = lossBabyNum;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public Integer getPsptType() {
        return psptType;
    }

    public void setPsptType(Integer psptType) {
        this.psptType = psptType;
    }

    public String getPsptId() {
        return psptId;
    }

    public void setPsptId(String psptId) {
        this.psptId = psptId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

}
