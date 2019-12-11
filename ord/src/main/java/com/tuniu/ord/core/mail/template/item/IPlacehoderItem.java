package com.tuniu.ord.core.mail.template.item;

import com.tuniu.ord.core.mail.template.handler.IPlacehoderHandler;

/**
 * @author fangzhongwei
 * 
 */
public interface IPlacehoderItem {

    String getKey();

    String getName();

    IPlacehoderHandler getHandler();

}
