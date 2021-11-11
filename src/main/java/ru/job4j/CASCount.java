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
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer i = count.get();
        Integer j;
        if (i == null) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        do {
             j = i++;
        } while (!count.compareAndSet(i, j));
    }

    public int get() {
        Integer i = count.get();
        if (i == null) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        return i;
    }
}
