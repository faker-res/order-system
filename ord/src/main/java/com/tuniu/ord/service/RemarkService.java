/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.OrdCommonRemark;
import com.tuniu.ord.vo.OrdCommonRemarkVo;

/**
 * @author fangzhongwei
 * 
 */
public interface RemarkService {

    List<OrdCommonRemark> getRemarkList(OrdCommonRemarkVo ordCommonRemarkVo);

    void saveRemark(OrdCommonRemarkVo ordCommonRemarkVo);

    void deleteRemark(OrdCommonRemarkVo ordCommonRemarkVo);

    int count(OrdCommonRemarkVo ordCommonRemarkVo);
}
