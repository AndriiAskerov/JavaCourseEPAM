package com.company.lesson5;

import java.util.Arrays;

public class ArrayRectangles {
    private Rectangle[] rectangleArray;
    ArrayRectangles(int n) {
        rectangleArray = new Rectangle[n];
    }
    ArrayRectangles(Rectangle ... n) { if (n.length - 1 >= 0) System.arraycopy(n, 0, rectangleArray, 0, n.length - 1); }
    public boolean addRectangle(Rectangle rectangle) {
        for (Rectangle item: rectangleArray) {
            if (item == null) {
                item = rectangle;
                return true;
            }
        }
        return false;
    }
//    public int numberOfTheRectangleWithMaxArea() {
//        for (Rectangle rectangle: rectangleArray) {
//            double maxArea = rectangle.area();
//            if (rectangle.area() > maxArea) {
//
//                return ;
//            }
//        }
//    }
}