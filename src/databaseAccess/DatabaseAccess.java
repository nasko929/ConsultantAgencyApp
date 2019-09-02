package databaseAccess;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * A class to easily provide a connection from a connection pool.
 */
public class DatabaseAccess {
    private static BasicDataSource dataSource = new BasicDataSource(); // The connection pool object.

    private DatabaseAccess() {
    }

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/consultant_customer_taskdb" +
                "?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    /**
     * A method which returns a currently non- used connection from the connection pool.
     *
     * @return Connection from the pool.
     * @throws SQLException - throws SQLException.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
