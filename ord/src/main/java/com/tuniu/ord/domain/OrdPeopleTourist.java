/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 15:04:54 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

import java.util.Date;

/**
 * OrdPeopleTourist ord_people_tourist
 */
public class OrdPeopleTourist extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ���۶���������ID ord_people_tourist.sell_order_id
     */
    private Integer sellOrderId;

    /**
     * fab������id ord_people_tourist.fab_contact_id
     */
    private String fabContactId;

    /**
     * fab������name ord_people_tourist.fab_contact_name
     */
    private String fabContactName;

    /**
     * ���˶�ͯ���λ������Ϊ0����ͯΪ1 ord_people_tourist.tourist_type
     */
    private String touristType;

    /**
     * ���� ord_people_tourist.name
     */
    private String name;

    /**
     * �� ord_people_tourist.firstname
     */
    private String firstname;

    /**
     * �� ord_people_tourist.lastname
     */
    private String lastname;

    /**
     * �� ord_people_tourist.country
     */
    private String country;

    /**
     * �Ա�1Ϊ�� 0ΪŮ 9Ϊδ֪ ord_people_tourist.sex
     */
    private String sex;

    /**
     * ֤����� 1=���֤ 2=���� 3=���֤ 4=�۰�ͨ��֤ 6=���� 7=̨��֤ ord_people_tourist.pspt_type
     */
    private String psptType;

    /**
     * ֤������ ord_people_tourist.pspt_id
     */
    private String psptId;

    /**
     * ֤����Ч�� ord_people_tourist.pspt_end_date
     */
    private Date psptEndDate;

    /**
     * �������� ord_people_tourist.birth_of_date
     */
    private Date birthOfDate;

    /**
     * ���� ord_people_tourist.age
     */
    private Integer age;

    /**
     * �ֻ� ord_people_tourist.tel
     */
    private String tel;

    /**
     * �Ƿ�Ĭ����ϵ�ˣ�'1ΪĬ����ϵ�� 0Ϊ��' ord_people_tourist.is_default
     */
    private Integer isDefault;

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public String getFabContactId() {
        return fabContactId;
    }

    public void setFabContactId(String fabContactId) {
        this.fabContactId = fabContactId;
    }

    public String getFabContactName() {
        return fabContactName;
    }

    public void setFabContactName(String fabContactName) {
        this.fabContactName = fabContactName;
    }

    public String getTouristType() {
        return touristType;
    }

    public void setTouristType(String touristType) {
        this.touristType = touristType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPsptType() {
        return psptType;
    }

    public void setPsptType(String psptType) {
        this.psptType = psptType;
    }

    public String getPsptId() {
        return psptId;
    }

    public void setPsptId(String psptId) {
        this.psptId = psptId;
    }

    public Date getPsptEndDate() {
        return psptEndDate;
    }

    public void setPsptEndDate(Date psptEndDate) {
        this.psptEndDate = psptEndDate;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}