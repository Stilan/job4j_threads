package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * - метод fork() служит для деления. Это аналогично тому, что мы запустили бы рекурсивный метод еще раз
 * - метод join(). Этот метод как раз дает пулу знать, что нужно запустить задачу в отдельном потоке
 * - метод invoke(). Этот метод служит для запуска выполнения
 */
public class ParallelIndexLookup extends RecursiveTask<Integer> {

    private final int[] array;
    private final int elm;

    public ParallelIndexLookup(int[] array,  int elm) {
        this.array = array;
        this.elm = elm;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
            int total = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == elm) {
                    total = i;
                }
            }
            return total;
        } else {
            ParallelIndexLookup parallelIndexLookup1 = new ParallelIndexLookup(Arrays.copyOfRange(array, 0, array.length / 2), elm);
            ParallelIndexLookup parallelIndexLookup2 = new ParallelIndexLookup(Arrays.copyOfRange(array, array.length / 2, array.length), elm);
            parallelIndexLookup1.fork();
            parallelIndexLookup2.fork();
            return parallelIndexLookup1.join() + parallelIndexLookup2.join();
        }
    }

    public static void main(String[] args) {
     int[] array = new int[]{1, 2, 4, 6, 10, 23, 45, 18, 9, 7, 77, 66, 41, 51, 35};
     ParallelIndexLookup parallelIndexLookup = new ParallelIndexLookup(array, 23);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int f = forkJoinPool.invoke(parallelIndexLookup);
        System.out.println(f);
    }
}
