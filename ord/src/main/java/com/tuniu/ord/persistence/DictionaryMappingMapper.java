/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:53:16 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import com.tuniu.ord.domain.DictionaryMapping;

public interface DictionaryMappingMapper {
    /**
     * deleteByPrimaryKey
     * @param Integer id
     * @return int 
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     * @param DictionaryMapping record
     * @return int 
     */
    int insert(DictionaryMapping record);

    /**
     * insertSelective
     * @param DictionaryMapping record
     * @return int 
     */
    int insertSelective(DictionaryMapping record);

    /**
     * selectByPrimaryKey
     * @param Integer id
     * @return DictionaryMapping 
     */
    DictionaryMapping selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * @param DictionaryMapping record
     * @return int 
     */
    int updateByPrimaryKeySelective(DictionaryMapping record);

    /**
     * updateByPrimaryKey
     * @param DictionaryMapping record
     * @return int 
     */
    int updateByPrimaryKey(DictionaryMapping record);
}