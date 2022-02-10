package ua.basics.lesson07.task;

import ua.basics.lesson07.task.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    static int i = 0;
    BaseDeposit bd1;
    BaseDeposit     bd2;
    SpecialDeposit sd1;
    SpecialDeposit  sd2;
    LongDeposit ld1;
    LongDeposit     ld2;
    Client client;
    Client client1;

    BigDecimal expected1;

    @BeforeEach
    void setUp() {
        bd1 = new BaseDeposit(new BigDecimal(1000), 1);
        bd2 = new BaseDeposit(new BigDecimal(2000), 10);
        sd1 = new SpecialDeposit(new BigDecimal(1000),  1);
        sd2 = new SpecialDeposit(new BigDecimal(2000), 10);
        ld1 = new LongDeposit(new BigDecimal(1000)); // 7 by default
        ld2 = new LongDeposit(new BigDecimal(2000), 10);
        client = new Client(bd1, bd2, sd1, sd2, ld1, ld2);
        client1 = new Client();

        expected1 = new BigDecimal(0);
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void addDeposit() {
        Deposit[] depos = new Deposit[]{
                new BaseDeposit(new BigDecimal(1001), 1),
                new BaseDeposit(new BigDecimal(1002), 1),
                new BaseDeposit(new BigDecimal(1003), 1),
                new BaseDeposit(new BigDecimal(1004), 1),
                new BaseDeposit(new BigDecimal(1005), 1),
                new BaseDeposit(new BigDecimal(1006), 1),
                new BaseDeposit(new BigDecimal(1007), 1),
                new BaseDeposit(new BigDecimal(1008), 1),
                new BaseDeposit(new BigDecimal(1009), 1),
                new BaseDeposit(new BigDecimal(1010), 1),
                new BaseDeposit(new BigDecimal(1011), 1)};

        for (int i = 0; i < depos.length-1; i++) {
            assertTrue(client1.addDeposit(depos[i]));
            System.out.print(i+1 + ", ");
        }
        assertFalse(client1.addDeposit(depos[depos.length-1])); // 11th element
        System.out.println("[11: there is no space in array]. ");
    }

    @Test
    void totalIncome() {
        BigDecimal total = bd1.income().add(bd2.income()).add(sd1.income()).add(sd2.income()).add(ld1.income()).add(ld2.income());
        assertEquals(total, client.totalIncome());
        System.out.println("Total income: " + client.totalIncome() + " (" + total + ")");
    }

    @Test
    void maxIncome() {
        for (int i = 1; i < 7; i++) {
            System.out.println(i + ". Income: " + client.getIncomeByNum(i));
        }
        System.out.println("Highest income: " + client.maxIncome() + " (" + ld2.income() + ")");
        assertEquals(ld2.income(), client.maxIncome());
    }

    @Test
    void getIncomeByNum() {
        Deposit[] depos = new Deposit[]{ bd1, bd2, sd1, sd2, ld1, ld2};
        for (int i = 1; i < 7; i++) {
            assertEquals(depos[i-1].income(), client.getIncomeByNum(i));
            System.out.println(i + ". Income: " + client.getIncomeByNum(i));
        }
        assertEquals(expected1, client.getIncomeByNum(7));
        System.out.println("7. Income: " + client.getIncomeByNum(7));
    }
}