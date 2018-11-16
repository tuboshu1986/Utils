package com.test.inet;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public class Test
{   
    public static void main(String[] args) throws Exception
    {
        for (String string : args){
            System.out.println(string);
        }
        System.out.println("--------------------------------");
        
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
        System.out.println(address.getCanonicalHostName());
        System.out.println(address.toString());
        System.out.println(address instanceof Inet4Address);
        System.out.println(address instanceof Inet6Address);
        
        System.out.println("---------------------------localhost");
        InetAddress[] as = InetAddress.getAllByName("localhost");
        for (InetAddress inetAddress : as){
            System.out.println(inetAddress);
            System.out.println(inetAddress.isLoopbackAddress());
        }

        System.out.println("---------------------------WINDELL002");
        InetAddress[] as1 = InetAddress.getAllByName("WINDELL002");
        for (InetAddress inetAddress : as1){
            System.out.println(inetAddress);
            System.out.println(inetAddress.isLoopbackAddress());
        }

        System.out.println("---------------------------");
        InetAddress[] as2 = InetAddress.getAllByName("www.csdn.net");
        for (InetAddress inetAddress : as2){
            System.out.println(inetAddress);
        }
        
        System.out.println("---------------------------");
        InetAddress[] as3 = InetAddress.getAllByName("www.2cto.com");
        for (InetAddress inetAddress : as3){
            System.out.println(inetAddress);
        }

        System.out.println("---------------------------");
        InetAddress[] as4 = InetAddress.getAllByName("www.sina.com.cn");
        for (InetAddress inetAddress : as4){
            System.out.println(inetAddress);
            System.out.println(inetAddress.isAnyLocalAddress());
        }
        
    }
}
