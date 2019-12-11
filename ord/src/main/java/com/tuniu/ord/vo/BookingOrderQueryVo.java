/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-25                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;


/**
 * <Description> 接收从D类产品系统查询的数据<br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-23 <br>
 */

public class BookingOrderQueryVo {
    /**
     * productId
     */
    private Integer productId;

    /**
     * productName
     */
    private String productName;

    /**
     * departDate
     */
    private OrderDepartDates[] departDates;

    /**
     * firstDestGroupId
     */
    private Integer firstDestGroupId;

    /**
     * firstGestGroupName
     */
    private String firstGestGroupName;

    /**
     * secDestGroupId
     */
    private Integer secDestGroupId;

    /**
     * secDestGroupName
     */
    private String secDestGroupName;

    /**
     * destCategoryId
     */
    private Integer destCategoryId;

    /**
     * destCategoryName
     */
    private String destCategoryName;

    /**
     * groupId
     */
    private Integer groupId;

    /**
     * groupName
     */
    private String groupName;

    /**
     * distributorId
     */
    private String distributorId;

    /**
     * productManagerId
     */
    private Integer productManagerId;

    /**
     * productManagerName
     */
    private String productManagerName;

    /**
     * get productId
     * 
     * @return Returns the productId.<br>
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * set productId
     * 
     * @param productId
     *            The productId to set. <br>
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * get productName
     * 
     * @return Returns the productName.<br>
     */
    public String getProductName() {
        return productName;
    }

    /**
     * set productName
     * 
     * @param productName
     *            The productName to set. <br>
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * get departDates
     * 
     * @return Returns the departDates.<br>
     */
    public OrderDepartDates[] getDepartDates() {
        return departDates;
    }

    /**
     * set departDates
     * 
     * @param departDates
     *            The departDates to set. <br>
     */
    public void setDepartDates(OrderDepartDates[] departDates) {
        this.departDates = departDates;
    }

    /**
     * get firstDestGroupId
     * 
     * @return Returns the firstDestGroupId.<br>
     */
    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    /**
     * set firstDestGroupId
     * 
     * @param firstDestGroupId
     *            The firstDestGroupId to set. <br>
     */
    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    /**
     * get firstGestGroupName
     * 
     * @return Returns the firstGestGroupName.<br>
     */
    public String getFirstGestGroupName() {
        return firstGestGroupName;
    }

    /**
     * set firstGestGroupName
     * 
     * @param firstGestGroupName
     *            The firstGestGroupName to set. <br>
     */
    public void setFirstGestGroupName(String firstGestGroupName) {
        this.firstGestGroupName = firstGestGroupName;
    }

    /**
     * get secDestGroupId
     * 
     * @return Returns the secDestGroupId.<br>
     */
    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    /**
     * set secDestGroupId
     * 
     * @param secDestGroupId
     *            The secDestGroupId to set. <br>
     */
    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    /**
     * get secDestGroupName
     * 
     * @return Returns the secDestGroupName.<br>
     */
    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    /**
     * set secDestGroupName
     * 
     * @param secDestGroupName
     *            The secDestGroupName to set. <br>
     */
    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName;
    }

    /**
     * get destCategoryId
     * 
     * @return Returns the destCategoryId.<br>
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * set destCategoryId
     * 
     * @param destCategoryId
     *            The destCategoryId to set. <br>
     */
    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    /**
     * get destCategoryName
     * 
     * @return Returns the destCategoryName.<br>
     */
    public String getDestCategoryName() {
        return destCategoryName;
    }

    /**
     * set destCategoryName
     * 
     * @param destCategoryName
     *            The destCategoryName to set. <br>
     */
    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName;
    }

    /**
     * get groupId
     * 
     * @return Returns the groupId.<br>
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * set groupId
     * 
     * @param groupId
     *            The groupId to set. <br>
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * get groupName
     * 
     * @return Returns the groupName.<br>
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * set groupName
     * 
     * @param groupName
     *            The groupName to set. <br>
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * get distributorId
     * 
     * @return Returns the distributorId.<br>
     */
    public String getDistributorId() {
        return distributorId;
    }

    /**
     * set distributorId
     * 
     * @param distributorId
     *            The distributorId to set. <br>
     */
    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    /**
     * get productManagerId
     * 
     * @return Returns the productManagerId.<br>
     */
    public Integer getProductManagerId() {
        return productManagerId;
    }

    /**
     * set productManagerId
     * 
     * @param productManagerId
     *            The productManagerId to set. <br>
     */
    public void setProductManagerId(Integer productManagerId) {
        this.productManagerId = productManagerId;
    }

    /**
     * get productManagerName
     * 
     * @return Returns the productManagerName.<br>
     */
    public String getProductManagerName() {
        return productManagerName;
    }

    /**
     * set productManagerName
     * 
     * @param productManagerName
     *            The productManagerName to set. <br>
     */
    public void setProductManagerName(String productManagerName) {
        this.productManagerName = productManagerName;
    }

}
