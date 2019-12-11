/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 15:04:54 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.vo.PeopleTouristVo;

public interface OrdPeopleTouristMapper {
    /**
     * deleteByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     * 
     * @param OrdPeopleTourist
     *            record
     * @return int
     */
    int insert(OrdPeopleTourist record);

    /**
     * insertSelective
     * 
     * @param OrdPeopleTourist
     *            record
     * @return int
     */
    int insertSelective(OrdPeopleTourist record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return OrdPeopleTourist
     */
    OrdPeopleTourist selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param OrdPeopleTourist
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(OrdPeopleTourist record);

    /**
     * updateByPrimaryKey
     * 
     * @param OrdPeopleTourist
     *            record
     * @return int
     */
    int updateByPrimaryKey(OrdPeopleTourist record);

    List<OrdPeopleTourist> selectByFields(PeopleTouristVo peopleTouristVo);

}
