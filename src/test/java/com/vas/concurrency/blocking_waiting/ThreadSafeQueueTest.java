package com.vas.concurrency.blocking_waiting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ThreadSafeQueueTest {
    @Test
    public void testDequeueWaitingForData() throws InterruptedException {
        ThreadSafeQueue queue = new ThreadSafeQueue(3);

        Thread t1 = new Thread(() -> {
            // trying to dequeue from an empty queue should block the thread until data is
            // available
            queue.dequeue();
        }, "Thread A");

        Thread t2 = new Thread(() -> {
            try {
                // sleep for 500ms to allow thread A to start and block
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            queue.enqueue(1);
        }, "Thread B");

        t1.start();
        t2.start();
        Thread.sleep(2000);
        assertEquals(0, queue.getQueue().size());
    }

    @Test
    public void testEnqueueWaitingForCapacity() throws InterruptedException {
        ThreadSafeQueue queue = new ThreadSafeQueue(3);
        Thread t3 = new Thread(() -> {
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            // trying to enqueue to a full queue should block the thread until space is
            queue.enqueue(4);
        }, "Thread C");

        Thread t4 = new Thread(() -> {
            try {
                // sleep for 500ms to allow thread C to start and block
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            queue.dequeue();
        }, "Thread D");

        t3.start();
        t4.start();
        Thread.sleep(5000);
        assertEquals(3, queue.getQueue().size());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
    }
}
