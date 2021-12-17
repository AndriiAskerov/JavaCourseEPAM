package com.company.finalCoursach.PL;

import com.company.finalCoursach.BLL.City;
import com.company.finalCoursach.BLL.Country;
import com.company.finalCoursach.DAL.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    // MAIN FUNCTION -------------------------------------------------------------------------------
    ArrayList<Country> worldMap = new ArrayList<>();
    public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws IOException {
        worldMap = Serializer.loadFromXML();
        mainMenu();
    }

    // SUBMENU SECTION -----------------------------------------------------------------------------
    private void mainMenu() throws IOException {
        while (true) {
            System.out.print("""
                MAIN-MENU______________________
                    1. Cities
                    2. Countries
                    3. Search by ID
                    0. Save & Exit
                    
                Choose an option:\040""");
            int answer = Integer.parseInt(reader.readLine());

            switch (answer) {
                case 1:
                    cityMenu();
                    break;
                case 2:
                    countryMenu();
                    break;
                case 3:
                    search();
                    break;
                case 0:
                    Serializer.saveToXML(worldMap);
                    return;
            }
        }
    }

    private void cityMenu() throws IOException {
        if (countryShowList() != null) {
            Country country;
            System.out.print(countryShowList() + """
                                        
                    Choose any country from the list above:\040""");
            try {
                int answer = Integer.parseInt(reader.readLine());
                country = worldMap.get(answer - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no country with such index!");
                return;
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
                return;
            }
            while (true) {
                System.out.print("""
                        CITY-MENU______________________
                            1. Add city
                            2. Remove
                            3. Edit
                            4. Show city info
                            0. Save & back to main menu
                            
                        Choose an option:\040""");
                int answer = Integer.parseInt(reader.readLine());
                switch (answer) {
                    case 1:
                        cityAdd(country);
                        break;
                    case 2:
                        cityRemove(country);
                        break;
                    case 3:
                        cityEdit(country);
                        break;
                    case 4:
                        cityShowInfo(country);
                        break;
                    case 0:
                        Serializer.saveToXML(worldMap);
                        return;
                }
            }
        } else {
            System.out.println("ERROR: you cannot access this menu until you have at least one country");
        }
    }

    private void countryMenu() throws IOException {
        while (true) {
            System.out.print("""
                    COUNTRY-MENU___________________
                        1. Add country
                        2. Remove
                        3. Edit
                        4. Show country info
                        0. Save & back to main menu
                        
                    Choose an option:\040""");
            int answer = Integer.parseInt(reader.readLine());
            switch (answer) {
                case 1:
                    countryAdd();
                    break;
                case 2:
                    countryRemove();
                    break;
                case 3:
                    countryEdit();
                    break;
                case 4:
                    countryShowInfo();
                    break;
                case 0:
                    Serializer.saveToXML(worldMap);
                    return;
            }
        }
    }

    // CITY MENU METHODS ---------------------------------------------------------------------------
    private void cityAdd(Country country) throws IOException {
        try {
            Pattern pattern = Pattern.compile("^[A-Z][a-z]+$");
            Matcher matcher;
            System.out.print("Enter the name of new city: ");
            String name = reader.readLine();
            matcher = pattern.matcher(name);
            if (matcher.matches()) {
                for (City city : country.cities) {
                    if (city.getName().equals(name)) {
                        System.out.println("A city with this name already exists. Please, choose another one");
                        return;
                    }
                }
                System.out.print("Enter population: ");
                int population = Integer.parseInt(reader.readLine());
                boolean isCapital;
                System.out.print("It is the capital? [\"1 - yes\"\\\"0 -no\"]: ");
                int answer = Integer.parseInt(reader.readLine());
                if (answer == 1) {
                    isCapital = true;
                } else {
                    isCapital = false;
                }
                country.cities.add(new City(name, UUID.randomUUID().toString().substring(0, 6), country.getName(), population, isCapital));
                if (country.cities.get(country.cities.size() - 1).isCapital() == true) {
                    country.setCapital(name);
                    for (int i = 0; i <= country.cities.size() - 2; i++) {
                        country.cities.get(i).setIsCapital(false);
                    }
                }
                System.out.println("City was successfully created!\n");
            } else {
                System.out.println("INVALID INPUT: input does not match the mask!");
            }
        } catch (NumberFormatException e) {
            System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
            return;
        }
    }

    private void cityRemove(Country country) throws IOException {
        if (countryShowList() != null) {
            System.out.print(cityShowList(country) + """
                                        
                    Choose any city from the list above:\040""");
            int index = 0;
            try {
                index = Integer.parseInt(reader.readLine());
                country.cities.remove(index - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no city with such index!");
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
            }
        } else {
            System.out.println("There are no cities yet. Please, add at least one");
        }
    }

    private void cityEdit(Country country) throws IOException {
        if (cityShowList(country) != null) {
            System.out.print(cityShowList(country) + """
                                        
                    Choose any city from the list above:\040""");
            int index = 0;
            try {
                index = Integer.parseInt(reader.readLine());
                cityEditor(country.cities.get(index - 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no city with such index!");
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
            }
        } else {
            System.out.println("There are no cities yet! Please, add at least one");
        }
    }

    private void cityEditor(City city) throws IOException {
        System.out.println("""
                1 - Edit name
                2 - Edit population
                """);
        System.out.print("Choose: ");
        String editChoice = reader.readLine();
        if (editChoice.equals("1")) {
            System.out.print("Enter new name: ");
            String prevName = city.getName();
            String name = reader.readLine();


            city.setName(name);

            if (city.getName() == null) {
                System.out.println("Invalid name. Didn't change");
                city.setName(prevName);
            }
        } else if (editChoice.equals("2")) {
            try {
                System.out.print("Enter population: ");
                int population = Integer.parseInt(reader.readLine());
                city.setPopulation(population);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value");
            }
        }
    }

    private void cityShowInfo(Country country) throws IOException {
        if (cityShowList(country) != null) {
            System.out.print(cityShowList(country) + """
                                        
                    Choose any city from the list above:\040""");
            int index = 0;
            try {
                index = Integer.parseInt(reader.readLine());
                System.out.print(country.cities.get(index - 1).toString() + """
                                                
                        Press ENTER to continue..\040""");
                reader.readLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no city with such index!");
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
            }
        } else {
            System.out.println("There are no cities yet! Please, add at least one");
        }
    }

    private String cityShowList(Country country) {
        if (country.cities.size() > 0) {
            String output = "";
            int index = 0;
            for (City city : country.cities) {
                output += ++index + ". " + city.getName() + "\n";
            }
            return output;
        } else {
            return null;
        }
    }


    // COUNTRY MENU METHODS ------------------------------------------------------------------------
    private void countryAdd() throws IOException {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]+$");
        Matcher matcher;
        System.out.print("Enter the name of new country: ");
        String name = reader.readLine();
        matcher = pattern.matcher(name);
        if (matcher.matches()) {
            String id = UUID.randomUUID().toString().substring(0, 6);
            worldMap.add(new Country(name, id));
            System.out.println("Country was successfully created!\n");
        } else {
            System.out.println("INVALID INPUT: input does not match the mask!");
        }
    }

    private void countryRemove() throws IOException {
        if (countryShowList() != null) {
            System.out.print(countryShowList() + """
                                        
                    Choose any country from the list above:\040""");
            int index = 0;
            try {
                index = Integer.parseInt(reader.readLine());
                worldMap.remove(index - 1);
                System.out.println("Country was successfully removed!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no country with such index!");
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
            }
        } else {
            System.out.println("There are no countries yet. Please, add at least one");
        }
    }

    private void countryEdit() throws IOException {
        if (countryShowList() != null) {
            System.out.print(countryShowList() + """
                                        
                    Choose any country from the list above:\040""");
            int index = 0;
            try {
                index = Integer.parseInt(reader.readLine());
                if (index > worldMap.size() || index < 1) {
                    throw new IndexOutOfBoundsException();
                }
                System.out.print("Enter new name: ");
                String name = reader.readLine();
                worldMap.get(index - 1).setName(name);
                System.out.println("Country was successfully renamed!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no country with such index!");
            }
        } else {
            System.out.println("There are no countries yet. Please, add at least one");
        }
    }

    private void countryShowInfo() throws IOException {
        if (countryShowList() != null) {
            System.out.print(countryShowList() + """
                                        
                    Choose any country from the list above:\040""");
            int index = 0;
            try {
                index = Integer.parseInt(reader.readLine());
                System.out.print(worldMap.get(index - 1).toString() + """
                                                
                        Press ENTER to continue..\040""");
                reader.readLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("INVALID INPUT: there is no country with such index!");
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT: incorrect input value! Please, enter only integer values");
            }
        } else {
            System.out.println("There are no countries yet! Please, add at least one");
        }
    }

    private String countryShowList() {
        if (worldMap.size() > 0) {
            String output = "";
            int index = 0;
            for (Country country : worldMap) {
                output += ++index + ". " + country.getName() + "\n";
            }
            return output;
        } else {
            return null;
        }
    }

    // ADDITIONAL METHODS --------------------------------------------------------------------------
    private void search() throws IOException {
        System.out.print("Enter id of object you want to find: ");
        String id = reader.readLine();
        if (id.length() == 6) {
            for (Country country : worldMap) {
                if (country.getId().equals(id)) {
                    System.out.println(country + """
                                                
                        Press ENTER to continue..\040""");
                    reader.readLine();
                    break;
                }
                for (City city : country.cities) {
                    if (city.getId().equals(id)) {
                        System.out.print(city + """
                                                
                        Press ENTER to continue..\040""");
                        reader.readLine();
                        break;
                    }
                }
            }
            System.out.println("The object with this ID was not found.");
        } else {
            System.out.println("INVALID INPUT: id consists of 6 characters!");
        }
    }
}
