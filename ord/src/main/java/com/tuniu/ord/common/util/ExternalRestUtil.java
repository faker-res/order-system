package com.tuniu.ord.common.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.vo.common.ExternalResponseObj;
import com.tuniu.ord.vo.common.ResponseVo;
import com.tuniu.ord.vo.external.MemberInfoVo;
import com.tuniu.ord.vo.external.QueryGroupOutputVo;

public class ExternalRestUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ExternalRestUtil.class);

    /**
     * 根据产品ID和团期 查询团基本信息 升级方案等等信息
     * 
     * @param productId
     * @param departDates
     * @return
     */
    public static ExternalResponseObj<QueryGroupOutputVo> getGroupBaseInfo(Integer productId, String[] departDates) {
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("departDate", departDates);
        LOGGER.info("查询团基本信息入参:{}", JsonUtil.toString(map));

        String groupBaseStr = RestUtil.executeWithTenantId(SystemInitParameter.queryGroupBaseUrl, SystemConstants.HTTP_GET,
                JsonUtil.toString(map));
        LOGGER.info("查询团基本信息出参:{}", groupBaseStr);
        if (groupBaseStr == null) {
            throw new SaaSSystemException("查询团基本信息无返回");
        }

        ExternalResponseObj<QueryGroupOutputVo> externalResponseObj = JsonUtil.toBean(groupBaseStr, ExternalResponseObj.class,
                QueryGroupOutputVo.class);
        if (externalResponseObj == null) {
            throw new SaaSSystemException("json序列化无返回");
        }

        if (!externalResponseObj.isSuccess()) {
            throw new SaaSSystemException(externalResponseObj.getMsg());
        }

        return externalResponseObj;
    }

    public static ResponseVo<MemberInfoVo> getMemberInfoById(Integer memberId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", memberId);

        String response = RestUtil.executeWithTenantId(SystemInitParameter.queryMemberById, SystemConstants.HTTP_GET,
                JsonUtil.toString(map));
        LOGGER.info("查询人员信息出参:{}", response);
        if (response == null) {
            throw new SaaSSystemException("查询人员信息无返回");
        }

        ResponseVo<MemberInfoVo> responseVo = JsonUtil.toBean(response, ResponseVo.class, MemberInfoVo.class);
        if (responseVo == null) {
            throw new SaaSSystemException("json序列化无返回");
        }

        if (!responseVo.isSuccess()) {
            throw new SaaSSystemException(responseVo.getMsg());
        }

        return responseVo;
    }
}
