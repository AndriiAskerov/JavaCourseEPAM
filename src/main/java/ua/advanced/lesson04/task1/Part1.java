package ua.advanced.lesson04.task1;

public class Part1 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " start");
        ThreadExtender th1 = new ThreadExtender();
        th1.start(); // запуск
        th1.join(); // очікування завершення роботи th1

        Thread th2 = new Thread(new RunnableImplementer());
        th2.start(); // запуск
        th2.join(); // очікування завершення роботи th2
        System.out.println(Thread.currentThread().getName() + " end");
    }
}
