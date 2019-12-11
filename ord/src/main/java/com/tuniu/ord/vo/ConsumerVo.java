/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月15日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 * 
 */
public class ConsumerVo {
    private Integer consumerId;
    private String consumerName;
    private Integer sex;
    private Integer consumerAge;
    private String consumerBirthday;
    private Integer cardType;
    private String consumerCardNo;
    private String consumerPhone;
    private Integer consumerAgeSegment;
    private Integer consumerLevel;
    /**
     * 会员ID
     */
    private Integer fabId;

    /**
     * @return the fabId
     */
    public Integer getFabId() {
        return fabId;
    }

    /**
     * @param fabId
     *            the fabId to set
     */
    public void setFabId(Integer fabId) {
        this.fabId = fabId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public Integer getConsumerAge() {
        return consumerAge;
    }

    public void setConsumerAge(Integer consumerAge) {
        this.consumerAge = consumerAge;
    }

    public String getConsumerBirthday() {
        return consumerBirthday;
    }

    public void setConsumerBirthday(String consumerBirthday) {
        this.consumerBirthday = consumerBirthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getConsumerCardNo() {
        return consumerCardNo;
    }

    public void setConsumerCardNo(String consumerCardNo) {
        this.consumerCardNo = consumerCardNo;
    }

    public String getConsumerPhone() {
        return consumerPhone;
    }

    public void setConsumerPhone(String consumerPhone) {
        this.consumerPhone = consumerPhone;
    }

    public Integer getConsumerAgeSegment() {
        return consumerAgeSegment;
    }

    public void setConsumerAgeSegment(Integer consumerAgeSegment) {
        this.consumerAgeSegment = consumerAgeSegment;
    }

    public Integer getConsumerLevel() {
        return consumerLevel;
    }

    public void setConsumerLevel(Integer consumerLevel) {
        this.consumerLevel = consumerLevel;
    }

}
