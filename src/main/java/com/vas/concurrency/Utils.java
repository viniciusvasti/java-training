package com.vas.concurrency;

public class Utils {
    public static void printNumbers(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
