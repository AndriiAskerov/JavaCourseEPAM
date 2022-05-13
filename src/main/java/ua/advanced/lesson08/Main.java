package ua.advanced.lesson08;

import ua.advanced.lesson08.connector.HikariCPDataSource;
import ua.advanced.lesson08.dao.MovieDAO;
import ua.advanced.lesson08.dao.PersonDAO;
import ua.advanced.lesson08.model.Movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            Connection connection = HikariCPDataSource.getConnection();
            connection.close();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MovieDAO movieDAO = new MovieDAO();
        PersonDAO personDAO = new PersonDAO();

        System.out.println("---");
        System.out.println("- Task01 (new movies >= '2021'):");
        printOutList(movieDAO.newMovies());

        System.out.println("- Task02 (actors from movie):");
        printOutList(personDAO.actorsInMovie("title001"));

        System.out.println("- Task03 (actors who plays in movies more than\\exactly 'N' times):");
        printOutList(personDAO.actorsFromSeveralFilms(4));

        System.out.println("- Task04 (actors who are directors):");
        printOutList(personDAO.actorsWhoAreDirectors());

        System.out.println("- Task05 (delete movies that are 'N' years old):");
        task05_demo(movieDAO, 2022, 100);
        System.out.println("---");
    }

    private static void task05_demo(MovieDAO movieDAO, int currentYear, int nYearsOld) throws SQLException {
        movieDAO.create(new Movie("title601", Date.valueOf("1521-11-29"), "Spain"));
        movieDAO.create(new Movie("title602", Date.valueOf("1411-12-19"), "Spain"));
        movieDAO.create(new Movie("title603", Date.valueOf("1215-01-09"), "Spain"));
        printOutList(movieDAO.findAll());
        movieDAO.deleteMoviesThatOlderThan(currentYear, nYearsOld);
        printOutList(movieDAO.findAll());
    }

    // utilities:
    private static void printOutList(List list) {
        Iterator iterator = list.iterator();
        for (int i = 1; iterator.hasNext(); i++) {
            if (i < 10) {
                System.out.println("0" + i + ". " + iterator.next());
            } else
                System.out.println(i + ". " + iterator.next());
        }
        System.out.println();
    }
}
