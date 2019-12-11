package com.tuniu.ord.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.domain.CheckLossTourist;
import com.tuniu.ord.persistence.CheckLossTouristMapper;
import com.tuniu.ord.service.CheckLossTouristService;
import com.tuniu.ord.vo.CheckLossTouristVo;

@Service(value = "checkLossTouristServiceImpl")
public class CheckLossTouristServiceImpl implements CheckLossTouristService {

    @Resource
    private CheckLossTouristMapper checkLossTouristMapper;

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public Integer insert(CheckLossTourist record) {
        return null;
    }

    @Override
    public Integer insertSelective(CheckLossTourist record) {
        return checkLossTouristMapper.insertSelective(record);
    }

    @Override
    public CheckLossTourist selectByPrimaryKey(Integer id) {
        return checkLossTouristMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CheckLossTourist record) {
        return checkLossTouristMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(CheckLossTourist record) {
        return checkLossTouristMapper.updateByPrimaryKey(record);
    }

    @Override
    public void selectTouristByPspIdAndSave(CheckLossTouristVo checkLossTouristVo) {
        List<CheckLossTouristVo> touristList = checkLossTouristMapper.selectByCheckLossIdAndPsptId(checkLossTouristVo);

        // 判断是否查询到对应游客
        ArgumentValidator.isNotNullEmpty("touristList", touristList);
        for (CheckLossTouristVo tourist : touristList) {
            CheckLossTourist checkLossTourist = new CheckLossTourist();
            checkLossTourist.setCheckLossId(checkLossTouristVo.getCheckLossId());
            checkLossTourist.setOrdPeopleTouristId(tourist.getPeopleTouristId());
            checkLossTourist.setAddTime(new Date());
            checkLossTouristMapper.insertSelective(checkLossTourist);
        }
    }

}
