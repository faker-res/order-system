package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.CheckLossTourist;
import com.tuniu.ord.vo.CheckLossTouristVo;

public interface CheckLossTouristService {
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
     * @param CheckLoss
     *            record
     * @return Integer
     */
    Integer insert(CheckLossTourist record);

    /**
     * insertSelective
     * 
     * @param CheckLossTourist
     *            record
     * @return int
     */
    Integer insertSelective(CheckLossTourist record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integer
     *            id
     * @return CheckLossTourist
     */
    CheckLossTourist selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param CheckLossTourist
     *            record
     * @return Integer
     */
    Integer updateByPrimaryKeySelective(CheckLossTourist record);

    /**
     * updateByPrimaryKey
     * 
     * @param CheckLossTourist
     *            record
     * @return Integer
     */
    Integer updateByPrimaryKey(CheckLossTourist record);

    /**
     * 查询游客信息并且保存 selectTouristByPspIdAndSave
     * 
     * @param checkLossTouristVo
     */
    void selectTouristByPspIdAndSave(CheckLossTouristVo checkLossTouristVo);

}
