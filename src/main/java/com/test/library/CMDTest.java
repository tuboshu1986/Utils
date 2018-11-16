package com.test.library;

import java.io.IOException;

public class CMDTest
{
    public static void main(String[] args) throws IOException
    {
        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("cmd /c echo 公司的V的asd>d:/a.txt");
        runtime.exec("cmd /c echo 公司的V的asd >> d:/a.txt");
    }
}
