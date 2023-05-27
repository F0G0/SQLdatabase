package org.ngu.Controller;

import java.sql.*;

public final class ConnectionController {
    public static Connection conn;
    public static Statement stmt;
    public static void connect(String username, String password) throws SQLException {
        String url = "jdbc:oracle:thin:@84.237.50.81:1521:"; // replace with your own Oracle database URL

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, username, password);

            stmt = conn.createStatement();


        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC driver not found");
            e.printStackTrace();
        }
    }


}