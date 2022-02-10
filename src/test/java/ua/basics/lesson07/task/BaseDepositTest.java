package ua.basics.lesson07.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson07.task.BaseDeposit;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

class BaseDepositTest {
    BaseDeposit bd1;
    BaseDeposit bd2;
    BaseDeposit bd3;
    BigDecimal expected1;
    BigDecimal expected2;
    BigDecimal expected3;
    BigDecimal expected3_1;

    @BeforeEach
    void setUp() {
        bd1 = new BaseDeposit(new BigDecimal(1000), 1);
        bd2 = new BaseDeposit(new BigDecimal(2000), 1);
        bd3 = new BaseDeposit(new BigDecimal(1000), 10);

        expected1 = new BigDecimal(50);
        expected2 = new BigDecimal(100);
        expected3 = new BigDecimal(628.89);
        expected3_1 = expected3.add(new BigDecimal(0.01));
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test.\n");
    }

    @Test
    void income() {
        assertTrue(expected1.compareTo(bd1.income()) == 0);
        System.out.println("Income of the 1st deposit: " + bd1.income());
        assertTrue(expected2.compareTo(bd2.income()) == 0);
        System.out.println("Income of the 2nd deposit: " + bd2.income());
        assertTrue(expected3.compareTo(bd3.income()) == -1 && expected3_1.compareTo(bd3.income()) == 1); // 628.89 < x < 628.90
        System.out.println("Income of the 3rd deposit: " + bd3.income());
    }
}