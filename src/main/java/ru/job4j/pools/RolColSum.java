package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < sums.length; i++) {
            for (int j = 0; j < sums.length; j++) {
                 rowSum += matrix[i][j];
                 colSum += matrix[j][i];
            }
            sums[i] = new Sums();
            sums[i].setRowSum(rowSum);
            sums[i].setColSum(colSum);
            rowSum = 0;
            colSum = 0;
        }

        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
       Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = getTask(matrix, i).get();
        }

         return sums;
    }
    public static CompletableFuture<Sums> getTask(int[][] data, int j) {
      return CompletableFuture.supplyAsync(() -> {
          Sums sums = new Sums();
          int rowSum = 0;
          int colSum = 0;
          for (int i = 0; i < data.length; i++) {
             rowSum += data[j][i];
             colSum += data[i][j];
          }
          sums.setRowSum(rowSum);
          sums.setColSum(colSum);
          return sums;
              });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] arr = {{1, 2}, {4, 5}};
        Sums[] sums = sum(arr);
        Arrays.stream(sums).map(Sums::getColSum).forEach(System.out :: println);
        Arrays.stream(sums).map(Sums::getRowSum).forEach(System.out :: println);
    }
}
