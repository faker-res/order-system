package com.tuniu.ord.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuniu.ord.domain.ManualTouristCertificate;

public interface ManualTouristCertificateMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTouristId(@Param("touristId") Integer touristId);

    int insert(ManualTouristCertificate record);

    int insertSelective(ManualTouristCertificate record);

    ManualTouristCertificate selectByPrimaryKey(Integer id);
    
    List<ManualTouristCertificate> selectByTouristId(@Param("touristId") Integer touristId);

    int updateByPrimaryKeySelective(ManualTouristCertificate record);

    int updateByPrimaryKey(ManualTouristCertificate record);
    
    int batchInsert(@Param("records") List<ManualTouristCertificate> records);
    
    int batchRemove(@Param("records") List<ManualTouristCertificate> records);
}