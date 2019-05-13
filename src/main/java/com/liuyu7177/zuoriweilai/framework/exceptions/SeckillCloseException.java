package com.liuyu7177.zuoriweilai.framework.exceptions;

/**
 * Created by liuyu7177 On 2019/5/8
 */
public class SeckillCloseException extends  SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
