package com.company.lesson5;

public class Rectangle {
    private double sideA;
    private double sideB;

    public Rectangle(double a, double b) {
        sideA = a;
        sideB = b;
    }

    public Rectangle(double a) {
        this(a, 5);
    }

    public Rectangle() {
        this(4, 3);
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double perimeter() {
        return (sideA + sideB) * 2;
    }

    public double area() {
        return sideA * sideB;
    }

    public boolean isSquare() {
        return sideA == sideB;
    }

    public void replaceSides() {
        double tmp = sideA;
        sideA = sideB;
        sideB = tmp;
    }
}