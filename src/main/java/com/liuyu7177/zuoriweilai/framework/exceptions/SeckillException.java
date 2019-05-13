package com.liuyu7177.zuoriweilai.framework.exceptions;

/**
 * Created by liuyu7177 On 2019/5/8
 */
public class SeckillException extends  RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
