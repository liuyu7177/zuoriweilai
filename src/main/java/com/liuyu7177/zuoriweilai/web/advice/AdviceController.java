package com.liuyu7177.zuoriweilai.web.advice;

import com.liuyu7177.zuoriweilai.framework.utils.DateUtils;
import com.liuyu7177.zuoriweilai.framework.web.JsonResult;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@Controller
@RequestMapping("/advice")
public class AdviceController extends JsonBaseController {

    @RequestMapping("/test")
    @ResponseBody
    public JsonResult<Map<String, Object>> testAdvice(Date date,
                                         @NumberFormat(pattern = "##,###,00") BigDecimal amount,
                                         @ModelAttribute("userName") String userName,
                                         Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName",model.asMap().get("userName"));
        map.put("userNameByModelAttribute",userName);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//显示2017-10-27格式
        map.put("date", DateUtils.FormatToyyyyMMdd(date));
        map.put("amount",amount);
        return ApiSuccess(map);
    }

    @RequestMapping("/exception")
    public void exception()
    {
        throw  new RuntimeException("测试异常跳转");
    }
}
