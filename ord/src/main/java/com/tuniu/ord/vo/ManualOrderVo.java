package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.domain.ManualContact;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.domain.ManualRequirement;

public class ManualOrderVo {
    private ManualRequirement requirement;

    private ManualOrder order;

    private List<ManualContact> contact;

    private List<ManualRemark> remark;

    public ManualRequirement getRequirement() {
        return requirement;
    }

    public void setRequirement(ManualRequirement requirement) {
        this.requirement = requirement;
    }

    public ManualOrder getOrder() {
        return order;
    }

    public void setOrder(ManualOrder order) {
        this.order = order;
    }

    public List<ManualContact> getContact() {
        return contact;
    }

    public void setContact(List<ManualContact> contact) {
        this.contact = contact;
    }

    public List<ManualRemark> getRemark() {
        return remark;
    }

    public void setRemark(List<ManualRemark> remark) {
        this.remark = remark;
    }

}
