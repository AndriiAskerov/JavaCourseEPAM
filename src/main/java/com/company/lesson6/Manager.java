package com.company.lesson6;

import java.math.BigDecimal;

public class Manager extends Employee {
    private int quantity;

    public Manager(String lastName, BigDecimal salary, int amountOfClients) {
        super(lastName, salary);
        this.quantity = amountOfClients;
    }

    @Override
    public void setBonus(BigDecimal bonus) {
        if (this.quantity > 150) {
            this.bonus = bonus.add(new BigDecimal(1000));
        }
        else if (this.quantity > 100) {
            this.bonus = bonus.add(new BigDecimal(500));
        }
        else {
            this.bonus = bonus;
        }
    }
}