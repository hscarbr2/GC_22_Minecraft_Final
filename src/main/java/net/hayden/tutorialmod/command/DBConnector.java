package net.hayden.tutorialmod.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Added a java class called "DBConnector" to introduce the database connector between MariaDB and Java
public class DBConnector {
    public static void main(String[] args) {
        //This URL is using the DB driver and database type to connect with the local host
        //on default port 3306.
        //It is reaching out to database "tickets"
        String url = "jdbc:mariadb://localhost:3306/tickets";
        //We created a new profile called "TB" that has admin privileges
        String user = "TB";
        //This is a static password associated with username TB so it will be automatic log in via code.
        String password = "Password123";


        //This code reaches the DriverManager package to test and connect the connection to the MariaDB
        //using the URL,USER,PASSWORD listed above. If connected, the following success message is printed.
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Successfully connected to MariaDB");

            //This code to a failsafe to tell the user that  there is an issue with the connection.
            //The SQL exception package is triggered when there is an exception error produced.
        } catch (SQLException e) {
            System.out.println("Connection Failed!!!");
            e.printStackTrace();
        }
    }
}