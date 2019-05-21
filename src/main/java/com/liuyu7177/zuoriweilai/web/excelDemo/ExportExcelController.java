package com.liuyu7177.zuoriweilai.web.excelDemo;

import com.liuyu7177.zuoriweilai.framework.service.ExcelExportService;
import com.liuyu7177.zuoriweilai.framework.web.view.ExcelView;
import com.liuyu7177.zuoriweilai.model.entity.UserInfo;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/21
 */
@Controller
@RequestMapping("exportExcel")
public class ExportExcelController extends JsonBaseController {

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView export()
    {
        ExcelView ev=new ExcelView(exportService());
        List<UserInfo> userInfoList=new ArrayList<UserInfo>();
        UserInfo u=new UserInfo(1,"liuyu7177","noteTest");
        userInfoList.add(u);
        mv.addObject("userList",userInfoList);
        mv.setView(ev);
        return View();
    }

    @SuppressWarnings({"unchecked"})
    public ExcelExportService exportService()
    {
        return (Map<String,Object> model, Workbook wb)->{
            List<UserInfo> userList=(List<UserInfo>)model.get("userList");
            Sheet sheet=wb.createSheet("所有角色");
            Row title=sheet.createRow(0);
            title.createCell(0).setCellValue("编号");
            title.createCell(1).setCellValue("名称");
            title.createCell(2).setCellValue("备注");
            int rowIndex=0;
            for (UserInfo u:userList)
            {
                rowIndex++;
                Row r=sheet.createRow(rowIndex);
                title.createCell(0).setCellValue(u.getUserId());
                title.createCell(1).setCellValue(u.getUserName());
                title.createCell(2).setCellValue(u.getNote());
            }
        };
    }
}
