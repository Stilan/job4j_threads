package ru.job4j.thread;

import java.io.*;
import java.net.URL;

/**
 * В каких единицах у вас измеряется speed?
 *  Миллисекунда равна 1/1000 секунды
 *  speed = 1 мБит/с = 1/1000 мин секунд = 0,001
 *  System.currentTimeMillis() = мин секунд
 */

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run()  {
        try {
           try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
               byte[] dataBuffer = new byte[1024];
               int bytesRead;
               long start = System.currentTimeMillis();
               while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                   fileOutputStream.write(dataBuffer, 0, bytesRead);
                   long end = System.currentTimeMillis();
                   long time = end - start;
                   System.out.println(time);
                   var speedTimeBit = speed / 1000;
                   if (time < speedTimeBit) {
                       Thread.sleep((speedTimeBit - time) * 1000);
                   }

               }
           } catch (IOException e) {
               e.printStackTrace();
           }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private static boolean validatorArgs(String[] args) {
       if (args.length != 2) {
           return false;
       }
        return  true;
    }

    public static void main(String[] args) throws InterruptedException {
        if (validatorArgs(args)) {
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            Thread wget = new Thread(new Wget(url, speed));
            wget.start();
            wget.join();
        } else {
            throw new IllegalArgumentException("Parameters entered incorrectly ");
        }
    }


}
