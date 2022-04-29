package ua.advanced.lesson05.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Auction {
    private static int penalty = 3; // кількість лотів (+ 1), що пропускає учасник
    private static int numberOfLots = 8;
    private static int numberOfParticipants = 5;
    private static final CountDownLatch START = new CountDownLatch(numberOfParticipants + 3);

    public static void main(String[] args) throws InterruptedException {
        List<Bidder> participants = new ArrayList<>();
        for (int i = 1; i < numberOfParticipants + 1; i++) {
            participants.add(new Bidder(i));
        } // створюємо пул учасників

        for (int lot = 1; lot < numberOfLots + 1; lot++) {
            for (Bidder participant : participants) { // регулювання доступу до торгування за лот
                if (participant.getPenalty() > 0) {
                    participant.setPenalty(participant.getPenalty() - 1);
                    if (participant.getPenalty() == 0) {
                        participant.setTakingPart(true);
                    }
                }
            }

            int initialLotPrice = getRandomInt(1, 11) * 100; // генеруємо ціну лота
            System.out.println("Lot #" + lot + " starts with the initial price of " + initialLotPrice + " UAH");

            int highestBid = initialLotPrice;
            Bidder winner = null; // посилання на переможця
            for (int subRound = 1; subRound < 3 + 1; subRound++) {
                //System.out.println("Subround #" + subRound + " has begun:");
                for (int i = 1; i < numberOfParticipants + 1; i++) {
                    // 50% шансу на те, що учасник прийме участь у розіграші лоту + якщо учасник "кидало" - його не допускають
                    if (getRandomInt(1, 3) == 1 && participants.get(i - 1).isTakingPart) {
                        // пропонувати ціну, що є меншою - безглуздо, тож ціна завжди більша, хоча б на "1"
                        highestBid = getRandomInt(highestBid + 1, (highestBid + getRandomInt(1, 11) * 10));

                        winner = participants.get(i - 1); // переможець - той, хто останнім змінив ціну
                        winner.setBidPrice(highestBid);
                        new Thread(winner).start();
                    } else {
                        //System.out.println("\tParticipant #" + i + " won't bit..");
                    }
                    Thread.sleep(400); // пауза між ставками
                    START.countDown();
                }
            }

            while (START.getCount() > 3) { // зупинка потоку main, допоки ставки не будуть зроблені
                Thread.sleep(200);
            }

            Thread.sleep(1000);

            for (int i = 3; i > 0; i--) { // феєричний відлік..
                System.out.println(i + "..");
                START.countDown();
                Thread.sleep(1000);
            }

            if (winner == null) // оголошення результатів торгу
                System.out.println("The lot #" + lot + " goes to the auction house.");
            else {
                System.out.printf("Participant #" + winner.getNumber() + ", you have 5 seconds to pay the lot: ");
                Bidder finalWinner = winner;
                int finalLot = lot;
                Thread th1 = new Thread(() -> {
                    if (getRandomInt(1, 4) != 1) { // 66% ймовірності, що переможець сплатить ставку
                        System.out.println("The lot #" + finalLot + " has been sold to participant #" + finalWinner.getNumber() + " for " + finalWinner.getBidPrice() + " UAH");
                        return;
                    } else {
                        try {
                            Thread.sleep(5000); // зачекати
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Unfortunately, the payment was not made.. Participant #" + finalWinner.getNumber() + " is banned for " + (penalty - 1) + " lots!");
                        finalWinner.setTakingPart(false);
                        finalWinner.setPenalty(penalty);
                    }
                });
                th1.start();
                th1.join();
            }
            System.out.printf("\n");
        }

    }

    public static int getRandomInt(int fromInclusive, int toExclusive) {
        return (int) (fromInclusive + (Math.random() * (toExclusive - fromInclusive)));
    }

    static class Bidder implements Runnable {

        private int number;
        private int bidPrice;
        private boolean isTakingPart;
        private int penalty;

        Bidder(int number) {
            this(number, 0);
        }

        Bidder(int number, int bidPrice) {
            this.number = number;
            this.bidPrice = bidPrice;
            isTakingPart = true;
            penalty = 0;
        }

        public int getNumber() {
            return number;
        }

        public int getBidPrice() {
            return bidPrice;
        }

        public void setBidPrice(int bidPrice) {
            this.bidPrice = bidPrice;
        }

        public boolean getIsTakingPart() {
            return isTakingPart;
        }

        public void setTakingPart(boolean takingPart) {
            isTakingPart = takingPart;
        }

        public int getPenalty() {
            return penalty;
        }

        public void setPenalty(int penalty) {
            this.penalty = penalty;
        }

        @Override
        public void run() {
            System.out.println("\tParticipant #" + number + " has made a bid: " + bidPrice + " UAH");
            START.countDown();
            try {
                START.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
