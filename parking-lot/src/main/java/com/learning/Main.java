package com.learning;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        System.out.println(IntStream.range(1,100).filter(num -> {
            System.out.println(num);
            return num%2 == 0;
        }).findFirst().getAsInt());
    }
}