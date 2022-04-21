package ua.advanced.lesson04.task3;

public class Part3 {
    private int counter1 = 0;
    private int counter2 = 0;
    private static Object obj = new Object(); // об'єкт синхронізації потоків

    long interval = 400; // інтервал сну

    public static void main(String[] args) throws InterruptedException {
        Part3 p = new Part3();
        System.out.println("Start compare() method:");
        p.compare(); // виклик не синхронізованих потоків


        Thread.sleep(2000); // призупиняємо потік виконання програми, щоб дочекатись кінця першої демонстрації
        System.out.println("\n\nLet's reset counters!\n");
        p.resetCounters();

        System.out.println("Start compareSync() method: ");
        p.compareSync(); // виклик синхронізованих потоків
    }

    public void resetCounters() {
        counter1 = 0;
        counter2 = 0;
    }

    public void compare() {
        MyThread th1 = new MyThread();
        MyThread th2 = new MyThread();
        MyThread th3 = new MyThread();
        th1.start();
        th2.start();
        th3.start();
    }

    public void compareSync() {
        MySyncedThread th1 = new MySyncedThread(obj);
        MySyncedThread th2 = new MySyncedThread(obj);
        MySyncedThread th3 = new MySyncedThread(obj);
        th1.start();
        th2.start();
        th3.start();
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.print((counter1 == counter2) + "\t"); // compares the value of the counters and prints out the result of the comparison;
                counter1++; // increments the first counter;
                try {
                    Thread.sleep(interval); // sleeps for 100 milliseconds;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter2++; // increments the second counter.
            }
        }
    }

    public class MySyncedThread extends Thread {
        Object syncObj;

        MySyncedThread(Object syncObj) {
            this.syncObj = syncObj;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (syncObj) {
                    System.out.print((counter1 == counter2) + "\t"); // compares the value of the counters and prints out the result of the comparison;
                    counter1++; // increments the first counter;
                    try {
                        Thread.sleep(interval); // sleeps for 100 milliseconds;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    counter2++; // increments the second counter.
                }
            }
        }
    }
}
