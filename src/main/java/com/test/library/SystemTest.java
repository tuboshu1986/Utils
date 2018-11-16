package com.test.library;

public class SystemTest
{
    public static void main(String[] args)
    {
        System.out.println(System.currentTimeMillis());
        
        System.out.println(">>>>*************************************");
        
        new Dog("小明");
        System.gc();

    }
    
}

class Dog {
    private String no;
    private String name;
    
    public Dog(String name){
        this.name = name;
    }
    
    public void finalize(){
        System.out.println(name+"被回收");
    }
    
    public String getNo()
    {
        return no;
    }
    public void setNo(String no)
    {
        this.no = no;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
}