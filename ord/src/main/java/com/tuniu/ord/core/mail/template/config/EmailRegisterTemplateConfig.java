package com.tuniu.ord.core.mail.template.config;

import java.util.LinkedList;
import java.util.List;

import com.tuniu.ord.core.mail.template.item.IPlacehoderItem;
import com.tuniu.ord.core.mail.template.item.PlacehoderItemRegister;

/**
 * @author fangzhongwei
 * 
 */
public class EmailRegisterTemplateConfig extends AbstractTemplateConfig {

    private static List<IPlacehoderItem> items = new LinkedList<IPlacehoderItem>();

    @Override
    public void init() {
        items.add(PlacehoderItemRegister.item_company);
        items.add(PlacehoderItemRegister.item_account);
    }

    @Override
    public List<IPlacehoderItem> availableItems() {
        return items;
    }
}
