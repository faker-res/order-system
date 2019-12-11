package com.tuniu.ord.jsondeseralize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * checkStyle 加备注 <Description> <br>
 * 
 * @version 1.0<br>
 * @CreateDate 2015年12月29日 <br>
 */
public class JackJsonTimeParse extends JsonDeserializer<Date> {
    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(JackJsonTimeParse.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String date = jp.getText();
        if(date == null || date.equals("")) {
        	return null;
        } 
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            return formatter.parse(date);
        } catch (Exception e) {
            LOG.error("时间解析出错", e);
        }
        return null;
    }
}
