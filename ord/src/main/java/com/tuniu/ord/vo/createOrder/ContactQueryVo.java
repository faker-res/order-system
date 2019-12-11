/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

/**
 * 联系人查询条件
 * 
 * @author zhairongping
 *
 */
public class ContactQueryVo extends MemberQueryInputVo {
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 称谓 1:先生 0:女士
     */
    private Integer appellation;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 电话
     */
    private String tel;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 传真
     */
    private String fax;

    /**
     * 订单号
     */
    private Integer manualOrderId;

    /**
     * @return the manualOrderId
     */
    public Integer getManualOrderId() {
        return manualOrderId;
    }

    /**
     * @param manualOrderId
     *            the manualOrderId to set
     */
    public void setManualOrderId(Integer manualOrderId) {
        this.manualOrderId = manualOrderId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the appellation
     */
    public Integer getAppellation() {
        return appellation;
    }

    /**
     * @param appellation
     *            the appellation to set
     */
    public void setAppellation(Integer appellation) {
        this.appellation = appellation;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
