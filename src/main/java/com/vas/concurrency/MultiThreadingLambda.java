package com.vas.concurrency;

public class MultiThreadingLambda {
    public static void main(String[] args) {
        // Using lambda expression to create a Runnable object thus avoiding creating a separate class
        Thread t1 = new Thread(() -> {
            Utils.printNumbers(10);
        }, "Thread A");
        Thread t2 = new Thread(() -> {
            Utils.printNumbers(10);
        }, "Thread B");
        t1.start();
        t2.start();
    }
}
