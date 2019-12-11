package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.CheckLossDiffInfo;
import com.tuniu.ord.vo.CheckLossDiffPriceVo;

public interface CheckLossDiffInfoMapper {
    int insertSelective(CheckLossDiffInfo checkLossDiffInfo);

    CheckLossDiffInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckLossDiffInfo record);

    int updateByPrimaryKey(CheckLossDiffInfo record);

    /**
     * 根据核损编号查询AD批次对应信息
     * 
     * @param checkLossId
     * @return
     */
    List<CheckLossDiffInfo> queryInfoByCheckLossId(@Param("checkLossId") Integer checkLossId);

    /**
     * 查询AD信息不对等切位单
     * 
     * @param checkLossId
     * @return
     */
    List<CheckLossDiffInfo> queryADDiffInfo(@Param("checkLossId") Integer checkLossId);

    /**
     * 根据核损ID查询AD相对的核损成人和儿童价格
     * 
     * @param checkLossId
     * @return
     */
    List<CheckLossDiffPriceVo> queryCheckLossDIffPrice(@Param("checkLossId") Integer checkLossId);
}