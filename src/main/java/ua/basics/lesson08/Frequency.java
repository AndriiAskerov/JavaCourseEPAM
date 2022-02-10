package ua.basics.lesson08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Frequency {
    Scanner fileScanner;
    private String[] words;
    private Map<String, Integer> table;

    Frequency(String fileName) throws FileNotFoundException {
        fileScanner = new Scanner(new File(fileName));
        words = fileScanner.nextLine().split("\s");
        table = new HashMap<>();
    }

    public String getFrequency(int amount) {
        for (String word : words) {
            if (table.containsKey(word)) {
                table.put(word, table.get(word) + 1);
            } else {
                table.put(word, 1);
            }
        }
        return sortTable(amount);
    }

    private String sortTable(int amount) {
        int comparisonResult;
        String output = "";
        String[] keys = table.keySet().toArray(new String[0]);
        Integer[] values = table.values().toArray(new Integer[0]);

        // by frequency
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

        // alphabetically
        for (int j = 0; j < amount - 1; j++) {
            //System.out.println("Comparing \"" + keys[j] + "\" to \"" + keys[j + 1] + "\"...");
            comparisonResult = keys[j].compareTo(keys[j + 1]);
            //System.out.println("The result of the comparison was " + comparisonResult);

            //System.out.print("This means that \"" + keys[j] + "\" ");
            if (comparisonResult < 0) {
                //System.out.println("lexicographically precedes \"" + keys[j + 1] + "\".");
                String tmpKey = keys[j];
                keys[j] = keys[j + 1];
                keys[j + 1] = tmpKey;

                int tmpValue = values[j];
                values[j] = values[j + 1];
                values[j + 1] = tmpValue;

            } /*else if (comparisonResult > 0) {
                System.out.println("lexicographically follows \"" + keys[j + 1] + "\".");
            }*/
        }

        // forming output
        for (int i = 0; i < amount; i++) {
            output += (i + 1) + ". " + keys[i] + " ==> " + values[i] + "\n";
        }
        return output;
    }
}