/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.agence_medicale.TypeInterventions;

import com.mycompany.utils.DataSourceA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Chimou
 */
public class Service_TypeInterventions implements IService<TypeInterventions> {
    
   private Connection cnx = DataSourceA.getInstance().getCnx();
    
   @Override
    public void ajouter(TypeInterventions i)  {
        try {
            String req = "INSERT INTO typeinterventions(nom,deescripition) VALUES (?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, i.getNom());
            pst.setString(2, i.getDeescripition());
            
            pst.executeUpdate();
            System.out.println("Type Intervention ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void modifier(TypeInterventions i) {
        try {
            String req = "UPDATE typeinterventions SET id=?, nom=?, deescripition=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, i.getId());
            pst.setString(2, i.getNom());
            pst.setString(3, i.getDeescripition());
            pst.setInt(4, i.getId());
            pst.executeUpdate();
            System.out.println("Type Intervention modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void supprimer(TypeInterventions i) {
        try {
            String req = "DELETE from typeinterventions WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, i.getId());
            pst.executeUpdate();
            System.out.println(" Type Intervention supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public List<TypeInterventions> afficher() {
        List<TypeInterventions> list = new ArrayList<>();
        
        String req = "SELECT * FROM TypeInterventions";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new TypeInterventions(rs.getInt("id"), rs.getString("nom"), rs.getString("deescripition")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

}
