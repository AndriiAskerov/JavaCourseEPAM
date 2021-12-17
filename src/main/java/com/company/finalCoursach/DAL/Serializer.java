package com.company.finalCoursach.DAL;

import com.company.finalCoursach.BLL.Country;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Serializer {
    private static String path = "./src/main/java/com/company/finalCoursach/serial.xml";

    public static void saveToXML(ArrayList<Country> countries) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)))) {
            xmlEncoder.writeObject(countries);
            xmlEncoder.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Country> loadFromXML() {
        ArrayList<Country> countries = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)))) {
            countries = (ArrayList<Country>) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }
}