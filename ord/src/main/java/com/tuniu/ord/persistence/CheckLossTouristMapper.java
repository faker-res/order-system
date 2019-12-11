package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.CheckLossTourist;
import com.tuniu.ord.vo.CheckLossTouristVo;
import com.tuniu.ord.vo.SalesInfoWhenCheckLossVo;

public interface CheckLossTouristMapper {

    /**
     * deleteByPrimaryKey
     * 
     * @param Integereger
     *            id
     * @return Integer
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * insert
     * 
     * @param CheckLossTourist
     *            record
     * @return Integer
     */
    Integer insert(CheckLossTourist record);

    /**
     * insertSelective
     * 
     * @param CheckLossTourist
     *            record
     * @return Integer
     */
    Integer insertSelective(CheckLossTourist record);

    /**
     * selectByPrimaryKey
     * 
     * @param Integereger
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

    List<CheckLossTouristVo> selectCheckLossTouristByCheckLossIdAndPsptId(CheckLossTouristVo checkLossTouristVo);

    /**
     * 根据核损单号和人员ID查询信息
     * 
     * @param checkLossTouristVo
     * @return
     */
    List<CheckLossTouristVo> selectByCheckLossIdAndPsptId(CheckLossTouristVo checkLossTouristVo);

    /**
     * 根据核损Id查询出订单下的所有游客
     * 
     * @param checkLossTouristVo
     * @return
     */
    List<CheckLossTouristVo> selectAllTouristByCheckLossId(CheckLossTouristVo checkLossTouristVo);

    /**
     * 根据核损单号获取核损出游人列表
     * 
     * @param checkLossTouristVo
     * @return
     */
    List<CheckLossTourist> getLossTouristList(CheckLossTouristVo checkLossTouristVo);

    /**
     * 根据核损信息人员信息查询销售对应的信息
     * 
     * @param requirementId
     * @param tuniuOrderId
     * @param touristIds
     * @return
     */
    List<SalesInfoWhenCheckLossVo> querySalesByCheckLossInfo(@Param("requirementId") Integer requirementId,
            @Param("tuniuOrderId") Integer tuniuOrderId, @Param("touristIds") List<String> touristIds);
}
