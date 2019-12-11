/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:50:39 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * CheckLossTourist check_loss_tourist
 */
public class CheckLossTourist extends BaseDomain {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7626778736814758385L;

    /**
     * 
     * check_loss_tourist.check_loss_id
     */
    private Integer checkLossId;

    /**
     * 
     * check_loss_tourist.ord_people_tourist_id
     */
    private Integer ordPeopleTouristId;

    public Integer getCheckLossId() {
        return checkLossId;
    }

    public void setCheckLossId(Integer checkLossId) {
        this.checkLossId = checkLossId;
    }

    public Integer getOrdPeopleTouristId() {
        return ordPeopleTouristId;
    }

    public void setOrdPeopleTouristId(Integer ordPeopleTouristId) {
        this.ordPeopleTouristId = ordPeopleTouristId;
    }
}
