package ua.advanced.lesson05.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Exchange implements Runnable{ // клас: Біржа
    private List<Broker> brokerList;
    private List<Shares> sharesList;
    private AtomicInteger index;

    public Exchange() {
        this(null, null);
    }

    public Exchange(List<Broker> brokerList, List<Shares> sharesList) {
        this.brokerList = brokerList;
        this.sharesList = sharesList;
        this.index = new AtomicInteger(100);
    }

    public List<Broker> getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }

    public void addBroker(Broker broker) {
        brokerList.add(broker);
    }

    public void removeBroker(Broker broker) {
        brokerList.remove(broker);
    }

    public List<Shares> getSharesList() {
        return sharesList;
    }

    public void setSharesList(List<Shares> sharesList) {
        this.sharesList = sharesList;
    }

    public void addShares(Shares shares) {
        sharesList.add(shares);
    }

    public void removeShares(Shares shares) {
        sharesList.remove(shares);
    }

    public int getIndex() {
        return index.get();
    }

    public void increaseIndex() {
        index = new AtomicInteger(getIndex() + 2);

    }

    public void decreaseIndex() {
        index = new AtomicInteger(getIndex() - 2);
    }

    public boolean isOpen() {
        return getIndex() >= 75;
    }

    @Override
    public void run() {
        System.out.println("Exchange has launched! Initial index is: " + getIndex());
        for (var broker :
                brokerList) {
            new Thread(broker).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Exchange exchange = new Exchange();
        Thread exchangeThread = new Thread(exchange);

        List<Shares> shares = new ArrayList<>();
        shares.add(new Shares("Apple", 740));
        shares.add(new Shares("Gucci", 910));
        shares.add(new Shares("Meizu", 320));
        shares.add(new Shares("Pepsi", 150));

        List<Broker> brokers = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            brokers.add(new Broker(i, exchange, shares));
        }

        exchange.setSharesList(shares);
        exchange.setBrokerList(brokers);
        exchangeThread.start();
    }
}
