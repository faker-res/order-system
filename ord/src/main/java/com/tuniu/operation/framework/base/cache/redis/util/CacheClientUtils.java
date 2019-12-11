package com.tuniu.operation.framework.base.cache.redis.util;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheClientUtils {

    private static final String LOG_Format = "CMD=> {0} - K(s)=> {1}";

    public static String generateIdentity(Commands method, Object... keys) {
        return MessageFormat.format(LOG_Format, method, arrayToString(keys));
    }

    private static String arrayToString(Object... keys) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < keys.length; i++) {
            buffer.append(keys[i]);
            if (i != keys.length - 1) {
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

    public static String generateIdentity(Commands method, String... keys) {
        Object[] objs = Arrays.copyOfRange(keys, 0, keys.length, Object[].class);
        return generateIdentity(method, objs);
    }

    public static void main(String[] args) {
        System.out.println(generateIdentity(Commands.APPEND, "A", "B"));
    }

    public static List<String> bytesToString(byte[]... bytesArr) {
        if (null != bytesArr) {
            List<String> list = new ArrayList<String>(bytesArr.length);
            for (byte[] bytes : bytesArr) {
                list.add(new String(bytes, Charset.defaultCharset()));
            }
            return list;
        }
        return null;
    }
}
