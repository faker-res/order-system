package com.tuniu.ord.core.mail.template.config;

import java.util.List;

import com.tuniu.ord.core.mail.template.handler.IPlacehoderHandler;
import com.tuniu.ord.core.mail.template.item.IPlacehoderItem;

/**
 * @author fangzhongwei
 * 
 */
public interface ITemplateConfig {

    void init();

    List<IPlacehoderItem> availableItems();

    IPlacehoderHandler getHandler(String key);

}
