package com.test;
import java.sql.*;

//Standard class for creating a connection to the database. I probably didn't need a whole class for this,
//but I feel it's good practice for when the database is more complex.
public class DBConnection {
    private static final String url = "jdbc:sqlite:/home/magnus/IdeaProjects/QueueApp/identifier.sqlite";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
