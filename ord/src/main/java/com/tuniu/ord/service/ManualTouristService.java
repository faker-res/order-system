package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.ManualTouristVo;
import com.tuniu.ord.vo.common.ListVo;

public interface ManualTouristService {

    void saveTourist(ManualTouristVo tourist);
    
    void saveTourists(List<ManualTouristVo> tourists);
    
    void removeTourist(Integer touristId);
    
    void batchRemoveTourist(List<ManualTouristVo> tourists);
    
    ListVo queryTourists(Integer orderId);

    void checkTourist(ManualTouristVo vo);
}
