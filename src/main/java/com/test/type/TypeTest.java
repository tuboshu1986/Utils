package com.test.type;

import java.util.ArrayList;
import java.util.List;

public class TypeTest
{
    public static void main(String[] args)
    {
        Animal a = new Animal();
        a.setName("马");
        
        Cat c = new Cat();
        c.setName("猫");
        c.setSong("喵喵喵喵");
        
        System.out.println(getName(a));
        System.out.println(getName(c));
        
        List<Animal> list = new ArrayList<Animal>();
        list.add(a);
        list.add(c);
        System.out.println(getSize(list));
        
        List<Cat> list1 = new ArrayList<Cat>();
        list1.add(c);
    }
    
    public static String getName(Animal animal){
        return animal.getName();
    }
    
    public static int getSize(List<Animal> list){
        return list.size();
    }
    
}

class Cat extends Animal{
    private String song;

    public String getSong()
    {
        return song;
    }

    public void setSong(String song)
    {
        this.song = song;
    }
}

class Animal{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    
    
}
