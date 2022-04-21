package ua.advanced.lesson04.task2;

import java.io.IOException;

public class Part2 {
    public static void main(String[] args) {
        String[] messages = new String[]{"@@@", "bbbbbbb", "to-toooooo", "bep-bop", "bla-bla-bla"};
        int[] intervals = new int[]{333, 222, 666, 777, 111};

        // creation of the spam threads
        Spam spamBot = new Spam(messages, intervals);

        // collapse mechanism (Press Enter to stop the spamBot)
        new Thread( () -> {
            char symbol = 'a';
            while(symbol != '\n') { // if the "Enter" button has been pressed
                try {
                    symbol = (char) System.in.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            spamBot.stop(); // all spam threads will stop
        }).start();

        // launch threads
        spamBot.start();
    }
}
