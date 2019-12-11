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

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tuniu.operation.framework.base.time.DateFormatUtils;

/**
 * @author fangzhongwei
 * 
 */
public class JackJsonDateFormat extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeString(null == value ? null : DateFormatUtils.format("yyyy-MM-dd", value));
    }

}
