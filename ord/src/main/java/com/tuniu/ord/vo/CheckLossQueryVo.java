package com.tuniu.ord.vo;

public class CheckLossQueryVo extends BaseQueryVo {
    private static final long serialVersionUID = -2452950594008421755L;

    /**
     * 产品Id
     */
    private int productId;

    /**
     * 负责人Id
     */
    private Integer userId;

    /**
     * 售卖渠道code
     */
    private String sellChannel;

    /**
     * 销售订单Id主键
     */
    private int sellOrderId;

    /**
     * 出游日期
     */
    private String tourDate;

    /**
     * 核损单状态
     */
    private String status;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSellChannel() {
        return sellChannel;
    }

    public void setSellChannel(String sellChannel) {
        this.sellChannel = sellChannel;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public String getTourDate() {
        return tourDate;
    }

    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
