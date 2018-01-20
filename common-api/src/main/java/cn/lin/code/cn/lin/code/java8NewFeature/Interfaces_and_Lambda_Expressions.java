package cn.lin.code.cn.lin.code.java8NewFeature;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c)2017,德稻  All rights reserved.
 * <br/>
 * <core></core>
 *
 * @author wxl
 */
public class Interfaces_and_Lambda_Expressions {
    Runnable r = new Runnable(){
        @Override
        public void run() {
            System.out.println("My Runnable");
        }};
    Runnable r1 = () -> {
        System.out.println("My Runnable");
    };
/*    Interface1 i1 = (s) -> System.out.println(s);

i1.method1("abc");*/


    public static void main(String[] args) {
        List<Integer> numberList = new ArrayList<Integer>(3);
        numberList.add(4);
        numberList.add(4);
        numberList.add(23);

        int sum = numberList.stream().reduce(0, (x, y) -> x+y);

        System.out.println(sum);

        @NotNull String abc = String.join(" ","java","8");
        System.out.println(abc);
        String str = new String();

        System.out.println(str);
    }


}
