/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-8-9                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-8-9 <br>
 */

public class AddExtDbatchIdVo {

    /**
     * orderId D类订单编号
     */
    private int orderId;

    /**
     * roundId D类产品入库批次号
     */
    private int[] droundId;

    /**
     * get orderId
     * 
     * @return Returns the orderId.<br>
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * set orderId
     * 
     * @param orderId
     *            The orderId to set. <br>
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * get droundId
     * 
     * @return Returns the droundId.<br>
     */
    public int[] getDroundId() {
        return droundId;
    }

    /**
     * set droundId
     * 
     * @param droundId
     *            The droundId to set. <br>
     */
    public void setDroundId(int[] droundId) {
        this.droundId = droundId;
    }

}
