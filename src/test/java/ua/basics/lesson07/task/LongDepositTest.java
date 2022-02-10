package ua.basics.lesson07.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson07.task.LongDeposit;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LongDepositTest {
    LongDeposit ld1;
    LongDeposit ld2;
    LongDeposit ld3;
    BigDecimal expected1;
    BigDecimal expected2__;
    BigDecimal expected2_1;
    BigDecimal expected3__;
    BigDecimal expected3_1;

    @BeforeEach
    void setUp() {
        ld1 = new LongDeposit(new BigDecimal(1000));
        ld2 = new LongDeposit(new BigDecimal(2000), 10);
        ld3 = new LongDeposit(new BigDecimal(1000), 30);

        expected1 = new BigDecimal(150);
        expected2__ = new BigDecimal(1498.01);
        expected2_1 = new BigDecimal(1498.015);
        expected3__ = new BigDecimal(27625.16);
        expected3_1 = new BigDecimal(27625.165);
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test.\n");
    }

    @Test
    void income() {
        assertTrue(expected1.compareTo(ld1.income()) == 0);
        System.out.println("Income of the 1st deposit: " + ld1.income());
        assertTrue(expected2__.compareTo(ld2.income()) == -1 && expected2_1.compareTo(ld2.income()) == 1); // 1498.01 < x < 1498.015
        System.out.println("Income of the 2nd deposit: " + ld2.income());
        assertTrue(expected3__.compareTo(ld3.income()) == -1 && expected3_1.compareTo(ld3.income()) == 1); // 27625.16 < x < 27625.17
        System.out.println("Income of the 3rd deposit: " + ld3.income());
    }
}