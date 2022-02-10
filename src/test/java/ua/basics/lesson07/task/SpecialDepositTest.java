package ua.basics.lesson07.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson07.task.SpecialDeposit;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SpecialDepositTest {
    SpecialDeposit sd1;
    SpecialDeposit sd2;
    SpecialDeposit sd3;
    BigDecimal expected1;
    BigDecimal expected2;
    BigDecimal expected3__;
    BigDecimal expected3_1;

    @BeforeEach
    void setUp() {
        sd1 = new SpecialDeposit(new BigDecimal(1000), 1);
        sd2 = new SpecialDeposit(new BigDecimal(2000), 1);
        sd3 = new SpecialDeposit(new BigDecimal(1000), 10);

        expected1 = new BigDecimal(10);
        expected2 = new BigDecimal(20);
        expected3__ = new BigDecimal(701.81999999999);
        expected3_1 = new BigDecimal(701.820);

    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test.\n");
    }

    @Test
    void income() {
        assertTrue(expected1.compareTo(sd1.income()) == 0);
        System.out.println("Income of the 1st deposit: " + sd1.income());
        assertTrue(expected2.compareTo(sd2.income()) == 0);
        System.out.println("Income of the 2nd deposit: " + sd2.income());
        System.out.println("Income of the 3rd deposit: " + sd3.income());
        assertTrue(expected3__.compareTo(sd3.income()) == -1 && expected3_1.compareTo(sd3.income()) == 1); // 701.81(9) < x < 701.820
    }
}