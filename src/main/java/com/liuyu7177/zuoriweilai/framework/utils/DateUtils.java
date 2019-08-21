package com.liuyu7177.zuoriweilai.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public class DateUtils {

    /**
     * 时间格式化工具
     * @param date
     * @return
     */
    public static String FormatToyyyyMMdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//显示2017-10-27格式
        return sdf.format(date);
    }
    public static String FormatToyyyyMMddHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//显示2017-10-27格式
        return sdf.format(date);
    }
    public static String GetDateTimeNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//显示2017-10-27格式
        return sdf.format(new Date());
    }
}
