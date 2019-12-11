/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

import java.io.Serializable;

/**
 * 会员查询结果
 * 
 * @author zhairongping
 *
 */
public class MemberQueryOutputVo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 会员ID
     */
    private Integer id;
    /**
     * 公司名称全称
     */
    private String fullName;

    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the memberId
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     *            the memberId to set
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

}
