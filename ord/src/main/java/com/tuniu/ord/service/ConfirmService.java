/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月23日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.CancelConfirmInputVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.ProductAPI;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhairongping
 *
 */
public interface ConfirmService {
    public ResponseVo initiateConfirm(ConfirmInputVo confirmInputVo);

    public ResponseVo cancelConfirm(CancelConfirmInputVo cancelConfirmInputVo);

    public ResponseVo lanuchConfirm(String sign, List<ProductAPI> pa, boolean isSuccess, Integer sessionId);
}
