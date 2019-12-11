package com.tuniu.ord.common.excel;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

public interface TouristUpload {


    /**
     * 导入出游人
     * @param inputStream
     */
    void uploadTourist(String tenantId, Integer manualOrderId, MultipartFile touristFile) throws IOException , ParseException ;
}
