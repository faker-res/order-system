/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class TourBaseOutputVo {
    /**
     * 团模板
     */
    private Integer tourId;
    /**
     * 团名称
     */
    private String tourName;

    /**
     * @return the tourId
     */
    public Integer getTourId() {
        return tourId;
    }

    /**
     * @param tourId
     *            the tourId to set
     */
    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    /**
     * @return the tourName
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * @param tourName
     *            the tourName to set
     */
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

}
