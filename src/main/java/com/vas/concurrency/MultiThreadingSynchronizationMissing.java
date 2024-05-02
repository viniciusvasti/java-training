package com.vas.concurrency;

public class MultiThreadingSynchronizationMissing {
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

        public Stack(int size) {
            stack = new int[size];
            top = -1;
        }

        // This method is not thread-safe: two threads can push at the same time. Thread A may have already incremented the top pointer, but not yet assigned the value to the stack. Thread B may then increment the top pointer and assign a value to the stack. This will cause Thread A's value to be overwritten. The Stack will then be in an inconsistent state.
        public boolean push(int value) {
            if (isFull()) {
                return false;
            }
            ++top;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stack[top] = value;
            return true;
        }

        // This method is not thread-safe: two threads can pop at the same time. Thread A may have already decremented the top pointer, but not yet returned the value. Thread B may then decrement the top pointer and return the value. This will cause Thread A's value to be lost. The Stack will then be in an inconsistent state.
        public int pop() {
            if (isEmpty()) {
                return Integer.MIN_VALUE;
            }
            int value = stack[top];
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            top--;
            return value;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == stack.length - 1;
        }
    }

}
