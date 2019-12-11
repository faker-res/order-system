package com.tuniu.operation.framework.base.rest;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.tuniu.operation.framework.base.util.SystemConstants;

/**
 * @author fangzhongwei
 * 
 */
public class RestCodec {

    public static String encodeBase64(String data) throws RestException {
        if (null == data) {
            return null;
        }
        try {
            return Base64.encodeBase64String(data.getBytes(SystemConstants.UTF8_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw RestException.withError(e);
        }
    }

    public static String decodeBase64(String data) throws RestException {
        if (null == data) {
            return null;
        }
        try {
            return new String(Base64.decodeBase64(data), SystemConstants.UTF8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw RestException.withError(e);
        }
    }

}
