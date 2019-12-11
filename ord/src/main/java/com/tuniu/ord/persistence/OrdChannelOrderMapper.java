/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu Nov 17 17:18:59 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.OrdChannelOrder;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryInputVo;

public interface OrdChannelOrderMapper {

    int insertSelective(OrdChannelOrder record);

    OrdChannelOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdChannelOrder record);

    int getChannelOrderNum(ChannelOrderQueryInputVo channelOrderQueryInputVo);

    List<OrdChannelOrder> getChannelOrderList(ChannelOrderQueryInputVo channelOrderQueryInputVo);

}