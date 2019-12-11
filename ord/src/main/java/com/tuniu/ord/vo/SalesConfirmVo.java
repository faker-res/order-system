/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月15日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.vo.common.BaseVo;

/**
 * @author zhairongping
 *
 */
public class SalesConfirmVo extends BaseVo {
    private Integer uid;
    private String token;
    private String nickname;
    private String tenantId;
    private String timestamp;
    private Integer sessionId;
    private List<SaleVo> sales;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public List<SaleVo> getSales() {
        return sales;
    }

    public void setSales(List<SaleVo> sales) {
        this.sales = sales;
    }

}
