/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.domain.OrdChannelOrder;
import com.tuniu.ord.enums.ChannelOrderStatusEnum;
import com.tuniu.ord.enums.OperatorTypeEnum;
import com.tuniu.ord.persistence.OrdChannelOrderMapper;
import com.tuniu.ord.proxy.ORDProxy;
import com.tuniu.ord.service.ChannelOrderService;
import com.tuniu.ord.service.NewConfirmService;
import com.tuniu.ord.vo.CheckLossDepartVo;
import com.tuniu.ord.vo.CheckLossReqVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.DepartDate;
import com.tuniu.ord.vo.Product;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;
import com.tuniu.ord.vo.Tourist;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryInputVo;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryOutputVo;
import com.tuniu.ord.vo.channelorder.DealCommonVo;

/**
 * @author zhairongping
 *
 */
@Service
public class ChannelOrderServiceImpl implements ChannelOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(ChannelOrderServiceImpl.class);

    @Resource
    private OrdChannelOrderMapper ordChannelOrderMapper;

    @Resource
    private NewConfirmService newConfirmServiceImpl;

    @Resource
    private ORDProxy oRDProxy;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ChannelOrderService#validateChannelOrderQuery(com.tuniu.ord.vo.channelorder.
     * ChannelOrderQueryInputVo)
     */
    @Override
    public ResultVo validateChannelOrderQuery(ChannelOrderQueryInputVo channelOrderQueryInputVo) {
        ResultVo resultVo = new ResultVo();
        return resultVo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ChannelOrderService#getChannelOrderNum(com.tuniu.ord.vo.channelorder.ChannelOrderQueryInputVo)
     */
    @Override
    public Integer getChannelOrderNum(ChannelOrderQueryInputVo channelOrderQueryInputVo) {
        return new Integer(ordChannelOrderMapper.getChannelOrderNum(channelOrderQueryInputVo));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tuniu.ord.service.ChannelOrderService#getChannelOrderList(com.tuniu.ord.vo.channelorder.ChannelOrderQueryInputVo)
     */
    @Override
    public List<ChannelOrderQueryOutputVo> getChannelOrderList(ChannelOrderQueryInputVo channelOrderQueryInputVo) {
        List<OrdChannelOrder> oldlist = ordChannelOrderMapper.getChannelOrderList(channelOrderQueryInputVo);
        List<ChannelOrderQueryOutputVo> newList = new ArrayList<ChannelOrderQueryOutputVo>(oldlist.size());
        for (OrdChannelOrder ordChannelOrder : oldlist) {
            ChannelOrderQueryOutputVo channelOrderQueryOutputVo = new ChannelOrderQueryOutputVo();
            initChannelOrderQueryOutputVo(channelOrderQueryOutputVo, ordChannelOrder);
            newList.add(channelOrderQueryOutputVo);
        }
        LOG.info("oldList=>newList oldlist:[{}],newList[{}]", JsonUtil.toString(oldlist), JsonUtil.toString(newList));
        return newList;
    }

    /**
     * 初始化实例
     * 
     * @param channelOrderQueryOutputVo
     * @param ordChannelOrder
     */
    private void initChannelOrderQueryOutputVo(ChannelOrderQueryOutputVo channelOrderQueryOutputVo,
            OrdChannelOrder ordChannelOrder) {
        channelOrderQueryOutputVo.setId(ordChannelOrder.getId());
        channelOrderQueryOutputVo.setAddTime(df.format(ordChannelOrder.getAddTime()));
        channelOrderQueryOutputVo.setChannelName("途牛A");
        channelOrderQueryOutputVo.setChannelOrderId(ordChannelOrder.getChannelOrderId());
        channelOrderQueryOutputVo.setOperatorType(ordChannelOrder.getOperatorType());
        channelOrderQueryOutputVo.setStatus(ordChannelOrder.getStatus());

        String beforeChangeTouristStr = ordChannelOrder.getBeforeChangeTourist();
        if (beforeChangeTouristStr != null && !"".equals(beforeChangeTouristStr)) {
            channelOrderQueryOutputVo.setBeforeChangeTourist(JsonUtil.toList(beforeChangeTouristStr, Tourist.class));
        }

        String afterChangeTouristStr = ordChannelOrder.getAfterChangeTourist();
        if (afterChangeTouristStr != null && !"".equals(afterChangeTouristStr)) {
            channelOrderQueryOutputVo.setAfterChangeTourist(JsonUtil.toList(afterChangeTouristStr, Tourist.class));
        }

        String str = ordChannelOrder.getOriginalParam();
        if (ordChannelOrder.getOperatorType().intValue() == OperatorTypeEnum.CONFIRM_OPE.getKey().intValue()) {
            // 计算确认位置数
            Integer confirmNum = new Integer(0);
            ConfirmInputVo confirmInputVo = JsonUtil.toBean(str, ConfirmInputVo.class);
            List<DepartDate> departDates = confirmInputVo.getProducts().get(0).getDepartDates();
            for (DepartDate departDate : departDates) {
                confirmNum += departDate.getAffirmAdultNum() + departDate.getAffirmChildNum() + departDate.getAffirmBabyNum();
            }
            channelOrderQueryOutputVo.setConfirmNum(confirmNum);
        }
        if (ordChannelOrder.getOperatorType().intValue() == OperatorTypeEnum.LOSS_OPE.getKey().intValue()) {
            // 计算核损位置数
            Integer lossNum = new Integer(0);
            try {
                CheckLossReqVo checkLossReqVo = JsonUtil.toBean(str, CheckLossReqVo.class);
                List<CheckLossDepartVo> departDates = checkLossReqVo.getProducts().get(0).getDepartDates();
                for (CheckLossDepartVo cld : departDates) {
                    lossNum += cld.getLossAdultNum() + cld.getLossChildNum() + cld.getLossBabyNum();
                }
                channelOrderQueryOutputVo.setLossNum(lossNum);
            } catch (Exception e) {
                LOG.error("计算核损位置数异常:[{}]", e.getMessage());
                channelOrderQueryOutputVo.setLossNum(lossNum);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ChannelOrderService#validateChannelOrder(com.tuniu.ord.vo.channelorder.DealCommonVo)
     */
    @Override
    public ResultVo validateChannelOrder(DealCommonVo dealCommonVo) {
        ResultVo resultVo = new ResultVo();
        if (null == dealCommonVo.getId()) {
            resultVo.setSuccess(false);
            resultVo.setMsg("ID不能为空");
            return resultVo;
        }
        OrdChannelOrder ordChannelOrder = ordChannelOrderMapper.selectByPrimaryKey(dealCommonVo.getId());
        if (null == ordChannelOrder) {
            resultVo.setSuccess(false);
            resultVo.setMsg("ID:" + dealCommonVo.getId() + "对应的外部订单不存在");
            return resultVo;
        }
        if (!CollectionUtils.isNotEmpty(dealCommonVo.getAfterChangeTourist())) {
            resultVo.setSuccess(false);
            resultVo.setMsg("变更后的出游人不能为空");
            return resultVo;
        }
        ConfirmInputVo confirmInputVo = getNewConfirmInputVo(dealCommonVo);
        resultVo = newConfirmServiceImpl.validateNewInitiateConfirm(confirmInputVo);
        return resultVo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ChannelOrderService#dealChannelOrderConfirm(com.tuniu.ord.vo.channelorder.DealCommonVo)
     */
    @Override
    public ResponseVo dealChannelOrderConfirm(DealCommonVo dealCommonVo) {
        // 构建新的发起确认参数
        ConfirmInputVo confirmInputVo = getNewConfirmInputVo(dealCommonVo);

        // 发起确认
        ResponseVo responseVo = oRDProxy.newInitiateConfirm(confirmInputVo);
        if (responseVo.isSuccess()) {
            // 更新外部订单
            OrdChannelOrder ordChannelOrder = new OrdChannelOrder();
            initChannelOrder(dealCommonVo, confirmInputVo, ordChannelOrder);
            ordChannelOrderMapper.updateByPrimaryKeySelective(ordChannelOrder);
        }
        LOG.info("【调用订单系统】外部订单号:[{}],success:[{}],msg:[{}],param:[{}]",
                confirmInputVo.getProducts().get(0).getTuniuInfo().getTuniuOrderId(), responseVo.isSuccess(),
                responseVo.getMsg(), JsonUtil.toString(confirmInputVo));

        return responseVo;
    }

    /**
     * @param dealCommonVo
     * @param confirmInputVo
     * @param ordChannelOrder
     */
    private void initChannelOrder(DealCommonVo dealCommonVo, ConfirmInputVo confirmInputVo, OrdChannelOrder ordChannelOrder) {
        ordChannelOrder.setId(dealCommonVo.getId());
        ordChannelOrder.setStatus(ChannelOrderStatusEnum.DEALED.getKey());
        ordChannelOrder.setAfterChangeTourist(JsonUtil.toString(confirmInputVo.getProducts().get(0).getTourist()));
    }

    /**
     * @param dealCommonVo
     * @return
     */
    private ConfirmInputVo getNewConfirmInputVo(DealCommonVo dealCommonVo) {
        OrdChannelOrder ordChannelOrder = ordChannelOrderMapper.selectByPrimaryKey(dealCommonVo.getId());
        String originalParam = ordChannelOrder.getOriginalParam();
        ConfirmInputVo confirmInputVo = JsonUtil.toBean(originalParam, ConfirmInputVo.class);
        Product product = confirmInputVo.getProducts().get(0);
        List<Tourist> tourist = product.getTourist();
        List<Tourist> newTourist = new ArrayList<Tourist>();

        List<Tourist> afterChangeTourist = dealCommonVo.getAfterChangeTourist();
        for (Tourist tst : afterChangeTourist) {
            String touristId = tst.getTouristId();
            for (Tourist t : tourist) {
                if (touristId.equals(t.getTouristId())) {
                    newTourist.add(t);
                    break;
                }
            }
        }
        product.setTourist(newTourist);
        return confirmInputVo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ChannelOrderService#addOrdChannelOrder(com.tuniu.ord.domain.OrdChannelOrder)
     */
    @Override
    public int addOrdChannelOrder(ConfirmInputVo confirmInputVo) {
        OrdChannelOrder ordChannelOrder = new OrdChannelOrder();
        initOrdChannelOrder(confirmInputVo, ordChannelOrder);
        return ordChannelOrderMapper.insertSelective(ordChannelOrder);
    }

    /**
     * @param confirmInputVo
     * @param ordChannelOrder
     */
    private void initOrdChannelOrder(ConfirmInputVo confirmInputVo, OrdChannelOrder ordChannelOrder) {
        Product product = confirmInputVo.getProducts().get(0);
        ordChannelOrder.setChannelOrderId(product.getTuniuInfo().getTuniuOrderId());
        ordChannelOrder.setOriginalParam(JsonUtil.toString(confirmInputVo));
        ordChannelOrder.setBeforeChangeTourist(JsonUtil.toString(product.getTourist()));
        ordChannelOrder.setOperatorType(OperatorTypeEnum.CONFIRM_OPE.getKey());
        ordChannelOrder.setStatus(ChannelOrderStatusEnum.DEALING.getKey());
        Date date = DateUtils.now();
        ordChannelOrder.setAddUid(UserSessionUtil.getUid() == 0 ? SystemConstants.SYSTEM_ID : UserSessionUtil.getUid());
        ordChannelOrder.setAddUname((UserSessionUtil.getNickname() == null
                || "".equals(UserSessionUtil.getNickname())) ? SystemConstants.SYSTEM_NAME : UserSessionUtil.getNickname());
        ordChannelOrder.setAddTime(date);
        ordChannelOrder.setUpdateUid(UserSessionUtil.getUid() == 0 ? SystemConstants.SYSTEM_ID : UserSessionUtil.getUid());
        ordChannelOrder.setUpdateUname((UserSessionUtil.getNickname() == null
                || "".equals(UserSessionUtil.getNickname())) ? SystemConstants.SYSTEM_NAME : UserSessionUtil.getNickname());
        ordChannelOrder.setUpdateTime(date);
    }

}
