package com.liuyu7177.zuoriweilai.web.account;

import com.liuyu7177.zuoriweilai.model.entity.UserInfo;
import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuyu7177 On 2019/5/20
 */
@Controller
@RequestMapping("account")
@SessionAttributes(names = {"userInfo", "userId", "userName"})
public class AccountController extends JsonBaseController {

    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    public String setSession(Model model) {
        UserInfo u = new UserInfo();
        u.setUserId(11);
        u.setUserName("liuyu7177");
        model.addAttribute("userInfo", u);
        model.addAttribute("userId", u.getUserId());
        model.addAttribute("userName", u.getUserName());
        model.addAttribute("userJson", u.toString());
        return Redirect("/account/getSession");
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public ModelAndView getSession(@SessionAttribute(name = "userInfo", required = false) UserInfo u,
                                   @SessionAttribute(name = "userId", required = false) Integer userId,
                                   @SessionAttribute(name = "userName", required = false) String name,
                                   @SessionAttribute(name = "userJson", required = false) String userJson) {
        mv.addObject("user", u);
        mv.addObject("id", userId);
        mv.addObject("name", name);
        mv.addObject("json", userJson);
        return View();
    }


    @RequestMapping(value = "/setCookie", method = RequestMethod.GET)
    public String setCookie(HttpServletResponse response) {
        UserInfo u = new UserInfo();
        u.setUserId(11);
        u.setUserName("liuyu7177");

        Cookie cookie = new Cookie("userName", u.getUserName());
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
        return Redirect("/account/showHeadAndCookie");
    }

    @RequestMapping(value = "/showHeadAndCookie", method = RequestMethod.GET)
    public ModelAndView showHeadAndCookie(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestHeader(value = "User-Agent", required = false) String userAgent,
                                          @CookieValue(value = "userName", required = false) String userName) {

        mv.addObject("userAgent", userAgent);
        mv.addObject("userName", userName);
        return View();
    }
}
