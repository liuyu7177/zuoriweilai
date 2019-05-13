package com.liuyu7177.zuoriweilai.framework.exceptions;

/**
 * Created by liuyu7177 On 2019/5/8
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
