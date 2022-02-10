package ua.basics.lesson08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Duplicates {
    Scanner fileScanner;
    private String[] words;
    private Map<String, Boolean> table;

    Duplicates(String fileName) throws FileNotFoundException {
        fileScanner = new Scanner(new File(fileName));
        words = fileScanner.nextLine().split("\s");
        table = new HashMap<>();
    }

    public String getDuplicates(int amount) {
        int counter = 0;

        for (String word : words) {
            if (table.containsKey(word) && (table.get(word) == false)) {
                table.put(word, true);
                counter++;
                if (counter == amount) {
                    break;
                }
            } else if (table.containsKey(word) && (table.get(word) == true)) {
                continue;
            } else {
                table.put(word, false);
            }
        }
        return sortTable(amount);
    }

    private String sortTable(int amount) {
        String output = "";
        String[] keys = table.keySet().toArray(new String[0]);
        Boolean[] values = table.values().toArray(new Boolean[0]);

        // if repeats
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] != true) {
                boolean tmpValue = values[i];
                for (int j = i + 1; j < values.length; j++) {
                    if (values[j] == true) {
                        tmpValue = values[i];
                        values[i] = values[j];
                        values[j] = tmpValue;

                        String tmpKey = keys[i];
                        keys[i] = keys[j];
                        keys[j] = tmpKey;

                        break;
                    }
                }

            }
        }
        // formatting
        String tmp;
        for (int i = 0; i < amount; i++) {
            tmp = "";
            for(int j = keys[i].length() - 1; j >= 0; j--) {
                tmp += keys[i].charAt(j);
            }
            keys[i] = tmp.toUpperCase();
        }

        // forming output
        for (int i = 0; i < amount; i++) {
            output += (i + 1) + ". " + keys[i] + " ==> " + values[i] + "\n";
        }
        return output;
    }
}