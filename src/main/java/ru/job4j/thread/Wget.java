package ru.job4j.thread;

import java.io.*;
import java.net.URL;

/**
 * В каких единицах у вас измеряется speed?
 *  Миллисекунда равна 1/1000 секунды
 *  speed = 1 kilobyte/s
 *  System.currentTimeMillis() = мин секунд
 */

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    /**
     * ограничения в Кбайт/с
     */
    @Override
    public void run()  {
        try {
           try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
               byte[] dataBuffer = new byte[1024];
               int bytesRead;
               var speedBytes = speed * 1025;
               long start = System.currentTimeMillis();
               while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                   fileOutputStream.write(dataBuffer, 0, bytesRead);
                   long time = System.currentTimeMillis() - start;
                   if (bytesRead > speedBytes && time < 1000) {
                       Thread.sleep(1000 - time);
                       bytesRead = 0;
                   }
                   start = System.currentTimeMillis();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private static void validatorArgs(String[] args, int num) {
       if (args.length != num) {
           throw new IllegalArgumentException("Parameters entered incorrectly ");
       }
    }

    public static void main(String[] args) throws InterruptedException {
            validatorArgs(args, 2);
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            Thread wget = new Thread(new Wget(url, speed));
            wget.start();
            wget.join();
    }
}
