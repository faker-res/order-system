/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 09:56:13 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.vo.CheckLossInfoVo;
import com.tuniu.ord.vo.CheckLossQueryVo;

public interface CheckLossMapper {
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
     * @param CheckLoss
     *            record
     * @return int
     */
    int insert(CheckLoss record);

    /**
     * insertSelective
     * 
     * @param CheckLoss
     *            record
     * @return int
     */
    int insertSelective(CheckLoss record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return CheckLoss
     */
    CheckLoss selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param CheckLoss
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(CheckLoss record);

    /**
     * updateByPrimaryKey
     * 
     * @param CheckLoss
     *            record
     * @return int
     */
    int updateByPrimaryKey(CheckLoss record);

    /**
     * 核损列表查询
     * 
     * @param checkLossQueryVo
     * @return
     */
    List<CheckLoss> selectCheckLossList(CheckLossQueryVo checkLossQueryVo);

    /**
     * 核损数的查询
     * 
     * @param checkLossQueryVo
     * @return
     */
    int count(CheckLossQueryVo checkLossQueryVo);

    List<CheckLossInfoVo> selectCheckLossByRequeirmentIdAndTuniuOrderId(@Param("tuniuOrderId") Integer tuniuOrderId,
            @Param("requirementId") Integer requirementId);

    List<CheckLoss> queryInfoByCheckLoss(CheckLoss checkLoss);
}