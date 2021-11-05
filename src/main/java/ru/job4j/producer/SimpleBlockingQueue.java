package ru.job4j.producer;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            if (!queue.isEmpty()) {
                wait();
                System.out.println("Producer wait()");
            }
            System.out.println("Producer offer()");
            queue.offer(value);
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            if (queue.isEmpty()) {
                wait();
                System.out.println("Consumer wait()");
            }
            notify();
            System.out.println("Consumer poll()");
            return queue.poll();
        }
    }
}
