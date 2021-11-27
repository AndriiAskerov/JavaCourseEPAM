package com.company.lesson07.task;

import java.math.BigDecimal;

public class BaseDeposit extends Deposit{

    BaseDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    BigDecimal income() {
        BigDecimal total = getDepositAmount();
        for (int i = this.getDepositPeriod(); i > 0; i--) {
            total = total.multiply(new BigDecimal(1.05f));
        }
        total = total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal income = total.subtract(getDepositAmount());
        return income;
    }
}