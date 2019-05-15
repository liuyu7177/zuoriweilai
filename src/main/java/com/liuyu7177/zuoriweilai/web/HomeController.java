package com.liuyu7177.zuoriweilai.web;

import com.liuyu7177.zuoriweilai.model.entity.Seckill;
import com.liuyu7177.zuoriweilai.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/13
 */
@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        System.out.println(model.getClass());
        return "list";
    }
}
