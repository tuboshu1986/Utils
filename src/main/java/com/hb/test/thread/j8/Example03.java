package com.hb.test.thread.j8;

public class Example03
{
    public static void main(String[] args) throws InterruptedException
    {
        Queu q = new Queu();
        new Producer(q);
        new Consumer(q);
    }
}

class Queu{
    private int n = -1;
    private boolean valueSet = false;
    public synchronized int get(){
        while(!valueSet){
            try
            {
                this.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("消费："+this.n);
        valueSet = false;
        this.notify();
        return this.n;
    }
    public synchronized void put(int n){
        while(valueSet){
            try
            {
                this.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        this.n = n;
        System.out.println("生产："+this.n);
        valueSet = true;
        this.notify();
    }
}

class Producer extends Thread{
    private Queu q;
    public Producer(Queu q){
        this.q = q;
        this.start();
    }
    @Override
    public void run()
    {
        for(int i=0;i<10;i++){
            this.q.put(i);
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread{
    private Queu q;
    public Consumer(Queu q){
        this.q = q;
        this.start();
    }
    @Override
    public void run()
    {
        for(int i=0;i<10;i++){
            this.q.get();
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
