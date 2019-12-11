package com.tuniu.ord.service;

import com.tuniu.ord.vo.channelorder.DealCommonVo;

public interface ChannelOrderExceptionService {

    void updateRedundantTourist(DealCommonVo channelOrderUpdateVo) throws Exception;
}
