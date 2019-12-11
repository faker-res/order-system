package com.tuniu.ord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.persistence.ManualRemarkMapper;
import com.tuniu.ord.service.ManualRemarkService;
import com.tuniu.ord.vo.common.ListVo;

@Service
public class ManualRemarkServiceImpl implements ManualRemarkService {

    @Resource
    private ManualRemarkMapper remarkMapper;

    @Override
    public void saveRemark(ManualRemark record) {
        if (record == null) {
            throw new IllegalArgumentException("manual remark is null");
        }
        BaseDomainUtil.setBasePropertyValue(record);
        if (record.getId() == null) {
            // new a manual remark record
            remarkMapper.insertSelective(record);
        } else {
            remarkMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public void removeRemark(ManualRemark record) {
        if (record == null || record.getId() == null) {
            // do nothing if record is null;
            return;
        }
        remarkMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public ListVo queryRemarks(Integer manualOrderId) {
        if (manualOrderId == null) {
            throw new IllegalArgumentException("order id is null");
        }
        ListVo vo = new ListVo();
        List<ManualRemark> remarks = remarkMapper.queryByManualOrderId(manualOrderId);
        vo.setRows(remarks);
        vo.setCount(remarks.size());
        return vo;
    }

    @Override
    public List<ManualRemark> queryRemarkByManualOrderId(Integer manualOrderId) {
        return remarkMapper.queryByManualOrderId(manualOrderId);
    }

}
