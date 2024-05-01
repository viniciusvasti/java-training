package com.vas.concurrency;

public class MultiThreadingExtending {
    // The drawback of this approach is that we can't extend any other class
    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Utils.printNumbers(10);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread("Thread A");
        Thread t2 = new MyThread("Thread B");
        t1.start();
        t2.start();
    }
}
