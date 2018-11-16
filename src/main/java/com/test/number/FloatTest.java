package com.test.number;

import java.math.BigDecimal;

public class FloatTest
{
    public static void main(String[] args)
    {
        bigDecimalTest1();
    }

    

    public static void bigDecimalTest1(){
        BigDecimal a = new BigDecimal(0.1);
        BigDecimal b = new BigDecimal(0.1);
        
        for (int i = 0; i < 100; i++)
        {
            a = a.add(b);
            System.out.println(a.doubleValue());
        }
    }
    

    public static void bigDecimalTest(){
        BigDecimal a = new BigDecimal(0.1);
        for(int i=0;i<100;i++){
            a = new BigDecimal(a.add(new BigDecimal(0.1)).doubleValue());
            System.out.println(a);
        }
    }
    

    public static void intTest1(){
        int a = 1;
        for(int i=0;i<100;i++){
            a+=1;
            System.out.println(new Integer(a).floatValue()/10);
        }
    }
    

    public static void intTest(){
        int a = 1;
        for(int i=0;i<100;i++){
            a+=1;
            System.out.println(Float.parseFloat(a+"")/10);
        }
    }
    
    public static void doubleTest(){
        double a = 0.1d;
        for(int i=0;i<100;i++){
            System.out.println(a+=0.1);
        }
    }
    
    public static void floatTest(){
        float a = 0.1f;
        for(int i=0;i<100;i++){
            System.out.println(a+=0.1);
        }
    }
}
