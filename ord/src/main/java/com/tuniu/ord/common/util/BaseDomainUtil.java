package com.tuniu.ord.common.util;

import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.ord.domain.BaseDomain;

public class BaseDomainUtil {
    public static void setBasePropertyValue(BaseDomain baseDomain) {
        baseDomain.setAddUid(UserSessionUtil.getUid());
        baseDomain.setAddUname(UserSessionUtil.getNickname());
        baseDomain.setAddTime(DateUtils.now());
        baseDomain.setUpdateUid(UserSessionUtil.getUid());
        baseDomain.setUpdateUname(UserSessionUtil.getNickname());
        baseDomain.setUpdateTime(DateUtils.now());
    }
    
    public static void setBasePropertyValueBySystem(BaseDomain baseDomain) {
        baseDomain.setAddUid(79);
        baseDomain.setAddUname("系统");
        baseDomain.setAddTime(DateUtils.now());
        baseDomain.setUpdateUid(79);
        baseDomain.setUpdateUname("系统");
        baseDomain.setUpdateTime(DateUtils.now());
    }

    public static void setUpdatePropertyValue(BaseDomain baseDomain) {
        baseDomain.setUpdateUid(UserSessionUtil.getUid());
        baseDomain.setUpdateUname(UserSessionUtil.getNickname());
        baseDomain.setUpdateTime(DateUtils.now());
    }
}
