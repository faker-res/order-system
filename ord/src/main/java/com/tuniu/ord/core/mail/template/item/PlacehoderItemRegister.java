package com.tuniu.ord.core.mail.template.item;

import com.tuniu.ord.core.mail.template.handler.TextPlacehoderHandler;

/**
 * @author fangzhongwei
 * 
 */
public final class PlacehoderItemRegister {

    private PlacehoderItemRegister() {
    }

    public static final PlacehoderItem item_company = new PlacehoderItem("company", "企业名称",
            new TextPlacehoderHandler("company"));

    public static final PlacehoderItem item_account = new PlacehoderItem("account", "登录名", new TextPlacehoderHandler("account"));
}
