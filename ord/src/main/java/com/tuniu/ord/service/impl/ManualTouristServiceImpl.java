package com.tuniu.ord.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.excel.ExcelReader;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.BeanUtil;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualTourist;
import com.tuniu.ord.domain.ManualTouristCertificate;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualTouristCertificateMapper;
import com.tuniu.ord.service.ManualTouristService;
import com.tuniu.ord.vo.ManualTouristVo;
import com.tuniu.ord.vo.common.ListVo;

@Service
public class ManualTouristServiceImpl extends CommonOrderServiceImpl implements ManualTouristService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualTouristServiceImpl.class);
    
    @Resource
    private ManualTouristCertificateMapper touristCertificateMapper;

    @Override
    public void saveTourist(ManualTouristVo tourist) {
        if(tourist == null) {
            throw new IllegalArgumentException("manual requirement is null");
        }
        if (tourist.getId() == null) {
            // new a manual tourist record
            BaseDomainUtil.setBasePropertyValue(tourist);
            touristMapper.insertSelective(tourist);
            if(!CollectionUtils.isEmpty(tourist.getCertificates())) {
                for (ManualTouristCertificate certificate : tourist.getCertificates()) {
                    BaseDomainUtil.setBasePropertyValue(certificate);
                    certificate.setTouristId(tourist.getId());
                    touristCertificateMapper.insertSelective(certificate);
                }
            }
        } else {
            //删除游客证件信息
            touristCertificateMapper.deleteByTouristId(tourist.getId());
            //添加游客证件信息
            if(!CollectionUtils.isEmpty(tourist.getCertificates())) {
                for (ManualTouristCertificate certificate : tourist.getCertificates()) {
                    BaseDomainUtil.setBasePropertyValue(certificate);
                    certificate.setTouristId(tourist.getId());
                    touristCertificateMapper.insertSelective(certificate);
                }
            }
            BaseDomainUtil.setUpdatePropertyValue(tourist);
            touristMapper.updateByPrimaryKeySelective(tourist);
        }
    }

    @Override
    public void removeTourist(Integer touristId) {
        if(touristId == null) {
            // do nothing if record is null;
            return;
        }
        ManualTourist tourist = touristMapper.selectByPrimaryKey(touristId);
        if(tourist == null) {
            LOGGER.error("select manual tourist failure,[TouristId]:{},[Msg]:{}", touristId, "result is empty");
            throw new OrdCustomException(OrdError.NO_TOURIST_ERROR);
        }
        tourist.setDelFlag(Constants.DELETE);
        touristMapper.updateByPrimaryKeySelective(tourist);
        List<ManualTouristCertificate> certificates = touristCertificateMapper.selectByTouristId(tourist.getId());
        if(!CollectionUtils.isEmpty(certificates)) {
            for (ManualTouristCertificate certificate : certificates) {
                certificate.setDelFlag(Constants.DELETE);
                touristCertificateMapper.updateByPrimaryKeySelective(certificate);
            }
        }
    }

    @Override
    public ListVo queryTourists(Integer manualOrderId) {
        if (manualOrderId == null) {
            return null;
        }
        List<ManualTourist> tourists = touristMapper.selectByManualOrderId(manualOrderId);
        List<ManualTouristVo> touristVos = translateTourist(tourists);
        if(!CollectionUtils.isEmpty(touristVos)) {
            for (ManualTouristVo tourist : touristVos) {
                List<ManualTouristCertificate> certificates = touristCertificateMapper.selectByTouristId(tourist.getId());
                tourist.setCertificates(certificates);
            }
        }
        ListVo listVo = new ListVo();
        if(!CollectionUtils.isEmpty(touristVos)) {
            listVo.setCount(touristVos.size());
            listVo.setRows(touristVos);
        }
        return listVo;
    }

    private List<ManualTouristVo> translateTourist(List<ManualTourist> tourists) {
        if(CollectionUtils.isEmpty(tourists)) {
            return null;
        }
        List<ManualTouristVo> touristVos = new LinkedList<ManualTouristVo>();
        for (ManualTourist tourist : tourists) {
            ManualTouristVo touristVo = new ManualTouristVo();
            BeanUtil.copyBeanProperties(tourist, touristVo);
            touristVos.add(touristVo);
        }
        return touristVos;
    }

    @Override
    public void saveTourists(List<ManualTouristVo> tourists) {
        if (CollectionUtils.isEmpty(tourists)) {
            return;
        }
        for (ManualTouristVo tourist : tourists) {
            saveTourist(tourist);
        }
    }

    @Override
    public void checkTourist(ManualTouristVo vo) {
        ManualOrder manualOrder = getManualOrder(vo.getManualOrderId());
        if(manualOrder == null) {
            LOGGER.error("manuan order is null");
            throw new OrdCustomException(OrdError.NO_MANUAL_ORDER);
        }
        
        List<ManualTourist> tourists = getTourist(manualOrder.getId());
        int adultNum = 0, childNum = 0;
        if(!CollectionUtils.isEmpty(tourists)) {
            for (ManualTourist tourist : tourists) {
                if(tourist.getTouristType().equals(Constants.ADULT)) {
                    adultNum ++;
                } else if (tourist.getTouristType().equals(Constants.CHILD)) {
                    childNum ++;
                }
            }
        }
        if(vo.getTouristType().equals(Constants.ADULT)) {
            adultNum ++;
        } else if (vo.getTouristType().equals(Constants.CHILD)) {
            childNum ++;
        }
        if(adultNum > manualOrder.getAdultCount()) {
            LOGGER.error("tourist list's adult number is bigger than manual order's adult number.");
            throw new OrdCustomException(OrdError.TOURIST_ADULTNUM_ERROR);
        }
        if(childNum > manualOrder.getChildCount()) {
            LOGGER.error("tourist list's child number is bigger than manual order's child number.");
            throw new OrdCustomException(OrdError.TOURIST_CHILDNUM_ERROR);
        }
    }

    @Override
    public void batchRemoveTourist(List<ManualTouristVo> tourists) {
        if (CollectionUtils.isEmpty(tourists)) {
            return;
        }
        for (ManualTouristVo tourist : tourists) {
            removeTourist(tourist.getId());
        }
    }
}
