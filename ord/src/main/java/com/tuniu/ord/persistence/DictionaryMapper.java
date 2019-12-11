/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:52:34 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import com.tuniu.ord.domain.Dictionary;

public interface DictionaryMapper {
    /**
     * deleteByPrimaryKey
     * @param Integer id
     * @return int 
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     * @param Dictionary record
     * @return int 
     */
    int insert(Dictionary record);

    /**
     * insertSelective
     * @param Dictionary record
     * @return int 
     */
    int insertSelective(Dictionary record);

    /**
     * selectByPrimaryKey
     * @param Integer id
     * @return Dictionary 
     */
    Dictionary selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * @param Dictionary record
     * @return int 
     */
    int updateByPrimaryKeySelective(Dictionary record);

    /**
     * updateByPrimaryKey
     * @param Dictionary record
     * @return int 
     */
    int updateByPrimaryKey(Dictionary record);
}