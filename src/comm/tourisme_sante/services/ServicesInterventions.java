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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chimou
 */
public class ServicesInterventions implements services <Interventions> {
    
   private Connection cnx = Datasource.getInstance().getCnx();
   
   
  
   @Override
    public void ajouter(Interventions ti) {
        try {
            String req = "INSERT INTO interventions (nomType,prix,descripition,idmedecin,idtypeintervention) VALUES (?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
          System.out.println(ti.getIdtypeintervention());
            pst.setString(1, ti.getNomType());
            pst.setInt(2, (int) ti.getPrix());
            pst.setString(3, ti.getdescripition());
            pst.setInt(4, ti.getIdmedecin());
            pst.setInt(5, ti.getIdtypeintervention());
            
            pst.executeUpdate();
            System.out.println("Intrvention ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void modifier(Interventions ti) {
        try {
           
            String req = "UPDATE interventions SET id=?, nomType=?, prix=?, descripition=?, idmedecin=?, idtypeintervention=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, ti.getId());
            pst.setString(2, ti.getNomType());
             pst.setInt(3, (int) ti.getPrix());
            pst.setString(4, ti.getdescripition());
            pst.setInt(5, ti.getIdmedecin());
             pst.setInt(6, ti.getIdtypeintervention());
            pst.setInt(7, ti.getId());
            
            pst.executeUpdate();
            System.out.println("Intervention modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void supprimer(Interventions ti) {
        try {
            String req = "DELETE from interventions WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, ti.getId());
            pst.executeUpdate();
            System.out.println("Intervention supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public List<Interventions> afficher() {
        List<Interventions> list = new ArrayList<>();
 
        String req = "SELECT Interventions.id,nomType,idmedecin,idtypeintervention,prix,descripition,fullName, nom FROM TypeInterventions join Interventions on TypeInterventions.id=Interventions.idtypeintervention join medecins on Interventions.idmedecin=medecins.id ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Interventions(rs.getInt("id"), rs.getString("nomType"),rs.getInt("prix"), rs.getString("descripition"),rs.getInt("idmedecin"),rs.getInt("idtypeintervention"), rs.getString("nom"),rs.getString("fullName")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

    
   public int getInterventions(String nom){
      
       String req= "Select (*) nb FROM Interventions WHERE nomType=?";
       int nb=0;
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,nom);
            ResultSet rs = pst.executeQuery();
             if(rs.next()) 
                      System.out.println(rs.getInt("nb"));
                      nb=rs.getInt("nb");
             
             }catch (SQLException ex){
                     
            
            System.out.println(ex.getMessage());
            }
    return nb;
   
   
   
        }
    
}
