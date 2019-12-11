package com.tuniu.ord.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.tuniu.ord.domain.ManualContact;

public interface ManualContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ManualContact record);

    ManualContact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManualContact record);

    @Select("SELECT * FROM manual_contact WHERE manual_order_id=#{manualOrderId} AND del_flag =#{delFlag} ORDER BY ${sort} ${dir}")
    @ResultMap("BaseResultMap")
    List<ManualContact> getOrderContacts(Map<String, Object> params);

    /**
     * @param manualOrderId
     * @return
     */
    List<ManualContact> selectByManualOrderId(Integer manualOrderId);
}