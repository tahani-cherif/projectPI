package comm.tourisme_sante.services;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.tourisme_sante.entities.Hotel;
import com.tourisme_sante.utils.Datasource;
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
public class ServiceHotel implements services<Hotel> {
           private Connection cnx = Datasource.getInstance().getCnx();
        
           @Override
       public void ajouter(Hotel p) {
        try {
            String req = "INSERT INTO hotel(nom, adresse, classification,email,telephone,prix) VALUES (?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getAdresse());
            pst.setString(3, p.getClassification());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getTelephone());
            pst.setFloat(6, p.getPrix());


            pst.executeUpdate();
            System.out.println("Hotel ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
       }
           @Override
           public void modifier(Hotel p) {
        try {
            String req = "UPDATE hotel SET nom=?, adresse=?,classification=?,email=?,telephone=?, prix=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(7, p.getId());
            pst.setString(1, p.getNom());
            pst.setString(2, p.getClassification());
            pst.setString(3, p.getAdresse());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getTelephone());
            pst.setFloat(6, p.getPrix());

            pst.executeUpdate();
            System.out.println("Hotel modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
           @Override
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
             
           @Override
              public List<Hotel> afficher() {
        List<Hotel> list = new ArrayList<>();
        
        String req = "SELECT * FROM hotel";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Hotel(rs.getInt("id"), rs.getString("nom"),rs.getString("adresse"),rs.getString("classification"),rs.getString("email"), rs.getInt("telephone"),rs.getFloat("prix")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
