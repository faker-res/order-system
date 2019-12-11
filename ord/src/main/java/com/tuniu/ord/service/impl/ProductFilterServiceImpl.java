package com.tuniu.ord.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.service.ProductFilterService;
import com.tuniu.ord.vo.ManualPrdouctResultVo;

@Service
public class ProductFilterServiceImpl implements ProductFilterService {

    @Override
    public ManualPrdouctResultVo filterProducts(List<ManualPrdouctResultVo> rows, Integer dOrderId) {
        if(!CollectionUtils.isNotEmpty(rows) || dOrderId == null) {
            return null;
        }
        for (ManualPrdouctResultVo row : rows) {
            if(row.getdOrderId().equals(dOrderId)) {
                return row;
            }
        }
        return null;
    }

    @Override
    public List<ManualPrdouctResultVo> filterProducts(List<ManualPrdouctResultVo> rows, ManualRequirement requirement) {
        List<ManualPrdouctResultVo> result = new LinkedList<ManualPrdouctResultVo>();
        if(!CollectionUtils.isNotEmpty(rows) || requirement == null) {
            return null;
        }
        ManualPrdouctResultVo filterProduct = filterProducts(rows, requirement.getdOrderId());
        if(filterProduct == null) {
            result = filterProductsByNeedNum(rows, requirement.getAdultNum() + requirement.getChildNum());
            return result;
        }
        if(filterProduct.getCanSaleNum() >= requirement.getAdultNum() + requirement.getChildNum()) {
            result.add(filterProduct);
        } else if (filterProduct.getCanSaleNum() == 0) {
            result.addAll(filterProductsByNeedNum(rows, requirement.getAdultNum() + requirement.getChildNum()));
        } else {
            result.add(filterProduct);
            result.addAll(filterProductsByNeedNum(rows, requirement.getAdultNum() + requirement.getChildNum() - filterProduct.getCanSaleNum()));
        }
        return result;
    }

    @Override
    public List<ManualPrdouctResultVo> filterProductsByNeedNum(List<ManualPrdouctResultVo> rows, Integer needNum) {
        List<ManualPrdouctResultVo> result = new LinkedList<ManualPrdouctResultVo>();
        if(!CollectionUtils.isNotEmpty(rows) || needNum == null) {
            return null;
        }
        Collections.sort(rows, new Comparator<ManualPrdouctResultVo>() {
            //按成人价格进行排序
            @Override
            public int compare(ManualPrdouctResultVo o1, ManualPrdouctResultVo o2) {
                // 第一次比较可收人数
                int i = o1.getCanSaleNum().compareTo(o2.getCanSaleNum());
                if(i == 0) {
                    //如果可收人数相同则比较成人价格
                    return o1.getAdultPrice().compareTo(o2.getAdultPrice());
                }
                return i;
            }
        });
        Integer tmp = needNum;
        for (ManualPrdouctResultVo row : rows) {
            if(tmp <= 0) {
                break;
            }
            if(row.getCanSaleNum() <=0 ) {
                continue;
            }
            result.add(row);
            tmp -= row.getCanSaleNum();
        }
        return result;
    }

}
