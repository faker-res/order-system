/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.vo.common.BaseVo;

/**
 * @author zhairongping
 *
 */
public class LossApplyInfoVo extends BaseVo {
    private String uid;
    private String token;
    private String nickname;
    private String ternentId;
    private String timestamp;
    private Integer sessionId;
    private List<LossApplyInfoDataVo> data;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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

    public String getTernentId() {
        return ternentId;
    }

    public void setTernentId(String ternentId) {
        this.ternentId = ternentId;
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

    public List<LossApplyInfoDataVo> getData() {
        return data;
    }

    public void setData(List<LossApplyInfoDataVo> data) {
        this.data = data;
    }

}
