/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import com.tuniu.ord.vo.UserVo;

/**
 * 将用户登陆的uid和nickname存储在ThreadLocal中
 * 
 * @author fangzhongwei
 * 
 */
public final class UserSessionUtil {

    private UserSessionUtil() {
    }

    private static ThreadLocal<UserVo> USER_TL = new ThreadLocal<UserVo>();

    public static void set(UserVo userVo) {
        USER_TL.set(userVo);
    }

    public static UserVo get() {
        UserVo userVo = USER_TL.get();
        if (null == userVo) {
            userVo = new UserVo();
        }
        return userVo;
    }

    /**
     * 获取当前登陆用户的uid
     * 
     * @return uid
     */
    public static int getUid() {
        return get().getUid();
    }

    /**
     * 获取当前登陆用户的nickname
     * 
     * @return nickname
     */
    public static String getNickname() {
        return get().getNickname();
    }
}
