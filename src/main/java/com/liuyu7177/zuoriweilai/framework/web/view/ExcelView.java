package com.liuyu7177.zuoriweilai.framework.web.view;

import com.liuyu7177.zuoriweilai.framework.service.ExcelExportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/21
 */
public class ExcelView extends AbstractXlsView {
    //文件名
    private String fileName = null;

    //导出视图自定义接口
    private ExcelExportService excelExportService = null;

    public ExcelView(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    public ExcelView(String fileName, ExcelExportService excelExportService) {
        this.fileName = fileName;
        this.excelExportService = excelExportService;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (excelExportService == null) {
            throw new RuntimeException("导出服务接口不能为null");
        }
        if (!StringUtils.isEmpty(fileName)) {
            String reqCharset = request.getCharacterEncoding();
            reqCharset = reqCharset == null ? "UTF-8" : reqCharset;

            fileName = new String(fileName.getBytes(reqCharset), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        }
        excelExportService.makeWorkBook(model,workbook);
    }
}
