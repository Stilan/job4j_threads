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
            boolean is = true;
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                if (is) {
                    System.out.print("\r Loading..."  +  " " + "|" + "/" + ".");
                    is = false;
                } else {
                    System.out.print("\r Loading..."  +  "\\" + "|" + " " + ".");
                    is = true;
                }
            }
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgress());
            progress.start();
            Thread.sleep(1000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
