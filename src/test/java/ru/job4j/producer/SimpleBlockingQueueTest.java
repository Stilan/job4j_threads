package ru.job4j.producer;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleBlockingQueueTest  {

    @Ignore
    @Test
    public void simpleBlockingQueueTest1() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(5);
        Thread consumer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            simpleBlockingQueue.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println();
                        }
                    }
                },
                "consumer"
        );
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            simpleBlockingQueue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },
                "producer"
        );
        consumer.start();
        producer.start();
        consumer.join();
        consumer.join();
    }
}

