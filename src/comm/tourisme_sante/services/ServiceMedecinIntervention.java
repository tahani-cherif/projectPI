/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.medecin_interventions;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceMedecinIntervention implements services<medecin_interventions>{
    private Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void ajouter(medecin_interventions p) {
       try {
            String req = "INSERT INTO medecins_interventions(id_medecin, id_interventios) VALUES (?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_medecin());
            pst.setInt(2, p.getId_interventios());
            pst.executeUpdate();
            System.out.println("medecins_interventions ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
    public void modifier(medecin_interventions p) {
        try {
            String req = "UPDATE medecins_interventions SET id_medecin=?, id_interventios=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_medecin());
            pst.setInt(2, p.getId_interventios());
            pst.setInt(3,p.getId());
            pst.executeUpdate();
            System.out.println("medecins_interventions modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     @Override
    public void supprimer(medecin_interventions p) {
        try {
            String req = "DELETE from medecins_interventions WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,p.getId());
            pst.executeUpdate();
            System.out.println("medecins_interventions supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List afficher() {
      List<medecin_interventions> list = new ArrayList<>();
        
        String req = "SELECT * FROM medecins_interventions";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new medecin_interventions(rs.getInt("id"), rs.getInt("id_medecin"), rs.getInt("id_interventios")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
}
