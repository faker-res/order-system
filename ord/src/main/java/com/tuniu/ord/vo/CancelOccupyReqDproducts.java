/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-31                                                      
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
 * @CreateDate 2016-5-31 <br>
 */

public class CancelOccupyReqDproducts {

    /**
     * dProductId
     */
    private Integer dProductId;

    /**
     * dProductType
     */
    private Integer dProductType;

    /**
     * departDates
     */
    private List<CancelOccupyReqDepartDates> departDates;

    /**
     * get dProductId
     * 
     * @return Returns the dProductId.<br>
     */
    public Integer getdProductId() {
        return dProductId;
    }

    /**
     * set dProductId
     * 
     * @param dProductId
     *            The dProductId to set. <br>
     */
    public void setdProductId(Integer dProductId) {
        this.dProductId = dProductId;
    }

    /**
     * get dProductType
     * 
     * @return Returns the dProductType.<br>
     */
    public Integer getdProductType() {
        return dProductType;
    }

    /**
     * set dProductType
     * 
     * @param dProductType
     *            The dProductType to set. <br>
     */
    public void setdProductType(Integer dProductType) {
        this.dProductType = dProductType;
    }

    /**
     * get departDates
     * 
     * @return Returns the departDates.<br>
     */
    public List<CancelOccupyReqDepartDates> getDepartDates() {
        return departDates;
    }

    /**
     * set departDates
     * 
     * @param departDates
     *            The departDates to set. <br>
     */
    public void setDepartDates(List<CancelOccupyReqDepartDates> departDates) {
        this.departDates = departDates;
    }

}
