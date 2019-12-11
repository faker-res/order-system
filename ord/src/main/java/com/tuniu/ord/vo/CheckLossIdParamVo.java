package com.tuniu.ord.vo;

import com.tuniu.ord.domain.BaseDomain;

/**
 * 只包含核损编号的查询条件
 * 
 * @author guojun3
 * 
 */
public class CheckLossIdParamVo extends BaseDomain {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6641393328503807321L;

    private Integer checkLossId;

    public Integer getCheckLossId() {
        return checkLossId;
    }

    public void setCheckLossId(Integer checkLossId) {
        this.checkLossId = checkLossId;
    }
}
