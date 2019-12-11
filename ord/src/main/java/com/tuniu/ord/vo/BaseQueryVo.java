package com.tuniu.ord.vo;

import com.tuniu.ord.domain.BaseDomain;

/**
 * @author fangzhongwei
 * 
 */
public class BaseQueryVo extends BaseDomain {

    private static final long serialVersionUID = 3512884356694709801L;

    /**
     * 默认显示第1页
     */
    private static final int DEFAULT_PAGE_INDEX = 1;

    /**
     * 默认每页显示20条记录
     */
    private static final int DEFAULT_LIMIT = 20;

    /**
     * 第几页
     */
    protected int currentPageIndex;

    private static final int DEFAULT_PAGE_INDEX_ = 0;

    /**
     * 分页偏移量
     */
    protected Integer start;

    /**
     * 每页显示记录
     */
    protected Integer limit;

    public int getCurrentPageIndex() {
        if (0 == this.currentPageIndex) {
            this.currentPageIndex = DEFAULT_PAGE_INDEX;
        }
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public Integer getStart() {
        if (start == null) {
            start = DEFAULT_PAGE_INDEX_;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        if (limit == null) {
            limit = DEFAULT_LIMIT;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
