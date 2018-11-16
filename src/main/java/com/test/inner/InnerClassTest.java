package com.test.inner;

public class InnerClassTest
{
    public static void main(String[] args) {
        Print p = new Print() {
            @Override
            public void print() {
                System.out.println("aaa");
            }
        };
        
        p.print();
    }
}


interface Print{
    void print();
}
