package com.company.lesson06;

import java.math.BigDecimal;

public class SalesPerson extends Employee {
    private int percent;

    public SalesPerson(String lastName, BigDecimal salary, int planPerformance) {
        super(lastName, salary);
        this.percent = planPerformance;
    }

    @Override
    public void setBonus(BigDecimal bonus) {
        if (this.percent > 200) {
            this.bonus = bonus.multiply(new BigDecimal(3));
        }
        else if (this.percent > 100) {
            this.bonus = bonus.multiply(new BigDecimal(2));
        }
        else {
            this.bonus = bonus;
        }
    }
}