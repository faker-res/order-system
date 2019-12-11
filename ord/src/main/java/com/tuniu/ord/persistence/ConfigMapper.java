/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:51:59 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import com.tuniu.ord.domain.Config;

public interface ConfigMapper {
    /**
     * deleteByPrimaryKey
     * @param Integer id
     * @return int 
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     * @param Config record
     * @return int 
     */
    int insert(Config record);

    /**
     * insertSelective
     * @param Config record
     * @return int 
     */
    int insertSelective(Config record);

    /**
     * selectByPrimaryKey
     * @param Integer id
     * @return Config 
     */
    Config selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * @param Config record
     * @return int 
     */
    int updateByPrimaryKeySelective(Config record);

    /**
     * updateByPrimaryKey
     * @param Config record
     * @return int 
     */
    int updateByPrimaryKey(Config record);
}