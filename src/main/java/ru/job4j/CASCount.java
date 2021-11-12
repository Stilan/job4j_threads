package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Это значит если две нити прочитали одно и тоже значение ref,
 * то первый вызов compareAndSet даст true, а второй вызов вернет false.
 * Вторая нить будет заново читать ref и выполнять операцию compareAndSet.
 */

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(1);

    public void increment() {
        Integer i;
        do {
              i = count.get();
        } while (!count.compareAndSet(i, i + 1));
    }

    public int get() {
        return count.get();
    }

}
