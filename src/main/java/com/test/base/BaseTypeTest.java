package com.test.base;

import java.math.BigDecimal;

public class BaseTypeTest{
    public static void main(String[] args){
        System.out.println(012);
        System.out.println(0xa);
        System.out.println(0b10_10);
        System.out.println(new Integer("1010"));
        
        double d1 = 12.0d;
        double d2 = 12.0d;
        System.out.println(d1 == d2);
        for(int i=0;i<100;i++){
            d1 += 0.1d;
            System.out.println(d1);
        }
        
        BigDecimal bigNum = new BigDecimal(12.0d);
        for(int i=0;i<100;i++){
            bigNum = bigNum.add(new BigDecimal("0.1"));
            System.out.println(bigNum);
        }
        
        
    }
}
