/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.service.PeopleTouristService;
import com.tuniu.ord.vo.PeopleTouristVo;

/**
 * @author fangzhongwei
 * 
 */
@Service
public class PeopleTouristServiceImpl implements PeopleTouristService {

    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @Override
    public List<OrdPeopleTourist> getOrdSalesPrice(PeopleTouristVo peopleTouristVo) {
        return ordPeopleTouristMapper.selectByFields(peopleTouristVo);
    }

}
