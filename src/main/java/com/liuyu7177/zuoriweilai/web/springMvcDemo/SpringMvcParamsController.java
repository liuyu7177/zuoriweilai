package com.liuyu7177.zuoriweilai.web.springMvcDemo;

import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import com.liuyu7177.zuoriweilai.web.springMvcDemo.model.RoleModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ModelAndView addRole(String roleName, String roleNote) {
        mv.addObject("roleName", roleName);
        mv.addObject("roleNote",roleNote);
        return View();
    }

    @RequestMapping(value = "/addRoleForPojo", method = RequestMethod.GET)
    public ModelAndView addRoleForPojo() {
        return View();
    }

    @RequestMapping(value = "/addRoleForPojo", method = RequestMethod.POST)
    public ModelAndView addRoleForPojo(RoleModel roleModel) {
        mv.addObject("role",roleModel);
        System.out.println(roleModel);
        return View();
    }

    @RequestMapping(value = "/addRoleForRequestParam", method = RequestMethod.GET)
    public ModelAndView addRoleForRequestParam() {
        return View();
    }

    @RequestMapping(value = "/addRoleForRequestParam", method = RequestMethod.POST)
    public ModelAndView addRoleForRequestParam(
            @RequestParam("role_name") String roleName,
            @RequestParam("role_note")String roleNote) {
        mv.addObject("roleName", roleName);
        mv.addObject("roleNote",roleNote);
        return View();
    }

    @RequestMapping(value = "/getParamByUrl/{id}", method = RequestMethod.GET)
    public ModelAndView getParamByUrl(@PathVariable(value = "id",required = false) Long id) {
        mv.addObject("id:",id);
        return View("spring/ssm/getParamByUrl");
    }
}
