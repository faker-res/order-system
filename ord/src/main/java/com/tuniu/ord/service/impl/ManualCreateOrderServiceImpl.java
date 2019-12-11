/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.ord.common.task.OrderTaskEnum;
import com.tuniu.ord.common.task.TaskFlow;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualContact;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.persistence.ManualContactMapper;
import com.tuniu.ord.persistence.ManualOrderMapper;
import com.tuniu.ord.persistence.ManualRemarkMapper;
import com.tuniu.ord.persistence.ManualRequirementMapper;
import com.tuniu.ord.service.OrderService;
import com.tuniu.ord.vo.createOrder.ContactQueryVo;
import com.tuniu.ord.vo.createOrder.ManualCreateOrderParamInputVo;
import com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo;
import com.tuniu.ord.vo.createOrder.OrderQueryVo;
import com.tuniu.ord.vo.createOrder.RequirementQueryVo;

/**
 * 人工下单服务
 * 
 * @author zhairongping
 *
 */
@Service(value = "manualCreateOrderService")
public class ManualCreateOrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(ManualCreateOrderServiceImpl.class);

    @Resource
    private ManualOrderMapper manualOrderMapper;

    @Resource
    private ManualRequirementMapper manualRequirementMapper;

    @Resource
    private ManualContactMapper manualContactMapper;

    @Resource
    private ManualRemarkMapper manualRemarkMapper;

    @Resource
    private TaskFlow taskFlow;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrderService#validateOrder(com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo)
     */
    @Override
    public Map<String, Object> validateOrder(OrderBaseParamInputVo input) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "下单校验成功");

        // 校验下单参数类型
        if (!(input instanceof ManualCreateOrderParamInputVo)) {
            map.put("success", false);
            map.put("msg", "人工下单参数类型不正确");
            return map;
        }
        ManualCreateOrderParamInputVo param = (ManualCreateOrderParamInputVo) input;

        // 联系人必填
        List<ContactQueryVo> contacts = param.getContacts();
        if (contacts == null || contacts.size() == 0) {
            map.put("success", false);
            map.put("msg", "联系人必填");
            return map;
        }

        for (ContactQueryVo contact : contacts) {
            if (contact.getMemberId() == null || contact.getMemberId().intValue() == 0) {
                map.put("success", false);
                map.put("msg", "联系人所属会员不能为空");
                return map;
            }
            if (contact.getName() == null || "".equals(contact.getName())) {
                map.put("success", false);
                map.put("msg", "联系人姓名必填");
                return map;
            }
        }

        // 出游需求必填
        RequirementQueryVo requirement = param.getRequirement();
        if (requirement == null) {
            map.put("success", false);
            map.put("msg", "出游需求必填");
            return map;
        }

        if (requirement.getProductId() == null) {
            map.put("success", false);
            map.put("msg", "产品编号必填");
            return map;
        }

        // 订单信息必填
        OrderQueryVo orderQueryVo = param.getOrderInfo();
        if (orderQueryVo == null) {
            map.put("success", false);
            map.put("msg", "订单信息必填");
            return map;
        }

        return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrderService#createOrder(com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo)
     */
    @Override
    public Integer createOrder(OrderBaseParamInputVo input) {
        ManualCreateOrderParamInputVo param = (ManualCreateOrderParamInputVo) input;
        List<ContactQueryVo> contacts = param.getContacts();
        RequirementQueryVo requirement = param.getRequirement();
        OrderQueryVo orderInfo = param.getOrderInfo();

        // 生成订单号
        ManualOrder manualOrder = new ManualOrder();
        initOrder(manualOrder, orderInfo);
        manualOrderMapper.insertSelective(manualOrder);
        Integer orderId = manualOrder.getId();

        // 添加出游需求
        ManualRequirement manualRequirement = new ManualRequirement();
        initRequirement(manualRequirement, requirement, orderInfo);
        manualRequirement.setManualOrderId(orderId);
        manualRequirementMapper.insertSelective(manualRequirement);

        // 添加联系人
        for (ContactQueryVo contactQueryVo : contacts) {
            ManualContact manualContact = new ManualContact();
            initContact(manualContact, contactQueryVo);
            manualContact.setManualOrderId(orderId);
            manualContactMapper.insertSelective(manualContact);
        }

        // 添加下单备忘
        ManualRemark manualRemark = new ManualRemark();
        manualRemark.setManualOrderId(orderId);
        manualRemark.setContent(
                (orderInfo.getRemark() != null && !"".equals(orderInfo.getRemark())) ? orderInfo.getRemark() : "订单完成下单操作");
        BaseDomainUtil.setBasePropertyValue(manualRemark);
        manualRemarkMapper.insertSelective(manualRemark);

        // 初始化任务流
        taskFlow.initTaskFlow(orderId);
        // 完成下单任务
        taskFlow.finish(orderId, OrderTaskEnum.ORDER);

        return orderId;
    }

    /**
     * 初始化订单信息
     * 
     * @param manualOrder
     * @param orderInfo
     */
    public void initOrder(ManualOrder manualOrder, OrderQueryVo orderInfo) {
        manualOrder.setAdultPrice(orderInfo.getAdultPrice());
        manualOrder.setAdultCount(orderInfo.getAdultCount());
        manualOrder.setChildPrice(orderInfo.getChildPrice());
        manualOrder.setChildCount(orderInfo.getChildCount());
        manualOrder.setSingleRoomCount(orderInfo.getSingleRoomCount());
        manualOrder.setSingleRoomPrice(orderInfo.getSingleRoomPrice());
        try {
            BigDecimal adultTotal = new BigDecimal(0);
            if (orderInfo.getAdultPrice() != null) {
                adultTotal = orderInfo.getAdultPrice().multiply(new BigDecimal(orderInfo.getAdultCount()));
            }
            BigDecimal childTotal = new BigDecimal(0);
            if (orderInfo.getChildPrice() != null) {
                childTotal = orderInfo.getChildPrice().multiply(new BigDecimal(orderInfo.getChildCount()));
            }
            BigDecimal singleRoomTotal = new BigDecimal(0);
            if (orderInfo.getSingleRoomPrice() != null) {
                singleRoomTotal = orderInfo.getSingleRoomPrice().multiply(new BigDecimal(orderInfo.getSingleRoomCount()));
            }
            BigDecimal totalPrice = adultTotal.add(childTotal).add(singleRoomTotal);
            manualOrder.setTotalPrice(totalPrice);
        } catch (Exception ex) {
            LOG.error("订单价格处理异常:{}", ex);
        }
        manualOrder.setStatusCode(OrderTaskEnum.ORDER.getKey());
        BaseDomainUtil.setBasePropertyValue(manualOrder);
    }

    /**
     * 初始化需求信息
     * 
     * @param manualRequirement
     * @param requirement
     */
    public void initRequirement(ManualRequirement manualRequirement, RequirementQueryVo requirement, OrderQueryVo orderInfo) {
        manualRequirement.setAdultNum(orderInfo.getAdultCount());
        manualRequirement.setChildNum(orderInfo.getChildCount());
        manualRequirement.setOlderNum(orderInfo.getOlderCount());
        manualRequirement.setCutType(orderInfo.getCutType());
        manualRequirement.setdOrderId(orderInfo.getdOrderId());
        manualRequirement.setDestCategoryId(requirement.getDestCategoryId());
        manualRequirement.setDestCategoryName(requirement.getDestCategoryName());
        if (requirement.getStartDate() != null && !"".equals(requirement.getStartDate())) {
            try {
                manualRequirement.setStartDate(
                        DateUtils.parseDate(requirement.getStartDate(), new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
            } catch (ParseException e) {
                LOG.error("出发日期转化失败:{}", e);
            }
        }
        if (requirement.getEndDate() != null && !"".equals(requirement.getEndDate())) {
            try {
                manualRequirement.setEndDate(
                        DateUtils.parseDate(requirement.getEndDate(), new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
            } catch (ParseException e) {
                LOG.error("截团日期转化失败:{}", e);
            }
        }
        manualRequirement.setFirstDestGroupId(requirement.getFirstDestGroupId());
        manualRequirement.setFirstDestGroupName(requirement.getFirstDestGroupName());
        manualRequirement.setSecDestGroupId(requirement.getSecDestGroupId());
        manualRequirement.setSecDestGroupName(requirement.getSecDestGroupName());
        manualRequirement.setDestId(requirement.getDestId());
        manualRequirement.setDestName(requirement.getDestName());
        manualRequirement.setProductId(requirement.getProductId());
        manualRequirement.setProductName(requirement.getProductName());
        manualRequirement.setdOrderId(requirement.getdOrderId());
        BaseDomainUtil.setBasePropertyValue(manualRequirement);
    }

    /**
     * 初始化联系人
     */
    public void initContact(ManualContact manualContact, ContactQueryVo contactQueryVo) {
        manualContact.setMemberId(contactQueryVo.getMemberId());
        manualContact.setName(contactQueryVo.getName());
        manualContact.setAppellation(1);
        manualContact.setTel(contactQueryVo.getTel());
        manualContact.setEmail(contactQueryVo.getEmail());
        manualContact.setFax(contactQueryVo.getFax());
        manualContact.setPhone(contactQueryVo.getPhone());
        manualContact.setFullName(contactQueryVo.getFullName());
        BaseDomainUtil.setBasePropertyValue(manualContact);
    }

}
