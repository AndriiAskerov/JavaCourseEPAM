package com.company.lesson6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    static int i = 0;
    SalesPerson p1;
    SalesPerson p2;
    SalesPerson p3;
    Manager m1;
    Manager m2;
    Manager m3;
    Company employees;
    BigDecimal bonus;

    @BeforeEach
    void setUp() {
        p1 = new SalesPerson("Jamis", new BigDecimal(2045.85), 231);
        p2 = new SalesPerson("Katty", new BigDecimal(3070.4), 76);
        p3 = new SalesPerson("Louis", new BigDecimal(1621.15), 121);
        m1 = new Manager("Salvador", new BigDecimal(2375), 231);
        m2 = new Manager("Markus", new BigDecimal(3012.78), 76);
        m3 = new Manager("Percival", new BigDecimal(1591.23), 121);
        employees = new Company(p1, m1, p2, m2, p3, m3);
        bonus = new BigDecimal(400);
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void giveEverybodyBonus() {
        employees.giveEverybodyBonus(bonus);
        BigDecimal expectedP1 = bonus.multiply(new BigDecimal(3));
        BigDecimal expectedP2 = bonus.multiply(new BigDecimal(1));
        BigDecimal expectedP3 = bonus.multiply(new BigDecimal(2));
        BigDecimal expectedM1 = new BigDecimal(1000).add(bonus);
        BigDecimal expectedM2 = new BigDecimal(0).add(bonus);
        BigDecimal expectedM3 = new BigDecimal(500).add(bonus);

//        assertEquals(expectedP1, p1.toPay().subtract(p1.getSalary()));
        assertEquals(0, expectedP1.compareTo(p1.toPay().subtract(p1.getSalary())));
        System.out.println("p1: passes");
        assertEquals(0, expectedP2.compareTo(p2.toPay().subtract(p2.getSalary())));
        System.out.println("p2: passes");
        assertEquals(0, expectedP3.compareTo(p3.toPay().subtract(p3.getSalary())));
        System.out.println("p3: passes");
        assertEquals(0, expectedM1.compareTo(m1.toPay().subtract(m1.getSalary())));
        System.out.println("m1: passes");
        assertEquals(0, expectedM2.compareTo(m2.toPay().subtract(m2.getSalary())));
        System.out.println("m2: passes");
        assertEquals(0, expectedM3.compareTo(m3.toPay().subtract(m3.getSalary())));
        System.out.println("m3: passes");

    }

    @Test
    void totalToPay() {
        employees.giveEverybodyBonus(bonus);
        BigDecimal expected = p1.toPay().add(p2.toPay()).add(p3.toPay()).add(m1.toPay()).add(m2.toPay()).add(m3.toPay());
        assertEquals(expected, employees.totalToPay());
        System.out.println("employeesTotalToPay: passes (" + employees.totalToPay() + ")");
    }
}