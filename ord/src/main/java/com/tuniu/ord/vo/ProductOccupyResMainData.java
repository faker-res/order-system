/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-6-16                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-30 <br>
 */

public class ProductOccupyResMainData {

    /**
     * sessionId
     */
    private Integer sessionId;

    /**
     * dProducts
     */
    private List<OccupyResDproducts> dProducts;

    /**
     * get sessionId
     * 
     * @return Returns the sessionId.<br>
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * set sessionId
     * 
     * @param sessionId
     *            The sessionId to set. <br>
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * get dProducts
     * 
     * @return Returns the dProducts.<br>
     */
    public List<OccupyResDproducts> getdProducts() {
        return dProducts;
    }

    /**
     * set dProducts
     * 
     * @param dProducts
     *            The dProducts to set. <br>
     */
    public void setdProducts(List<OccupyResDproducts> dProducts) {
        this.dProducts = dProducts;
    }

}
