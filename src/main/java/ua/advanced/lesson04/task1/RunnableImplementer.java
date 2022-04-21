package ua.advanced.lesson04.task1;

public class RunnableImplementer implements Runnable{

    @Override
    public void run() {
        System.out.println("The ThreadImplementer execution has begun.");

        int period = 2; // (time in seconds) Task: during about 2 seconds..
        long millisPrintRatio = 333333333L; // (ratio of output) Task: every 1/3 seconds..

        float ratioInSeconds = (float) millisPrintRatio / 1000000000; // i need longValue for sleep() and floatValue for formula and output
        int times = (int) (period / ratioInSeconds); // so (for 2 by 1/3 ratio) the name should be printed about 6 times

        for (int i = 0; i < times; i++) {
            System.out.print(i+1 + ". " + Thread.currentThread().getName());
            try {
                Thread.sleep(millisPrintRatio/1000000); // balanced to 0.3 seconds = 333 milliseconds
                System.out.println(" | Time elapsed: " + ratioInSeconds*(i+1) + " (sec)");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("   " + Thread.currentThread().getName() + " has stopped. Finished in: " + ratioInSeconds*times + " seconds");

        System.out.println("The ThreadImplementer execution has ended.");
    }
}
