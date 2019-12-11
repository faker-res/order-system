package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.vo.ManualPrdouctResultVo;

public interface ProductFilterService {

    ManualPrdouctResultVo filterProducts(List<ManualPrdouctResultVo> rows, Integer dOrderId);
   
    /**
     * 按照requirement筛选产品
     * @param rows
     * @param requirement
     * @return
     */
    List<ManualPrdouctResultVo> filterProducts(List<ManualPrdouctResultVo> rows, ManualRequirement requirement);
    
    /**
     * 按照数量筛选产品
     * @param rows
     * @param needNum
     * @return
     */
    List<ManualPrdouctResultVo> filterProductsByNeedNum(List<ManualPrdouctResultVo> rows, Integer needNum);
}
