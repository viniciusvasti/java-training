package com.vas.concurrency;

public class MultiThreadingRunnable {
    // Although more code is needed, this approach is more flexible because we can
    // extend other classes and/or implement other interfaces
    static class MyThread implements Runnable {
        @Override
        public void run() {
            Utils.printNumbers(10);
        }
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread(), "Thread A");
        Thread t2 = new Thread(new MyThread(), "Thread B");
        t1.start();
        t2.start();
    }
}
