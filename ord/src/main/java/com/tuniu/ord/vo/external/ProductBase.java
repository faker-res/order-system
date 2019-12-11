package com.tuniu.ord.vo.external;

import java.math.BigDecimal;

import com.tuniu.ord.domain.BaseDomain;

/**
 * ProductBase product_base
 */
public class ProductBase extends BaseDomain {

    private static final long serialVersionUID = -2671537488910472315L;

    /**
     * 名称 product_base.product_name
     */
    private String productName;

    /**
     * 产品质量分级 product_base.quality_level
     */
    private Integer qualityLevel;

    /**
     * 产品特色词 product_base.characteristic_word
     */
    private String characteristicWord;

    /**
     * 目的地大类ID product_base.dest_category_id
     */
    private Integer destCategoryId;

    /**
     * 目的地大类名称 product_base.dest_category_name
     */
    private String destCategoryName;

    /**
     * 一级目的地分组ID product_base.first_dest_group_id
     */
    private Integer firstDestGroupId;

    /**
     * 一级目的地分组名称 product_base.first_dest_group_name
     */
    private String firstDestGroupName;

    /**
     * 二级目的地分组ID product_base.sec_dest_group_id
     */
    private Integer secDestGroupId;

    /**
     * 二级目的地分组名称 product_base.sec_dest_group_name
     */
    private String secDestGroupName;

    private Integer destId;
    private String destName;
    private Integer productLineId;

    /**
     * 最低价 product_base.lowest_price
     */
    private BigDecimal lowestPrice;

    /**
     * 最高价 product_base.highest_price
     */
    private BigDecimal highestPrice;

    /**
     * 币种，0：人民币 product_base.currency_type
     */
    private String currencyType;

    /**
     * 备注事项 product_base.remarks
     */
    private String remarks;

    /**
     * 字典码 product_base.group_type
     */
    private String groupType;

    /**
     * 产品状态，0:编辑中；1:完成编辑；2:组团中；3:完成组团；4:产品发布中；5:完成发布 product_base.status
     */
    private Integer status;

    /**
     * @return product_base.product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param String
     *            productName (product_base.product_name )
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getQualityLevel() {
        return qualityLevel;
    }

    public void setQualityLevel(Integer qualityLevel) {
        this.qualityLevel = qualityLevel;
    }

    /**
     * @return product_base.characteristic_word
     */
    public String getCharacteristicWord() {
        return characteristicWord;
    }

    /**
     * @param String
     *            characteristicWord (product_base.characteristic_word )
     */
    public void setCharacteristicWord(String characteristicWord) {
        this.characteristicWord = characteristicWord == null ? null : characteristicWord.trim();
    }

    /**
     * @return product_base.dest_category_id
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * @param Integer
     *            destCategoryId (product_base.dest_category_id )
     */
    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    /**
     * @return product_base.dest_category_name
     */
    public String getDestCategoryName() {
        return destCategoryName;
    }

    /**
     * @param String
     *            destCategoryName (product_base.dest_category_name )
     */
    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName == null ? null : destCategoryName.trim();
    }

    /**
     * @return product_base.first_dest_group_id
     */
    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    /**
     * @param Integer
     *            firstDestGroupId (product_base.first_dest_group_id )
     */
    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    /**
     * @return product_base.first_dest_group_name
     */
    public String getFirstDestGroupName() {
        return firstDestGroupName;
    }

    /**
     * @param String
     *            firstDestGroupName (product_base.first_dest_group_name )
     */
    public void setFirstDestGroupName(String firstDestGroupName) {
        this.firstDestGroupName = firstDestGroupName == null ? null : firstDestGroupName.trim();
    }

    /**
     * @return product_base.sec_dest_group_id
     */
    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    /**
     * @param Integer
     *            secDestGroupId (product_base.sec_dest_group_id )
     */
    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    /**
     * @return product_base.sec_dest_group_name
     */
    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    /**
     * @param String
     *            secDestGroupName (product_base.sec_dest_group_name )
     */
    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName == null ? null : secDestGroupName.trim();
    }

    /**
     * @return product_base.lowest_price
     */
    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    /**
     * @param BigDecimal
     *            lowestPrice (product_base.lowest_price )
     */
    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    /**
     * @return product_base.highest_price
     */
    public BigDecimal getHighestPrice() {
        return highestPrice;
    }

    /**
     * @param BigDecimal
     *            highestPrice (product_base.highest_price )
     */
    public void setHighestPrice(BigDecimal highestPrice) {
        this.highestPrice = highestPrice;
    }

    /**
     * @return product_base.currency_type
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * @param String
     *            currencyType (product_base.currency_type )
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    /**
     * @return product_base.remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param String
     *            remarks (product_base.remarks )
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * @return product_base.group_type
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * @param String
     *            groupType (product_base.group_type )
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

}