package com.company.lesson6;

import java.math.BigDecimal;
import java.util.Arrays;

public class Company {
    private Employee[] staff;

    Company(Employee ... employees) {
        staff = Arrays.copyOf(employees, employees.length);
    }

    public void giveEverybodyBonus(BigDecimal companyBonus) {
        for (var employee :
                staff) {
            employee.setBonus(companyBonus);
        }
    }

    public BigDecimal totalToPay() {
        BigDecimal total = new BigDecimal(0);
        for (var employee :
                staff) {
            total = total.add(employee.toPay());
        }
        return total;
    }
}