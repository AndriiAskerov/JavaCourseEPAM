package ua.basics.lesson10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.basics.lesson10.KMDA;

import java.io.IOException;

import static ua.basics.lesson10.DataSupplier.getUserFromURL;
import static org.junit.jupiter.api.Assertions.*;

class KMDATest {
    static int i = 0;
    String[] urls;
    String expected;

    @BeforeEach
    void setUp() {
        urls = new String[] {
                "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2021.csv",
                "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2021.csv",
                "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/953aae94-8c82-4296-881f-f57b3a7be389/download/berezen-2021.csv"
        };
        System.out.println("Test_" + ++i + ": ");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test_" + i + ".\n");
    }

    @Test
    void userWithMaxSalary() throws IOException {
        expected = "Max salary: 13125.0\n" +
                "User name : Хонда М. П.\n";
        assertEquals(expected, KMDA.userWithMaxSalary(getUserFromURL(urls[0])));
    }

    @Test
    void userWithMinSalary() throws IOException {
        expected = "Min salary: 4720.0\n" +
                "User name : Кличко В. В.\n";
        assertEquals(expected, KMDA.userWithMinSalary(getUserFromURL(urls[0])));
    }

    @Test
    void getAverageSalary() throws IOException {
        assertEquals(12396.57, KMDA.getAverageSalary(urls), 1.0);
    }

    @Test
    void getMonthWithMaxAverageSalary() throws IOException {
        assertEquals("January", KMDA.getMonthWithMaxAverageSalary(urls));
    }
}