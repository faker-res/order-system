/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryInputVo;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryOutputVo;
import com.tuniu.ord.vo.channelorder.DealCommonVo;

/**
 * @author zhairongping
 *
 */
public interface ChannelOrderService {

    public ResultVo validateChannelOrderQuery(ChannelOrderQueryInputVo channelOrderQueryInputVo);

    public Integer getChannelOrderNum(ChannelOrderQueryInputVo channelOrderQueryInputVo);

    public List<ChannelOrderQueryOutputVo> getChannelOrderList(ChannelOrderQueryInputVo channelOrderQueryInputVo);

    public ResultVo validateChannelOrder(DealCommonVo dealCommonVo);

    public ResponseVo dealChannelOrderConfirm(DealCommonVo dalCommonVo);

    public int addOrdChannelOrder(ConfirmInputVo confirmInputVo);

}
