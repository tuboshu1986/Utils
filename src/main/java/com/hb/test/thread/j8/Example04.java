package com.hb.test.thread.j8;

public class Example04
{
    public static void main(String[] args)
    {
        final A a = new A();
        final B b = new B();
        new Thread(){
            @Override
            public void run()
            {
                a.foo(b);
            }
        }.start();
        new Thread(){
            @Override
            public void run()
            {
                b.bar(a);
            }
        }.start();
    }
}

class A{
    public synchronized void foo(B b){
        String name = Thread.currentThread().getName();
        System.out.println(name+" entered A.foo");
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(name+" trying call B.last");
        b.last();
    }
    
    public synchronized void last(){
        String name = Thread.currentThread().getName();
        System.out.println(name+" entered A.last");
    }
}

class B{
    public synchronized void bar(A a){
        String name = Thread.currentThread().getName();
        System.out.println(name+" entered B.bar");
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(name+" trying call A.last");
        a.last();
    }
    
    public synchronized void last(){
        String name = Thread.currentThread().getName();
        System.out.println(name+" entered B.last");
    }
}
