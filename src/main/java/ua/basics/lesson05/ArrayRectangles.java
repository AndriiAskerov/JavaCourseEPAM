package ua.basics.lesson05;

public class ArrayRectangles {
    private Rectangle[] rectangleArray;

    public ArrayRectangles(int n) {
        rectangleArray = new Rectangle[n];
    }

    public ArrayRectangles(Rectangle... n) {
        this(n.length);
        System.arraycopy(n, 0, rectangleArray, 0, n.length);
    }

    public boolean addRectangle(Rectangle rectangle) {
        for (int i = 0; i < rectangleArray.length; i++) {
            if (rectangleArray[i] == null) {
                rectangleArray[i] = rectangle;
                return true;
            }
        }
        return false;
    }

    public int numberOfTheRectangleWithMaxArea() {
        double maxArea = 0;
        int number = -1;
        for (var rectangle : rectangleArray) {
            if (rectangle != null) {
                double currentArea = rectangle.area();
                if (currentArea > maxArea) {
                    number++;
                    maxArea = currentArea;
                }
            } else {
                return number;
            }
        }
        return number;
    }

    public int numberOfTheRectangleWithMinPerimeter() {
        double maxPerimeter = 0;
        int number = -1;
        for (var rectangle : rectangleArray) {
            if (rectangle != null) {
                double currentArea = rectangle.perimeter();
                if (currentArea > maxPerimeter) {
                    number++;
                    maxPerimeter = currentArea;
                }
            } else {
                return number;
            }
        }
        return number;
    }

    public int numberOfSquares() {
        int number = 0;
        for (var rectangle : rectangleArray) {
            if (rectangle != null) {
                if (rectangle.isSquare()) {
                    number++;
                }
            } else {
                return number;
            }
        }
        return number;
    }
}