package comm.tourisme_sante.services;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.tourisme_sante.entities.Hotel;
import com.tourisme_sante.utils.Datasource;
import comm.tourisme_sante.services.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mariem
 */
public abstract class ServiceHotel implements services<Hotel> {
           private Connection cnx = Datasource.getInstance().getCnx();
        
       public void ajouter(Hotel p) {
        try {
            String req = "INSERT INTO hotel(nom, classification, adresse,email,telephone) VALUES (?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getClassification());
            pst.setString(3, p.getAdresse());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getTelephone());


            pst.executeUpdate();
            System.out.println("Hotel ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
       }
           public void modifier(Hotel p) {
        try {
            String req = "UPDATE hotel SET nom=?,classification=?, adresse=?,email=?,telephone=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(6, p.getId());
            pst.setString(1, p.getNom());
            pst.setString(2, p.getClassification());
            pst.setString(3, p.getAdresse());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getTelephone());

            pst.executeUpdate();
            System.out.println("Hotel modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
             public void supprimer(Hotel p) {
        try {
            String req = "DELETE from hotel WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Hotel supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
             
              public List<Hotel> afficher() {
        List<Hotel> list = new ArrayList<>();
        
        String req = "SELECT * FROM hotel";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Hotel(rs.getInt("id"), rs.getString("classification"),rs.getString("adresse"),rs.getString("email"), rs.getInt("telephone")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
