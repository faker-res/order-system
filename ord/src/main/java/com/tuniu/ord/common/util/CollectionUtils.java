/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月19日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import java.util.Collection;

/**
 * @author fangzhongwei
 * 
 */
public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> boolean isNotEmpty(Collection<T> collections) {
        if (null != collections && collections.size() > 0) {
            return true;
        }

        return false;
    }

    /**
     * judge collections is empty
     * @param collections
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> collections) {
        if (null == collections || collections.size() == 0) {
            return true;
        }

        return false;
    }
}
