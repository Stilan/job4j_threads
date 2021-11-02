package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

      String content(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bis.read()) > 0) {
                 if (filter.test((char) data)) {
                     stringBuilder.append((char) data);
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public String getContent() {
        return content(str -> true);
    }

    public String getContentWithoutUnicode() {
        return content(str -> str < 0x80);
    }

}
