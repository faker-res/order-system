package com.tuniu.ord.common.util;

import com.tuniu.ord.vo.ResponseVo;

/**
 * Controller的ResponseVo工具
 * @author zhairongping
 *
 */
public class ControllerResponseUtils {
	/**
	 * 根据结果获取responseVo实例
	 * @param success
	 * @param errorCode
	 * @param msg
	 * @param data
	 * @return
	 */
	public static ResponseVo getResponseVo(boolean success, int errorCode, String msg, Object data) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(success);
		responseVo.setErrorCode(errorCode);
		responseVo.setMsg(msg);
		responseVo.setData(data);
		return responseVo;
	}
}
