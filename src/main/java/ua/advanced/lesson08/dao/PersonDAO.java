package ua.advanced.lesson08.dao;

import ua.advanced.lesson08.connector.HikariCPDataSource;
import ua.advanced.lesson08.model.Person;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class PersonDAO implements DAO {

    // general queries()
    private static final String SQL_ADD_PERSON = "INSERT INTO people (name, birthdate) VALUES (?, ?)";
    private static final String SQL_FIND_PERSON = "SELECT * FROM people WHERE person_id=(?)";
    private static final String SQL_FIND_PEOPLE = "SELECT * FROM people";
    private static final String SQL_UPDATE_PERSON = "UPDATE people SET name=(?), birthdate=(?) WHERE person_id=(?)";
    private static final String SQL_DELETE_PERSON = "DELETE FROM people WHERE person_id=(?)";

    // task special queries() for person
    private static final String SQL_ACTORS_FROM_MOVIE = "SELECT * FROM people WHERE people.person_id IN (SELECT actors.person_id FROM actors WHERE actors.movie_id=(SELECT movies.id FROM movies WHERE title=(?)))";
                // дія 1. вибрати ІД кінострічки, НАЗВА якої відповідає вказаній
                // дія 2. вибрати АКТОРІВ, що ГРАЛИ у (1)ДАНОМУ_ФІЛЬМІ
                // дія 3. вибрати ДАНІ_ПРО_ЛЮДИНУ, що є (2)АКТОРОМ, що (1)ГРАВ_У_ДАНОМУ_ФІЛЬМІ
    private static final String SQL_FIND_ACTORS = "SELECT * FROM actors"; // should be at "ActorsDAO.java"
    private static final String SQL_ACTORS_FROM_SEVERAL_FILMS = "SELECT * FROM people WHERE people.person_id IN (?)";
    private static final String SQL_ACTORS_WHO_ARE_DIRECTORS = "SELECT * FROM people WHERE people.person_id IN (SELECT person_id FROM actors WHERE person_id IN (SELECT person_id FROM directors))";
                // дія 1. SELECT person_id FROM directors
                // дія 2. вибрати АКТОРІВ, котрі є (1)РИЖЕСЕРАМИ
                // дія 3. вибрати ДАНІ_ПРО_ЛЮДИНУ, що є (2)і АКТОРОМ і РИЖЕСЕРОМ

    // general methods()
    @Override
    public boolean create(Object entity) {
        boolean flag;
        Person person = (Person) entity;

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            pStatement = connection.prepareStatement(SQL_ADD_PERSON);

            /*pStatement.setInt(1, person.getId());*/
            pStatement.setString(1, person.getName());
            pStatement.setDate(2, person.getBirthdate());
            pStatement.execute();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            close(pStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public List<Person> findAll() {
        return getPeople(SQL_FIND_PEOPLE);
    }

    @Override
    public Object findEntityById(Object id) {
        Person person = new Person();

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            pStatement = connection.prepareStatement(SQL_FIND_PERSON);
            pStatement.setInt(1, (Integer) id);

            ResultSet result = pStatement.executeQuery();
            person = getPeopleListFromResult(result).remove(0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pStatement);
            close(connection);
        }
        return person;
    }

    @Override
    public Object update(Object entity) {
        Person newPerson = (Person) entity;
        Person oldPerson = new Person();

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();

            // пошук елемента, що буде оновлено:
            pStatement = connection.prepareStatement(SQL_FIND_PERSON);
            pStatement.setInt(1, (newPerson.getId()));
            ResultSet result = pStatement.executeQuery();
            oldPerson = getPeopleListFromResult(result).remove(0);

            // оновлення:
            pStatement = connection.prepareStatement(SQL_UPDATE_PERSON);
            pStatement.setString(1, newPerson.getName());
            pStatement.setDate(2, newPerson.getBirthdate());
            pStatement.setInt(3, newPerson.getId());
            pStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pStatement);
            close(connection);
        }
        // повернення старої інфорамаціїї елемента (до його оновлення)
        return oldPerson;
    }

    @Override
    public boolean delete(Object id) {
        boolean flag;

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            pStatement = connection.prepareStatement(SQL_DELETE_PERSON);

            pStatement.setInt(1, (Integer) id);
            /* also, here should be invoked queries that remove records
             * from "actors" and "directors" tables
             */
            pStatement.executeQuery();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            close(pStatement);
            close(connection);
        }
        return flag;
    }

    // task special methods() for person
    public List<Person> actorsInMovie(String title) throws SQLException {
        List<Person> output = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            statement = connection.createStatement();

            PreparedStatement pStatement = connection.prepareStatement(SQL_ACTORS_FROM_MOVIE);
            pStatement.setString(1, title);
            ResultSet result = pStatement.executeQuery();
            output = getPeopleListFromResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return output;
    }
    public List<Person> actorsFromSeveralFilms(int numberOfMovies) throws SQLException {
        List<Person> output = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_FIND_ACTORS);

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

            StringBuilder stringBuilder = new StringBuilder("SELECT * FROM people WHERE people.person_id IN (");
            Iterator<Integer> iterator = idList.iterator();
            while (iterator.hasNext()) {
                stringBuilder.append(iterator.next());
                if (iterator.hasNext()) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(')');

            // виконуємо запит:
            result = statement.executeQuery(stringBuilder.toString());

            // формуємо результат:
            output = getPeopleListFromResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return output;
    }
    public List<Person> actorsWhoAreDirectors() throws SQLException {
        return getPeople(SQL_ACTORS_WHO_ARE_DIRECTORS);
    }

    // additional utilities
    private List<Person> getPeople(String sqlActorsFromMovie) {
        List<Person> output = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sqlActorsFromMovie);
            output = getPeopleListFromResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return output;
    }
    private List<Person> getPeopleListFromResult(ResultSet result) throws SQLException {
        List<Person> actors = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("person_id");
            String name = result.getString("name");
            Date birthdate = result.getDate("birthdate");
            actors.add(new Person(id, name, birthdate));
        }
        return actors;
    }
}
