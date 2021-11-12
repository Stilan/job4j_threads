package ru.job4j;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class CASCountTest {
    @Test
    public void testCASCount1() throws InterruptedException {
         CASCount casCount = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        casCount.increment();
                    }
                }
        );
      thread1.start();
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        casCount.increment();
                    }
                }
        );
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(casCount.get(), is(10));
    }

    @Test
    public void testCASCount2() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        casCount.increment();
                    }
                }
        );
        thread1.start();
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 1; i++) {
                        casCount.increment();
                    }
                }
        );
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(casCount.get(), is(6));
    }
}