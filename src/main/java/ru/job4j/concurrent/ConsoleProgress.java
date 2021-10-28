package ru.job4j.concurrent;

/**
 * InterruptedException: исключения в Java
 * По сути, InterruptedException сигнализирует о том, что поток просят завершить его работу.
 *
 * В момент вызова метода interrupt(), каждый из потоков может находиться
 * в одном из двух состояний — либо спать,
 * либо пытаться проверить условие (!Thread.interrupted()).
 * Бросается исключение InterruptedException.
 * Так как было выброшено исключение, то данный поток, несмотря на вызов метода interrupt(),
 * не помечается как прерванный.
 * Соответственно, условие (!Thread.interrupted()) остается равно true.
 *
 */
public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        try {
            String[] str = new String[]{"\\", "|", "/", "-"};
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                System.out.print("\r Loading..."  + str[i++]);
                if (i == str.length) {
                    i = 0;
                }
                }
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println(Thread.currentThread().getState());
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgress());
            progress.start();
            Thread.sleep(5000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
