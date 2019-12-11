package com.tuniu.ord.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.jsondeseralize.JackJsonDateTimeFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateTimeParse;

/**
 * @author fangzhongwei
 */
public abstract class BaseDomain implements Serializable {

    private static final long serialVersionUID = -4044748596355664588L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 添加时间 product_base.add_time
     */
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    /**
     * 添加人ID product_base.add_uid
     */
    private Integer addUid;

    /**
     * 添加人名称 product_base.add_uname
     */
    private String addUname;

    /**
     * 更新时间 product_base.update_time
     */
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人ID product_base.update_uid
     */
    private Integer updateUid;

    /**
     * 更新人名称 product_base.update_uname
     */
    private String updateUname;

    /**
     * 删除标识，0:未删除；1:已删除 product_base.del_flag
     */
    private Integer delFlag;

    public Date getAddTime() {
        return addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getAddUid() {
        return addUid;
    }

    public void setAddUid(Integer addUid) {
        this.addUid = addUid;
    }

    public String getAddUname() {
        return addUname;
    }

    public void setAddUname(String addUname) {
        this.addUname = addUname;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public String getUpdateUname() {
        return updateUname;
    }

    public void setUpdateUname(String updateUname) {
        this.updateUname = updateUname;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
