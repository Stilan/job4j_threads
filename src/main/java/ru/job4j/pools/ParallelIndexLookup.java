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
    private final int from;
    private final int to;
    private final int elm;

    public ParallelIndexLookup(int[] array, int from, int to, int elm) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.elm = elm;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = from; i < to; i++) {
                if (array[i]  == elm) {
                    return i;
                }
            }
             return -1;
        }
            int mid = (from + to) / 2;
            ParallelIndexLookup parallelIndexLookup1 = new ParallelIndexLookup(array, from,  mid, elm);
            ParallelIndexLookup parallelIndexLookup2 = new ParallelIndexLookup(array, mid + 1, to, elm);
            parallelIndexLookup1.fork();
            parallelIndexLookup2.fork();
            int leftParallelIndexLookup = parallelIndexLookup1.join();
            int rightParallelIndexLookup = parallelIndexLookup2.join();
            return Math.max(leftParallelIndexLookup, rightParallelIndexLookup);

    }

    public static void main(String[] args) {
     int[] array = new int[]{1, 2, 4, 6, 10, 23,
             45, 18, 9, 7, 77, 66, 41, 51, 35};
      ParallelIndexLookup parallelIndexLookup = new ParallelIndexLookup(array, 0, array.length, 23);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int f = forkJoinPool.invoke(parallelIndexLookup);
        System.out.println(f);
    }
}
