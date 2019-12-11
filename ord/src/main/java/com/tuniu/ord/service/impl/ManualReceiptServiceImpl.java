/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualReceipt;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualReceiptMapper;
import com.tuniu.ord.service.ManualReceiptService;

/**
 * @author zhoujie8
 * 
 */
@Service
public class ManualReceiptServiceImpl implements ManualReceiptService {

    @Autowired
    private ManualReceiptMapper manualReceiptMapper;

    /*
     * (non-Javadoc)
     */
    @Override
    public void addManualReceipt(ManualReceipt manualReceipt) throws OrdCustomException {
        validateParam(manualReceipt);
        BaseDomainUtil.setBasePropertyValue(manualReceipt);
        manualReceiptMapper.insertSelective(manualReceipt);
    }

    /**
     * @param manualReceipt
     */
    private void validateParam(ManualReceipt manualReceipt) throws OrdCustomException {
        if (manualReceipt.getReceiptNumber() == null || manualReceipt.getReceiptNumber().equals("")) {
            throw new OrdCustomException(OrdError.RECEIPT_PARAM_ERROR, "合同编号为空！");
        }

        if (manualReceipt.getReceiptPrice() == null || (0 == manualReceipt.getReceiptPrice().compareTo(BigDecimal.ZERO))) {
            throw new OrdCustomException(OrdError.RECEIPT_PARAM_ERROR, "合同价格为空！");
        }
        // 任意正负小数，小数点后两位
        String numberRegex = "^-?\\d+\\.?\\d{0,2}$";
        boolean numerFlag = Pattern.matches(numberRegex, manualReceipt.getReceiptPrice().toString());
        if (!numerFlag) {
            throw new OrdCustomException(OrdError.RECEIPT_PRICE_ERROR);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ManualReceiptService#updateManualReceipt(com.tuniu.ord.domain.ManualReceipt)
     */
    @Override
    public void updateManualReceipt(ManualReceipt manualReceipt) throws OrdCustomException {
        manualReceiptMapper.updateByPrimaryKeySelective(manualReceipt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ManualReceiptService#delManualReceipt(java.lang.Integer)
     */
    @Override
    public void delManualReceipt(Integer id) throws OrdCustomException {
        manualReceiptMapper.deleteByPrimaryKey(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ManualReceiptService#queryManualReceipt(java.lang.Integer)
     */
    @Override
    public List<ManualReceipt> queryManualReceipt(Integer manualOrderId) throws OrdCustomException {
        return manualReceiptMapper.queryManualReceiptByManualOrderId(manualOrderId);
    }

}
