package com.hb.test.thread.j8;

public class Example02
{
    public static void main(String[] args)
    {
        Callme cm = new Callme();
        new Thread(new Caller(cm, "aaa")).start();
        new Thread(new Caller(cm, "bbb")).start();
        new Thread(new Caller(cm, "ccc")).start();
    }
}

class Callme{
    public Callme(){
    }
    
    public synchronized void call(String msg){
        System.out.print("["+msg);
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("-"+msg+"]");
    }
}

class Caller implements Runnable{
    private Callme callme;
    private String msg;
    public Caller(Callme callme, String msg){
        this.callme  = callme;
        this.msg = msg;
    }
    
    @Override
    public void run()
    {
        callme.call(this.msg);
    }
}
