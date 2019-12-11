package com.tuniu.ord.common.excel;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tuniu.ord.core.datasource.DataSourceEnum;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.service.ManualTouristService;

@Service
public class TouristUploadImpl implements TouristUpload {

    @Resource
    private ManualTouristService touristService;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTourist(String tenantId, Integer manualOrderId,  MultipartFile touristFile) throws IOException, ParseException {
        DataSourceEnum ds = DataSourceEnum.getDataSource(tenantId);
        DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
        DataSourceSwitch.setTenantId(tenantId);
        ExcelReader reader = new ExcelReader(manualOrderId, touristFile.getInputStream());
        reader.readExcelContent();
        touristService.saveTourists(reader.getTourists());
    }
}
