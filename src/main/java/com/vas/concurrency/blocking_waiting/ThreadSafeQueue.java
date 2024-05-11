package com.vas.concurrency.blocking_waiting;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {
    private Queue<Integer> queue;
    private int capacity;

    public ThreadSafeQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    synchronized public boolean enqueue(int data) {
        if (queue.size() == capacity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        queue.add(data);
        this.notifyAll();
        return true;
    }

    synchronized public int dequeue() {
        if (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        int i = queue.remove();
        this.notifyAll();
        return i;
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return "ThreadSafeQueue [queue=" + queue + ", capacity=" + capacity + "]";
    }
}
