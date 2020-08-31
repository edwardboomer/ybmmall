package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;


//时间转化处理的工具类
public class DateTimeUtil {
//用了joda-time类包处理时间

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //把str->Date
    public static Date strToDate(String dateTimeStr, String formatStr){
        //joda-time中的把时间格式化方法
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    //把Date->str
    public static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }




    public static void main(String[] args) {
        //当前时间转化成str格式
        System.out.println(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        //把这个格式的时间str转化成date
        System.out.println(DateTimeUtil.strToDate("2018-06-01 11:11:11","yyyy-MM-dd HH:mm:ss"));

    }
}
