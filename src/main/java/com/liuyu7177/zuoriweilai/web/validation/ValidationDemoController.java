package com.liuyu7177.zuoriweilai.web.validation;

import com.liuyu7177.zuoriweilai.web.baseCode.JsonBaseController;
import com.liuyu7177.zuoriweilai.web.validation.model.Transaction;
import com.liuyu7177.zuoriweilai.web.validation.model.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/21
 */
@Controller
@RequestMapping("validation")
public class ValidationDemoController extends JsonBaseController {

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ModelAndView transaction() {
        return View();
    }

    @InitBinder
    public  void initBinder(DataBinder binder)
    {
        //binder.setValidator(new Transaction());
        binder.addValidators(new TransactionValidator());
    }
    @Autowired
    @Qualifier("transactionValidator")
    private TransactionValidator transactionValidator;

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ModelAndView transaction(@Valid Transaction trans, Errors errors) {
        transactionValidator.validate(trans, errors);

        if(errors.hasErrors())
        {
            List<FieldError> errorList=errors.getFieldErrors();
           for (FieldError error:errorList)
           {
               System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
           }
        }
        return View();
    }
}
