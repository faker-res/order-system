/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月31日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author douyanhui
 * 
 */
public class ListVo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3011076761711753454L;

    private Integer count;

    private List<? extends Object> rows;

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return the rows
     */
    public List<? extends Object> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            the rows to set
     */
    public void setRows(List<? extends Object> rows) {
        this.rows = rows;
    }

}
