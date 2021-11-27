package com.company.lesson07.task;

import java.math.BigDecimal;
import java.util.Arrays;

public class Client {
    private Deposit[] deposits;

    Client() {
        deposits = new Deposit[10];
    }

    Client(Deposit... deposits) {
        this.deposits = Arrays.copyOf(deposits, deposits.length);
    }

    public boolean addDeposit(Deposit deposit) {
        if (deposits[deposits.length-1] == null) {
            for (int i = 0; i < deposits.length; i++) {
                if (deposits[i] == null) {
                    deposits[i] = deposit;
                    return true;
                }
            }
        }
        return false;
    }

    public BigDecimal totalIncome() {
        BigDecimal total = new BigDecimal(0);
        for (var deposit :
                deposits) {
            total = total.add(deposit.income());
        }
        return total;
    }

    public BigDecimal maxIncome() {
        BigDecimal maxIncome = new BigDecimal(0);
        int index = -1;
        for (int i = 0; i < deposits.length; i++) {
            if (maxIncome.compareTo(deposits[i].income()) == -1) {
                maxIncome = deposits[i].income();
                index = i;
            }
        }
        return maxIncome;
    }

    public BigDecimal getIncomeByNum(int index) {
        try {
            return deposits[index-1].income();
        } catch (Exception exception) {
            return new BigDecimal(0);
        }
    }
}