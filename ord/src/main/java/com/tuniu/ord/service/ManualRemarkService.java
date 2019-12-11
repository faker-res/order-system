package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.vo.common.ListVo;

public interface ManualRemarkService {

    void saveRemark(ManualRemark record);

    void removeRemark(ManualRemark record);

    ListVo queryRemarks(Integer orderId);

    List<ManualRemark> queryRemarkByManualOrderId(Integer manualOrderId);
}
