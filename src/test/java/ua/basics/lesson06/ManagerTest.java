package ua.basics.lesson06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson06.Manager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    static int i = 0;
    Manager m1;
    Manager m2;
    Manager m3;
    BigDecimal bonus;

    @BeforeEach
    void setUp() {
        m1 = new Manager("Salvador", new BigDecimal(2000), 231);
        m2 = new Manager("Markus", new BigDecimal(3000), 76);
        m3 = new Manager("Percival", new BigDecimal(1500), 121);
        bonus = new BigDecimal(400);
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void setBonus() {
        m1.setBonus(bonus);
        m2.setBonus(bonus);
        m3.setBonus(bonus);
        BigDecimal expected1 = m1.getSalary().add(new BigDecimal(1000).add(bonus));
        BigDecimal expected2 = m2.getSalary().add(new BigDecimal(0).add(bonus));
        BigDecimal expected3 = m3.getSalary().add(new BigDecimal(500).add(bonus));

        assertEquals(expected1, m1.toPay());
        System.out.println("m1: passes");
        assertEquals(expected2, m2.toPay());
        System.out.println("m2: passes");
        assertEquals(expected3, m3.toPay());
        System.out.println("m3: passes");
    }
}