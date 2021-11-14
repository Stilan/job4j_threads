package ru.job4j.pool;

import ru.job4j.producer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * int size = Runtime.getRuntime().availableProcessors()
 * Инициализация пула должна быть по количеству ядер в системе.
 * Количество нитей всегда одинаковое и равно size. В каждую нить передается блокирующая очередь tasks.
 * В методе run мы должны получить задачу их очереди tasks.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);


    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
         tasks.offer(job);
    }

    public void shutdown() {
        for (Thread t: threads) {
             t.interrupt();
        }
    }
}