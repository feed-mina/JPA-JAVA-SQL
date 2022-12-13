package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args){
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?" +
                            "user=minty&password=greatsqldb");

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("SQLException:"+ e.getMessage());
            }
        }
    }
}
