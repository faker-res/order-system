package com.tuniu.ord.core.mail.template.handler;

import java.util.Date;

import com.tuniu.operation.framework.base.time.DateFormatUtils;
import com.tuniu.ord.core.mail.template.context.IContext;

/**
 * @author fangzhongwei
 * 
 */
public class DatePlacehoderHandler implements IPlacehoderHandler {

    private final String placehoderKey;

    public DatePlacehoderHandler(String placehoderKey) {
        this.placehoderKey = placehoderKey;
    }

    @Override
    public boolean accept(String placeHodlerKey) {
        return this.placehoderKey.equals(placeHodlerKey.trim());
    }

    @Override
    public String handle(String placeHodlerKey, IContext context) {
        Object value = context.getValue(placeHodlerKey.trim());
        if (null == value) {
            return null;
        }
        if (value instanceof Date) {
            return DateFormatUtils.formatDatetime((Date) value);
        }
        return value.toString();
    }

}
