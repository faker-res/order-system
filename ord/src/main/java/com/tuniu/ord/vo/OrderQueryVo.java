/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-25                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.Date;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-23 <br>
 */

public class OrderQueryVo {
    /**
     * productId
     */
    private Integer productId;

    /**
     * departDate
     */
    private Date[] departDate;

    // public OrderQueryVo(BookingOrderVo bookingOrderVo) {
    // this.productId = bookingOrderVo.getProductId();
    // this.departDate = bookingOrderVo.getDepartDate();
    // }

    /**
     * get productId
     * 
     * @return Returns the productId.<br>
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * set productId
     * 
     * @param productId
     *            The productId to set. <br>
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * get departDate
     * 
     * @return Returns the departDate.<br>
     */
    public Date[] getDepartDate() {
        return departDate;
    }

    /**
     * set departDate
     * 
     * @param departDate
     *            The departDate to set. <br>
     */
    public void setDepartDate(Date[] departDate) {
        this.departDate = departDate;
    }
}
