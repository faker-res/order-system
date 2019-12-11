/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.io.Serializable;

/**
 * 用于存储用户登陆后的uid和nickname
 * 
 * @author fangzhongwei
 * 
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = 4772129157219646324L;

    private int uid;

    private String nickname = "";

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
