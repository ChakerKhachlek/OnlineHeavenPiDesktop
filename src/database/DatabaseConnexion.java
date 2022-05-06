/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lord Solari
 */
public class DatabaseConnexion {
    //DB CREDENTIALS
    final static String URL = "jdbc:mysql://127.0.0.1:3325/espritpi";
    final static String USERNAME = "root";
    final static String PASSWORD = "";
    
    //Connection init
    static DatabaseConnexion instance = null;
    private Connection cnx;
    
    //constructor
    private DatabaseConnexion() {
        
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //getters
    public static DatabaseConnexion getInstance() {
        if (instance == null) {
            instance = new DatabaseConnexion();
        }
        
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
