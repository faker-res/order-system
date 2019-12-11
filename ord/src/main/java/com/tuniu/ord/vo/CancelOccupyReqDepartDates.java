/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-31                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-31 <br>
 */

public class CancelOccupyReqDepartDates {

    /**
     * outId
     */
    private Integer[] outIds;

    /**
     * departDate
     */
    private String departDate;

    /**
     * adultNum
     */
    private Integer adultNum;

    /**
     * get outIds
     * 
     * @return Returns the outIds.<br>
     */
    public Integer[] getOutIds() {
        return outIds;
    }

    /**
     * set outIds
     * 
     * @param outIds
     *            The outIds to set. <br>
     */
    public void setOutIds(Integer[] outIds) {
        this.outIds = outIds;
    }

    /**
     * get departDate
     * 
     * @return Returns the departDate.<br>
     */
    public String getDepartDate() {
        return departDate;
    }

    /**
     * set departDate
     * 
     * @param departDate
     *            The departDate to set. <br>
     */
    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    /**
     * get adultNum
     * 
     * @return Returns the adultNum.<br>
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     * set adultNum
     * 
     * @param adultNum
     *            The adultNum to set. <br>
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

}
