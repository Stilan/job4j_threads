package ru.job4j.buffer;

public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        synchronized (this) {
            System.out.print(value);
            buffer.append(value);
        }
    }

    @Override
    public  String toString() {
        synchronized (this) {
            return buffer.toString();
        }
    }
}
