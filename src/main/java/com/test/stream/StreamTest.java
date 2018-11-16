package com.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 1. 流是数据的渠道；
 * 2. 流不存储数据，只移动数据；
 * 3. 流不会修改数据源，不会修改数据源的顺序；
 * 4. 这里的流不是IO流；
 * 
 * 5. 流接口位于java.util.stream中，BaseStream是基础接口；
 */
public class StreamTest {
    public static void main(String[] args) {
        streamReduce();
    }
    
    public static void streamReduce(){
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(4);
        nums.add(34);
        nums.add(1);
        nums.add(3);
        
        Optional<Integer> opt = nums.stream().reduce((a,b)->a+b);
        System.out.println(opt.get());
        System.out.println("-------------------------------");
        
        Integer sum = nums.stream().reduce(2, (a,b)->a+b);
        System.out.println(sum);
        System.out.println("-------------------------------");
    }
    
    /**
     * 集合流
     */
    public static void collectionStream(){
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(4);
        nums.add(34);
        nums.add(1);
        nums.add(3);
        
        Stream<Integer> numsStream = nums.stream();
        Optional<Integer> opt = numsStream.min(Integer::compare);
        System.out.println(opt.get());
        System.out.println("-------------------------------");
        
        numsStream = nums.stream();
        Stream<Integer> sortedStream = numsStream.sorted(Integer::compare);
        sortedStream.forEach((a)->System.out.println(a));
        System.out.println("-------------------------------");
        
        numsStream = nums.stream();
        Stream<Integer> filterStream = numsStream.filter((a)->{
            if(a > 3){
                return true;
            }
            return false;
        });
        filterStream.forEach((a)->System.out.println(a));
    }
    
    /**
     * 数组流
     */
    public static void arrayStream(){
        String[] strs = new String[]{"fff","ggg","ddd","rrr"};
        
        try(Stream<String> strsStream = Arrays.stream(strs)){
            Iterator<String> iterator = strsStream.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }catch(Exception e){
            
        }
        
    }
}
