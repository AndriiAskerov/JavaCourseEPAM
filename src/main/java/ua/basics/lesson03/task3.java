package ua.basics.lesson03;

public class task3 {
    public static void main(String[] args) {
        System.out.println(ArithmeticProgression(5F, 3F, 4F));
        System.out.println(ArithmeticProgression(3F, 7F, 5F));
    }

    public static float ArithmeticProgression(float a, float t, float n) {
        float tmp = 1F;
        for (int i = 0; i < n; i++) {
            tmp *= a;
            a += t;
        }
        return tmp;
    }
}