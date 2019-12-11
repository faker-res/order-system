package com.tuniu.ord.common.converter;

/**
 * @author fangzhongwei
 * 
 */
public final class WhiteSpaceConverter {

    public static String convert(String value) {
        String s = new String();

        if (null != value) {
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                if (Character.isWhitespace(ch)) {
                    s = s.concat(" ");
                } else {
                    s = s.concat(Character.toString(ch));
                }
            }
        }

        return s;
    }

}
