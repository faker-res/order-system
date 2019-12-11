/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

/**
 * 产品查询条件
 * 
 * @author zhairongping
 *
 */
public class RequirementQueryVo extends MemberQueryInputVo {
    /**
     * 切位单号
     */
    private Integer dOrderId;
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 出发日期
     */
    private String startDate;
    /**
     * 返回日期
     */
    private String endDate;
    /**
     * 目的地大类ID
     */
    private Integer destCategoryId;
    /**
     * 目的地大类名称
     */
    private String destCategoryName;
    /**
     * 一级目的地分组ID
     */
    private Integer firstDestGroupId;
    /**
     * 一级目的地分组名称
     */
    private String firstDestGroupName;
    /**
     * 二级目的地分组ID
     */
    private Integer secDestGroupId;
    /**
     * 二级目的地分组名称
     */
    private String secDestGroupName;

    /**
     * 目的地
     */
    private Integer destId;

    /**
     * 目的地名称
     */
    private String destName;

    /**
     * @return the dOrderId
     */
    public Integer getdOrderId() {
        return dOrderId;
    }

    /**
     * @param dOrderId the dOrderId to set
     */
    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the destCategoryId
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * @param destCategoryId
     *            the destCategoryId to set
     */
    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    /**
     * @return the firstDestGroupId
     */
    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    /**
     * @param firstDestGroupId
     *            the firstDestGroupId to set
     */
    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    /**
     * @return the secDestGroupId
     */
    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    /**
     * @param secDestGroupId
     *            the secDestGroupId to set
     */
    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the destCategoryName
     */
    public String getDestCategoryName() {
        return destCategoryName;
    }

    /**
     * @param destCategoryName
     *            the destCategoryName to set
     */
    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName;
    }

    /**
     * @return the firstDestGroupName
     */
    public String getFirstDestGroupName() {
        return firstDestGroupName;
    }

    /**
     * @param firstDestGroupName
     *            the firstDestGroupName to set
     */
    public void setFirstDestGroupName(String firstDestGroupName) {
        this.firstDestGroupName = firstDestGroupName;
    }

    /**
     * @return the secDestGroupName
     */
    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    /**
     * @param secDestGroupName
     *            the secDestGroupName to set
     */
    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName;
    }

    /**
     * @return the destId
     */
    public Integer getDestId() {
        return destId;
    }

    /**
     * @param destId
     *            the destId to set
     */
    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    /**
     * @return the destName
     */
    public String getDestName() {
        return destName;
    }

    /**
     * @param destName
     *            the destName to set
     */
    public void setDestName(String destName) {
        this.destName = destName;
    }

}
