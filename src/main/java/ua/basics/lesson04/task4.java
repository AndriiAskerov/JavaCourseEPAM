package ua.basics.lesson04;

public class task4 {
    public static float GeometricProgression(float a, float t, float lim) {
        float tmp = 0.0F;
        for (int i = 0; i < lim; i++) {
            tmp += a;
            a *= t;
            if (a < lim) return tmp;
        }
        return tmp;
    }
}