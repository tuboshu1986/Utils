package com.hb.test.thread.j8;

public class Example01
{
    public static void main(String[] args)
    {
        Thread t1 = new Thread(new MyThread(), "one");
        Thread t2 = new Thread(new MyThread(), "two");
        Thread t3 = new Thread(new MyThread(), "three");

        t1.setPriority(10);
        t1.start();
        t2.start();
        t3.start();
        System.out.println("three :"+t3.isAlive());

        try
        {
            t1.join();
            System.out.println("three :"+t3.isAlive());
            t2.join();
            t3.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("three :"+t3.isAlive());
        System.out.println("MyThread.count="+MyThread.getCount());
        System.out.println("结束");
    }
}

class MyThread implements Runnable
{
    private static int count = 0;
    
    public static int getCount(){
        return MyThread.count;
    }
    
    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try
            {
                MyThread.count++;
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}
