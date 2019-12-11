/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 15:02:31 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.OrdCommonRemark;
import com.tuniu.ord.vo.OrdCommonRemarkVo;

public interface OrdCommonRemarkMapper {
    /**
     * deleteByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return int
     */
    int deleteByPrimaryKey(OrdCommonRemarkVo ordCommonRemarkVo);

    /**
     * insert
     * 
     * @param OrdCommonRemark
     *            record
     * @return int
     */
    int insert(OrdCommonRemark record);

    /**
     * insertSelective
     * 
     * @param OrdCommonRemark
     *            record
     * @return int
     */
    int insertSelective(OrdCommonRemark record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return OrdCommonRemark
     */
    OrdCommonRemark selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param OrdCommonRemark
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(OrdCommonRemark record);

    /**
     * updateByPrimaryKey
     * 
     * @param OrdCommonRemark
     *            record
     * @return int
     */
    int updateByPrimaryKey(OrdCommonRemark record);

    List<OrdCommonRemark> selectByFields(OrdCommonRemarkVo ordCommonRemarkVo);

    int countByFields(OrdCommonRemarkVo ordCommonRemarkVo);

}
