/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 09:56:13 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.CheckLossDetail;
import com.tuniu.ord.vo.CheckLossDetailShowVo;
import com.tuniu.ord.vo.CheckLossIdParamVo;

public interface CheckLossDetailMapper {
    /**
     * deleteByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return Integer
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * insert
     * 
     * @param CheckLossDetail
     *            record
     * @return Integer
     */
    Integer insert(CheckLossDetail record);

    /**
     * insertSelective
     * 
     * @param CheckLossDetail
     *            record
     * @return Integer
     */
    Integer insertSelective(CheckLossDetail record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integereger
     *            id
     * @return CheckLossDetail
     */
    CheckLossDetail selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param CheckLossDetail
     *            record
     * @return Integer
     */
    Integer updateByPrimaryKeySelective(CheckLossDetail record);

    /**
     * updateByPrimaryKey
     * 
     * @param CheckLossDetail
     *            record
     * @return Integer
     */
    Integer updateByPrimaryKey(CheckLossDetail record);

    /**
     * 根据核损id查询产品编号、名称，団期及币种
     * 
     * @param input
     * @return
     */
    List<CheckLossDetailShowVo> selectDetailInfo(CheckLossIdParamVo input);

    /**
     * 根据核损id查询核损详情
     * 
     * @param input
     * @return
     */
    List<CheckLossDetail> selectCheckLossDetailList(CheckLossIdParamVo input);

    /**
     * 统计核损人数
     * 
     * @param checkLossId
     * @return
     */
    List<CheckLossDetail> getCheckLossNum(Integer checkLossId);
}