package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.vo.CheckLossQueryVo;
import com.tuniu.ord.vo.CheckLossReqVo;

public interface CheckLossService {
    /**
     * 获取核损列表
     * 
     * @param checkLossQueryVo
     * @return
     */
    List<CheckLoss> getCheckLossList(CheckLossQueryVo checkLossQueryVo);

    /**
     * 获取数量
     * 
     * @param checkLossQueryVo
     * @return
     */
    int count(CheckLossQueryVo checkLossQueryVo);

    /**
     * 申请核损
     * 
     * @param checkLossReqVo
     */
    void addCheckLossByAPI(CheckLossReqVo checkLossReqVo) throws Exception;
}
