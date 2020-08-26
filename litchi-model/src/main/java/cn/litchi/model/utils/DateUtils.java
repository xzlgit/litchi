package cn.litchi.model.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateUtils {
    public static long getNowTimeAsEpochMilli() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long getNowTimeAsSecond() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
    }

    public static long getEpochMilliAtStartofDay() {
        return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long getEpochMilliAtStartofDay(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long getEpochMilliAtEndofDay(LocalDate localDate) {
        return localDate
                .plusDays(1)
                .atStartOfDay(ZoneId.systemDefault())
                .minusSeconds(1)
                .toInstant().toEpochMilli();
    }


    public static long getEpochMilliAtStartofDayByMinusDays(int day) {
        return LocalDate.now().minusDays(day).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static int dayOfYear(Instant instant) {
        return instant.atZone(ZoneOffset.ofHours(8)).toLocalDate().getDayOfYear();
    }

    public static int checkNodeDataParamDay(int day) {
        return day > 30 ? 30 : day;
    }


}
