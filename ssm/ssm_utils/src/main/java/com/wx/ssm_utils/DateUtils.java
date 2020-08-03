package com.wx.ssm_utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转换成字符串
    public static String dateToString(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd HH:mm";

    //字符串转换成日期
    public static Date stringToDate(String str,String patt) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
