package com.tuniu.ord.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateFormatUtils;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.ExternalRestUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.persistence.ManualOrderOccupyMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.service.ManualProductQueryService;
import com.tuniu.ord.vo.ManualPrdouctResultVo;
import com.tuniu.ord.vo.ManualProductQueryVo;
import com.tuniu.ord.vo.ProductInfoSelectedVo;
import com.tuniu.ord.vo.common.ExternalResponseObj;
import com.tuniu.ord.vo.common.ListVo;
import com.tuniu.ord.vo.common.ResponseListVo;
import com.tuniu.ord.vo.external.QueryGroupOutputVo;

@Service
public class ManualProductQueryServiceImpl implements ManualProductQueryService {
    @Resource
    private OrdDealOrderMapper dealOrderMapper;

    @Resource
    private ManualOrderOccupyMapper manualOrderOccupyMapper;

    @Override
    public ListVo queryManualProduct(ManualProductQueryVo manualProductQueryVo) {
        String response = RestUtil.executeWithTenantId(SystemInitParameter.queryManualProductInfo, SystemConstants.HTTP_GET,
                JsonUtil.toString(manualProductQueryVo));

        ResponseListVo<ManualPrdouctResultVo> responseListVo = JsonUtil.toBean(response, ResponseListVo.class,
                ManualPrdouctResultVo.class);
        if (responseListVo == null) {
            return null;
        }

        List<ManualPrdouctResultVo> filterList = new ArrayList<ManualPrdouctResultVo>();

        if (responseListVo.isSuccess()) {
            List<ManualPrdouctResultVo> data = responseListVo.getData();
            if (CollectionUtils.isNotEmpty(data)) {
                for (ManualPrdouctResultVo manualPrdouctResultVo : data) {
                    Integer dOrderId = manualPrdouctResultVo.getdOrderId();
                    OrdDealOrder ordDealOrder = dealOrderMapper.selectByOrderId(dOrderId);
                    manualPrdouctResultVo.setAdultPrice(ordDealOrder.getAdultPrice());
                    manualPrdouctResultVo.setChildPrice(ordDealOrder.getChildPrice());
                    int canSaleNum = ordDealOrder.getDealOrderNum() - ordDealOrder.getOccupyNum()
                            - ordDealOrder.getConfirmNum();
                    if (manualProductQueryVo.isOnlyCanOrderPrd()) {
                        if (canSaleNum <= 0) {
                            continue;
                        }
                    }
                    manualPrdouctResultVo.setCanSaleNum(canSaleNum);
                    manualPrdouctResultVo.setOccupyNum(ordDealOrder.getOccupyNum());
                    manualPrdouctResultVo.setSaledNum(ordDealOrder.getConfirmNum());

                    String bookEndDate = manualPrdouctResultVo.getBookEndDate();
                    try {
                        if (DateFormatUtils.parseDate(bookEndDate).compareTo(new Date()) == 1) {
                            manualPrdouctResultVo.setStop(false);
                        } else {
                            manualPrdouctResultVo.setStop(true);
                        }
                    } catch (ParseException e) {
                        throw new SaaSSystemException("时间转化错误");
                    }

                    filterList.add(manualPrdouctResultVo);
                }
            } else {
                return null;
            }
        } else {
            throw new SaaSSystemException(responseListVo.getMsg());
        }

        ListVo listVo = new ListVo();
        listVo.setCount(filterList.size());
        int toIndex = manualProductQueryVo.getStart() + manualProductQueryVo.getLimit();
        if (filterList.size() < toIndex) {
            listVo.setRows(filterList.subList(manualProductQueryVo.getStart(), filterList.size()));
        } else {
            listVo.setRows(filterList.subList(manualProductQueryVo.getStart(), toIndex));
        }
        return listVo;
    }

    @Override
    public Integer getAllCanSaleNum(List<ManualPrdouctResultVo> products) {
        Integer totalNum = 0;
        if (!CollectionUtils.isNotEmpty(products)) {
            return 0;
        }
        for (ManualPrdouctResultVo manualPrdouctResultVo : products) {
            totalNum += manualPrdouctResultVo.getCanSaleNum();
        }
        return totalNum;
    }
    
    @Override
    public Integer getAllSaledNum(List<ManualPrdouctResultVo> products) {
        Integer totalNum = 0;
        if (!CollectionUtils.isNotEmpty(products)) {
            return 0;
        }
        for (ManualPrdouctResultVo manualPrdouctResultVo : products) {
            totalNum += manualPrdouctResultVo.getSaledNum();
        }
        return totalNum;
    }
        
    @Override
    public Integer getAllOccupyNum(List<ManualPrdouctResultVo> products) {
        Integer totalNum = 0;
        if (!CollectionUtils.isNotEmpty(products)) {
            return 0;
        }
        for (ManualPrdouctResultVo manualPrdouctResultVo : products) {
            totalNum += manualPrdouctResultVo.getOccupyNum();
        }
        return totalNum;
    }

    @Override
    public ListVo queryPrdSelect(Integer manualOrderId) {
        List<ProductInfoSelectedVo> result = new ArrayList<ProductInfoSelectedVo>();

        List<ManualOrderOccupy> manualOrderOccupies = manualOrderOccupyMapper.queryInfoByManualOrderId(manualOrderId);
        for (ManualOrderOccupy manualOrderOccupy : manualOrderOccupies) {
            ProductInfoSelectedVo infoSelectedVo = new ProductInfoSelectedVo();

            OrdDealOrder ordDealOrder = dealOrderMapper.selectByOrderId(manualOrderOccupy.getdOrderId());
            infoSelectedVo.setAdultPrice(ordDealOrder.getAdultPrice());
            infoSelectedVo.setChildPrice(ordDealOrder.getChildPrice());

            ExternalResponseObj<QueryGroupOutputVo> groupBaseInfo = ExternalRestUtil.getGroupBaseInfo(
                    ordDealOrder.getProductId(), new String[] { DateFormatUtils.formatDate(ordDealOrder.getDepartDate()) });
            if (groupBaseInfo.getData() != null && CollectionUtils.isNotEmpty(groupBaseInfo.getData().getRows())) {
                infoSelectedVo.setDeadLineDate(
                        DateFormatUtils.formatDate(groupBaseInfo.getData().getRows().get(0).getDeadlineTime()));
            } else {
                throw new SaaSSystemException("团信息不存在");
            }
            infoSelectedVo.setOccupyNum(manualOrderOccupy.getNumber());
            infoSelectedVo.setOccupyStatus(manualOrderOccupy.getStatus());
            infoSelectedVo.setProductId(ordDealOrder.getProductId());
            infoSelectedVo.setProductName(ordDealOrder.getProductName());
            infoSelectedVo.setProductOwner(ordDealOrder.getProductStaffName());
            infoSelectedVo.setTourDate(DateFormatUtils.formatDate(ordDealOrder.getDepartDate()));
            infoSelectedVo.setManualOccpuyId(manualOrderOccupy.getId());

            result.add(infoSelectedVo);
        }

        ListVo listVo = new ListVo();
        listVo.setCount(result.size());
        listVo.setRows(result);
        return listVo;
    }
}
