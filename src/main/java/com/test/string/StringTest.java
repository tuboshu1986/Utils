package com.test.string;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(StringUtils.trimToEmpty("  safas  "));
        System.out.println(StringUtils.trimToEmpty("  sa  fas  "));
        System.out.println(StringUtils.trimToEmpty("    "));
        
        System.out.println("#########################################");
        
        System.out.println(Arrays.toString(":".split(":")));
        
        System.out.println("#########################################");
        
        System.out.println(Arrays.toString("  aaa   bbb ccc  ".split(" ")));
        
    }
}
