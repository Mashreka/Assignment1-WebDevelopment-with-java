package models;

import java.sql.*;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/gritAcademy?user=user1";
    //to establish a connection to gritAcademy MySQL database using JDBC.
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }
}
