package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = null;
        for (int i = 0; i < 2; i++) {
            first = new Thread(
                    () -> System.out.println(Thread.currentThread().getName())
            );
            System.out.println(first.getState());
            first.start();
            while (first.getState() != Thread.State.TERMINATED) {
                System.out.println(first.getState());
            }
            System.out.println(first.getState());
        }
            System.out.println("Работа завершена");
    }
}
