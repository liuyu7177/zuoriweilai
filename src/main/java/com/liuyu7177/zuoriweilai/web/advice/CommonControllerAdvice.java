package com.liuyu7177.zuoriweilai.web.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@ControllerAdvice(basePackages = {"com.liuyu7177.zuoriweilai.web.advice"})
public class CommonControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }

    @ModelAttribute
    public  void populateModel(Model model)
    {
        model.addAttribute("userName","liuyu7177");
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e){
        e.printStackTrace();
        return  "exception";
    }
}
