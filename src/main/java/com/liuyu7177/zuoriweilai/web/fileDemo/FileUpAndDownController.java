package com.liuyu7177.zuoriweilai.web.fileDemo;

import com.liuyu7177.zuoriweilai.framework.web.JsonResult;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

/**
 * Created by liuyu7177 On 2019/5/21
 */
@Controller
@RequestMapping("fileUpAndDown")
public class FileUpAndDownController extends JsonBaseController {

    private String filePath = "";

    public FileUpAndDownController() {
        filePath = this.getClass().getClassLoader().getResource("").getPath() + "/filePath";
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView upload() {
        return View();
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> upload(MultipartFile file) {
        long timeMillis = System.currentTimeMillis();
        String fileName = file.getOriginalFilename();
        String fileSavePath=filePath + "/" + timeMillis + fileName;
        File dest = new File(fileSavePath);// 上传后的文件保存目录及名字
        try {
            file.transferTo(dest);// 将上传文件保存到相应位置
        } catch (IOException e) {
            return ApiErr(e.getMessage());
        }
        return ApiSuccess(fileSavePath);
    }

    @RequestMapping(value = "/uploadA", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> uploadA(MultipartHttpServletRequest request) {
        long timeMillis = System.currentTimeMillis();
        MultipartFile file = request.getFile("file");// 与页面input的name相同
        String fileName = file.getOriginalFilename();
        String fileSavePath=filePath + "/" + timeMillis + fileName;
        File dest = new File(fileSavePath);// 上传后的文件保存目录及名字
        try {
            file.transferTo(dest);// 将上传文件保存到相应位置
        } catch (IOException e) {
            return ApiErr(e.getMessage());
        }
        return ApiSuccess(fileSavePath);
    }

}
