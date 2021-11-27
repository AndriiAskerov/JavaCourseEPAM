package com.company.lesson06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalesPersonTest {
    static int i = 0;
    SalesPerson p1;
    SalesPerson p2;
    SalesPerson p3;
    BigDecimal bonus;

    @BeforeEach
    void setUp() {
        p1 = new SalesPerson("Jamis", new BigDecimal(2000), 231);
        p2 = new SalesPerson("Katty", new BigDecimal(3000), 76);
        p3 = new SalesPerson("Louis", new BigDecimal(1500), 121);
        bonus = new BigDecimal(300);
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void setBonus() {
        p1.setBonus(bonus);
        p2.setBonus(bonus);
        p3.setBonus(bonus);
        BigDecimal expected1 = p1.getSalary().add(bonus.multiply(new BigDecimal(3)));
        BigDecimal expected2 = p2.getSalary().add(bonus.multiply(new BigDecimal(1)));
        BigDecimal expected3 = p3.getSalary().add(bonus.multiply(new BigDecimal(2)));

        assertEquals(expected1, p1.toPay());
        System.out.println("p1: passes");
        assertEquals(expected2, p2.toPay());
        System.out.println("p2: passes");
        assertEquals(expected3, p3.toPay());
        System.out.println("p3: passes");
    }
}