/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Hotel;
import com.tourisme_sante.entities.AgenceHotel;

import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21626
 */
public class ServiceAgenceHotel implements services<AgenceHotel> {
           private Connection cnx = Datasource.getInstance().getCnx();
        
           @Override
       public void ajouter(AgenceHotel p) {
        try {
            String req = "INSERT INTO agenceHotel( idAgence,idHotel ) VALUES (?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getIdAgence());
            pst.setInt(2, p. getIdHotel());
    

            pst.executeUpdate();
            System.out.println("AgenceHotel ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
       }
           @Override
           public void modifier(AgenceHotel p) {
        try {
            String req = "UPDATE agenceHotel SET idAgence=?, idHotel=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(3, p.getIdAgenceHotel());
            pst.setInt(1, p.getIdHotel());
            pst.setInt(2, p.getIdAgence());
     

            pst.executeUpdate();
            System.out.println("AgenceHotel modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
           @Override
             public void supprimer(AgenceHotel p) {
        try {
            String req = "DELETE from agenceHotel WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getIdAgenceHotel());
            pst.executeUpdate();
            System.out.println("AgenceHotel supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
             
           @Override
              public List<AgenceHotel> afficher() {
        List<AgenceHotel> list = new ArrayList<>();
        
        String req = "SELECT * FROM agenceHotel";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new AgenceHotel(rs.getInt("idAgenceHotel"), rs.getInt("idAgence"), rs.getInt("idHotel")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
