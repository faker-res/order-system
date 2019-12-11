/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.constant.SupplymentTypeEnum;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.ManualOrderOccupyNum;
import com.tuniu.ord.domain.ManualSupplyment;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualOrderOccupyMapper;
import com.tuniu.ord.persistence.ManualSupplymentMapper;
import com.tuniu.ord.persistence.ManualTouristMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.service.ManualSupplymentService;

/**
 * @author zhoujie8
 * 
 */
@Service
public class ManualSupplymentServiceImpl implements ManualSupplymentService {

    @Resource
    private ManualSupplymentMapper manualSupplymentMapper;

    @Resource
    private ManualTouristMapper manualTouristMapper;

    @Resource
    private ManualOrderOccupyMapper manualOrderOccupyMapper;

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    /**
     * 根据订单id查询所有增补项
     * 
     * @param orderId
     * @return
     */
    @Override
    public List<ManualSupplyment> querySupplyment(Integer manualOrderId) throws OrdCustomException {
        vatalidateOrderId(manualOrderId);
        List<ManualSupplyment> manualSupplymentList = manualSupplymentMapper.queryByManualOrderId(manualOrderId);
        return manualSupplymentList;
    }

    /**
     * @param orderId
     */
    private void vatalidateOrderId(Integer manualOrderId) {
        if (manualOrderId == null) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_PARAM_ERROR, "订单id为空！");
        }
    }

    /*
     * 修改订单增补项
     * 
     * @Param supplyment
     */
    @Override
    public void updateSupplyment(ManualSupplyment supplyment) throws OrdCustomException {
        validateId(supplyment);
        validateAccountAndNum(supplyment);
        manualSupplymentMapper.updateByPrimaryKeySelective(supplyment);
    }

    /**
     * @param supplyment
     */
    private void validateId(ManualSupplyment supplyment) {
        if (supplyment.getId() == null) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_PARAM_ERROR, "id为空，无法进行修改！");
        }
    }

    /**
     * 验证增补项中的单价和数量
     * 
     * @param supplyment
     */
    private void validateAccountAndNum(ManualSupplyment supplyment) throws OrdCustomException {
        if (supplyment.getNumber() == null) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_PARAM_ERROR, "增补项数量不能为空！");
        }

        if (supplyment.getPrice() == null) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_PARAM_ERROR, "增补项单价不能为空！");
        }

        // 任意正负小数，小数点后两位
        String accountRegex = "^-?\\d+\\.?\\d{0,2}$";
        boolean accountFlag = Pattern.matches(accountRegex, supplyment.getPrice().toString());
        if (!accountFlag) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_PRICE_ERROR);
        }

        // 任意数字，不能为0
        String numRegex = "\\d+";
        boolean numFlag = Pattern.matches(numRegex, supplyment.getNumber().toString());
        if (!numFlag) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_NUM_ERROR);
        }

    }

    /*
     * 添加（支持批量添加）订单增补项
     */
    @Override
    public void addSupplyment(ManualSupplyment supplyment) throws OrdCustomException {
        validateAddSupplyment(supplyment);
        BaseDomainUtil.setBasePropertyValue(supplyment);
        if (checkExist(supplyment)) {
            manualSupplymentMapper.updateByPrimaryKeySelective(supplyment);
        } else {
            manualSupplymentMapper.insertSelective(supplyment);
        }

    }

    /**
     * @param supplyment
     * @return
     */
    private boolean checkExist(ManualSupplyment supplyment) {
        if (supplyment.getId() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验添加订单增补项时的参数
     * 
     * @param supplymentList
     */
    private void validateAddSupplyment(ManualSupplyment supplyment) {
        validateAccountAndNum(supplyment);
    }

    /*
     * 删除订单增补项
     */
    @Override
    public void delSupplyment(Integer id) throws OrdCustomException {
        if (id == null) {
            throw new OrdCustomException(OrdError.SUPPLYMENT_PARAM_ERROR, "删除订单增补项时id为空！");
        }
        manualSupplymentMapper.deleteByPrimaryKey(id);

    }

    /*
     * 
     * @see com.tuniu.ord.service.ManualSupplymentService#queryPriceDetail(java.lang.Integer)
     */
    @Override
    public String queryPriceDetail(Integer manualOrderId) {
        BigDecimal total = BigDecimal.ZERO;

        StringBuffer occupyStr = new StringBuffer();

        List<ManualOrderOccupyNum> queryOccupyPriceList = manualOrderOccupyMapper.queryOccupyPriceList(manualOrderId);
        if (CollectionUtils.isNotEmpty(queryOccupyPriceList)) {
            for (int i = 0; i < queryOccupyPriceList.size(); i++) {
                ManualOrderOccupyNum occupyNum = queryOccupyPriceList.get(i);
                BigDecimal adultTotal = occupyNum.getAdultPrice().multiply(new BigDecimal(occupyNum.getAdultNum()));
                BigDecimal childTotal = occupyNum.getChildPrice().multiply(new BigDecimal(occupyNum.getChildNum()));
                total = total.add(childTotal).add(adultTotal);
                if (i != 0) {
                    occupyStr.append("+");
                }
                occupyStr.append(getStringBuffer(occupyNum));
            }
        }

        StringBuffer manualSupply = new StringBuffer();

        List<ManualSupplyment> supplymentList = manualSupplymentMapper.queryByManualOrderId(manualOrderId);
        if (CollectionUtils.isNotEmpty(supplymentList)) {
            for (int i = 0; i < supplymentList.size(); i++) {
                ManualSupplyment supplyment = supplymentList.get(i);
                BigDecimal totalCost = supplyment.getPrice().multiply(new BigDecimal(supplyment.getNumber()));
                total = total.add(totalCost);
                if (i != 0) {
                    manualSupply.append("+");
                }
                manualSupply.append(getStringBufferFromSupplyment(supplyment));
            }
        }

        StringBuffer resultString = new StringBuffer();
        resultString.append("订单总价(").append(total.toString()).append(")");

        if (occupyStr.length() > 0) {
            resultString.append(" = ");
            resultString.append(occupyStr);
            if (manualSupply.length() > 0) {
                resultString.append("+");
                resultString.append(manualSupply);
            }
        } else {
            if (manualSupply.length() > 0) {
                resultString.append(" = ");
                resultString.append(manualSupply);
            }
        }

        return resultString.toString();
    }

    /**
     * @param manualOrderOccupyNum
     * @return
     */
    private StringBuffer getStringBufferFromSupplyment(ManualSupplyment supplyment) {
        Integer type = supplyment.getSupplyType();
        StringBuffer sb = new StringBuffer();
        sb.append("${订单增补项-").append(SupplymentTypeEnum.getNameFromType(type)).append("}单价(").append(supplyment.getPrice())
                .append(")*数量(").append(supplyment.getNumber()).append(")");
        return sb;
    }

    /**
     * @param occupy
     * @return
     */
    private StringBuffer getStringBuffer(ManualOrderOccupyNum occupy) {
        StringBuffer sb = new StringBuffer();
        sb.append("成人价(").append(occupy.getAdultPrice().toString()).append(")*成人数(").append(occupy.getAdultNum()).append(")");
        sb.append("+");
        sb.append("儿童价(").append(occupy.getChildPrice().toString()).append(")*儿童数(").append(occupy.getChildNum()).append(")");
        return sb;
    }

    /**
     * 
     * @param orderOccupy
     * @param touristList
     * @return
     */
    private ManualOrderOccupyNum handlePriceDetail(ManualOrderOccupy orderOccupy) {
        OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(orderOccupy.getdOrderId());
        ManualOrderOccupyNum manualOrderOccupyNum = new ManualOrderOccupyNum();
        manualOrderOccupyNum.setAdultPrice(ordDealOrder.getAdultPrice());
        manualOrderOccupyNum.setChildPrice(ordDealOrder.getChildPrice());
        manualOrderOccupyNum.setdOrderId(orderOccupy.getdOrderId());
        manualOrderOccupyNum.setChildNum(orderOccupy.getChildNum());
        manualOrderOccupyNum.setAdultNum(orderOccupy.getAdultNum());
        return manualOrderOccupyNum;
    }

}
