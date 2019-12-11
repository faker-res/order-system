package com.tuniu.ord.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.common.util.JackJsonDateFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateParse;

public class ChangeProductVo extends BaseManualVo{
    
    private Integer dOrderId;
    
    private Integer productId;
    
    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departDate;

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
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    /**
     * @return the departDate
     */
    public Date getDepartDate() {
        return departDate;
    }
    /**
     * @param departDate the departDate to set
     */
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }
    
}
