package com.tuniu.ord.common.util;

public class TouristUtil {

    public static Integer convertSex(String sex) {
        Integer result = 9;
        if (sex.equals("男")) {
            result = 1;
        } else if (sex.equals("女")) {
            result =  0;
        }
        return result;
    }
    
    public static Integer convertTouristType (String touristType) {
        Integer result = 0;
        if (touristType.equals("成人")) {
            result = 0;
        } else if (touristType.equals("儿童")) {
            result = 1;
        } else {
            //游客类型必填
            throw new IllegalArgumentException(" tourist type is null");
        }
        return result;
    }
    
    public static Integer convertPsptType (String psptType) {
        if(psptType == null) {
            return null;
        }
        Integer result = 0;
        if (psptType.equals("未知")) {
            result = 0;
        } else if (psptType.equals("身份证")) {
            result = 1;
        } else if (psptType.equals("护照")) {
            result = 2;
        } else if (psptType.equals("军官证")) {
            result = 3;
        } else if (psptType.equals("港澳通行证")) {
            result = 4;
        } else if (psptType.equals("其他")) {
            result = 5;
        } else if (psptType.equals("台胞证")) {
            result = 6;
        }
        return result;
    }
}
