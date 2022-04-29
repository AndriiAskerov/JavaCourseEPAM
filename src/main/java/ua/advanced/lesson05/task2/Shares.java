package ua.advanced.lesson05.task2;

public class Shares { // клас: Акції
    private String orgLabel;
    private int price;

    public Shares(String orgLabel, int price) {
        this.orgLabel = orgLabel;
        this.price = price;
    }

    public String getOrgLabel() {
        return orgLabel;
    }

    public int getPrice() {
        return price;
    }

    public void increasePrice(int value) {
        price += value;
    }

    public void decreasePrice(int value) {
        price -= value;
    }
}
