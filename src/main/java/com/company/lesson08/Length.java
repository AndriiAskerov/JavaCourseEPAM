package com.company.lesson08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Length {
    Scanner fileScanner;
    private String[] words;
    private Map<String, Integer> table;

    Length(String fileName) throws FileNotFoundException {
        fileScanner = new Scanner(new File(fileName));
        words = fileScanner.nextLine().split("\s");
        table = new HashMap<>();
    }

    public String getLongest(int amount) {
        for (String word : words) {
            if (!table.containsKey(word)) {
                table.put(word, word.length());
            }
        }
        return sortTable(amount);
    }

    private String sortTable(int amount) {
        String output = "";
        String[] keys = table.keySet().toArray(new String[0]);
        Integer[] values = table.values().toArray(new Integer[0]);

        // by length
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                if (values[j] < values[j + 1]) {
                    int tmpValue = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = tmpValue;

                    String tmpKey = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = tmpKey;
                }
            }
        }

        // forming output
        for (int i = 0; i < amount; i++) {
            output += (i + 1) + ". " + keys[i] + " ==> " + values[i] + "\n";
        }
        return output;
    }
}