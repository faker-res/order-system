/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月23日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

/**
 * 分页查询条件
 * 
 * @author zhairongping
 *
 */
public class PageQueryVo {

    /**
     * 默认显示第1页
     */
    private static final int DEFAULT_PAGE_INDEX = 0;

    /**
     * 默认每页显示20条记录
     */
    private static final int DEFAULT_LIMIT = 20;

    /**
     * 分页偏移量
     */
    protected Integer start;

    /**
     * 每页显示记录
     */
    protected Integer limit;

    public Integer getStart() {
        if (start == null) {
            start = DEFAULT_PAGE_INDEX;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        if (limit == null) {
            limit = DEFAULT_LIMIT;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
