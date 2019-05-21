package com.liuyu7177.zuoriweilai.framework.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/21
 */
public interface ExcelExportService {
    public void makeWorkBook(Map<String,Object> model, Workbook workbook);
}
