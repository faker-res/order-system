package com.tuniu.ord.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.tuniu.ord.vo.CheckLossDetailShowVo;
import com.tuniu.ord.vo.CheckLossDetailUpdateVo;
import com.tuniu.ord.vo.CheckLossIdParamVo;

public interface CheckLossDetailService {
    /**
     * 核损反馈
     * 
     * @param input
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws Exception
     */
    void respCheckLossDetailToAPI(CheckLossIdParamVo input);

    /**
     * 根据核损id获取核损详情页信息
     * 
     * @param input
     * @return
     */
    CheckLossDetailShowVo queryCheckLossDetailById(CheckLossIdParamVo input);

    /**
     * 修改核损详情
     * 
     * @param checkLossDetailUpdateVo
     * @return
     */
    boolean updateCheckLossDetail(CheckLossDetailUpdateVo checkLossDetailUpdateVo);

}
