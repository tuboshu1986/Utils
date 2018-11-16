package com.test.util;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date currDate = new Date();
        System.out.println(currDate);
        System.out.println(currDate.after(new Date(123)));

    }
}
