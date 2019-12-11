/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月24日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util.session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import com.tuniu.operation.framework.base.time.DateUtils;

/**
 * @author zhairongping
 *
 */
public class SessionIdByTimeStamp implements ISessionId {
    private static DateFormat df = new SimpleDateFormat("yyMMdd");

    /*
     * (non-Javadoc) 使用时间戳+随机数
     * 
     * @see com.tuniu.ord.common.util.session.ISessionId#createSessionId()
     */
    @Override
    public Integer createSessionId() {
        StringBuffer sb = new StringBuffer();
        sb.append(df.format(DateUtils.now()));
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(9));
        }
        return Integer.valueOf(sb.toString());
    }

}
