package com.test.util;

import java.util.StringTokenizer;

public class StringokenizerTest {
    public static void main(String[] args) {
        String str1 = "柴门闻犬吠    \n    风雪夜归人\n";
        
        StringTokenizer tokenizer1 = new StringTokenizer(str1);
        while(tokenizer1.hasMoreTokens()){
            System.out.println(tokenizer1.nextToken());
        }
        
        System.out.println("===========================");

        String str2 = "柴门闻犬吠，?;风雪夜归人。";
        StringTokenizer tokenizer2 = new StringTokenizer(str2, "，;。?");
        while(tokenizer2.hasMoreTokens()){
            System.out.println(tokenizer2.nextToken());
        }

        System.out.println("===========================");

        String str3 = "柴门闻犬吠，?;风雪夜归人。";
        StringTokenizer tokenizer3 = new StringTokenizer(str3, "，;。?", true);
        while(tokenizer3.hasMoreTokens()){
            System.out.println(tokenizer3.nextToken());
        }

        System.out.println("===========================");
        
    }
}
