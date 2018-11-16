package com.test.lambda;

/**
  * 1. Lambda表達式不能獨立執行，而是構成了一個函數式接口的抽象方法的實現
  * 2. 函数式接口可以用来执行任何与其兼容的lambda表达式
  * 3. 只包含单个表达式的lambda体称为表达式体
  *
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        Hello h = (str) -> "Hello "+str+" !!!";
        System.out.println(h.hello("小明"));
        
        MyNumber myNumber = (n)-> n%2==0;
        System.out.println(myNumber.isEven(6));
        
        Printer p = (str)->{
            System.out.println(str);
            return str+" 已经打印";
        };
        System.out.println(p.print("举杯邀明月，对影成三人。"));
        
        Printer p1 = (str1)->str1+"123";
        mainPrint(p1);
        mainPrint((str1)->str1+"123");
        
        MyPrinter<Integer> myP = (a)->a+a;
        System.out.println(myP.print(123));

        MyPrinter<String> myP1 = (a)->a+a;
        System.out.println(myP1.print("aaa"));
        
        //方法引用
        mainPrint(LambdaTest1::mainFunc);
    }
    
    private static void mainPrint(Printer p){
        System.out.println(p.print("今岁不如往年"));
    }
    
    public static String mainFunc(String msg){
        System.out.println(msg);
        return msg + ", 完结";
    }
    
    /**
     * 一个函数式接口：
     * 函数式接口是指只定义了一个抽象方法的接口
     */
    public static interface Printer{
        String print(String msg);
    }

    /**
     * 泛型的函数式接口
     */
    public static interface MyPrinter<T> {
        T print(T param);
    }
    
    /**
     * 一个函数式接口
     */
    public static interface Hello{
        String hello(String name);
    }

    /**
     * 一个函数式接口
     */
    public static interface MyNumber{
        boolean isEven(int n);
    }
    
}
