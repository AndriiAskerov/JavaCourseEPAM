package ua.advanced.lesson08.dao;

import ua.advanced.lesson08.connector.HikariCPDataSource;
import ua.advanced.lesson08.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements DAO {

    // general queries()
    private static final String SQL_ADD_MOVIE = "INSERT INTO movies (title, date, country) VALUES (?, ?, ?)";
    private static final String SQL_FIND_MOVIE = "SELECT * FROM movies WHERE id=(?)";
    private static final String SQL_FIND_MOVIES = "SELECT * FROM movies";
    private static final String SQL_UPDATE_MOVIE = "UPDATE movies SET title=(?), date=(?), country=(?) WHERE id=(?)";
    private static final String SQL_DELETE_MOVIE = "DELETE FROM movies WHERE id=(?)";

    // task special queries() for movie
    private static final String SQL_NEW_MOVIES = "SELECT * FROM movies WHERE YEAR(date)>2020";
    private static final String SQL_DELETE_MOVIE_OLDER_THAN = "DELETE FROM movies WHERE YEAR(date)<=(?)";

    // general methods()
    @Override
    public boolean create(Object entity) {
        boolean flag;
        Movie movie = (Movie) entity;

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            pStatement = connection.prepareStatement(SQL_ADD_MOVIE);

            /*pStatement.setInt(1, movie.getId());*/
            pStatement.setString(1, movie.getTitle());
            pStatement.setDate(2, movie.getReleaseDate());
            pStatement.setString(3, movie.getCountry());
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
    public List<Movie> findAll() {
        return getMovies(SQL_FIND_MOVIES);
    }

    @Override
    public Object findEntityById(Object id) {
        Movie movie = new Movie();

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            pStatement = connection.prepareStatement(SQL_FIND_MOVIE);
            pStatement.setInt(1, (Integer) id);

            ResultSet result = pStatement.executeQuery();
            movie = getMovieListFromResult(result).remove(0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pStatement);
            close(connection);
        }
        return movie;
    }

    @Override
    public Object update(Object entity) {
        Movie newMovie = (Movie) entity;
        Movie oldMovie = new Movie();

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();

            // пошук елемента, що буде оновлено:
            pStatement = connection.prepareStatement(SQL_FIND_MOVIE);
            pStatement.setInt(1, (newMovie.getId()));
            ResultSet result = pStatement.executeQuery();
            oldMovie = getMovieListFromResult(result).remove(0);

            // оновлення:
            pStatement = connection.prepareStatement(SQL_UPDATE_MOVIE);
            pStatement.setString(1, newMovie.getTitle());
            pStatement.setDate(2, newMovie.getReleaseDate());
            pStatement.setString(3, newMovie.getCountry());
            pStatement.setInt(4, newMovie.getId());
            pStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pStatement);
            close(connection);
        }
        // повернення старої інфорамаціїї елемента (до його оновлення)
        return oldMovie;
    }

    @Override
    public boolean delete(Object id) {
        boolean flag;

        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            pStatement = connection.prepareStatement(SQL_DELETE_MOVIE);

            pStatement.setInt(1, (Integer) id);
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

    // task special methods() for movie
    public List<Movie> newMovies() throws SQLException {
        return getMovies(SQL_NEW_MOVIES);
    }
    public void deleteMoviesThatOlderThan(int currentYear, int nYearsOld) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            statement = connection.createStatement();

            int threshold = currentYear - nYearsOld;
            PreparedStatement preparedStmt = connection.prepareStatement(SQL_DELETE_MOVIE_OLDER_THAN);
            preparedStmt.setInt(1, threshold);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
    }

    // additional utilities
    private List<Movie> getMovies(String sqlFindAllMovies) {
        List<Movie> output = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = HikariCPDataSource.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sqlFindAllMovies);
            output = getMovieListFromResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return output;
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
}
