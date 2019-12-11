package com.tuniu.ord.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.domain.OrdChannelOrder;
import com.tuniu.ord.enums.ChannelOrderStatusEnum;
import com.tuniu.ord.persistence.OrdChannelOrderMapper;
import com.tuniu.ord.service.ChannelOrderExceptionService;
import com.tuniu.ord.service.CheckLossService;
import com.tuniu.ord.vo.CheckLossReqVo;
import com.tuniu.ord.vo.CheckLossTouristVo;
import com.tuniu.ord.vo.Tourist;
import com.tuniu.ord.vo.channelorder.DealCommonVo;

@Service
public class ChannelOrderExceptionServiceImpl implements ChannelOrderExceptionService {

    @Resource
    private OrdChannelOrderMapper channelOrderMapper;

    @Resource
    private CheckLossService checkLossService;

    @Override
    public void updateRedundantTourist(DealCommonVo channelOrderUpdateVo) throws Exception {
        Assert.notNull(channelOrderUpdateVo, "对象不能为空");
        Assert.notNull(channelOrderUpdateVo.getId(), "主键ID不能为空");
        Assert.notEmpty(channelOrderUpdateVo.getAfterChangeTourist(), "人员信息不能为空");

        OrdChannelOrder ordChannelOrder = channelOrderMapper.selectByPrimaryKey(channelOrderUpdateVo.getId());
        if (ordChannelOrder == null) {
            throw new SaaSSystemException("没有对应的信息");
        }

        String originalParam = ordChannelOrder.getOriginalParam();

        CheckLossReqVo checkLossReqVo = JsonUtil.toBean(originalParam, CheckLossReqVo.class);

        List<CheckLossTouristVo> newLossTourist = new ArrayList<CheckLossTouristVo>();

        List<CheckLossTouristVo> lossTourist = checkLossReqVo.getProducts().get(0).getLossTourist();

        List<Tourist> tourists = channelOrderUpdateVo.getAfterChangeTourist();
        for (Tourist tourist : tourists) {
            for (CheckLossTouristVo checkLossTouristVo : lossTourist) {
                if (checkLossTouristVo.getTouristId().equals(tourist.getTouristId())) {
                    newLossTourist.add(checkLossTouristVo);
                }
            }
        }

        checkLossReqVo.getProducts().get(0).setLossTourist(newLossTourist);

        // 生成核损单
        CheckLossServiceImpl.validateCheckLossData(checkLossReqVo);

        checkLossService.addCheckLossByAPI(checkLossReqVo);

        ordChannelOrder.setAfterChangeTourist(JsonUtil.toString(newLossTourist));
        ordChannelOrder.setStatus(ChannelOrderStatusEnum.DEALED.getKey().intValue());
        BaseDomainUtil.setUpdatePropertyValue(ordChannelOrder);
        channelOrderMapper.updateByPrimaryKeySelective(ordChannelOrder);
    }
}
