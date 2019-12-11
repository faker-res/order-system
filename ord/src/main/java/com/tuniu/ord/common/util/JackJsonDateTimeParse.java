/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月28日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.operation.framework.base.time.DateFormatUtils;

/**
 * @author fangzhongwei
 * 
 */
public class JackJsonDateTimeParse extends JsonDeserializer<Date> {

    private static final Logger LOG = LoggerFactory.getLogger(JackJsonDateTimeParse.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String date = jp.getText();
        try {
            return DateFormatUtils.parse("yyyy-MM-dd", date);
        } catch (Exception e) {
            LOG.error("日期解析出错", e);
        }
        return null;
    }

}
