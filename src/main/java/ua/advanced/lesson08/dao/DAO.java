package ua.advanced.lesson08.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface DAO<K, T> {
    /*default*/
    /*C*/   boolean create(T entity);
    /*R*/   List<T> findAll();
    /*R*/   T findEntityById(K id);
    /*U*/   T update(T entity);
    /*D*/   boolean delete(K id);
    /*operations*/

    // close stmt + connection methods():
    default void close(Statement statement) {
        try {
            if (statement != null)
                statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    default void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
