
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package services;

import entities.Agence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import services.IService;
import utils.DataSource;

/**
 *
 * @author Mariem
 */
public class ServiceAgence  implements IService<Agence>  {
        private Connection cnx = DataSource.getInstance().getCnx();
        
       public void ajouter(Agence p) {
        try {
            String req = "INSERT INTO agence(nom, adresse,telephone) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getAdresse());
            pst.setInt(3, p.getTelephone());
            pst.executeUpdate();
            System.out.println("Agence ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
       }
        @Override
           public void modifier(Agence p) {
        try {
            String req = "UPDATE agence SET nom=? , adresse=? , telephone=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getAdresse());
            pst.setInt(3, p.getTelephone());
            pst.setInt(4, p.getId());
            pst.executeUpdate();
            System.out.println("Agence modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
        @Override
             public void supprimer(Agence p) {
        try {
            String req = "DELETE from agence WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Agence supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
             
              public List<Agence> afficher() {
        List<Agence> list = new ArrayList<>();
        
        String req = "SELECT * FROM agence";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Agence(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("telephone")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}