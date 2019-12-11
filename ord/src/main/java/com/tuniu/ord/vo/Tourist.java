/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class Tourist {
    private String touristName;
    private Integer touristType;
    private String firstName;
    private String lastName;
    private String country;
    private String englishName;
    private Integer psptType;
    private String psptId;
    private String psptEndDate;
    private String birthday;
    private Integer sex;
    private String touristTel;
    private Integer hasHkvisa;
    private Integer hkvisaType;
    private Integer isContactTourist;
    private String touristId;

    /* 保存添加出游人的主键,用于组团 */
    private Integer consumerId;

    public Integer getIsContactTourist() {
        return isContactTourist;
    }

    public void setIsContactTourist(Integer isContactTourist) {
        this.isContactTourist = isContactTourist;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public Integer getTouristType() {
        return touristType;
    }

    public void setTouristType(Integer touristType) {
        this.touristType = touristType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
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

    public String getPsptEndDate() {
        return psptEndDate;
    }

    public void setPsptEndDate(String psptEndDate) {
        this.psptEndDate = psptEndDate;
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

    public String getTouristTel() {
        return touristTel;
    }

    public void setTouristTel(String touristTel) {
        this.touristTel = touristTel;
    }

    public Integer getHasHkvisa() {
        return hasHkvisa;
    }

    public void setHasHkvisa(Integer hasHkvisa) {
        this.hasHkvisa = hasHkvisa;
    }

    public Integer getHkvisaType() {
        return hkvisaType;
    }

    public void setHkvisaType(Integer hkvisaType) {
        this.hkvisaType = hkvisaType;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }
}
