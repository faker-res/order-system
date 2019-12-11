package com.tuniu.ord.common.excel;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.CollectionUtils;

import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.ord.common.util.TouristUtil;
import com.tuniu.ord.domain.ManualTouristCertificate;
import com.tuniu.ord.vo.ManualTouristVo;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {
    
    private Integer manualOrderId;
    
    private Workbook workbook;
    
    private List<Sheet> sheets;
    
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    
    private List<ManualTouristVo> tourists;
    
    private String[] dateModel = new String[]{"name", "firstname", "lastname", "sex", "touristType", "psptType", "psptId", "psptEndDate", "tel"};

    public ExcelReader(Integer manualOrderId, InputStream is) {
        try {
            this.manualOrderId = manualOrderId;
            this.workbook = WorkbookFactory.create(is);
            //Sheet的数量 
            this.sheets = new LinkedList<Sheet>();
            int sheetCount = this.workbook.getNumberOfSheets();
            for (int s = 0; s < sheetCount; s++) {  
                this.sheets.add(this.workbook.getSheetAt(s));
            }
            this.tourists = new LinkedList<ManualTouristVo>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     * @throws ParseException 
     */
    public void readExcelContent() throws ParseException {
        if(CollectionUtils.isEmpty(sheets)) {
            throw new RuntimeException("excel sheet empty.");
        }
        Sheet sheet = sheets.get(0);
//        for (Sheet sheet : sheets) {
            //获取总行数  
            int rowCount = sheet.getPhysicalNumberOfRows();
            //遍历每一行 ,从第二行开始
            for (int r = 1; r < rowCount; r++) {
                Row row = sheet.getRow(r);  
                if (row == null) {
                    continue;
                }
                //获取总列数  
                int cellCount = row.getPhysicalNumberOfCells();
                //遍历每一列 
                Map<String, String> cellValues = new HashMap<String, String>();
                for (int c = 0; c < cellCount; c++) {
                    cellValues.put(this.dateModel[c], getCellDate(row.getCell(c)));
                }
                //validateCellValues(cellValues);
                ManualTouristVo touristVo = buildTourist(cellValues);
                if(touristVo != null) {
                    this.tourists.add(touristVo);
                }
                System.out.println();
            }  
//        }
    }
    
//    private void validateCellValues(Map<String, String> cellValues) {
//        Iterator<String> keyIt = cellValues.keySet().iterator();
//        while (keyIt.hasNext()) {
//            String cellMode = keyIt.next();
//        }
//    }
    private ManualTouristVo buildTourist(Map<String, String> cellValues) throws ParseException {
        if(CollectionUtils.isEmpty(cellValues)) {
            return null;
        }
        ManualTouristVo touristVo = new ManualTouristVo();
        touristVo.setManualOrderId(this.manualOrderId);
        touristVo.setName(cellValues.get("name"));
        touristVo.setFirstname(cellValues.get("firstname"));
        touristVo.setLastname(cellValues.get("lastname"));
        touristVo.setSex(TouristUtil.convertSex(cellValues.get("sex")));
        touristVo.setTouristType(TouristUtil.convertTouristType(cellValues.get("touristType")));
        touristVo.setTel(cellValues.get("tel"));
        ManualTouristCertificate certificate = new ManualTouristCertificate();
        certificate.setPsptType(TouristUtil.convertPsptType(cellValues.get("psptType")));
        certificate.setPsptId(cellValues.get("psptId"));
        certificate.setPsptEndDate(DateUtils.stringToDate(cellValues.get("psptEndDate")));
        List<ManualTouristCertificate> certificates = new LinkedList<ManualTouristCertificate>();
        certificates.add(certificate);
        touristVo.setCertificates(certificates);
        return touristVo;
    }
    
    private String getCellDate(Cell cell) {
        int cellType = cell.getCellType();  
        String cellValue = null;  
        switch(cellType) {  
            case Cell.CELL_TYPE_STRING: //文本  
                cellValue = cell.getStringCellValue();  
                break;  
            case Cell.CELL_TYPE_NUMERIC: //数字、日期  
                if(DateUtil.isCellDateFormatted(cell)) {
                    //日期型  
                    cellValue = fmt.format(cell.getDateCellValue());
                } else {
                    //数字 
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }  
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                //布尔型  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
                //空白  
            case Cell.CELL_TYPE_BLANK:
                cellValue = cell.getStringCellValue();  
                break;  
            case Cell.CELL_TYPE_ERROR:
                //错误  
                cellValue = "错误";  
                break;  
            case Cell.CELL_TYPE_FORMULA:
                //公式  
                cellValue = "错误";  
                break;  
            default:  
                cellValue = "错误";  
        }  
        System.out.print(cellValue + "");
        return cellValue;
    }
    /**
     * @return the tourists
     */
    public List<ManualTouristVo> getTourists() {
        return this.tourists;
    }
}