/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * 【校验结果类】
 * 
 * @author zhairongping
 *
 */
public class ResultVo {
    private static boolean DEFAULT_SUCCESS = true;
    private static String DEFAULT_MSG = "校验成功";
    private boolean success = DEFAULT_SUCCESS;
    private String msg = DEFAULT_MSG;

    /**
     * 标志: 位置数小于出游人数 false 位置数等于出游人数 true
     */
    private boolean opeFlag;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the opeFlag
     */
    public boolean isOpeFlag() {
        return opeFlag;
    }

    /**
     * @param opeFlag
     *            the opeFlag to set
     */
    public void setOpeFlag(boolean opeFlag) {
        this.opeFlag = opeFlag;
    }

}
