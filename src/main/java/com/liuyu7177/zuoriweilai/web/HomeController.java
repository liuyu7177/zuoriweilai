package com.liuyu7177.zuoriweilai.web;

import com.liuyu7177.zuoriweilai.service.SeckillService;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liuyu7177 On 2019/5/13
 */
@Controller
public class HomeController  extends JsonBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        return View("index");
    }


}
