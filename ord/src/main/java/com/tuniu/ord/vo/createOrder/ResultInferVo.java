/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月11日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

/**
 * @author zhairongping
 *
 */
public class ResultInferVo implements ResultInfer {
    private static final boolean DEFAULT_SUCCESS = true;
    private static final String DEFAULT_MSG = "校验成功";
    /**
     * 结果
     */
    private boolean success = DEFAULT_SUCCESS;
    /**
     * 信息
     */
    private String msg = DEFAULT_MSG;

    /**
     * 
     */
    public ResultInferVo() {
        super();
    }

    /**
     * @param succcess
     *            the succcess to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.vo.createOrder.ResultInfer#isSuccess()
     */
    @Override
    public boolean isSuccess() {
        return this.success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.vo.createOrder.ResultInfer#getMsg()
     */
    @Override
    public String getMsg() {
        return this.getMsg();
    }

}
