package com.tuniu.ord.vo.external;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tuniu.ord.common.util.JackJsonDateFormat;
import com.tuniu.ord.domain.BaseDomain;

/**
 * ProductSellChannel product_sell_channel
 */
public class ProductSellChannel extends BaseDomain {

    private static final long serialVersionUID = 9198410034866806254L;

    /**
     * 产品ID product_sell_channel.product_id
     */
    private Integer productId;

    /**
     * 渠道code product_sell_channel.sell_channel
     */
    private String sellChannel;

    /**
     * 团期 product_sell_channel.tour_date
     */
    @JsonSerialize(using = JackJsonDateFormat.class)
    private String tourDate;

    /**
     * 渠道数量 product_sell_channel.channel_count
     */
    private Integer channelCount;

    /**
     * 报价规则方式:1:固定报价；2:*式加价；3:+式加价 product_sell_channel.price_type
     */
    private Byte priceType;

    /**
     * 成人价 product_sell_channel.adult_price
     */
    private BigDecimal adultPrice;

    /**
     * 儿童价 product_sell_channel.child_price
     */
    private BigDecimal childPrice;

    /**
     * 单房差 product_sell_channel.single_room_price
     */
    private BigDecimal singleRoomPrice;

    /**
     * 币种类型 product_sell_channel.currency_type
     */
    private String currencyType;

    /**
     * 售卖渠道状态，40:完成组团；50:产品审核；60:完成发布
     */
    private Integer sellChannelStatus;

    /**
     * 对应生成D订单号，product_sell_channel.dord_id
     */
    private Integer dordId;

    /**
     * 渠道类型 1 库存 2 现询
     */
    private Integer channelType;

    /**
     * get dordId
     * 
     * @return Returns the dordId.<br>
     */
    public Integer getDordId() {
        return dordId;
    }

    /**
     * set dordId
     * 
     * @param dordId
     *            The dordId to set. <br>
     */
    public void setDordId(Integer dordId) {
        this.dordId = dordId;
    }

    /**
     * get serialversionuid
     * 
     * @return Returns the serialversionuid.<br>
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return product_sell_channel.product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param Integer
     *            productId (product_sell_channel.product_id )
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return product_sell_channel.sell_channel
     */
    public String getSellChannel() {
        return sellChannel;
    }

    /**
     * @param String
     *            sellChannel (product_sell_channel.sell_channel )
     */
    public void setSellChannel(String sellChannel) {
        this.sellChannel = sellChannel == null ? null : sellChannel.trim();
    }

    /**
     * @return product_sell_channel.tour_date
     */
    public String getTourDate() {
        return tourDate;
    }

    /**
     * @param Date
     *            tourDate (product_sell_channel.tour_date )
     */
    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    /**
     * @return product_sell_channel.channel_count
     */
    public Integer getChannelCount() {
        return channelCount;
    }

    /**
     * @param Integer
     *            channelCount (product_sell_channel.channel_count )
     */
    public void setChannelCount(Integer channelCount) {
        this.channelCount = channelCount;
    }

    /**
     * @return product_sell_channel.price_type
     */
    public Byte getPriceType() {
        return priceType;
    }

    /**
     * @param Byte
     *            priceType (product_sell_channel.price_type )
     */
    public void setPriceType(Byte priceType) {
        this.priceType = priceType;
    }

    /**
     * @return product_sell_channel.adult_price
     */
    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    /**
     * @param BigDecimal
     *            adultPrice (product_sell_channel.adult_price )
     */
    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    /**
     * @return product_sell_channel.child_price
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * @param BigDecimal
     *            childPrice (product_sell_channel.child_price )
     */
    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    /**
     * @return product_sell_channel.single_room_price
     */
    public BigDecimal getSingleRoomPrice() {
        return singleRoomPrice;
    }

    /**
     * @param BigDecimal
     *            singleRoomPrice (product_sell_channel.single_room_price )
     */
    public void setSingleRoomPrice(BigDecimal singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    /**
     * @return product_sell_channel.currency_type
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * @param String
     *            currencyType (product_sell_channel.currency_type )
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    public Integer getSellChannelStatus() {
        return sellChannelStatus;
    }

    public void setSellChannelStatus(Integer sellChannelStatus) {
        this.sellChannelStatus = sellChannelStatus;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

}