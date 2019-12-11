/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

/**
 * 会员查询条件
 * 
 * @author zhairongping
 *
 */
public class MemberQueryInputVo extends DMSQueryBaseInputVo {
    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 公司名称全称
     */
    private String fullName;

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

}
