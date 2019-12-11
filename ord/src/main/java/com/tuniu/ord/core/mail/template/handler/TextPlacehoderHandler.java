package com.tuniu.ord.core.mail.template.handler;

import com.tuniu.ord.core.mail.template.context.IContext;

/**
 * @author fangzhongwei
 * 
 */
public class TextPlacehoderHandler implements IPlacehoderHandler {

    private final String placehoderKey;

    public TextPlacehoderHandler(String placehoderKey) {
        this.placehoderKey = placehoderKey;
    }

    @Override
    public boolean accept(String placeHodlerKey) {
        return this.placehoderKey.equals(placeHodlerKey.trim());
    }

    @Override
    public String handle(String placeHodlerKey, IContext context) {
        Object value = context.getValue(placeHodlerKey.trim());
        return null == value ? null : value.toString();
    }

}
