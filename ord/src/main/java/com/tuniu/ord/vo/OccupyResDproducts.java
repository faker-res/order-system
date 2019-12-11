/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-30                                                      
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

public class OccupyResDproducts {
    /**
     * dProductId
     */
    private Integer dProductId;

    /**
     * dProductName
     */
    private String dProductName;

    /**
     * dProductType
     */
    private Integer dProductType;

    /**
     * departDates
     */
    private List<OccupyResDepartDates> departDates;

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
     * get dProductName
     * 
     * @return Returns the dProductName.<br>
     */
    public String getdProductName() {
        return dProductName;
    }

    /**
     * set dProductName
     * 
     * @param dProductName
     *            The dProductName to set. <br>
     */
    public void setdProductName(String dProductName) {
        this.dProductName = dProductName;
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
    public List<OccupyResDepartDates> getDepartDates() {
        return departDates;
    }

    /**
     * set departDates
     * 
     * @param departDates
     *            The departDates to set. <br>
     */
    public void setDepartDates(List<OccupyResDepartDates> departDates) {
        this.departDates = departDates;
    }

}
