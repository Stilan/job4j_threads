package ru.job4j.barrier;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
          count++;
          monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count >= total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(2);
        Thread master = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");

                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    countBarrier.count();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }

}
