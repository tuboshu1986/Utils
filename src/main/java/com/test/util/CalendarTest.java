package com.test.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {
    public static void main(String[] args) {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        System.out.println(calendar);
        System.out.println(calendar.getTimeZone());
        System.out.println(calendar.getTimeInMillis()+"-"+System.currentTimeMillis());
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println(calendar.isLeapYear(2018));
        System.out.println(calendar.isLeapYear(2000));
        System.out.println(calendar.toZonedDateTime());
    }
}
