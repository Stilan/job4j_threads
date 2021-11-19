package ru.job4j.pools;

import org.junit.Test;
import java.util.concurrent.ForkJoinPool;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParallelIndexLookupTest  {

    @Test
    public void indexSearch() {
        int[] array = new int[]{1, 2, 4, 6, 10, 23,
                45, 18, 9, 7, 77, 66, 41, 51, 35, 90, 23, 55, 11, 13};
        ParallelIndexLookup parallelIndexLookup = new ParallelIndexLookup(array, 0, array.length, 11);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int index = forkJoinPool.invoke(parallelIndexLookup);
        assertThat(18, is(index));
    }
    @Test
    public void notFoundIndexSearch() {
        int[] array = new int[]{1, 2, 4, 6, 10, 23,
                45, 18, 9, 7, 77, 66, 41, 51, 35, 90, 23, 55, 11, 13};
        ParallelIndexLookup parallelIndexLookup = new ParallelIndexLookup(array, 0, array.length, 0);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int index = forkJoinPool.invoke(parallelIndexLookup);
        assertThat(-1, is(index));
    }
}