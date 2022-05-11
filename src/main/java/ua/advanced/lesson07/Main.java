package ua.advanced.lesson07;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        dbConnector.establishConnection();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
        System.out.println("---");

        System.out.println("- Task01 (new movies >= '2021'):");
        printOutMovies(newMovies());

        System.out.println("- Task02 (actors from movie):");
        printOutPersons(actorsInMovie("title001"));

        System.out.println("- Task03 (actors who plays in movies more than 'N' times):");
        printOutPersons(actorsFromSeveralFilms(4));

        System.out.println("- Task04 (actors who are directors):");
        printOutPersons(actorsWhoAreDirectors());

        System.out.println("- Task05 (delete movies that are 'N' years old):");
        task05_demo();

        System.out.println("---");
        dbConnector.closeConnection(dbConnector.getConnection());
    }

    private static void task05_demo() throws SQLException {
        /* for demo ONLY:
         * I'm adding a bunch of new blank records to the DB
         * only for showing that the deleting method works. */
        statement.addBatch("" +
                "INSERT INTO movies (title, date) VALUES ('title901', '1240-11-01');");
        statement.addBatch("" +
                "INSERT INTO movies (title, date) VALUES ('title902', '1020-03-27');");
        statement.addBatch("" +
                "INSERT INTO movies (title, date) VALUES ('title903', '1181-11-13');");
        statement.addBatch("" +
                "INSERT INTO movies (title, date) VALUES ('title904', '1473-07-04');");
        statement.executeBatch();
        statement.clearBatch(); /*
         */

        // List of movies before deleting:
        ResultSet result = statement.executeQuery("" +
                "SELECT * FROM movies");
        System.out.println("List of movies before deleting:");
        printOutMovies(getMovieListFromResult(result));

        // Perform deleting:
        deleteMoviesThatOlderThan(2022, 50);

        // List of movies after deleting:
        result = statement.executeQuery("" +
                "SELECT * FROM movies");
        System.out.println("\nList of movies after deleting:");
        printOutMovies(getMovieListFromResult(result));
    }

    // utilities:
    private static void printOutMovies(List<Movie> list) {
        Iterator<Movie> iterator = list.iterator();
        for (int i = 1; iterator.hasNext(); i++) {
            if (i < 10) {
                System.out.println("0" + i + ". " + iterator.next());
            } else
                System.out.println(i + ". " + iterator.next());
        }
        System.out.println();
    }
    private static void printOutPersons(List<Person> list) {
        Iterator<Person> iterator = list.iterator();
        for (int i = 1; iterator.hasNext(); i++) {
            if (i < 10) {
                System.out.println("0" + i + ". " + iterator.next());
            } else
                System.out.println(i + ". " + iterator.next());
        }
        System.out.println();
    }
    private static List<Movie> getMovieListFromResult(ResultSet result) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            Date releaseDate = result.getDate("date");
            String country = result.getString("country");
            movies.add(new Movie(id, title, releaseDate, country));
        }
        return movies;
    }
    private static List<Person> getPeopleListFromResult(ResultSet result) throws SQLException {
        List<Person> actors = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("person_id");
            String name = result.getString("name");
            Date birthdate = result.getDate("birthdate");
            actors.add(new Person(id, name, birthdate));
        }
        return actors;
    }

    // task methods:
    public static List<Movie> newMovies() throws SQLException {
        ResultSet result = statement.executeQuery("" +
                "SELECT * FROM movies WHERE YEAR(date)>2020");

        return getMovieListFromResult(result);
    }
    public static List<Person> actorsInMovie(String title) throws SQLException {
        ResultSet result = statement.executeQuery("" +
                // дія 3. вибрати ДАНІ_ПРО_ЛЮДИНУ, що є (2)АКТОРОМ, що (1)ГРАВ_У_ДАНОМУ_ФІЛЬМІ
                "SELECT * FROM people WHERE people.person_id IN (" +

                // дія 2. вибрати АКТОРІВ, що ГРАЛИ у (1)ДАНОМУ_ФІЛЬМІ
                "SELECT actors.person_id FROM actors WHERE actors.movie_id=(" +

                // дія 1. вибрати ІД кінострічки, НАЗВА якої відповідає вказаній
                "SELECT movies.id FROM movies WHERE title='" + title + "'))");

        return getPeopleListFromResult(result);
    }
    public static List<Person> actorsFromSeveralFilms(int numberOfMovies) throws SQLException {
        ResultSet result = statement.executeQuery("" +
                "SELECT * FROM actors");

        // мапа ключ:PERSON_ID, значення:КІЛЬКІСТЬ_ФІЛЬМІВ_У_ЯКИХ_ГРАВ_АКТОР
        Map<Integer, Integer> map = new HashMap<>();
        while (result.next()) {
            int personId = result.getInt("person_id");
            map.merge(personId, 1, Integer::sum);
        }

        // список значень PERSON_ID акторів, що грали більш ніж у N фільмах
        List<Integer> idList = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for (var entry : set) {
            if (entry.getValue() >= numberOfMovies) {
                idList.add(entry.getKey());
            }
        }

        StringBuilder idListForQuery = new StringBuilder("(");
        /* MySQL "SELECT" запит повинен отримати вказівку "IN (x1, x2, ... xN)"
         * звертаючись до списку людей "WHERE person_id" обираємо лише тих, що
         * пердставленні у наборі "IN (x1, x2, ... xN)"
         * */
        Iterator<Integer> iterator = idList.iterator();
        while (iterator.hasNext()) {
            idListForQuery.append(iterator.next());
            if (iterator.hasNext()) {
                idListForQuery.append(", ");
            }
        }
        idListForQuery.append(')');

        // виконуємо запит:
        result = statement.executeQuery("" +
                "SELECT * FROM people WHERE person_id IN " + idListForQuery);

        return getPeopleListFromResult(result);
    }
    public static List<Person> actorsWhoAreDirectors() throws SQLException {
        ResultSet result = statement.executeQuery("" +
                // дія 3. вибрати ДАНІ_ПРО_ЛЮДИНУ, що є (2)і АКТОРОМ і РИЖЕСЕРОМИ
                "SELECT * FROM people WHERE person_id IN (" +

                // дія 2. вибрати АКТОРІВ, котрі є (1)РИЖЕСЕРАМИ
                "SELECT person_id FROM actors WHERE person_id IN (" +

                // дія 1. ...
                "SELECT person_id FROM directors))");

        return getPeopleListFromResult(result);
    }
    public static void deleteMoviesThatOlderThan(int currentYear, int nYearsOld) throws SQLException {
        int threshold = currentYear - nYearsOld;
        String query = "" +
                // видалити усі ФІЛЬМИ, чия ДАТА_ВИХОДУ <= за ВКАЗАНИЙ_РІК
                "DELETE FROM movies WHERE YEAR(date)<=" + threshold;

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();
    }
}
