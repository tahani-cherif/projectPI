package comm.tourisme_sante.services;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.tourisme_sante.entities.Hotel;
import com.tourisme_sante.entities.Transport;
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
public class ServiceTransport implements services<Transport> {
               private Connection cnx = Datasource.getInstance().getCnx();
        
       public void ajouter(Transport p) {
        try {
            String req = "INSERT INTO transport(matricule, transportType,prix,idAgence) VALUES (?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getMatricule());
           pst.setString(2, p.getTransportType().toString());
            pst.setFloat(3, p.getPrix());
            pst.setInt(4, p.getIdAgence());



            pst.executeUpdate();
            System.out.println("Transport ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
       }
           public void modifier(Transport p) {
        try {
            String req = "UPDATE transport SET matricule=?,transportType=?,prix=?,idAgence=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(5, p.getId());
            pst.setString(1, p.getMatricule());
            pst.setString(2, p.getTransportType().toString());
             pst.setFloat(3, p.getPrix());
             pst.setInt(4, p.getIdAgence());


        

            pst.executeUpdate();
            System.out.println("Transport modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
             public void supprimer(Transport p) {
        try {
            String req = "DELETE from transport WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Transport supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
             
              public List<Transport> afficher() {
        List<Transport> list = new ArrayList<>();
        
               String req = "SELECT transport.id, agence.nom as nomAgence ,matricule, transport.transportType as transportType ,transport.prix as prix,idAgence FROM agence JOIN transport on agence.id=transport.idAgence;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Transport(rs.getInt("id"), rs.getString("matricule"), rs.getString("transportType"),rs.getFloat("prix"),rs.getInt("idAgence"),rs.getString("nomAgence")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
