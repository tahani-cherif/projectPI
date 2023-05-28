/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public  class serviceRDV implements services<RDV>{
     private Connection cnx = Datasource.getInstance().getCnx();

    @Override
    public void ajouter(RDV p) {
       try {
           String fullName = null;
           String nomUser = null;
            String req1 = "select fullName from medecins where id=?;";
            PreparedStatement pst1 = cnx.prepareStatement(req1);
             pst1.setInt(1, p.getIdmedecin());
            ResultSet rs = pst1.executeQuery();
              while(rs.next()) {
            fullName=rs.getString("fullName");}
             String req2 = "select nom,prenom from  utilisateur where id=?;";
            PreparedStatement pst2 = cnx.prepareStatement(req2);
             pst2.setInt(1, p.getIduser());
            ResultSet rs2 = pst2.executeQuery();
              while(rs2.next()) {
            nomUser=rs2.getString("nom")+" "+rs2.getString("prenom");}
         //   String req = "INSERT INTO RDV(idmedecin, iduser,dateRDV) VALUES (?,?,?);";
         String req = "INSERT INTO RDV(idmedecin, iduser,dateRDV,fullName,nomuser) VALUES (?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getIdmedecin());
            pst.setInt(2, p.getIduser());
            pst.setDate(3,p.getDateRDV());
            pst.setString(4,fullName);
            pst.setString(5, nomUser);
            pst.executeUpdate();
            System.out.println("RDV ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
    public void modifier(RDV p) {
        try {
            String req = "UPDATE RDV SET idmedecin=?, iduser=? , dateRDV=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getIdmedecin());
            pst.setInt(2, p.getIduser());
            pst.setDate(3,p.getDateRDV());
             pst.setInt(4, p.getId());
            pst.executeUpdate();
            System.out.println("RDV modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     @Override
    public void supprimer(RDV p) {
        try {
            String req = "DELETE from RDV WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,p.getId());
            pst.executeUpdate();
            System.out.println("RDV supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List afficher() {
 List<RDV> list = new ArrayList<>();
        
       String req = "SELECT RDV.id,fullName,idmedecin,iduser, nom,prenom,dateRDV FROM medecins JOIN RDV on medecins.id=rdv.idmedecin join utilisateur on rdv.iduser=utilisateur.id; ";
      try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
             list.add(new RDV(rs.getInt("id"),rs.getInt("idmedecin"),rs.getInt("iduser"),rs.getString("fullName"),rs.getString("nom")+" "+rs.getString("prenom"),rs.getDate("dateRDV")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }



}

