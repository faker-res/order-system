/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.vo.PeopleTouristVo;

/**
 * @author fangzhongwei
 * 
 */
public interface PeopleTouristService {

    List<OrdPeopleTourist> getOrdSalesPrice(PeopleTouristVo peopleTouristVo);

}
