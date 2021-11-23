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

    public String nameMaxSalary() {
        BigDecimal highestSalary = new BigDecimal(0);
        int index = -1;
        for (int i = 0; i < staff.length-1; i++) {
            if (highestSalary.compareTo(staff[i].toPay()) == -1) {
                highestSalary = staff[i].toPay();
                index = i;
            }
        }
        return staff[index].lastName;
    }
}