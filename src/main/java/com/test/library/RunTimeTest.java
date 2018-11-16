package com.test.library;

import java.io.IOException;

public class RunTimeTest
{
    public static void main(String[] args) throws IOException
    {
        Runtime runtime = Runtime.getRuntime();
        
        System.out.println(">>>>计算器");
        Process calcProcess = runtime.exec("calc");
        calcProcess.destroy();
        
        System.out.println(">>>>资源管理器");
        Process explorerProcess = runtime.exec("explorer");
        explorerProcess.destroy();
        
        System.out.println(">>>>Runtime信息");
        System.out.println("最大内存空间："+runtime.maxMemory());
        System.out.println("可用内存空间："+runtime.freeMemory());
        System.out.println("总计内存空间："+runtime.totalMemory());

        runtime.exec("cmd /c echo "+"可用内存空间："+runtime.freeMemory()+">>d:/a.txt");
        String str = "";
        for(int i=0;i<100000;i++){
            str += i;
        }
        
        runtime.exec("cmd /c echo "+"最大内存空间："+runtime.maxMemory()+">>d:/a.txt");
        runtime.exec("cmd /c echo "+"可用内存空间："+runtime.freeMemory()+">>d:/a.txt");
        runtime.exec("cmd /c echo "+"总计内存空间："+runtime.totalMemory()+">>d:/a.txt");

        runtime.exec("cmd /c echo "+"gc之前，可用内存空间："+runtime.freeMemory()+">>d:/a.txt");
        System.out.println(str);
        runtime.gc();
        runtime.exec("cmd /c echo "+"gc之后，可用内存空间："+runtime.freeMemory()+">>d:/a.txt");
        
    }
}
