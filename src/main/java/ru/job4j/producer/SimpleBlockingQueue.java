package ru.job4j.producer;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final int size;

    public SimpleBlockingQueue(int pause) {
        this.size = pause;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            if (queue.size() == size) {
                wait();
            }
            queue.offer(value);
            notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            if (queue.isEmpty()) {
                wait();
            }
            notify();
            return queue.poll();
        }
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
