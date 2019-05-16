package com.liuyu7177.zuoriweilai.web.baseCode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuyu7177 On 2019/5/13
 */
@Controller
public class BaseController {
    public BaseController() {

    }

    /**
     * 重定向到指定url
     *
     * @param url
     * @return
     */
    public String Redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 请求转发到指定url
     *
     * @param url
     * @return
     */
    public String Forward(String url) {
        return "forward:" + url;
    }

    /**
     * ModelAndView 实体对象
     */
    public ModelAndView mv;

    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ModelAndView getMv() {
        return mv;
    }

    public void setMv(ModelAndView mv) {
        this.mv = mv;
    }


    @ModelAttribute
    public void init(HttpServletRequest request) {
        mv = new ModelAndView();
        mv.setViewName(request.getRequestURI());
    }

    /**
     * 返回ModelAndView
     *
     * @return
     */
    public ModelAndView View() {
        return mv;
    }

    /**
     * 返回ModelAndView
     *
     * @param viewName 视图名称（视图路径）
     * @return
     */
    public ModelAndView View(String viewName) {
        mv.setViewName(viewName);
        return mv;
    }

    /**
     * 返回ModelAndView对象
     *
     * @param viewName
     * @return
     */
    public ModelAndView View(View viewName) {
        mv.setView(viewName);
        return mv;
    }

}
