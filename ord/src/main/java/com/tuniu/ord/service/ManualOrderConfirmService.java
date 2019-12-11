package com.tuniu.ord.service;

import com.tuniu.ord.vo.ResponseVo;

public interface ManualOrderConfirmService {
    /**
     * 发起确认<br>
     * 1.生成订单子流水销售单 <br>
     * 2.产品签约出库
     * 
     * @param orderOccupyId
     */
    ResponseVo confirm(Integer orderOccupyId);

    /**
     * 完成确认<br>
     * 1.发送派团
     * 
     * @param orderId
     *            订单号
     */
    void finishConfirm(Integer orderId);
}
