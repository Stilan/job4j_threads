package ru.job4j.io;

import java.io.*;

public class Save {
    private final File file;

    public Save(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
       try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
           for (int i = 0; i < content.length(); i += 1) {
               bos.write(content.charAt(i));
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
