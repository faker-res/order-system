/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.ord.common.util.session.ISessionId;
import com.tuniu.ord.common.util.session.SessionIdByTimeStamp;

/**
 * @author zhairongping
 *
 */
public class SessionUtil {
    private static final Logger LOG = LoggerFactory.getLogger(SessionUtil.class);

    private static ISessionId session = new SessionIdByTimeStamp();

    /**
     * 产生sessionId
     * 
     * @return
     */
    public static Integer createSessionId() {
        Integer sessionId = session.createSessionId();
        LOG.info("【产生sessionId】===" + sessionId);
        return sessionId;
    }

    public static void main(String[] args) {
        System.out.println(createSessionId());
    }
}
