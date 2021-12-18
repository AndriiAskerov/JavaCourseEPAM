package com.company.lesson10;

import static com.company.lesson10.DataSupplier.getUserFromURL;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class KMDA {
    public static String userWithMaxSalary(ArrayList<User> users) throws IOException {
        double maxSalary = users.get(0).getSalary();
        String userName = users.get(0).getName();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSalary() > maxSalary) {
                maxSalary = users.get(i).getSalary();
                userName = users.get(i).getName();
            }
        }
        return "Max salary: " + maxSalary + "\n" +
                "User name : " + userName + "\n";
    }

    public static String userWithMinSalary(ArrayList<User> users) throws IOException {
        double minSalary = users.get(0).getSalary();
        String userName = users.get(0).getName();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSalary() < minSalary) {
                minSalary = users.get(i).getSalary();
                userName = users.get(i).getName();
            }
        }
        return "Min salary: " + minSalary + "\n" +
                "User name : " + userName + "\n";
    }

    public static ArrayList<User> usersWithAverageSalary(ArrayList<User> users) {
        ArrayList<User> averageSalary = new ArrayList<>();
        double salary = 0;
        for (int i = 0; i < users.size(); i++) {
            salary += users.get(i).getSalary();
        }
        salary /= users.size();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSalary() >= salary - salary / 5 && users.get(i).getSalary() <= salary + salary / 5) {
                averageSalary.add(users.get(i));
            }
        }
        return averageSalary;
    }

    public static double getAverageSalary(String... urls) throws IOException {
        double count = 0d;
        double salary = 0d;
        for (String url : urls) {
            for (User user : getUserFromURL(url)) {
                count++;
                salary += user.getSalary();
            }
        }
        double averageSalary = salary / count;
        BigDecimal bd = BigDecimal.valueOf(averageSalary);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getMonthWithMaxAverageSalary(String... urls) throws IOException {
        String[] months = {"January", "February", "March"};

        TreeMap<Double, String> monthsSalary = new TreeMap<>();
        for (int i = 0; i < urls.length; i++) {
            monthsSalary.put(averSalary(getUserFromURL(urls[i])), months[i]);
        }

        Collection<String> sortedMonths = monthsSalary.values();
        return sortedMonths.iterator().next();
    }

    public static double averSalary(ArrayList<User> users) {
        double salary = 0;
        for (User user : users)
            salary += user.getSalary();

        double averageSalary = salary / users.size();
        BigDecimal bd = BigDecimal.valueOf(averageSalary);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}