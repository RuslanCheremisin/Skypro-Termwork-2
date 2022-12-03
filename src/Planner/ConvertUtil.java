package Planner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class ConvertUtil {
    public static LocalDate convertStringToDate(String date) {
        int dayOfMonth = Integer.parseInt(date.substring(0, 2));
        int monthInt = Integer.parseInt(date.substring(3, 5));
        Month month = null;
        int year = Integer.parseInt(date.substring(6));
        switch (monthInt) {
            case 1:
                month = Month.JANUARY;
                break;
            case 2:
                month = Month.FEBRUARY;
                break;
            case 3:
                month = Month.MARCH;
                break;
            case 4:
                month = Month.APRIL;
                break;
            case 5:
                month = Month.MAY;
                break;
            case 6:
                month = Month.JUNE;
                break;
            case 7:
                month = Month.JULY;
                break;
            case 8:
                month = Month.AUGUST;
                break;
            case 9:
                month = Month.SEPTEMBER;
                break;
            case 10:
                month = Month.OCTOBER;
                break;
            case 11:
                month = Month.NOVEMBER;
                break;
            case 12:
                month = Month.DECEMBER;
                break;

        }
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        return localDate;
    }

    public static LocalTime convertStringToTime(String timeStr) {
        LocalTime time;
        if (timeStr.length() == 4) {
            time = LocalTime.of(Integer.parseInt(timeStr.substring(0, 1)), Integer.parseInt(timeStr.substring(2)));
        } else {
            time = LocalTime.of(Integer.parseInt(timeStr.substring(0, 2)), Integer.parseInt(timeStr.substring(3)));
        }
        return time;
    }

    public static LocalDateTime convertStringToDateTime(String dateStr, String timeStr) {
        int dayOfMonth = Integer.parseInt(dateStr.substring(0, 2));
        int monthInt = Integer.parseInt(dateStr.substring(3, 5));
        Month month = Month.JANUARY;
        int year = Integer.parseInt(dateStr.substring(6));
        switch (monthInt) {
            case 1:
                month = Month.JANUARY;
                break;
            case 2:
                month = Month.FEBRUARY;
                break;
            case 3:
                month = Month.MARCH;
                break;
            case 4:
                month = Month.APRIL;
                break;
            case 5:
                month = Month.MAY;
                break;
            case 6:
                month = Month.JUNE;
                break;
            case 7:
                month = Month.JULY;
                break;
            case 8:
                month = Month.AUGUST;
                break;
            case 9:
                month = Month.SEPTEMBER;
                break;
            case 10:
                month = Month.OCTOBER;
                break;
            case 11:
                month = Month.NOVEMBER;
                break;
            case 12:
                month = Month.DECEMBER;
                break;

        }

        int hour;
        int minute;
        if (timeStr.length() == 4) {
            hour = Integer.parseInt(timeStr.substring(0, 1));
            minute = Integer.parseInt(timeStr.substring(2));
        } else {
            hour = Integer.parseInt(timeStr.substring(0, 2));
            minute = Integer.parseInt(timeStr.substring(3));
        }
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        return localDateTime;
    }
}
