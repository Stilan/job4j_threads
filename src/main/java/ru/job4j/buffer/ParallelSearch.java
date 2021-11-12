package ru.job4j.buffer;

import ru.job4j.producer.SimpleBlockingQueue;

/**
 * «Ядовитая таблетка» - это общее решение для остановки или прерывания потоков как производителя, так и потребителя.
 * Идея состоит в том, что производитель помещает «ядовитую таблетку» в очередь и выходит,
 * если «потребитель» видит «ядовитую таблетку», то останавливается и выходит.
 * @param <T>
 */
public class ParallelSearch<T> {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);

        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
       Thread producer = new Thread(
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

                }

        );
        producer.start();
        producer.join();
        consumer.interrupt();
    }

}
