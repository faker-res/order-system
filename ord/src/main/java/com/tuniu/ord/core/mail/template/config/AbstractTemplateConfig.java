package com.tuniu.ord.core.mail.template.config;

import java.util.List;

import com.tuniu.ord.core.mail.template.handler.IPlacehoderHandler;
import com.tuniu.ord.core.mail.template.item.IPlacehoderItem;

public abstract class AbstractTemplateConfig implements ITemplateConfig {

    @Override
    public IPlacehoderHandler getHandler(String key) {
        List<IPlacehoderItem> items = availableItems();
        for (IPlacehoderItem item : items) {
            if (item.getKey().equals(key)) {
                return item.getHandler();
            }
        }
        return null;
    }

}
