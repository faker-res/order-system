/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.task.ManualTaskVo;
import com.tuniu.ord.common.task.OrderTaskEnum;
import com.tuniu.ord.common.task.TaskFlow;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.domain.ManualContract;
import com.tuniu.ord.domain.ManualReceipt;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.persistence.ManualContractMapper;
import com.tuniu.ord.persistence.ManualReceiptMapper;
import com.tuniu.ord.persistence.ManualRequirementMapper;
import com.tuniu.ord.service.ManualFinishSignService;

/**
 * @author zhoujie8
 * 
 */
@Service
public class ManualFinishSignServiceImpl extends CommonOrderServiceImpl implements ManualFinishSignService {
    @Resource
    private ManualReceiptMapper manualReceiptMapper;
    @Resource
    private ManualContractMapper manualContractMapper;

    @Resource
    private TaskFlow taskFlow;

    @Resource
    private ManualRequirementMapper manualRequirementMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ManualFinishSignService#finishSign(java.lang.Integer)
     */
    @Override
    public ManualTaskVo finishSign(Integer manualOrderId) {
        // 收款记录
        existReceipt(manualOrderId);
        // 存在合同
        existContract(manualOrderId);

        // 添加备注
        addRemarkBySystem(manualOrderId, "订单完成签约付款");

        taskFlow.finish(manualOrderId, OrderTaskEnum.PAY);

        return taskFlow.getCurrentTask(manualOrderId);
    }

    /**
     * @param manualOrderId
     */
    private void existContract(Integer manualOrderId) {
        List<ManualContract> list = manualContractMapper.selectByManualOrderId(manualOrderId);
        if (list == null || list.size() == 0) {
            throw new OrdCustomException("无法完成签约！缺少合同！");
        }
    }

    /**
     * @param manualOrderId
     */
    private void existReceipt(Integer manualOrderId) {
        ManualRequirement manualRequirement = manualRequirementMapper.selectByManualOrderId(manualOrderId);
        if (manualRequirement.getCutType() == 1) {
            List<ManualReceipt> list = manualReceiptMapper.queryManualReceiptByManualOrderId(manualOrderId);
            if (!CollectionUtils.isNotEmpty(list)) {
                throw new SaaSSystemException("硬切的订单需要有收款记录！");
            }
        }
    }
}
