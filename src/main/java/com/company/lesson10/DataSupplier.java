package com.company.lesson10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataSupplier {
    public static ArrayList<User> getUserFromURL(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            br.readLine();
            while (br.ready()) {
                String text = br.readLine();
                String[] words = text.split(";");
                users.add(new User(
                        words[0],
                        words[1],
                        Double.parseDouble(words[2].replace(",", ".").replace("₴", "").replaceAll(" ", ""))
                ));
            }
        }
        return users;
    }
}
