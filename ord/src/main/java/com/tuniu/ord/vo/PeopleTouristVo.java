/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.domain.BaseDomain;

/**
 * @author fangzhongwei
 * 
 */
public class PeopleTouristVo extends BaseDomain {

    private static final long serialVersionUID = 4052362818540353848L;

    private List<Integer> sellOrderIds;

    public List<Integer> getSellOrderIds() {
        return sellOrderIds;
    }

    public void setSellOrderIds(List<Integer> sellOrderIds) {
        this.sellOrderIds = sellOrderIds;
    }

}
