package com.test.library;

public class StringTest
{
    public static void main(String[] args)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("人生若只如初见，何事秋风悲画扇。");
        strBuffer.append("等闲变却故人心，却道故人心易变！");
        strBuffer.append("骊山语罢清宵半，泪雨零铃终不怨。");
        strBuffer.append("何如薄幸锦衣郎，比翼连枝当日愿!");
        
        System.out.println(strBuffer.toString());
        
        System.out.println(">>>>*********************************");
        
        strBuffer.insert(0, "挺身登峻岭，举目照遥空。");
        System.out.println(strBuffer.toString());
        

        System.out.println(">>>>*********************************");
        
        strBuffer.insert(12, "毁佛崇天帝，移民复古风。");
        System.out.println(strBuffer.toString());
        
        System.out.println(">>>>*********************************");
        
        strBuffer.delete(12, 24);
        System.out.println(strBuffer.toString());

        System.out.println(">>>>*********************************");
        
        strBuffer.reverse();
        System.out.println(strBuffer.toString());
        
        
        
        
        
        
        
    }
}
