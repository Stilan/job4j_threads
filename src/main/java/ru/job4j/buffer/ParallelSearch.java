package ru.job4j.buffer;

import ru.job4j.producer.SimpleBlockingQueue;

/**
 * «Ядовитая таблетка» - это общее решение для остановки или прерывания потоков как производителя, так и потребителя.
 * Идея состоит в том, что производитель помещает «ядовитую таблетку» в очередь и выходит,
 * если «потребитель» видит «ядовитую таблетку», то останавливается и выходит.
 * @param <T>
 */
public class ParallelSearch<T> {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        final Integer POISON = 900;

        final Thread consumer = new Thread(
                () -> {
                    Integer i = null;
                    while (!Thread.currentThread().isInterrupted() || !queue.isEmpty()) {
                        try {
                            i = queue.poll();
                            if (i == POISON) {
                                break;
                            }
                            System.out.println(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    try {
                        queue.offer(POISON);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }

        ).start();
        consumer.interrupt();
    }
}
