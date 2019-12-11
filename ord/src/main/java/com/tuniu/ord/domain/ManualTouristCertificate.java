package com.tuniu.ord.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.common.util.JackJsonDateFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateParse;

public class ManualTouristCertificate extends BaseDomain{

    /**
     * 
     */
    private static final long serialVersionUID = -1889051594217030866L;

    private Integer touristId;

    private Integer psptType;

    private String psptId;

    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date psptEndDate;

    public Integer getTouristId() {
        return touristId;
    }

    public void setTouristId(Integer touristId) {
        this.touristId = touristId;
    }

    /**
     * @return the psptType
     */
    public Integer getPsptType() {
        return psptType;
    }

    /**
     * @param psptType the psptType to set
     */
    public void setPsptType(Integer psptType) {
        this.psptType = psptType;
    }

    public String getPsptId() {
        return psptId;
    }

    public void setPsptId(String psptId) {
        this.psptId = psptId == null ? null : psptId.trim();
    }

    public Date getPsptEndDate() {
        return psptEndDate;
    }

    public void setPsptEndDate(Date psptEndDate) {
        this.psptEndDate = psptEndDate;
    }
}