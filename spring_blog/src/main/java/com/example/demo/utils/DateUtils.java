package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//将时间戳转换成标准的时间格式
public class DateUtils {
    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
