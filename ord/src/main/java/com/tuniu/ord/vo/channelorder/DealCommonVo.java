/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.channelorder;

import java.util.List;

import com.tuniu.ord.vo.Tourist;

/**
 *       {
        "id": 121122,
         "afterChangeTourist": [
              {
                "touristId": 2212122,
                "touristName": "张三"
              }
            ]
      }
 */
/**
 * @author zhairongping
 *
 */
public class DealCommonVo {
    private Integer id;
    private List<Tourist> afterChangeTourist;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the afterChangeTourist
     */
    public List<Tourist> getAfterChangeTourist() {
        return afterChangeTourist;
    }

    /**
     * @param afterChangeTourist
     *            the afterChangeTourist to set
     */
    public void setAfterChangeTourist(List<Tourist> afterChangeTourist) {
        this.afterChangeTourist = afterChangeTourist;
    }

}
