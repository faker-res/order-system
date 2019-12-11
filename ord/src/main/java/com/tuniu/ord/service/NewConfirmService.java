/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.CancelConfirmInputVo;
import com.tuniu.ord.vo.ConfirmFeedBackInputVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.DealConfirmResult;
import com.tuniu.ord.vo.NewDepartDate;
import com.tuniu.ord.vo.Product;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;

/**
 * 【新确认管理】
 * 
 * @author zhairongping
 *
 */
public interface NewConfirmService {
    /**
     * 发起确认校验
     * 
     * @param confirmInputVo
     * @return
     */
    ResultVo validateNewInitiateConfirm(ConfirmInputVo confirmInputVo);

    /**
     * 重新组装批次和出游人
     * 
     * @param confirmInputVo
     * @return
     */
    List<NewDepartDate> rebuilt(Product product);

    /**
     * 单批次处理发起确认逻辑并记录处理结果(不添加事务)
     * 
     * @param newDepartDate
     * @param confirmInputVo
     * @param dealConfirmResult
     */
    void dealConfirm(NewDepartDate newDepartDate, ConfirmInputVo confirmInputVo, DealConfirmResult dealConfirmResult);

    /**
     * 异步反馈
     * 
     * @param confirmFeedBackInputVo
     * @return
     */
    void confirmFeedBack(ConfirmFeedBackInputVo confirmFeedBackInputVo, DealConfirmResult dealConfirmResult);

    /**
     * 取消确认校验
     * 
     * @param cancelConfirmInputVo
     * @return
     */
    ResultVo validateNewCancelConfirm(CancelConfirmInputVo cancelConfirmInputVo);

    /**
     * 处理取消确认
     * 
     * @param cancelConfirmInputVo
     */
    void dealCancelConfirm(CancelConfirmInputVo cancelConfirmInputVo);

    /**
     * 初始化异步反馈实例
     * 
     * @param confirmFeedBackInputVo
     * @param newDepartDateList
     * @param confirmInputVo
     * @param dealSubConfirmResult
     */
    void initConfirmFeedBackInputParam(ConfirmFeedBackInputVo confirmFeedBackInputVo, List<NewDepartDate> newDepartDateList,
            ConfirmInputVo confirmInputVo);

    ResponseVo brushDataForGrpSale(List<Integer> extOrderIds);

}
