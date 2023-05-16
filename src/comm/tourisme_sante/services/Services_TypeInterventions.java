/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.TypeInterventions;
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
public class Services_TypeInterventions implements services <TypeInterventions> {
    
   private Connection cnx = Datasource.getInstance().getCnx();
  
   @Override
    public void ajouter(TypeInterventions ti) {
        try {
            String req = "INSERT INTO type_interventions (nomType,prix,descripition) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
          
            pst.setString(1, ti.getNomType());
            pst.setDouble(2, ti.getPrix());
            pst.setString(3, ti.getdescripition());
            pst.executeUpdate();
            System.out.println("Type Intrvention ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void modifier(TypeInterventions ti) {
        try {
           
            String req = "UPDATE type_interventions SET Id=?, nomType=?, prix=?, descripition=? WHERE Id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, ti.getId());
             pst.setInt(5, ti.getId());
            pst.setString(2, ti.getNomType());
             pst.setDouble(3, ti.getPrix());
            pst.setString(4, ti.getdescripition());
            pst.executeUpdate();
            System.out.println("TypeIntervention modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void supprimer(TypeInterventions ti) {
        try {
            String req = "DELETE from type_interventions  WHERE Id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, ti.getId());
            pst.executeUpdate();
            System.out.println("TypeIntervention supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public List<TypeInterventions> afficher() {
        List<TypeInterventions> list = new ArrayList<>();
        
        String req = "SELECT * FROM type_interventions ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new TypeInterventions(rs.getInt("Id"), rs.getString("nomType"),rs.getDouble("prix"), rs.getString("descripition")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

}

