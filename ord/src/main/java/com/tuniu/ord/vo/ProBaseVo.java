/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import com.tuniu.ord.vo.common.BaseVo;

/**
 * @author zhairongping
 *
 */
public class ProBaseVo extends BaseVo {
    /**
     * 产品ID
     */
    private Integer id;

    /**
     * 分页偏移量
     */
    protected Integer start = new Integer(0);

    /**
     * 每页显示记录
     */
    protected Integer limit = new Integer(1);

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
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

}
