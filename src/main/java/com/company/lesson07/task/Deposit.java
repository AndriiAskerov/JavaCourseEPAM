package com.company.lesson07.task;

import java.math.BigDecimal;

public abstract class Deposit {
    private BigDecimal depositAmount;
    private int depositPeriod; // in months

    Deposit(BigDecimal depositAmount, int depositPeriod) {
        this.depositAmount = depositAmount;
        this.depositPeriod = depositPeriod;
    }

    abstract BigDecimal income();

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public int getDepositPeriod() {
        return depositPeriod;
    }
}