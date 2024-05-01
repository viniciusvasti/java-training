package com.vas.concurrency;

public class MultiThreadingDaemonThread {
    public static void main(String[] args) {
        Thread t1 = new MyThread("Thread A", 100);
        Thread t2 = new MyThread("Thread B", 10);

        // Daemon threads are low priority threads that run in the background and
        // provide services to user threads. That means their life depends on the user
        // threads. If all the user threads are done, daemon threads are killed by the
        // JVM.
        t1.setDaemon(true);

        t1.start();
        t2.start();
    }

    static class MyThread extends Thread {
        private int n = 10;

        public MyThread(String name) {
            super(name);
        }

        public MyThread(String name, int n) {
            super(name);
            this.n = n;
        }

        @Override
        public void run() {
            Utils.printNumbers(n);
        }
    }
}
