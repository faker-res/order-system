package com.tuniu.ord.core.mail.template.handler;

import com.tuniu.ord.core.mail.template.context.IContext;

/**
 * @author fangzhongwei
 * 
 */
public interface IPlacehoderHandler {

    boolean accept(String placeHodlerKey);

    String handle(String placeHodlerKey, IContext context);

}
