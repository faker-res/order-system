package com.tuniu.ord.jsondeseralize;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
/**
 * checkStyle 加备注
 * <Description> <br> 
 * @version 1.0<br>
 * @CreateDate 2015年12月29日 <br>
 */
public class JackJsonTimeFormat extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeString(null == value ? null : DateFormatUtils.format(value,"HH:mm:ss"));
    }
}
