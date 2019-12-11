/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-6-17                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-6-17 <br>
 */

public class AddExtBatchIdVo {

    /**
     * dOrderId D类订单编号
     */
    private Integer orderId;

    /**
     * roundId A类资源入库批次号
     */
    private Integer[] roundId;

    /**
     * get orderId
     * 
     * @return Returns the orderId.<br>
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * set orderId
     * 
     * @param orderId
     *            The orderId to set. <br>
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * get roundId
     * 
     * @return Returns the roundId.<br>
     */
    public Integer[] getRoundId() {
        return roundId;
    }

    /**
     * set roundId
     * 
     * @param roundId
     *            The roundId to set. <br>
     */
    public void setRoundId(Integer[] roundId) {
        this.roundId = roundId;
    }

}
