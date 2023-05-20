package comm.tourisme_sante.services;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.tourisme_sante.entities.Agence;
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
public class ServiceAgence  implements services<Agence>  {
        private Connection cnx = Datasource.getInstance().getCnx();
        
       public void ajouter(Agence p) {
        try {
            String req = "INSERT INTO agence(nom, adresse,telephone,prix) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getAdresse());
            pst.setInt(3, p.getTelephone());
            pst.setDouble(4, p.getPrix());


            pst.executeUpdate();
            System.out.println("Agence ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
       }
           public void modifier(Agence p) {
        try {
            String req = "UPDATE agence SET nom=?, adresse=?,telephone=?,prix=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(5, p.getId());
            pst.setString(1, p.getNom());
            pst.setString(2, p.getAdresse());
            pst.setInt(3, p.getTelephone());
            pst.setDouble(4, p.getPrix());


            pst.executeUpdate();
            System.out.println("Agence modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           
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
                list.add(new Agence(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("telephone"), rs.getInt("prix")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
