package ru.job4j.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        StringBuilder subject = new StringBuilder();
        subject.append(" Notification ").append(user.getUsername()).append(" to email ").append(user.getEmail());
        StringBuilder body = new StringBuilder();
        body.append(" Add a new event to ").append(user.getUsername());
        pool.submit(new Runnable() {
           @Override
           public void run() {
              send(subject.toString(), body.toString(), user.getEmail());
           }
       });

    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }
}
