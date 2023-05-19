/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Interventions;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chimou
 */
public class ServiceInterventions implements services<Interventions> {
    
   private Connection cnx = Datasource.getInstance().getCnx();
    
   @Override
    public void ajouter(Interventions i) {
        try {
            String req = "INSERT INTO interventions(nom,deescripition) VALUES (?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
          
            pst.setString(1, i.getNom());
            pst.setString(2, i.getdeescripition());
            pst.executeUpdate();
            System.out.println("Intrvention ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void modifier(Interventions i) {
        try {
            String req = "UPDATE interventions SET Id=?, nom=?, deescripition=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, i.getIdInter());
            pst.setInt(4, i.getIdInter());
            pst.setString(2, i.getNom());
            pst.setString(3, i.getdeescripition());
            pst.executeUpdate();
            System.out.println("Intervention modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void supprimer(Interventions i) {
        try {
            String req = "DELETE from interventions WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, i.getIdInter());
            pst.executeUpdate();
            System.out.println("Intervention supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public List<Interventions> afficher() {
        List<Interventions> list = new ArrayList<>();
        
        String req = "SELECT * FROM interventions";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Interventions(rs.getInt("id"), rs.getString("nom"), rs.getString("deescripition")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

}