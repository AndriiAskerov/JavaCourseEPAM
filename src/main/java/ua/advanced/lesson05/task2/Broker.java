package ua.advanced.lesson05.task2;

import java.util.List;

import static ua.advanced.lesson05.task1.Auction.getRandomInt;

public class Broker implements Runnable { // клас: Брокер
    private int id;
    private Exchange exchange;
    private List<Shares> sharesList;
    private final Object monitor = new Object();

    Broker(int id, Exchange exchange, List<Shares> sharesList) {
        this.id = id;
        this.exchange = exchange;
        this.sharesList = sharesList;
    }

    /*public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public void setShares(List<Shares> sharesList) {
        this.sharesList = sharesList;
    }*/

    private synchronized void doTrade() { // метод є ДУЖЕ спрощеним, авторським і буквально випадковим
        synchronized (exchange) {
            int sharesListIndex = getRandomInt(0, sharesList.size()); // випадковим чином зі списку обираються акції
            Shares shares = sharesList.get(sharesListIndex);
            String output = "Broker #" + id + " is handling the trade: [" + shares.getOrgLabel() + ": " + shares.getPrice() + " USD]. Sold! ";
            synchronized (monitor) {
                int difference;
                if (getRandomInt(1, 3) == 1) { // 50% ймовірності, що брокер буде посередником у тограх що призвели до:
                    difference = repIncreaseDeal(shares); // росту цін акцій N-організації
                    System.out.println(output + "Trade causes a price rise: " + shares.getPrice() + "(+" + difference + "). Exchange index rises: " + exchange.getIndex() + "(+2)");
                } else {
                    difference = repDecreaseDeal(shares); // падіння цін акцій N-організації
                    System.out.println(output + "Trade causes a price drop: " + shares.getPrice() + "(-" + difference + "). Exchange index drops: " + exchange.getIndex() + "(-2)");
                }
            }
        }
    }

    private synchronized int repIncreaseDeal(Shares shares) {
        float randomPercent =  ((float) (getRandomInt(2, 16)) / 100f);
        int value = (int) (shares.getPrice() * randomPercent);
        shares.increasePrice(value); // приріст ціни на N у.о.
        exchange.increaseIndex(); // приріст "індексу біржі"
        return value;
    }

    private synchronized int repDecreaseDeal(Shares shares) {
        float randomPercent =  ((float) (getRandomInt(2, 16)) / 100f);
        int value = (int) (shares.getPrice() * randomPercent);
        shares.decreasePrice(value); // падіння ціни на N у.о.
        exchange.decreaseIndex(); // падіння "індексу біржі"
        return value;
    }

    private synchronized int getValue(Shares shares) {

        int currentPrice = (shares.getPrice());
        /* приклад (приріст):
         * випадковеЧисло = 5, частка становить 0.05 ~ 5%
         * поточнаВартістьАкцій = 150, приріст становить "150 * 0.05 = 7.5", тобто - 7 у.о.
         */
        return (int) (currentPrice * (getRandomInt(1, 16) / 100));
    }

    @Override
    public void run() {
        synchronized (monitor) {
            while (exchange.isOpen()) {
                doTrade();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
