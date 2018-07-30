package com.spring.study.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/26.
 */
public class TimeUtils {

    public static long getCurr() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
