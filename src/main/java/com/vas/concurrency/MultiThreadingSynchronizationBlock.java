package com.vas.concurrency;

public class MultiThreadingSynchronizationBlock {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        // Here we could have an issue if we don't use synchronization in the push and
        // pop methods because one thread could be popping while the other is pushing but
        // the top pointer is not updated yet.
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " pushing " + stack.push(i));
            }
        }, "Thread A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " popping " + stack.pop());
            }
        }, "Thread B").start();
    }

    static class Stack {
        private int[] stack;
        private int top;
        private Object lock = new Object();

        public Stack(int size) {
            stack = new int[size];
            top = -1;
        }

        public boolean push(int value) {
            if (isFull()) {
                return false;
            }
            // The synchronized block is used to lock the block of code that needs to be
            // synchronized. Avoiding pushing and popping at the same time.
            synchronized (lock) {
                ++top;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stack[top] = value;
            }
            return true;
        }

        public int pop() {
            if (isEmpty()) {
                return Integer.MIN_VALUE;
            }
            // The same lock object is used to lock the block of code that needs to be
            // synchronized. Avoiding pushing and popping at the same time.
            synchronized (lock) {
                int value = stack[top];
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                top--;
                return value;
            }
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == stack.length - 1;
        }
    }

}
