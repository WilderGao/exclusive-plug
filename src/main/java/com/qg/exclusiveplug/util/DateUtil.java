package com.qg.exclusiveplug.util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author WilderGao
 * time 2018-09-23 11:49
 * motto : everything is no in vain
 * description
 */
public class DateUtil {
    private static String PATTERN = "yyyy-MM-dd hh:MM:ss";

    public static String currentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return formatter.format(LocalDateTime.now());
    }

}
