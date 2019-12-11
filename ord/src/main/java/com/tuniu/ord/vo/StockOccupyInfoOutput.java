/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月12日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class StockOccupyInfoOutput {
    /**
     * 占位单号
     */
    private Integer id;
    /**
     * 占位总数
     */
    private Integer inNumber;
    /**
     * 占位余位
     */
    private Integer leftNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInNumber() {
        return inNumber;
    }

    public void setInNumber(Integer inNumber) {
        this.inNumber = inNumber;
    }

    public Integer getLeftNumber() {
        return leftNumber;
    }

    public void setLeftNumber(Integer leftNumber) {
        this.leftNumber = leftNumber;
    }
}
