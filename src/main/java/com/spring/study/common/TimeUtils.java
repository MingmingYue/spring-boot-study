package com.spring.study.common;

import org.joda.time.DateTime;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/26.
 */
public class TimeUtils {

    private final static String FORMAT_ORDER_TIME = "yyMMddHHmmssSSS";

    public static long getCurr() {
        DateTime dateTime = new DateTime();
        return dateTime.getMillis();
    }

    public static String getFormatOrderTime() {
        DateTime dateTime = new DateTime();
        return dateTime.toString(FORMAT_ORDER_TIME);
    }
}
