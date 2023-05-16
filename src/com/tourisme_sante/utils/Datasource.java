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
 * @author LENOVO
 */
public class Datasource {
     private Connection cnx;
    
    private static Datasource instance;
    
    private final String URL = "jdbc:mysql://localhost:3306/tourisme_sante";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private  Datasource() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Datasource getInstance() {
        if(instance == null)
            instance = new Datasource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
