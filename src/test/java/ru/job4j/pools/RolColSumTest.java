package ru.job4j.pools;


import org.junit.Test;


import java.util.concurrent.ExecutionException;
import static org.junit.Assert.assertEquals;
import static ru.job4j.pools.RolColSum.asyncSum;
import static ru.job4j.pools.RolColSum.sum;

public class RolColSumTest {

    @Test
    public void asyncSumTest() throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 2}, {4, 5}};
        RolColSum.Sums[] sums = asyncSum(arr);
        assertEquals(3,  sums[0].getRowSum());

    }
    @Test
    public void asyncSumTest2() throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 2}, {4, 5}};
        RolColSum.Sums[] sums = asyncSum(arr);
        assertEquals(9,  sums[1].getRowSum());

    }
    @Test
    public void asyncSumTest3() throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 2}, {4, 5}};
        RolColSum.Sums[] sums = asyncSum(arr);
        assertEquals(5,  sums[0].getColSum());

    }
    @Test
    public void asyncSumTest4() throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 2}, {4, 5}};
        RolColSum.Sums[] sums = asyncSum(arr);
        assertEquals(7,  sums[1].getColSum());

    }
    @Test
    public void sumTest() throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 2}, {4, 5}};
        RolColSum.Sums[] sums = sum(arr);
        assertEquals(7,  sums[1].getColSum());

    }
}