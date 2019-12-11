/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-23                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import com.tuniu.ord.domain.BaseDomain;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-21 <br>
 */

public class CancelOrderVo extends BaseDomain {

    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 6884284753298278833L;

    /**
     * orderIds.<br>
     */
    private Integer[] orderIds;

    /**
     * cancelCount 指定取消占位数
     */
    private Integer cancelCount;

    /**
     * get cancelCount
     * 
     * @return Returns the cancelCount.<br>
     */
    public Integer getCancelCount() {
        return cancelCount;
    }

    /**
     * set cancelCount
     * 
     * @param cancelCount
     *            The cancelCount to set. <br>
     */
    public void setCancelCount(Integer cancelCount) {
        this.cancelCount = cancelCount;
    }

    /**
     * get orderIds
     * 
     * @return Returns the orderIds.<br>
     */
    public Integer[] getOrderIds() {
        return orderIds;
    }

    /**
     * set orderIds
     * 
     * @param orderIds
     *            The orderIds to set. <br>
     */
    public void setOrderIds(Integer[] orderIds) {
        this.orderIds = orderIds;
    }

}
