/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月24日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util.session;

import java.util.Random;

/**
 * @author zhairongping
 *
 */
public class SessionIdByRandom implements ISessionId {

    /*
     * 随机产生9位数字
     * 
     */
    @Override
    public Integer createSessionId() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(9));
        }
        return Integer.valueOf(sb.toString());
    }

}
