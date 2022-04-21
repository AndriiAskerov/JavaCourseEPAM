package ua.advanced.lesson04.task2;

import java.util.Arrays;

public class Spam {
    private Thread[] threads;

    public Spam(String[] messages, int[] intervals) {
        if (messages.length > intervals.length) // якщо посилань більше, ніж інтервалів
            messages = Arrays.copyOf(messages, intervals.length); // видаляємо зайві повідомлення

        threads = new Worker[messages.length];
        for (int i = 0; i < messages.length; i++) {
            threads[i] = new Worker(messages[i], intervals[i]);
        }

    }

    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public void stop() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].stop(); // method is unsafe
        }
    }

    private static class Worker extends Thread {
        String message;
        int interval;

        Worker(String message, int interval) {
            this.message = message;
            this.interval = interval;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(message); // вивести повідомлення
                try {
                    Thread.sleep(interval); // почекати до наступного виводу повідомлення
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
