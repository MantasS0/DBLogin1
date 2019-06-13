package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;


public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_NAME = "login";
    static final String DB_URL = "jdbc:mysql://localhost/" + DB_NAME + "?useSSL=true"; // "?useSSL=true" negalima rasyt. reik tinkamai susikonfiguruoti SSL ir sertifikatus.

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement stmt = null;

        Scanner input = new Scanner(System.in);
        String inputEmail;
        String inputPassword;

        try {
            //STEP 2: Register JDBC driver
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Iveskite El. pasta: ");
            inputEmail = input.next();
            System.out.println("Iveskite slaptazodi: ");
            inputPassword = input.next();

            System.out.println(inputEmail + " " + inputPassword);


            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, inputEmail);
            stmt.setString(2, inputPassword);
            ResultSet rs = stmt.executeQuery();

/*
            if (rs.next()) {
                //patikriname ar kazkas buvo grazinta is duombazes
                System.out.println("Sekmingai prisijungete");
                showApplication();
            } else {
                System.out.println("Neteisingi prisijungimo duomenys");
            }
*/

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");

                User.currentUser = new User(id, email, name, password);

                System.out.println("Sekmingai prisijungete");
                showApplication();


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Email: " + email);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void showApplication() {
        /* Sukuriame frame objekta ir parodome varotojui */
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
