package com.sesang06.retrofit.test.Service;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.Seconds;

/**
 * Created by sesan on 2018-01-14.
 */

public class DateService {

    public static String getUserFriendlyDate(String originalStringDate){
        LocalDateTime localDateTime = new LocalDateTime(originalStringDate);
        String pattern = "yyyy년 MM월 dd일 a hh:mm";
        LocalDateTime currentLocalDateTime = new LocalDateTime();
        if (currentLocalDateTime.minusMinutes(1).isBefore(localDateTime)){
            Seconds seconds = Seconds.secondsBetween(localDateTime,currentLocalDateTime);

            return seconds.getSeconds()+"초 전";
        }else if (currentLocalDateTime.minusHours(1).isBefore(localDateTime)){
            Minutes minutes = Minutes.minutesBetween(localDateTime,currentLocalDateTime);
            return minutes.getMinutes()+"분 전";
        }else if (currentLocalDateTime.minusDays(1).isBefore(localDateTime)){
            Hours hours = Hours.hoursBetween(localDateTime,currentLocalDateTime);
            return hours.getHours()+"시간 전";
        }

        return localDateTime.toString(pattern);
    }
}
