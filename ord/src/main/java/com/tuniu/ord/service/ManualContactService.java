package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.ManualContact;

public interface ManualContactService {

    void saveContact(ManualContact record);

    void removeContact(ManualContact record);

    /**
     * @param manualOrderId
     * @return
     */
    List<ManualContact> queryContactList(Integer manualOrderId);

    /**
     * @param id
     */
    void delContact(Integer id);
}
