/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Chimou
 */
public class DataSourceA {
    
     private Connection cnx;
    
    private static DataSourceA instance;
    
    private final String URL = "jdbc:mysql://localhost:3306/tourisme_sante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DataSourceA() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DataSourceA getInstance() {
        if(instance == null)
            instance = new DataSourceA();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    
    
}
