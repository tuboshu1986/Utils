package com.test.library;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeTest
{
    public static void main(String[] args)
    {
        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.MONTH));
    }
}
