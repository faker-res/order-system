/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月9日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import com.tuniu.ord.core.init.SystemInitParameter;

/**
 * 【环境工具类】
 * 
 * @author zhairongping
 *
 */
public class EnvironmentUtil {
    /* 生产环境 */
    private static final Integer PRD_ENV = new Integer(2);

    /* 开发和测试环境 */
    private static final Integer NON_PRD_ENV = new Integer(1);

    /**
     * 若是生产环境,则返回true;否则返回false
     * 
     * @return
     */
    public static boolean isPrdEnv() {
        return SystemInitParameter.envFlag.intValue() == PRD_ENV.intValue();
    }
}
