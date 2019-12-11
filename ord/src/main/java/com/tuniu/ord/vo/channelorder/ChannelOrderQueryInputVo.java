/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.channelorder;

import com.tuniu.ord.vo.BaseQueryVo;

/**
 *       {
        "channelOrderId": 2121212221,
        "status": 0,
        "operatorType":1
      }
 */

/**
 * @author zhairongping
 *
 */
public class ChannelOrderQueryInputVo extends BaseQueryVo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer channelOrderId;
    private Integer status;
    private Integer operatorType;

    /**
     * @return the channelOrderId
     */
    public Integer getChannelOrderId() {
        return channelOrderId;
    }

    /**
     * @param channelOrderId
     *            the channelOrderId to set
     */
    public void setChannelOrderId(Integer channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the operatorType
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * @param operatorType
     *            the operatorType to set
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

}
