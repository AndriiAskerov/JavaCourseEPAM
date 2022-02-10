package ua.basics.lesson07.task;

import java.math.BigDecimal;

public class LongDeposit extends Deposit{

    LongDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    LongDeposit(BigDecimal depositAmount) {
        this(depositAmount, 7);
    }

    @Override
    BigDecimal income() {
        BigDecimal total = getDepositAmount();
        int counter = 1;
        for (int i = this.getDepositPeriod(); i > 0; i--) {
            if (counter < 7) {
                counter++;
                continue;
            }
            total = total.multiply(new BigDecimal(1.15f));
        }
        total = total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal income = total.subtract(getDepositAmount());
        return income;
    }
}