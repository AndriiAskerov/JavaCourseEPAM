package com.company.lesson06;

import java.math.BigDecimal;

public abstract class Employee {
    protected String lastName;
    protected BigDecimal salary;
    protected BigDecimal bonus;

    public Employee(String lastName, BigDecimal salary) {
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public abstract void setBonus(BigDecimal bonus);

    public BigDecimal toPay() {
        return (salary.add(bonus));
    }
}