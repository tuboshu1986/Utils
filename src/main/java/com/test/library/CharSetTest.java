package com.test.library;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharSetTest
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        String str = "月";
        char c = str.charAt(0);
        System.out.println(Integer.toHexString(c));
        System.out.println(Arrays.toString(str.getBytes()));
        System.out.println(Arrays.toString(str.getBytes("utf-8")));
        System.out.println(Arrays.toString(str.getBytes("GBK")));
    }
}
