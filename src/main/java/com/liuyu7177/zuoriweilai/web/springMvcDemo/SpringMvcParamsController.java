package com.liuyu7177.zuoriweilai.web.springMvcDemo;

import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liuyu7177 On 2019/5/15
 */
@Controller
@RequestMapping("spring/ssm")
public class SpringMvcParamsController extends JsonBaseController {

    @RequestMapping(value = "/commonParams", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ModelAndView commonParams() {
        return View();
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public ModelAndView addRole() {
        return View();
    }

}
