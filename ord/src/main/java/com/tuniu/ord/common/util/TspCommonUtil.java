package com.tuniu.ord.common.util;

import java.util.List;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.core.datasource.TSPEnumUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.vo.StockOccupyInfoOutput;
import com.tuniu.ord.vo.StockOccupyQueryVo;
import com.tuniu.ord.vo.common.ExternalResponseObj;

public class TspCommonUtil {
    /**
     * 根据切位单查询
     * 
     * @param orderId
     * @return
     */
    public static List<StockOccupyInfoOutput> queryOccupyInfo(Integer orderId) {
        StockOccupyQueryVo stockOccupyQueryVo = new StockOccupyQueryVo();
        stockOccupyQueryVo.setOccupyObjId(orderId);
        stockOccupyQueryVo.setBusinessType(1111);
        String result = TspUtil.getTspResp(TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO),
                JsonUtil.toString(stockOccupyQueryVo), SystemConstants.HTTP_GET);
        if (result == null) {
            throw new SaaSSystemException("根据切位单查询占位记录无返回");
        }

        ExternalResponseObj<StockOccupyInfoOutput> externalResponseObj = JsonUtil.toBean(result, ExternalResponseObj.class,
                StockOccupyInfoOutput.class);
        if (externalResponseObj == null) {
            throw new SaaSSystemException("JSON序列化返回NULL值");
        }

        if (externalResponseObj.isSuccess() && externalResponseObj.getData() != null
                && externalResponseObj.getData().getCount() > 0) {
            return externalResponseObj.getData().getRows();
        } else {
            throw new SaaSSystemException("查询出错或查询无返回");
        }
    }
}
