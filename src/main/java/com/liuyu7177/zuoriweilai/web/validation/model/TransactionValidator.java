package com.liuyu7177.zuoriweilai.web.validation.model;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by liuyu7177 On 2019/5/21
 */

@Repository("transactionValidator")
public class TransactionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Transaction.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Transaction trans = (Transaction) object;
        double dis = trans.getAmount() - (trans.getPrice() * trans.getQuantity());
        if (Math.abs(dis) > 0.01) {
            errors.rejectValue("amount", null,"交易金额和购买数量价格不匹配");
        }
    }
}
